package com.facetorched.tfcaths.WorldGen.Generators;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import com.dunk.tfc.WorldGen.TFCBiome;
import com.dunk.tfc.api.Enums.EnumRegion;
import com.dunk.tfc.api.Enums.EnumTree;
import com.facetorched.tfcaths.AthsGlobal;
import com.facetorched.tfcaths.util.AthsLogger;
import com.facetorched.tfcaths.util.AthsParser;
import com.facetorched.tfcaths.util.BlockMetaPair;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.oredict.OreDictionary;

public class PlantSpawnData {
	public Block block;
	public int[] metas;
	public ArrayList<Block> canGrowOn = new ArrayList<Block>();
	public ArrayList<String> canGrowOnOreDict = new ArrayList<String>();
	public ArrayList<BlockMetaPair> canGrowOnBlockMeta = new ArrayList<BlockMetaPair>();
	public ArrayList<TFCBiome> biomes = new ArrayList<TFCBiome>();
	public ArrayList<EnumRegion> region = new ArrayList<EnumRegion>();
	public int size, dispersion, rarity, minAltitude, maxAltitude;
	public float minTemp, maxTemp, minRainfall, maxRainfall, minEVT, maxEVT, forestGen;

	public PlantSpawnData(String blockName, int[] metas, String[] canGrowOn, String[] biomes, String[] region, int size, int dispersion, int rarity, int minAltitude, int maxAltitude,
			float minTemp, float maxTemp, float minRainfall, float maxRainfall, float minEVT, float maxEVT, float forestGen){
		block = AthsParser.getBlockFromName(blockName);
		this.metas = metas;
		for(int i = 0; i < canGrowOn.length; i++) {
			Block b = Block.getBlockFromName(canGrowOn[i]);
			EnumTree et = AthsParser.getTreeFromName(canGrowOn[i]);
			if (canGrowOn[i].toLowerCase().equals("alltrees")) {
				canGrowOnBlockMeta.addAll(AthsGlobal.ALL_TREE_TRUNKS);
			}
			else if(b != null) {
				this.canGrowOn.add(b);
			}
			else if (et != null) {
				canGrowOnBlockMeta.addAll(AthsParser.getTrunkBlocks(et.woodMeta));
			}
			else if(canGrowOn[i].startsWith("ore:")) {
				this.canGrowOnOreDict.add(canGrowOn[i].substring(4));
			}
			else {
				AthsLogger.error("no block, tree or ore exists with name " + canGrowOn[i]);
			}
		}
		
		for(String biome : biomes) {
			if(biome.toLowerCase().equals("all"))
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
		
		for(int n = 0; n < region.length; n++) {
			for(int r = 0; r < AthsGlobal.ALLOWED_REGIONS.length; r++) {
				if(region[n].equals(AthsGlobal.ALLOWED_REGIONS[r]))
					this.region.add(EnumRegion.values()[r]);
			}
		}

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
	
	public boolean canGrowConditions(BiomeGenBase biome, EnumRegion region, float bioTemp, float rain, float evt) {
		return this.metas.length > 0 &&
				this.biomes.contains(biome) && this.region.contains(region) &&
				bioTemp >= minTemp && bioTemp <= maxTemp && 
				rain >= minRainfall && rain <= maxRainfall && 
				evt >= minEVT && evt <= maxEVT;
	}
	
	public boolean canGrowAltitude(int blockY) {
		return blockY >= minAltitude && blockY <= maxAltitude;
	}
	
	public boolean canGrowOnBlock(Block block, int meta) {
		if(canGrowOn.contains(block)) {
			return true;
		}
		if(!canGrowOnOreDict.isEmpty()) {
			int[] ids = OreDictionary.getOreIDs(new ItemStack(Item.getItemFromBlock(block), 1, meta));
			for(int id : ids)
				if(canGrowOnOreDict.contains(OreDictionary.getOreName(id)))
					return true;
		}
		if(!canGrowOnBlockMeta.isEmpty()){
			BlockMetaPair bm = new BlockMetaPair(block, meta);
			if(canGrowOnBlockMeta.contains(bm)) {
				return true;
			}
		}
		return false;
	}
}
