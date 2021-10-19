package com.facetorched.tfcaths.util;

import java.io.File;

import com.dunk.tfc.Reference;
import com.dunk.tfc.WorldGen.TFCBiome;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.Enums.EnumRegion;
import com.dunk.tfc.api.Enums.EnumTree;
import com.facetorched.tfcaths.AthsMod;
import com.facetorched.tfcaths.AthsGlobal;
import com.facetorched.tfcaths.WorldGen.Generators.AthsWorldGenPlants;
import com.facetorched.tfcaths.WorldGen.Generators.PlantSpawnData;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import scala.actors.threadpool.Arrays;

public class Config {
	//configuration object
	public static Configuration config;
	
	//define configuration fields here
	public static String[] soilBlocks;
	
	public static final int TREE_BASE_RARITY = 1000;
	
	private static final String[] ALLOWED_REGIONS = new String[] {"Americas","Europe","Africa","Asia"};
	
	private static String[] ALLOWED_BIOMES;
	
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
		System.out.println(Arrays.toString(AthsParser.getBiomeStringList()));
		int[] p = new int[AthsParser.getBiomeStringList().length];
		for(int i = 0; i < AthsParser.getBiomeStringList().length; i++)
			p[i] = TFCBiome.getBiomeByName(AthsParser.getBiomeStringList()[i]).biomeID;
		System.out.println(Arrays.toString(p));
		
		ALLOWED_BIOMES = AthsParser.add(AthsParser.append(AthsParser.getBiomeStringList(), AthsParser.prefix(AthsParser.getBiomeStringList(), "!")), "All");
		if(ALLOWED_BIOMES.length == 0)
			throw new IllegalStateException(); // something terrible has occurred
		
		athsPlantHelper(AthsGlobal.SAGEBRUSH, new int[] {0,1,2}, new String[] {"blockSoil", "blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","Mountains","High Plains","Lake","Mountain Range","Mountain Range Edge","Foothills"}, new String[]{"Americas"}, 
				/*size*/7, /*dispersion*/4, /*rarity*/128, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/22f, /*minRain*/180f, /*maxRain*/300f, /*minEVT*/0f, /*maxEVT*/1f);
		athsPlantHelper(AthsGlobal.PRARIE_GRASS, new int[] {0,1,2}, new String[] {"blockSoil"}, new String[]{"Plains","High Hills","Mountains","Rolling Hills","High Hills Edge","Foothills","Mountain Range Edge","Mountain Range","High Plains"}, new String[]{"Americas", "Asia", "Africa", "Europe"},
				/*size*/10, /*dispersion*/4, /*rarity*/128, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/18f, /*minRain*/120f, /*maxRain*/600f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(AthsGlobal.LEAFY_UNDERGROWTH, new int[] {0,1,2}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","River","Lake","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Americas", "Asia", "Africa", "Europe"},
				/*size*/8, /*dispersion*/6, /*rarity*/128, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/40f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.MEDIUM_UNDERGROWTH, new int[] {0,1,2}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Americas", "Asia", "Africa", "Europe"},
				/*size*/6, /*dispersion*/4, /*rarity*/128, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-6f, /*maxTemp*/40f, /*minRain*/350f, /*maxRain*/800f, /*minEVT*/0f, /*maxEVT*/2f);
		
		athsPlantHelper(AthsGlobal.BIRD_OF_PARADISE, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Africa"},
				/*size*/3, /*dispersion*/2, /*rarity*/384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/40f, /*minRain*/1000f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.BOLETUS, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas", "Asia", "Europe"},
				/*size*/3, /*dispersion*/2, /*rarity*/384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/18f, /*minRain*/700f, /*maxRain*/1500f, /*minEVT*/0.5f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.CHANTERELLE, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas", "Asia", "Africa", "Europe"},
				/*size*/3, /*dispersion*/2, /*rarity*/384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/13f, /*minRain*/800f, /*maxRain*/1500f, /*minEVT*/0.5f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.DEVILS_TOUNGE, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia"},
				/*size*/2, /*dispersion*/6, /*rarity*/4096, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/20f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.DUNE_GRASS, new int[] {0}, new String[] {"blockSoil","blockSand","blockGravel"}, new String[]{"Beach","Gravel Beach","Shore"}, new String[]{"Americas"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/146, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/16f, /*minRain*/300f, /*maxRain*/1500f, /*minEVT*/0f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.HORSETAIL, new int[] {0}, new String[] {"blockSoil"}, new String[]{"Plains","Rolling Hills","Lake","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas", "Asia", "Europe", "Africa"},
				/*size*/5, /*dispersion*/4, /*rarity*/512, /*minAltitude*/0, /*maxAltitude*/150, /*minTemp*/-8f, /*maxTemp*/16f, /*minRain*/500f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.INDIGO_MILK_CAP, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas", "Asia"},
				/*size*/3, /*dispersion*/6, /*rarity*/512, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/22f, /*minRain*/700f, /*maxRain*/1500f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(AthsGlobal.LOBSTER_CLAWS, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/5, /*dispersion*/5, /*rarity*/1536, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/18f, /*maxTemp*/40f, /*minRain*/1000f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.MOREL, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas", "Asia", "Europe"},
				/*size*/3, /*dispersion*/3, /*rarity*/4096, /*minAltitude*/0, /*maxAltitude*/145, /*minTemp*/0f, /*maxTemp*/16f, /*minRain*/700f, /*maxRain*/1600f, /*minEVT*/0.25f, /*maxEVT*/1f);
		athsPlantHelper(AthsGlobal.POND_GRASS, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Mountains","Mountain Range Edge","Mountain Range","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Americas", "Asia"},
				/*size*/10, /*dispersion*/2, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/146, /*minTemp*/-10f, /*maxTemp*/30f, /*minRain*/300f, /*maxRain*/2000f, /*minEVT*/0f, /*maxEVT*/8f);
		athsPlantHelper(AthsGlobal.THISTLE, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Mountains","Mountain Range Edge","Mountain Range","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Americas", "Asia"},
				/*size*/5, /*dispersion*/5, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-5f, /*maxTemp*/13f, /*minRain*/250f, /*maxRain*/750f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(AthsGlobal.TITAN_ARUM, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Asia"},
				/*size*/2, /*dispersion*/7, /*rarity*/8192, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/20f, /*maxTemp*/28f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.VOODOO_LILY, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Asia"},
				/*size*/3, /*dispersion*/6, /*rarity*/4096, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/25f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.BRIDAL_VEIL_STINKHORN, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/3, /*dispersion*/6, /*rarity*/4096, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/25f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.ENTOLOMA, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/3, /*dispersion*/6, /*rarity*/4096, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/25f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.SHITAKE, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/3, /*dispersion*/6, /*rarity*/4096, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/25f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		
		athsPlantHelper(AthsGlobal.LILY_PAD, new int[] {0,1,2}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:SaltWaterStationary","terrafirmacraftplus:Ice"}, AthsGlobal.SHALLOW_WATER_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/1, /*dispersion*/1, /*rarity*/64, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-20f, /*maxTemp*/40f, /*minRain*/80f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.ALGAE_MAT_GREEN, new int[] {0,1,2}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:SaltWaterStationary","terrafirmacraftplus:Ice"}, AthsGlobal.SHALLOW_WATER_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/64, /*dispersion*/1, /*rarity*/768, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-20f, /*maxTemp*/40f, /*minRain*/80f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.ALGAE_MAT_RED, new int[] {0,1,2}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:SaltWaterStationary","terrafirmacraftplus:Ice"}, AthsGlobal.SHALLOW_WATER_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/64, /*dispersion*/1, /*rarity*/768, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-20f, /*maxTemp*/40f, /*minRain*/80f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.ALGAE_MAT_CYANOBACTERIA, new int[] {0,1,2}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:SaltWaterStationary","terrafirmacraftplus:Ice"}, AthsGlobal.SHALLOW_WATER_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/64, /*dispersion*/1, /*rarity*/768, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-20f, /*maxTemp*/40f, /*minRain*/80f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		
		athsTreeHelper(AthsGlobal.YOUNG_ACACIA, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.UTACACIA);
		athsTreeHelper("Young_Acacia_America",AthsGlobal.YOUNG_ACACIA, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.KOA);
		athsTreeHelper(AthsGlobal.YOUNG_ASH, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.ASH);
		athsTreeHelper(AthsGlobal.YOUNG_ASPEN, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia","Europe"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.ASPEN);
		athsTreeHelper(AthsGlobal.YOUNG_BAOBAB, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.BAOBAB);
		athsTreeHelper(AthsGlobal.YOUNG_BIRCH, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia", "Europe"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.BIRCH);
		athsTreeHelper(AthsGlobal.YOUNG_CHESTNUT, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia","Europe"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.CHESTNUT);
		athsTreeHelper(AthsGlobal.YOUNG_DOUGLAS_FIR, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.DOUGLASFIR);
		athsTreeHelper(AthsGlobal.YOUNG_EBONY, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa", "Asia"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.EBONY);
		athsTreeHelper(AthsGlobal.YOUNG_FEVER, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.FEVERTREE);
		athsTreeHelper(AthsGlobal.YOUNG_GHAF, new int[] {0}, new String[] {"blockSoil","blockSand"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.GHAF);
		athsTreeHelper(AthsGlobal.YOUNG_GINGKO, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.GINGKO);
		athsTreeHelper(AthsGlobal.YOUNG_HICKORY, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.HICKORY);
		athsTreeHelper(AthsGlobal.YOUNG_KAPOK, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.KAPOK);
		athsTreeHelper(AthsGlobal.YOUNG_LAUREL, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/1, /*dispersion*/5, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.LAUREL);
		athsTreeHelper(AthsGlobal.YOUNG_LIMBA, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.LIMBA);
		athsTreeHelper(AthsGlobal.YOUNG_MAHOE, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.MAHOE);
		athsTreeHelper(AthsGlobal.YOUNG_MAHOGANY, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Africa"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.MAHOGANY);
		athsTreeHelper(AthsGlobal.YOUNG_MAPLE, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia", "Africa"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.MAPLE);
		athsTreeHelper(AthsGlobal.YOUNG_OAK, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia" ,"Africa", "Europe"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.OAK);
		athsTreeHelper(AthsGlobal.YOUNG_PINE, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia", "Africa", "Europe"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.PINE);
		athsTreeHelper(AthsGlobal.YOUNG_SEQUOIA, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.REDWOOD);
		athsTreeHelper(AthsGlobal.YOUNG_SPRUCE, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia", "Africa", "Europe"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.SPRUCE);
		athsTreeHelper(AthsGlobal.YOUNG_SYCAMORE, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe", "Asia"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.SYCAMORE);
		athsTreeHelper(AthsGlobal.YOUNG_TEAK, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.TEAK);
		athsTreeHelper(AthsGlobal.YOUNG_WHITE_CEDAR, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.WHITECEDAR);
		athsTreeHelper(AthsGlobal.YOUNG_WHITE_ELM, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe", "Asia"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.WHITEELM);
		athsTreeHelper(AthsGlobal.YOUNG_WILLOW, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia", "Europe", "Africa"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.WILLOW);
		athsTreeHelper(AthsGlobal.YOUNG_YEW, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe", "Asia"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.YEW);
		athsTreeHelper("Young_Yew_Africa", AthsGlobal.YOUNG_YEW, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.AFRICANYEW);
		
		athsPlantHelper(AthsGlobal.VICTORIA_LILY_PAD, new int[] {0}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:SaltWaterStationary","terrafirmacraftplus:Ice"}, AthsGlobal.SHALLOW_WATER_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/1, /*dispersion*/1, /*rarity*/768, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-20f, /*maxTemp*/40f, /*minRain*/80f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.YUCCA, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/1, /*dispersion*/1, /*rarity*/768, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-20f, /*maxTemp*/40f, /*minRain*/80f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		
		AthsWorldGenPlants.plantList.put("undergrowth", getPlantData("undergrowth", Reference.MOD_ID+":"+TFCBlocks.undergrowth.getUnlocalizedName().substring(5), new int[] {0}, new String[] {"blockSoil"}, new String[] {"All", "!Hell"}, new String[]{"Americas", "Asia"}, 
				/*size*/1, /*dispersion*/1, /*rarity*/1024, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/40f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f));

		if (config.hasChanged()) 
			config.save();
	}
	
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
			int rarity, int minAltitude, int maxAltitude, float minTemp, float maxTemp, float minRainfall, float maxRainfall, float minEVT, float maxEVT, float forestGen)
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
				(float)config.get(category, "maxEVT", maxEVT).getDouble(),
				(float)config.get(category, "forestGen", forestGen).getDouble()
		);
	}
	
	public static void athsPlantHelper(String name, int[] metas, String[] growOnBlocks, String[] biomes, String[] regions, int size, int dispersion,
			int rarity, int minAltitude, int maxAltitude, float minTemp, float maxTemp, float minRainfall, float maxRainfall, float minEVT, float maxEVT) {
		AthsWorldGenPlants.plantList.put(name, getPlantData(name, AthsMod.MODID+":"+name, metas, growOnBlocks, biomes, regions, size, dispersion,
				rarity, minAltitude, maxAltitude, minTemp, maxTemp, minRainfall, maxRainfall, minEVT, maxEVT, 0.0f));
	}
	
	/* forestGen version*/
	public static void athsPlantHelper(String name, int[] metas, String[] growOnBlocks, String[] biomes, String[] regions, int size, int dispersion,
			int rarity, int minAltitude, int maxAltitude, float minTemp, float maxTemp, float minRainfall, float maxRainfall, float minEVT, float maxEVT, float forestGen) {
		AthsWorldGenPlants.plantList.put(name, getPlantData(name, AthsMod.MODID+":"+name, metas, growOnBlocks, biomes, regions, size, dispersion,
				rarity, minAltitude, maxAltitude, minTemp, maxTemp, minRainfall, maxRainfall, minEVT, maxEVT, forestGen));
	}
	
	public static void athsTreeHelper(String name, int[] metas, String[] growOnBlocks, String[] biomes, String[] regions, int size, int dispersion,
			int minAltitude, int maxAltitude, EnumTree tree) {
		AthsWorldGenPlants.plantList.put(name, getPlantData(name, AthsMod.MODID+":"+name, metas, growOnBlocks, biomes, regions, size, dispersion,
				(int)(TREE_BASE_RARITY / tree.rarity), minAltitude, maxAltitude, tree.minTemp, tree.maxTemp, tree.minRain, tree.maxRain, tree.minEVT, tree.maxEVT, 1.0f));
	}
	
	/* alternate spawn parameters for same tree*/
	public static void athsTreeHelper(String name, String blockName, int[] metas, String[] growOnBlocks, String[] biomes, String[] regions, int size, int dispersion,
			int minAltitude, int maxAltitude, EnumTree tree) {
		AthsWorldGenPlants.plantList.put(name, getPlantData(name, AthsMod.MODID+":"+blockName, metas, growOnBlocks, biomes, regions, size, dispersion,
				(int)(TREE_BASE_RARITY / tree.rarity), minAltitude, maxAltitude, tree.minTemp, tree.maxTemp, tree.minRain, tree.maxRain, tree.minEVT, tree.maxEVT, 1.0f));
	}
}
