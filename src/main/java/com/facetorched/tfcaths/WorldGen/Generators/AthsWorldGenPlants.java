package com.facetorched.tfcaths.WorldGen.Generators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.dunk.tfc.Core.TFC_Climate;
import com.dunk.tfc.WorldGen.TFCBiome;
import com.dunk.tfc.WorldGen.Generators.WorldGenForests;
import com.dunk.tfc.api.Enums.EnumRegion;
import com.facetorched.tfcaths.blocks.BlockPlant;
import com.facetorched.tfcaths.blocks.BlockPlantTree;
import com.facetorched.tfcaths.enums.EnumVary;
import com.facetorched.tfcaths.util.AthsMath;
import com.facetorched.tfcaths.util.BitMap;
import com.facetorched.tfcaths.util.Point3D;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class AthsWorldGenPlants implements IWorldGenerator{
	
	public static Map<String, PlantSpawnData> plantList = new HashMap<String, PlantSpawnData>();
	
	public static WorldGenForests genForests = new WorldGenForests();
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
		// convert chunk coord too block coord at about the center of generation region
		int cornerX = chunkX * 16;
		int cornerZ = chunkZ * 16;
		
		int centerX = cornerX + 15;
		int centerZ = cornerZ + 15;
		
		int centerY = world.getTopSolidOrLiquidBlock(centerX, centerZ);
		
		float evt = TFC_Climate.getCacheManager(world).getEVTLayerAt(centerX, centerZ).floatdata1;
		float rain = TFC_Climate.getRainfall(world, centerX, centerY, centerZ);
		float bioTemp =TFC_Climate.getBioTemperatureHeight(world, centerX, centerY, centerZ);
		EnumRegion region = EnumRegion.values()[TFC_Climate.getRegionLayer(world, centerX, centerY, centerZ)];
		BiomeGenBase biome = world.getBiomeGenForCoords(centerX, centerZ);
		
		// iterate over all keys and find valid plants based on habitat conditions then plant them
		// in theory the order matters since it will skew bias to the earliest plants in the set
		// if effects are too noticeable, switch to a shuffled ArrayList
		BitMap bmp = new BitMap(cornerX, cornerZ, 32, 32); // TODO should this be expanded? (and risk chunk overflow)
		boolean firstPass = true;
		
		ArrayList<String> keys = new ArrayList<String>(plantList.keySet());
		Collections.shuffle(keys, random); // shuffle so that on average no given plant has a higher priority
		
		for(String key : keys) {
			PlantSpawnData data = plantList.get(key);
			Block plant = data.block;
			if(data.size > 0 && data.canGrowConditions(biome, region, bioTemp, rain, evt, centerY)) {
				int rarity = data.rarity;
				rarity *= Math.pow(getEnvironmentRarityScaling(world, centerX, centerY, centerZ, rain, evt), data.forestGen); //ignores this effect if forestGen is 0
				int numClusters = AthsMath.binoRNG(random, 16 * 16, rarity); // number of clusters for a given plant. Is this slow??
				for (int i = 0; i < numClusters; i++) {
					int x = cornerX + random.nextInt(16) + 8;
					int z = cornerZ + random.nextInt(16) + 8;
					// try to place plant at center of cluster. if it fails, skip the cluster
					// this isn't ideal but it leads to better looking generation and better performance
					if (placePlant(random, plant, data, world, x, z)) {
						if(firstPass) // avoid zeroing twice (bitmap is initialized as zeros)
							firstPass = false;
						else
							bmp.zero();
						bmp.set(x, z);
						expandClusterOrganic(random, plant, data, world, x, z, bmp); 
					}
				}
			}
		}
	}
	
	public void expandClusterOrganic(Random random, Block plant, PlantSpawnData data, World world, int x, int z, BitMap bmp) {
		int numPlants = AthsMath.binoRNG(random, data.size - 1, 2) + 1; // matlab moment (dies from cringe)
		if(numPlants < 2)
			return;
		int plantCount = 1; // we have already placed 1 plant
		ArrayList<Point3D> pointList= new ArrayList<Point3D>();
		int y = getTopSolidOrLiquidBlock(world, x, z);
		Point3D origin = new Point3D(x, y, z);
		pointList.add(origin); // add the origin plant
		int n; // how many plants back should be considered as possible seeds for cluster growth
		
		final float f = 2.0f; // determines organic versus circular shape. Larger value = more circular
		
		while(plantCount < numPlants) {
			n = (int) (Math.sqrt(pointList.size()) * f); 
			n = Math.min(n, pointList.size()); // avoid index out of bounds
			
			ArrayList<Integer> recentIndexes = new ArrayList<Integer>(n);
			for(int i = pointList.size() - n; i < pointList.size(); i++){
			    recentIndexes.add(i);
			}
			
			boolean hasPlaced = false;
			
			while (recentIndexes.size() > 0 && !hasPlaced){
				int seedIndex = recentIndexes.remove(random.nextInt(recentIndexes.size()));
				Point3D seed = pointList.get(seedIndex);
				Point3D newPoint3D = getNearestTo(getNeighbors(seed, world), origin, plant, data, world, random, bmp);
				
				if(newPoint3D != null) {
					if(random.nextInt(data.dispersion) == 0) {
						placePlant(random, plant, data, world, newPoint3D.x, newPoint3D.y, newPoint3D.z);
						plantCount ++;
						if(plantCount >= numPlants) {
							return;
						}
					}
					pointList.add(newPoint3D);
					bmp.set(newPoint3D.x, newPoint3D.z);
					hasPlaced = true;
				}
			}
			if(!hasPlaced) {
				//System.out.println("failure");
				return;
			}
		}
	}
	
	/**
	 *  place a plant and randomize it's meta
	 */
	public boolean placePlant(Random random, Block plant, PlantSpawnData data, World world, int x, int z) {
		int y = getTopSolidOrLiquidBlock(world, x, z);
		return placePlant(random, plant, data, world, x, y, z);
	}
	
	/**
	 *  place a plant and randomize it's meta
	 */
	public boolean placePlant(Random random, Block plant, PlantSpawnData data, World world, int x, int y, int z) {
		if(canPlacePlantAt(plant, data, world, x, y, z)) {
			int meta = data.metas[random.nextInt(data.metas.length)];
			world.setBlock(x, y, z, plant, meta, 2);
			// if it's below freezing during world generation, add snow!
			if(plant instanceof BlockPlant) {
				if(((BlockPlant)plant).hasVary(EnumVary.SNOW, meta)) {
					if(TFC_Climate.getHeightAdjustedTemp(world, x, y, z) <= 0) {
						((BlockPlant)plant).shiftToVary(world, x, y, z, world.getBlockMetadata(x, y, z), EnumVary.SNOW);
					}
				}
				plant.updateTick(world, x, y, z, random);
			}
			return true;
		}
		return false;
	}
	
	public boolean canPlacePlantAt(Block plant, PlantSpawnData data, World world, int x, int y, int z) {
		if(plant.canPlaceBlockAt(world, x, y, z)){
			if (plant instanceof BlockPlant) {
				return ((BlockPlant)plant).shouldGenerateAt(world, x, y, z);
			}
			else
				return true;
		}
		return false;
	}
	
	public static float getEnvironmentRarityScaling(World world, int x, int y, int z, float rainfall, float evt) {
		if (genForests.getNearWater(world, x, y, z)){
			rainfall = Math.max(rainfall * 2, 400);
			// evt /= 2;
		}
		if (world.getBiomeGenForCoords(x, z) == TFCBiome.SWAMPLAND || world.getBiomeGenForCoords(x, z) == TFCBiome.SALTSWAMP ){
			rainfall *= 1.75f;
		}
		float numTreesCalc = (float)(30f * (rainfall / 2000f) * Math.sqrt((Math.min(Math.max(0.1f, evt), 1f))));
		numTreesCalc = (numTreesCalc * (Math.min(2000f, rainfall) / 2000f));
		
		if (numTreesCalc <= 0.0001f) // avoid divide by 0
			return 10000f; // big number I guess
		if (numTreesCalc >= 5)
			return 0.2f;
		return 1.0f / numTreesCalc;
	}
	
	/**
     * Finds the highest block on the x, z coordinate that is solid and returns its y coord. Args x, z
     */
    public int getTopSolidOrLiquidBlock(World world, int x, int z)
    {
        Chunk chunk = world.getChunkFromBlockCoords(x, z);
        int k = chunk.getTopFilledSegment() + 15;
        x &= 15;

        for (z &= 15; k > 0; --k)
        {
            Block block = chunk.getBlock(x, k, z);

            if ((block.getMaterial().blocksMovement() || block.getMaterial() == Material.water) 
            		&& block.getMaterial() != Material.leaves && !block.isFoliage(world, x, k, z))
            {
                return k + 1;
            }
        }

        return -1;
    }
    
    private Point3D[] getNeighbors(Point3D point, World world) {
    	Point3D[] neighbors = new Point3D[4];
    	neighbors[0] = getPointFromXZ(point.x + 1, point.z, world);
    	neighbors[1] = getPointFromXZ(point.x - 1, point.z, world);
    	neighbors[2] = getPointFromXZ(point.x, point.z + 1, world);
    	neighbors[3] = getPointFromXZ(point.x, point.z - 1, world);
    	return neighbors;
    }
    
    public Point3D getPointFromXZ(int x, int z, World world) {
    	int y = getTopSolidOrLiquidBlock(world, x, z);
    	return new Point3D(x, y, z);
    }
    
    public Point3D getNearestTo(Point3D[] candidates, Point3D origin, Block plant, PlantSpawnData data, World world, Random random, BitMap bmp) {
    	int size = 0;
    	for(int i = 0; i < candidates.length; i++) {
    		boolean isTaken = false;
    		if(bmp != null) { 
    			isTaken = bmp.get(candidates[i].x, candidates[i].z); // has this block already been considered?
    		}
    		if(!isTaken && canPlacePlantAt(plant, data, world, candidates[i].x, candidates[i].y, candidates[i].z)) {
	    		if(size == 0) {
	    			candidates[0] = candidates[i];
	    			size = 1;
	    		}
	    		else {
	    			int newDist = candidates[i].getDistSq(origin);
	    			int oldDist = candidates[0].getDistSq(origin);
	    		
		    		if(newDist < oldDist) {
		    			candidates[0] = candidates[i];
		    			size = 1;
		    		}
		    		else if (newDist == oldDist) {
		    			candidates[size] = candidates[i];
		    			size ++;
		    		}
		    	}
	    	}
    	}
    	if (size < 1) { // no valid placement was found
    		return null;
    	}
    	return candidates[random.nextInt(size)];
    }
}
