package com.facetorched.tfcaths.WorldGen.Generators;
import java.util.ArrayList;

import com.dunk.tfc.WorldGen.TFCBiome;
import com.dunk.tfc.api.Enums.EnumRegion;
import com.facetorched.tfcaths.util.AthsLogger;
import com.facetorched.tfcaths.util.AthsParser;

import net.minecraft.block.Block;

public class PlantSpawnData {
	public Block block;
	public int[] metas;
	public ArrayList<Block> canGrowOn = new ArrayList<Block>();
	public ArrayList<String> canGrowOnOreDict = new ArrayList<String>();
	public TFCBiome[] biomes;
	public EnumRegion[] region;
	public int rarity;
	public float minTemp, maxTemp, minRainfall, maxRainfall, minEVT, maxEVT;

	public PlantSpawnData(String blockName, int[] metas, String[] canGrowOn, String[] biomes, EnumRegion[] region, int rarity,
			float minTemp, float maxTemp, float minRainfall, float maxRainfall, float minEVT, float maxEVT){
		block = AthsParser.getBlockFromName(blockName);
		this.metas = metas;
		for(int i = 0; i < canGrowOn.length; i++) {
			Block b = Block.getBlockFromName(canGrowOn[i]);
			if(b != null)
				this.canGrowOn.add(b);
			else
				this.canGrowOnOreDict.add(canGrowOn[i]);
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
}
