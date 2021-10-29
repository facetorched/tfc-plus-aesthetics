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
		int blockX = chunkX * 16 + 15;
		int blockZ = chunkZ * 16 + 15;
		
		int blockY = world.getTopSolidOrLiquidBlock(blockX, blockZ);
		
		float evt = TFC_Climate.getCacheManager(world).getEVTLayerAt(blockX, blockZ).floatdata1;
		float rain = TFC_Climate.getRainfall(world, blockX, blockY, blockZ);
		float bioTemp =TFC_Climate.getBioTemperatureHeight(world, blockX, blockY, blockZ);
		EnumRegion region = EnumRegion.values()[TFC_Climate.getRegionLayer(world, blockX, blockY, blockZ)];
		BiomeGenBase biome = world.getBiomeGenForCoords(blockX, blockZ);
		
		// generation region should be centered on 4 chunks
		blockX -= 7;
		blockZ -= 7;
		
		// iterate over all keys and find valid plants based on habitat conditions then plant them
		// in theory the order matters since it will skew bias to the earliest plants in the set
		// if effects are too noticeable, switch to a shuffled ArrayList
		for(String key : plantList.keySet()) {
			PlantSpawnData data = plantList.get(key);
			Block plant = data.block;
			if(data.canGrowConditions(biome, region, bioTemp, rain, evt, blockY)) {
				int rarity = data.rarity;
				rarity *= Math.pow(getEnvironmentRarityScaling(world, blockX, blockY, blockZ, rain, evt), data.forestGen); //ignores this effect if forestGen is 0
				int numClusters = binoRNG(random, 16 * 16, rarity); // number of clusters for a given plant. Is this slow??
				for (int i = 0; i < numClusters; i++) {
					int x = blockX + random.nextInt(16);
					int z = blockZ + random.nextInt(16);
					// try to place plant at center of cluster. if it fails, skip the cluster
					// this isn't ideal but it leads to better looking generation and better performance
					if (placePlant(random, plant, data, world, x, z)) {
						if(data.dispersion == 1) 
							//eventually I might make organic generation work with other dispersions but it's diminishing returns for loss of performance
							expandClusterOrganic(random, plant, data, world, x, z); 
						else
							expandClusterSquare(random, plant, data, world, x, z);
					
					}
				}
			}
		}
	}
	
	public void expandClusterSquare(Random random, Block plant, PlantSpawnData data, World world, int x, int z) {
		int numPlants = binoRNG(random, data.size - 1, 2) + 1; // matlab moment (dies from cringe)
		int plantCount = 1; // we have already placed 1 plant
		int size = 3;  // side length of square around the cluster
		while(plantCount < numPlants) {
			int r = 0;
			while (r < size) {
				int c = 0;
				while (c < size) {
					if(random.nextInt(data.dispersion) == 0) {
						placePlant(random, plant, data, world, x + c - size/2, z + r - size/2);
						plantCount ++;
						if(plantCount >= numPlants) {
							return;
						}
					}
					if(r == 0 || r == size - 1)
						c++;
					else
						c += size - 1; // skip interior blocks of square region
				}
				r++;
			}
			size++; // expand the radius of placement
		}
	}
	
	public void expandClusterOrganic(Random random, Block plant, PlantSpawnData data, World world, int x, int z) {
		int numPlants = binoRNG(random, data.size - 1, 2) + 1; // matlab moment (dies from cringe)
		if(numPlants < 2)
			return;
		int plantCount = 1; // we have already placed 1 plant
		ArrayList<Point3D> pointList= new ArrayList<Point3D>();
		int y = getTopSolidOrLiquidBlock(world, x, z);
		Point3D origin = new Point3D(x, y, z);
		pointList.add(origin); // add the origin plant
		int n; // how many plants back should be considered as possible seeds for cluster growth
		
		
		while(plantCount < numPlants) {
			n = (int) (Math.sqrt(plantCount) * 2.0); // the hard coded value determines organic versus circular shape. Larger value = more circular
			n = Math.min(n, plantCount); // avoid index out of bounds
			
			ArrayList<Integer> recentIndexes = new ArrayList<Integer>(n);
			for(int i = plantCount - n; i < plantCount; i++){
			    recentIndexes.add(i);
			}
			
			boolean hasPlaced = false;
			
			while (recentIndexes.size() > 0 && !hasPlaced){
				int seedIndex = recentIndexes.remove(random.nextInt(recentIndexes.size()));
				Point3D seed = pointList.get(seedIndex);
				Point3D newPoint3D = getNearestTo(getNeighbors(seed, world), origin, plant, data, world, random);
				
				if(newPoint3D != null ) { //&& random.nextInt(data.dispersion) == 0) {
					placePlant(random, plant, data, world, newPoint3D.x, newPoint3D.y, newPoint3D.z);
					pointList.add(newPoint3D);
					plantCount ++;
					if(plantCount >= numPlants) {
						return;
					}
					hasPlaced = true;
				}
			}
			if(!hasPlaced) {
				System.out.println("failure");
				return;
			}
		}
	}
	
	// place a plant and randomize it's meta
	public boolean placePlant(Random random, Block plant, PlantSpawnData data, World world, int x, int z) {
		int y = getTopSolidOrLiquidBlock(world, x, z);
		return placePlant(random, plant, data, world, x, y, z);
	}
	
	// place a plant and randomize it's meta
		public boolean placePlant(Random random, Block plant, PlantSpawnData data, World world, int x, int y, int z) {
			if(canPlacePlantAt(plant, data, world, x, y, z)) {
				world.setBlock(x, y, z, plant, data.metas[random.nextInt(data.metas.length)], 2);
				// if it's below freezing during world generation, add snow!
				if(plant instanceof BlockPlant) {
					if(((BlockPlant)plant).hasVary(EnumVary.SNOW)) {
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
	
	// simulate binomial distribution using reciprocal of p
	public static int binoRNG(Random random, int n, int recip_p) {
		int x = 0;
		for(int i = 0; i < n; i++) {
			if(random.nextInt(recip_p) == 0)
				x++;
		}
		return x;
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
		
		if (numTreesCalc <= 0.0001f)
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

            if ((block.getMaterial().blocksMovement() || block.getMaterial() == Material.water) && block.getMaterial() != Material.leaves && !block.isFoliage(world, x, k, z))
            {
                return k + 1;
            }
        }

        return -1;
    }
    
    public Point3D[] getNeighbors(Point3D point, World world) {
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
    
    public Point3D getNearestTo(Point3D[] candidates, Point3D origin, Block plant, PlantSpawnData data, World world, Random random) {
    	int size = 0;
    	for(int i = 0; i < candidates.length; i++) {
    		if(canPlacePlantAt(plant, data, world, candidates[i].x, candidates[i].y, candidates[i].z)) {
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
