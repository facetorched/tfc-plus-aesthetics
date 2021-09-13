package com.facetorched.tfcaths.WorldGen.Generators;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.dunk.tfc.Core.TFC_Climate;
import com.dunk.tfc.WorldGen.TFCBiome;
import com.dunk.tfc.api.Enums.EnumRegion;
import com.facetorched.tfcaths.blocks.BlockPlant;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

public class AthsWorldGenPlants implements IWorldGenerator{
	
	public static Map<String, PlantSpawnData> plantList = new HashMap<String, PlantSpawnData>();
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
		// convert chunk coord too block coord
		int blockX = chunkX * 16;
		int blockZ = chunkZ * 16;
		int blockY = world.getTopSolidOrLiquidBlock(blockX, blockZ);
		
		float evt = TFC_Climate.getCacheManager(world).getEVTLayerAt(blockX, blockZ).floatdata1;
		float rain = TFC_Climate.getRainfall(world, blockX, blockY, blockZ);
		float bioTemp =TFC_Climate.getBioTemperatureHeight(world, blockX, blockY, blockZ);
		EnumRegion region = EnumRegion.values()[TFC_Climate.getRegionLayer(world, blockX, blockY, blockZ)];
		BiomeGenBase biome = world.getBiomeGenForCoords(blockX, blockZ);
		
		// iterate over all keys and find valid plants based on habitat conditions
		for(String key : plantList.keySet()) {
			PlantSpawnData data = plantList.get(key);
			Block plant = data.block;
			if(data.canGrowConditions(biome, region, bioTemp, rain, evt, blockY)) {
				// create an ArrayList of types of plants and add each entry a number of times determined by rarity (could be zero)
				int numClusters = binoRNG(random, 16 * 16, data.rarity); // number of clusters for a given plant
				for (int i = 0; i < numClusters; i++) {
					int x = blockX + random.nextInt(16);
					int z = blockZ + random.nextInt(16);
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
		int y = world.getTopSolidOrLiquidBlock(x, z);
		if(plant.canPlaceBlockAt(world, x, y, z) && ( !(plant instanceof BlockPlant) || ((BlockPlant)plant).shouldGenerateAt(world, x, y, z) )) {
			world.setBlock(x, y, z, plant, random.nextInt(data.metas.length), 3);
			//world.setBlock(x, y, z, plant);
		}
	}
	
	// simulate binomial distribution using reciprocal of p
	public static int binoRNG(Random random, int n, int recip_p) {
		  int x = 0;
		  for(int i = 0; i < n; i++) {
		    if(random.nextInt(recip_p - 1) == 0)
		      x++;
		  }
		  return x;
		}

}
