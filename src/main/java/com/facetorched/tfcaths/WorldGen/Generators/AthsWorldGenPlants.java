package com.facetorched.tfcaths.WorldGen.Generators;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.dunk.tfc.Core.TFC_Climate;
import com.dunk.tfc.WorldGen.TFCBiome;
import com.dunk.tfc.WorldGen.Generators.WorldGenForests;
import com.dunk.tfc.api.Enums.EnumRegion;
import com.facetorched.tfcaths.blocks.BlockPlant;
import com.facetorched.tfcaths.blocks.BlockPlantTree;

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
		
		// convert chunk coord too block coord at center of spawn region
		
		int blockX = chunkX * 16 + 15;
		int blockZ = chunkZ * 16 + 15;
		
		int blockY = world.getTopSolidOrLiquidBlock(blockX, blockZ);
		
		float evt = TFC_Climate.getCacheManager(world).getEVTLayerAt(blockX, blockZ).floatdata1;
		float rain = TFC_Climate.getRainfall(world, blockX, blockY, blockZ);
		float bioTemp =TFC_Climate.getBioTemperatureHeight(world, blockX, blockY, blockZ);
		EnumRegion region = EnumRegion.values()[TFC_Climate.getRegionLayer(world, blockX, blockY, blockZ)];
		BiomeGenBase biome = world.getBiomeGenForCoords(blockX, blockZ);
		
		blockX -= 7;
		blockZ -= 7;
		
		// iterate over all keys and find valid plants based on habitat conditions
		for(String key : plantList.keySet()) {
			PlantSpawnData data = plantList.get(key);
			Block plant = data.block;
			if(data.canGrowConditions(biome, region, bioTemp, rain, evt, blockY)) {
				// create an ArrayList of types of plants and add each entry a number of times determined by rarity (could be zero)
				int rarity = data.rarity;
				rarity *= Math.pow(getEnvironmentRarityScaling(world, blockX, blockY, blockZ, rain, evt), data.forestGen);
				rarity = Math.max(rarity, 1); // a rarity of 1 is saturated
				int numClusters = binoRNG(random, 16 * 16, rarity); // number of clusters for a given plant
				for (int i = 0; i < numClusters; i++) {
					int x = blockX + random.nextInt(16) + 8;
					int z = blockZ + random.nextInt(16) + 8;
					placePlant(random, plant, data, world, x, z); // plant at center of cluster
					
					expandCluster(random, plant, data, world, x, z);
				}
			}
		}
	}
	
	public void expandCluster(Random random, Block plant, PlantSpawnData data, World world, int x, int z) {
		int numPlants = binoRNG(random, data.size - 1, 2) + 1; // matlab moment (dies from cringe)
		int plantCount = 1; // we have already placed 1 plant
		int size = 3;  // side length of square around the cluster
		while(plantCount < numPlants) {
			int r = 0;
			while (r < size) {
				int c = 0;
				while (c < size) {
					if(random.nextInt(data.dispersion - 1) == 0) {
						placePlant(random, plant, data, world, x + c - size/2, z + r - size/2);
						plantCount ++;
						if(plantCount < numPlants) {
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
	
	// place a plant and randomize it's meta
	public void placePlant(Random random, Block plant, PlantSpawnData data, World world, int x, int z) {
		int y = getTopSolidOrLiquidBlock(world, x, z);
		if(plant.canPlaceBlockAt(world, x, y, z) && ( !(plant instanceof BlockPlant) || ((BlockPlant)plant).shouldGenerateAt(world, x, y, z) )) {
			world.setBlock(x, y, z, plant, random.nextInt(data.metas.length), 2);
			//world.setBlock(x, y, z, plant);
		}
	}
	
	// simulate binomial distribution using reciprocal of p
	public static int binoRNG(Random random, int n, int recip_p) {
		int x = 0;
		for(int i = 0; i < n; i++) {
			if(recip_p <= 0)
				x++;
			else if(random.nextInt(recip_p - 1) == 0)
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
}
