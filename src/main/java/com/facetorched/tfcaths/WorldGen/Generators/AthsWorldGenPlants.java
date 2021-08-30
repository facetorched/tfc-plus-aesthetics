package com.facetorched.tfcaths.WorldGen.Generators;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class AthsWorldGenPlants implements IWorldGenerator{
	
	public static Map<String, PlantSpawnData> plantList = new HashMap<String, PlantSpawnData>();
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		// TODO Auto-generated method stub
		
	}

}
