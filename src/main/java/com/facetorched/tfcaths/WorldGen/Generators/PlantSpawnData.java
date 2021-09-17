package com.facetorched.tfcaths.WorldGen.Generators;
import java.util.ArrayList;
import java.util.Arrays;

import com.dunk.tfc.Core.TFC_Climate;
import com.dunk.tfc.WorldGen.TFCBiome;
import com.dunk.tfc.api.Enums.EnumRegion;
import com.facetorched.tfcaths.util.AthsLogger;
import com.facetorched.tfcaths.util.AthsParser;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class PlantSpawnData {
	public Block block;
	public int[] metas;
	public ArrayList<Block> canGrowOn = new ArrayList<Block>();
	public ArrayList<String> canGrowOnOreDict = new ArrayList<String>();
	public ArrayList<TFCBiome> biomes = new ArrayList<TFCBiome>();
	public ArrayList<EnumRegion> region;
	public int size, dispersion, rarity, minAltitude, maxAltitude;
	public float minTemp, maxTemp, minRainfall, maxRainfall, minEVT, maxEVT, forestGen;

	public PlantSpawnData(String blockName, int[] metas, String[] canGrowOn, String[] biomes, EnumRegion[] region, int size, int dispersion, int rarity, int minAltitude, int maxAltitude,
			float minTemp, float maxTemp, float minRainfall, float maxRainfall, float minEVT, float maxEVT, float forestGen){
		block = AthsParser.getBlockFromName(blockName);
		this.metas = metas;
		for(int i = 0; i < canGrowOn.length; i++) {
			Block b = Block.getBlockFromName(canGrowOn[i]);
			if(b != null)
				this.canGrowOn.add(b);
			else
				this.canGrowOnOreDict.add(canGrowOn[i]);
		}
		
		for(int i = 0; i < biomes.length; i++) {
			this.biomes.add(TFCBiome.getBiomeByName(biomes[i]));
		}
		this.region = new ArrayList<EnumRegion>(Arrays.asList(region));
		this.size = size;
		this.dispersion = dispersion;
		this.rarity = rarity;
		this.minAltitude = minAltitude;
		this.maxAltitude = maxAltitude;
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
		this.minRainfall = minRainfall;
		this.maxRainfall = maxRainfall;
		this.minEVT = minEVT;
		this.maxEVT = maxEVT;
		this.forestGen = forestGen;
	}
	
	public boolean canGrowConditions(BiomeGenBase biome, EnumRegion region, float bioTemp, float rain, float evt, int blockY)
	{
		/*System.out.println(this.biomes.contains(biome) && this.region.contains(region) &&
				bioTemp >= minTemp && bioTemp <= maxTemp && 
				rain >= minRainfall && rain <= maxRainfall && 
				evt >= minEVT && evt <= maxEVT &&
				blockY >= minAltitude && blockY <= maxAltitude);
				*/
		return this.biomes.contains(biome) && this.region.contains(region) &&
				bioTemp >= minTemp && bioTemp <= maxTemp && 
				rain >= minRainfall && rain <= maxRainfall && 
				evt >= minEVT && evt <= maxEVT &&
				blockY >= minAltitude && blockY <= maxAltitude;
	}
}
