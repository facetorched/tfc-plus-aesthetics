package com.facetorched.tfcaths.util;

import java.io.File;

import com.facetorched.tfcaths.WorldGen.Generators.PlantSpawnData;

import net.minecraft.block.Block;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;

import com.dunk.tfc.Reference;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.Enums.EnumRegion;
import com.facetorched.tfcaths.AthsMod;
import com.facetorched.tfcaths.Global;
import com.facetorched.tfcaths.WorldGen.Generators.AthsWorldGenPlants;

public class Config {
	//configuration object
	public static Configuration config;
	
	//define config fields here
	public static String[] soilBlocks;
	
	public static void preInit(File configDir)
	{
		if (config != null) throw new IllegalStateException("Preinit can't be called twice.");
		config = new Configuration(new File(configDir,"TFC+Aesthetics.cfg"));
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
		athsPlantHelper(Global.SAGEBRUSH, new int[] {0,1,2}, new String[] {"blockSoil", "blockSand"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"}, 
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.PRARIE_GRASS, new int[] {0,1,2}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.LEAFY_UNDERGROWTH, new int[] {0,1,2}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.MEDIUM_UNDERGROWTH, new int[] {0,1,2}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		
		athsPlantHelper(Global.BOLETUS, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.CHANTERELLE, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.DEVILS_TOUNGE, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.DUNE_GRASS, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.HORSETAIL, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.INDIGO_MILK_CAP, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.LOBSTER_CLAWS, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.MOREL, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.PONDGRASS, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.THISTLE, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.TITAN_ARUM, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.VOODOO_LILY, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		
		athsPlantHelper(Global.ALGAE_MAT_BROWN, new int[] {0}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:SaltWaterStationary"}, new String[] {"Ocean","River","Beach","Gravel Beach","Swamp","Lake","Shore","Salt Swamp","Lakeshore","Riverbank","River Source","Estuary"}, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);

		athsPlantHelper(Global.YOUNG_ACACIA, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_ASH, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_ASPEN, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_BAOBAB, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_BIRCH, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_CHESTNUT, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_DOUGLAS_FIR, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_EBONY, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_FEVER, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_GHAF, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_GINKO, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_HICKORY, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_KAPOK, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_LAUREL, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_LIMBA, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_MAHOE, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_MAHOGANY, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_MAPLE, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_OAK, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_PINE, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_SEQUOIA, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_SPRUCE, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_SYCAMORE, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_TEAK, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_WHITE_CEDAR, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_WHITE_ELM, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_WILLOW, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(Global.YOUNG_YEW, new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/25f, /*minRainfall*/100f, /*maxRainfall*/1000f, /*minEVT*/0f, /*maxEVT*/2f);
		
		
		
		AthsWorldGenPlants.plantList.put("undergrowth", getPlantData("undergrowth", Reference.MOD_ID+":"+TFCBlocks.undergrowth.getUnlocalizedName().substring(5), new int[] {0}, new String[] {"blockSoil"}, ALLOWED_BIOMES, new String[]{"Americas", "Asia"}, 5, 5, 16 * 16, 0, 255, 4f, 25f, 100f, 1000f, 0f, 2f));

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
	
	private static PlantSpawnData getPlantData(String category, String blockName, int[] metas, String[] growOnBlocks, String[] biomes, String[] regions, int size, int dispersion,
			int rarity, int minAltitude, int maxAltitude, float minTemp, float maxTemp, float minRainfall, float maxRainfall, float minEVT, float maxEVT)
	{
		return new PlantSpawnData(
				config.get(category, "blockName", blockName).getString(),
				config.get(category, "metas", metas).getIntList(),
				config.get(category, "growOnBlocks", growOnBlocks).getStringList(),
				config.get(category, "biomes", biomes).setValidValues(ALLOWED_BIOMES).getStringList(),
				getRegionEnumFromName(config.get(category, "regions", regions).getStringList()),
				config.get(category, "size", size).setMinValue(1).getInt(),
				config.get(category, "dispersion", dispersion).setMinValue(1).getInt(),
				config.get(category, "rarity", rarity).setMinValue(1).getInt(),
				config.get(category, "minAltitude", minAltitude).setMinValue(0).setMaxValue(255).getInt(),
				config.get(category, "maxAltitude", maxAltitude).setMinValue(0).setMaxValue(255).getInt(),
				(float)config.get(category, "minTemp", minTemp).getDouble(),
				(float)config.get(category, "maxTemp", maxTemp).getDouble(),
				(float)config.get(category, "minRainfall", minRainfall).getDouble(),
				(float)config.get(category, "maxRainfall", maxRainfall).getDouble(),
				(float)config.get(category, "minEVT", minEVT).getDouble(),
				(float)config.get(category, "maxEVT", maxEVT).getDouble()
		);
	}
	
	public static void athsPlantHelper(String name, int[] metas, String[] growOnBlocks, String[] biomes, String[] regions, int size, int dispersion,
			int rarity, int minAltitude, int maxAltitude, float minTemp, float maxTemp, float minRainfall, float maxRainfall, float minEVT, float maxEVT) {
		AthsWorldGenPlants.plantList.put(name, getPlantData(name, AthsMod.MODID+":"+name, metas, growOnBlocks, biomes, regions, size, dispersion,
				rarity, minAltitude, maxAltitude, minTemp, maxTemp, minRainfall, maxRainfall, minEVT, maxEVT));
	}
}
