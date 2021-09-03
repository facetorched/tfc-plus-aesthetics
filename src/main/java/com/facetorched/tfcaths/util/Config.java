package com.facetorched.tfcaths.util;

import java.io.File;

import com.facetorched.tfcaths.WorldGen.Generators.PlantSpawnData;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;

import com.dunk.tfc.Reference;
import com.dunk.tfc.api.Enums.EnumRegion;
import com.facetorched.tfcaths.AthsMod;
import com.facetorched.tfcaths.WorldGen.Generators.AthsWorldGenPlants;

public class Config {
	//configuration object
	public static Configuration config;
	
	//define config fields here
	public static String[] soilBlocks;
	
	public static void preInit(File configDir)
	{
		if (config != null) throw new IllegalStateException("Preinit can't be called twice.");
		config = new Configuration(new File(configDir,"TFC+ Aesthetics.cfg"));
	}
	public static void reload()
	{
		if (config == null) throw new IllegalStateException("Config reload attempt before preinit.");
		AthsLogger.info("Loading TFC+ Aesthetics Config");
		config.load();
		// set configs here
		String[] soilBlocks = new String[]{
				Reference.MOD_ID+":Dirt",Reference.MOD_ID+":Dirt2",Reference.MOD_ID+":Grass",Reference.MOD_ID+":Grass2",
				Reference.MOD_ID+":DryGrass",Reference.MOD_ID+":DryGrass2",Reference.MOD_ID+":Clay",Reference.MOD_ID+":Clay2",
				Reference.MOD_ID+":ClayGrass",Reference.MOD_ID+":ClayGrass2",Reference.MOD_ID+":tilledSoil",Reference.MOD_ID+":tilledSoil2",
				Reference.MOD_ID+":Peat"};
		soilBlocks = config.get("_soil ore dict", "blockSoil", soilBlocks, "blocks to add to the ore dictionary 'blockSoil'. Leave empty to disable").getStringList();
		
		for(String soil : soilBlocks) {
			OreDictionary.registerOre("blockSoil", AthsParser.getBlockFromName(soil));
		}
		
		if (config.hasChanged()) config.save();
	}
	
	//this must be run in the init phase (after blocks setup but before world gen)
	public static void reloadPlants() {
		
		AthsWorldGenPlants.plantList.put("sageBrush", getPlantData("sage brush", AthsMod.MODID+":sageBrush", new int[] {0,1,2}, new String[] {"blockSoil", "blockSand"}, new String[]{"Rolling Hills"}, new String[]{"Americas"}, 20, 4f, 20f, 100f, 1000f, 0f, 2f));
		if (config.hasChanged()) 
			config.save();
	}
	
	//based on ore config from tfc
	private static final String[] ALLOWED_BIOMES = new String[] {"Ocean","River","Hell","Beach","Gravel Beach","High Hills",
			"Plains","Swamp","High Hills Edge","Rolling Hills","Mountains","High Plains","Deep Ocean","Lake","Mountain Range",
			"Mountain Range Edge","Foothills","Shore","Salt Swamp","Lakeshore","Riverbank","River Source","Estuary"};
	
	private static final String[] ALLOWED_REGIONS = new String[] {"Americas","Europe","Africa","Asia"};
	
	private static EnumRegion[] getRegionEnumFromName(String[] names) {
		EnumRegion[] ret = new EnumRegion[names.length];
		for(int n = 0; n < names.length; n++) {
			for(int r = 0; r < ALLOWED_REGIONS.length; r++) {
				if(names[n].equals(ALLOWED_REGIONS[r]))
					ret[n] = EnumRegion.values()[r];
			}
		}
		return ret;
	}
	
	private static PlantSpawnData getPlantData(String category, String blockName, int[] metas, String[] growOnBlocks, String[] biomes, String[] regions, 
			int rarity, float minTemp, float maxTemp, float minRainfall, float maxRainfall, float minEVT, float maxEVT)
	{
		return new PlantSpawnData(
				config.get(category, "blockName", blockName).getString(),
				config.get(category, "metas", metas).getIntList(),
				config.get(category, "growOnBlocks", growOnBlocks).getStringList(),
				config.get(category, "biomes", biomes).setValidValues(ALLOWED_BIOMES).getStringList(),
				getRegionEnumFromName(config.get(category, "regions", regions).getStringList()),
				config.get(category, "rarity", rarity).getInt(),
				(float)config.get(category, "minTemp", minTemp).getDouble(),
				(float)config.get(category, "maxTemp", maxTemp).getDouble(),
				(float)config.get(category, "minRainfall", minRainfall).getDouble(),
				(float)config.get(category, "maxRainfall", maxRainfall).getDouble(),
				(float)config.get(category, "minEVT", minEVT).getDouble(),
				(float)config.get(category, "maxEVT", maxEVT).getDouble()
		);
	}
}
