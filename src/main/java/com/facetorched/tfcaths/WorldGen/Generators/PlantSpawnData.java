package com.facetorched.tfcaths.WorldGen.Generators;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import com.dunk.tfc.Core.TFC_Climate;
import com.dunk.tfc.WorldGen.TFCBiome;
import com.dunk.tfc.api.Enums.EnumRegion;
import com.facetorched.tfcaths.AthsGlobal;
import com.facetorched.tfcaths.util.AthsLogger;
import com.facetorched.tfcaths.util.AthsParser;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.oredict.OreDictionary;

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
		
		for(String biome : biomes) {
			if(biome.equals("All"))
				for(String allBiome : AthsGlobal.ALL_BIOMES)
					this.biomes.add(TFCBiome.getBiomeByName(allBiome)); // this is idiotic but the normal biome list contains nulls
			else if(!biome.startsWith("!"))
				this.biomes.add(TFCBiome.getBiomeByName(biome));
		}
		for(String biome : biomes) {
			if(biome.startsWith("!")) {
				if(!this.biomes.remove(TFCBiome.getBiomeByName(biome.substring(1))))
					throw new NoSuchElementException();
			}
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
		return this.metas.length > 0 &&
				this.biomes.contains(biome) && this.region.contains(region) &&
				bioTemp >= minTemp && bioTemp <= maxTemp && 
				rain >= minRainfall && rain <= maxRainfall && 
				evt >= minEVT && evt <= maxEVT &&
				blockY >= minAltitude && blockY <= maxAltitude;
	}
	
	public boolean canGrowOnBlock(Block block) {
		if(canGrowOn.contains(block)) {
			return true;
		}
		int[] ids = OreDictionary.getOreIDs(new ItemStack(Item.getItemFromBlock(block), 1, 0)); //forced to do damage 0
		for(int id : ids)
			if(canGrowOnOreDict.contains(OreDictionary.getOreName(id)))
				return true;
		return false;
	}
}
