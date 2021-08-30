package com.facetorched.tfcaths.WorldGen.Generators;
import com.dunk.tfc.WorldGen.TFCBiome;
import com.dunk.tfc.api.Enums.EnumRegion;
import com.facetorched.tfcaths.util.Logger;

import net.minecraft.block.Block;

public class PlantSpawnData {
	public Block block;
	public int[] metas;
	public Block[] canGrowOn;
	public TFCBiome[] biomes;
	public EnumRegion[] region;
	public int rarity;
	public float minTemp, maxTemp, minRainfall, maxRainfall, minEVT, maxEVT;

	public PlantSpawnData(String blockName, int[] metas, String[] canGrowOn, String[] biomes, EnumRegion[] region, int rarity,
			float minTemp, float maxTemp, float minRainfall, float maxRainfall, float minEVT, float maxEVT){
		block = getBlockFromName(blockName);
		this.metas = metas;
		this.canGrowOn = new Block[canGrowOn.length];
		for(int i = 0; i < canGrowOn.length; i++) {
			this.canGrowOn[i] = getBlockFromName(canGrowOn[i]);
		}
		this.biomes = new TFCBiome[biomes.length];
		for(int i = 0; i < biomes.length; i++) {
			this.biomes[i] = TFCBiome.getBiomeByName(biomes[i]);
		}
		this.region = region;
		this.rarity = rarity;
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
		this.minRainfall = minRainfall;
		this.maxRainfall = maxRainfall;
		this.minEVT = minEVT;
		this.maxEVT = maxEVT;
	}
	
	public static Block getBlockFromName(String blockName) {
		Block ret = Block.getBlockFromName(blockName);
		
		if (ret == null){
			Logger.error("config unable to get block from block name " + blockName);
			throw new java.lang.NullPointerException("invalid block name " + blockName);
		}
		return ret;
	}

}
