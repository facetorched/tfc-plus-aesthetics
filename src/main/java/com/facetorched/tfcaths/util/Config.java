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

import net.minecraft.block.Block;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import java.util.Arrays;

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
		
		
		
		
		
		athsPlantHelper(AthsGlobal.ALGAE_MAT_CYANOBACTERIA, new int[] {0,1,2}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:SaltWaterStationary","terrafirmacraftplus:Ice"}, AthsGlobal.SHALLOW_WATER_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/64, /*dispersion*/1, /*rarity*/2968, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-20f, /*maxTemp*/40f, /*minRain*/80f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.ALGAE_MAT_GREEN, new int[] {0,1,2}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:Ice"}, AthsGlobal.SHALLOW_WATER_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/64, /*dispersion*/1, /*rarity*/968, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-20f, /*maxTemp*/40f, /*minRain*/80f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.ALGAE_MAT_RED, new int[] {0,1,2}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:SaltWaterStationary","terrafirmacraftplus:Ice"}, AthsGlobal.SHALLOW_WATER_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/64, /*dispersion*/1, /*rarity*/9068, /*minAltitude*/144, /*maxAltitude*/255, /*minTemp*/-20f, /*maxTemp*/40f, /*minRain*/40f, /*maxRain*/800f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.ALOE_VERA, new int[] {0}, new String[] {"blockSoil","blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills"}, new String[]{"Asia"},
				/*size*/3, /*dispersion*/2, /*rarity*/3084, /*minAltitude*/156, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/40f, /*minRain*/100f, /*maxRain*/400f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.ANGELS_TRUMPET + "_Orange", new int[] {0}, new String[] {"blockSoil"}, new String[]{"Foothills","Mountains Edge","Mountains","Mountain Range","Mountain Range Edge"}, new String[]{"Americas"},
				/*size*/6, /*dispersion*/5, /*rarity*/10084, /*minAltitude*/150, /*maxAltitude*/220, /*minTemp*/25f, /*maxTemp*/40f, /*minRain*/1000f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.ANGELS_TRUMPET + "_Pink", new int[] {1}, new String[] {"blockSoil"}, new String[]{"High Hills","High Hills Edge","Foothills","Mountains Edge","Mountains","Mountain Range","Mountain Range Edge"}, new String[]{"Americas"},
				/*size*/6, /*dispersion*/5, /*rarity*/10084, /*minAltitude*/170, /*maxAltitude*/220, /*minTemp*/22f, /*maxTemp*/40f, /*minRain*/900f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.ANGELS_TRUMPET + "_White", new int[] {2}, new String[] {"blockSoil"}, new String[]{"High Hills","High Hills Edge","High Plains","Foothills","Mountains Edge","Mountains","Mountain Range","Mountain Range Edge"}, new String[]{"Americas"},
				/*size*/6, /*dispersion*/5, /*rarity*/10084, /*minAltitude*/150, /*maxAltitude*/220, /*minTemp*/20f, /*maxTemp*/35f, /*minRain*/1000f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.ANGEL_WING_CACTUS, new int[] {0}, new String[] {"blockSoil","blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge"}, new String[]{"Americas"},
				/*size*/15, /*dispersion*/6, /*rarity*/700, /*minAltitude*/0, /*maxAltitude*/190, /*minTemp*/19f, /*maxTemp*/30f, /*minRain*/80f, /*maxRain*/350f, /*minEVT*/0f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.BIRD_OF_PARADISE, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/3, /*dispersion*/3, /*rarity*/7584, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/12f, /*maxTemp*/16f, /*minRain*/600f, /*maxRain*/3000f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.BLUE_CEREUS_CACTUS, new int[] {0,1}, new String[] {"blockSoil","blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge"}, new String[]{"Americas"},
				/*size*/5, /*dispersion*/3, /*rarity*/1200, /*minAltitude*/0, /*maxAltitude*/210, /*minTemp*/23f, /*maxTemp*/34f, /*minRain*/80f, /*maxRain*/350f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.BOLETUS, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas", "Asia", "Europe"},
				/*size*/3, /*dispersion*/2, /*rarity*/7484, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-5f, /*maxTemp*/15f, /*minRain*/700f, /*maxRain*/1000f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.BRACKEN_FERN, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia","Europe"},
				/*size*/64, /*dispersion*/4, /*rarity*/868, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-10f, /*maxTemp*/30f, /*minRain*/500f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.BRIDAL_VEIL_STINKHORN, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia","Africa"},
				/*size*/2, /*dispersion*/1, /*rarity*/10096, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/20f, /*maxTemp*/32f, /*minRain*/1000f, /*maxRain*/8000f, /*minEVT*/3f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.BURDOCK, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Europe"},
				/*size*/4, /*dispersion*/3, /*rarity*/968, /*minAltitude*/144, /*maxAltitude*/255, /*minTemp*/-5f, /*maxTemp*/18f, /*minRain*/600f, /*maxRain*/1500f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.CAMAS_FLOWER, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/25, /*dispersion*/6, /*rarity*/6384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-13f, /*maxTemp*/12f, /*minRain*/550f, /*maxRain*/750f, /*minEVT*/0.5f, /*maxEVT*/2f);
		athsPlantHelper(AthsGlobal.CHANTERELLE, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Europe"},
				/*size*/4, /*dispersion*/1, /*rarity*/5384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-4f, /*maxTemp*/18f, /*minRain*/750f, /*maxRain*/3000f, /*minEVT*/1f, /*maxEVT*/8f);
		athsPlantHelper(AthsGlobal.CHIVES, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Europe","Asia","Americas"},
				/*size*/8, /*dispersion*/4, /*rarity*/984, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/3f, /*maxTemp*/16f, /*minRain*/600f, /*maxRain*/800f, /*minEVT*/1f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.CLOVER, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa","Americas","Europe","Asia"},
				/*size*/5, /*dispersion*/4, /*rarity*/656, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-6f, /*maxTemp*/33f, /*minRain*/650f, /*maxRain*/950f, /*minEVT*/1f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.COMMON_REEDS, new int[] {0}, new String[] {"blockSoil"}, new String[]{"Lake","Estuary","Lakeshore","Riverbank","Swamp","River"}, new String[]{"Africa"},
				/*size*/20, /*dispersion*/1, /*rarity*/984, /*minAltitude*/0, /*maxAltitude*/146, /*minTemp*/0f, /*maxTemp*/30f, /*minRain*/350f, /*maxRain*/2400f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.CREEPING_CHARLIE, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/15, /*dispersion*/2, /*rarity*/1056, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-6f, /*maxTemp*/33f, /*minRain*/650f, /*maxRain*/1200f, /*minEVT*/0.5f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.CUP_PLANT, new int[] {0,1}, new String[] {"blockSoil"}, new String[]{"Plains","Rolling Hills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/7, /*dispersion*/1, /*rarity*/4384, /*minAltitude*/0, /*maxAltitude*/160, /*minTemp*/0f, /*maxTemp*/15f, /*minRain*/600f, /*maxRain*/750f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.DAFFODIL, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/7, /*dispersion*/3, /*rarity*/7384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/12f, /*maxTemp*/28f, /*minRain*/500f, /*maxRain*/1300f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.DEVILS_CLUB, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/30, /*dispersion*/4, /*rarity*/7384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-10f, /*maxTemp*/7f, /*minRain*/1000f, /*maxRain*/16000f, /*minEVT*/0.5f, /*maxEVT*/2f);
		athsPlantHelper(AthsGlobal.DEVILS_FINGERS, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Europe","Americas", "Asia", "Africa"},
				/*size*/4, /*dispersion*/1, /*rarity*/5384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-4f, /*maxTemp*/18f, /*minRain*/750f, /*maxRain*/3000f, /*minEVT*/1f, /*maxEVT*/8f);
		athsPlantHelper(AthsGlobal.DEVILS_TOUNGE, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia"},
				/*size*/2, /*dispersion*/6, /*rarity*/10096, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/20f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.DUCKWEED, new int[] {0}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:Ice"}, AthsGlobal.SHALLOW_WATER_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/64, /*dispersion*/1, /*rarity*/668, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-10f, /*maxTemp*/40f, /*minRain*/500f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.DUNE_GRASS, new int[] {0}, new String[] {"blockSand","blockGravel"}, new String[]{"Beach","Gravel Beach","Shore","Estuary"}, new String[]{"Americas"},
				/*size*/12, /*dispersion*/4, /*rarity*/256, /*minAltitude*/146, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/16f, /*minRain*/300f, /*maxRain*/1500f, /*minEVT*/0f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.ELEPHANT_GRASS, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains"}, new String[]{"Africa"},
				/*size*/200, /*dispersion*/1, /*rarity*/10384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/32f, /*minRain*/450f, /*maxRain*/700f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(AthsGlobal.ENTOLOMA, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/5, /*dispersion*/3, /*rarity*/6096, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-5f, /*maxTemp*/15f, /*minRain*/900f, /*maxRain*/10000f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.FIELD_HORSETAIL, new int[] {0}, new String[] {"blockSoil"}, new String[]{"Plains","Lake","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Americas", "Asia", "Europe"},
				/*size*/23, /*dispersion*/3, /*rarity*/8012, /*minAltitude*/0, /*maxAltitude*/150, /*minTemp*/-10f, /*maxTemp*/12f, /*minRain*/700f, /*maxRain*/16000f, /*minEVT*/3f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.FIREWEED, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/20, /*dispersion*/2, /*rarity*/8384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-13f, /*maxTemp*/8f, /*minRain*/550f, /*maxRain*/750f, /*minEVT*/0.5f, /*maxEVT*/2f);
		athsPlantHelper(AthsGlobal.GIANT_HOGWEED, new int[] {0}, new String[] {"blockSoil"}, new String[]{"Mountains","High Hills","High Hills Edge","Mountains Edge","Foothills","Riverbank"}, new String[]{"Europe"},
				/*size*/13, /*dispersion*/3, /*rarity*/8384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/5f, /*maxTemp*/14f, /*minRain*/720f, /*maxRain*/930f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.HEATHER + "_Pink", new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge"}, new String[]{"Europe"},
				/*size*/40, /*dispersion*/9, /*rarity*/484, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-2f, /*maxTemp*/14f, /*minRain*/520f, /*maxRain*/850f, /*minEVT*/1f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.HEATHER + "_White", new int[] {1}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge"}, new String[]{"Europe"},
				/*size*/45, /*dispersion*/9, /*rarity*/384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-2f, /*maxTemp*/14f, /*minRain*/520f, /*maxRain*/850f, /*minEVT*/1f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.HIBISCUS + "_Orange", new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/5, /*dispersion*/2, /*rarity*/6969, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/26f, /*maxTemp*/30f, /*minRain*/650f, /*maxRain*/1000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.HIBISCUS + "_Pink", new int[] {1}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia"},
				/*size*/5, /*dispersion*/2, /*rarity*/6084, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/14f, /*maxTemp*/24f, /*minRain*/650f, /*maxRain*/1000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.HIBISCUS + "_Red", new int[] {2}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia"},
				/*size*/5, /*dispersion*/2, /*rarity*/6684, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/17f, /*maxTemp*/30f, /*minRain*/650f, /*maxRain*/1000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.HIBISCUS + "_White", new int[] {3}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/5, /*dispersion*/2, /*rarity*/6484, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/12f, /*maxTemp*/17f, /*minRain*/650f, /*maxRain*/1000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.HOSTA_HALCYON, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/5, /*dispersion*/2, /*rarity*/7368, /*minAltitude*/144, /*maxAltitude*/165, /*minTemp*/8f, /*maxTemp*/18f, /*minRain*/800f, /*maxRain*/3000f, /*minEVT*/0.5f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.HOSTA_PATRIOT, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/5, /*dispersion*/2, /*rarity*/7028, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/8f, /*maxTemp*/18f, /*minRain*/800f, /*maxRain*/3000f, /*minEVT*/0.5f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.HOSTA_VULCAN, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/5, /*dispersion*/2, /*rarity*/7688, /*minAltitude*/144, /*maxAltitude*/170, /*minTemp*/8f, /*maxTemp*/18f, /*minRain*/800f, /*maxRain*/3000f, /*minEVT*/0.5f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.INDIAN_PIPES, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Americas"},
				/*size*/3, /*dispersion*/1, /*rarity*/13384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/6f, /*maxTemp*/13f, /*minRain*/870f, /*maxRain*/10000f, /*minEVT*/3f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.INDIGO_MILK_CAP, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/3, /*dispersion*/4, /*rarity*/512, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/24f, /*minRain*/750f, /*maxRain*/3000f, /*minEVT*/1f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.IRIS, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Mountains","Mountain Range","Mountain range Edge","Foothills","Mountains Edge","High Plains","Riverbank"}, new String[]{"Americas","Asia","Europe"},
				/*size*/6, /*dispersion*/3, /*rarity*/5384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/13f, /*minRain*/400f, /*maxRain*/680f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(AthsGlobal.JACK_IN_THE_PULPIT, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/3, /*dispersion*/2, /*rarity*/984, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/3f, /*maxTemp*/16f, /*minRain*/800f, /*maxRain*/2000f, /*minEVT*/3f, /*maxEVT*/8f);
		athsPlantHelper(AthsGlobal.LADY_FERN, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia","Europe"},
				/*size*/16, /*dispersion*/4, /*rarity*/5968, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-7f, /*maxTemp*/20f, /*minRain*/800f, /*maxRain*/4000f, /*minEVT*/1f, /*maxEVT*/8f);
		athsPlantHelper(AthsGlobal.LAVENDER, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains"}, new String[]{"Africa","Asia","Europe"},
				/*size*/64, /*dispersion*/2, /*rarity*/9384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/25f, /*minRain*/400f, /*maxRain*/700f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.LEAFY_LOW_UNDERGROWTH, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","River","Lake","Riverbank","Swamp"}, new String[]{"Americas", "Asia", "Africa", "Europe"},
				/*size*/14, /*dispersion*/1, /*rarity*/728, /*minAltitude*/0, /*maxAltitude*/200, /*minTemp*/-2f, /*maxTemp*/30f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/1f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.LEAFY_UNDERGROWTH, new int[] {0,1,2}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia", "Africa", "Europe"},
				/*size*/8, /*dispersion*/4, /*rarity*/128, /*minAltitude*/0, /*maxAltitude*/230, /*minTemp*/0f, /*maxTemp*/40f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/1f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.LILY_PAD, new int[] {0,1,2}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:Ice"}, AthsGlobal.SHALLOW_WATER_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/18, /*dispersion*/2, /*rarity*/1064, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-20f, /*maxTemp*/40f, /*minRain*/600f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.LOBSTER_CLAWS, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/4, /*dispersion*/3, /*rarity*/3536, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/23f, /*maxTemp*/40f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/1f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.LUPINE + "_Blue", new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/15, /*dispersion*/2, /*rarity*/8384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-3f, /*maxTemp*/20f, /*minRain*/650f, /*maxRain*/850f, /*minEVT*/0.5f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.LUPINE + "_Orange", new int[] {1}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Amercias"},
				/*size*/15, /*dispersion*/2, /*rarity*/8384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/18f, /*minRain*/650f, /*maxRain*/850f, /*minEVT*/0.5f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.LUPINE + "_Purple", new int[] {2}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/15, /*dispersion*/2, /*rarity*/8384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-8f, /*maxTemp*/14f, /*minRain*/650f, /*maxRain*/850f, /*minEVT*/0.5f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.LUPINE + "_Red", new int[] {3}, new String[] {"blockSoil"}, new String[]{"High Hills","High Hills Edge","Foothills","Mountains","Mountain Range","Mountain Range Edge","Mountains Edge"}, new String[]{"Americas"},
				/*size*/15, /*dispersion*/2, /*rarity*/8384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/7f, /*maxTemp*/16f, /*minRain*/650f, /*maxRain*/850f, /*minEVT*/0.5f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.LUPINE + "_Yellow", new int[] {4}, new String[] {"blockSoil"}, new String[]{"High Hills","High Hills Edge","Foothills","Mountains","Mountain Range","Mountain Range Edge","Mountains Edge"}, new String[]{"Americas"},
				/*size*/15, /*dispersion*/2, /*rarity*/8384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/10f, /*maxTemp*/15f, /*minRain*/650f, /*maxRain*/850f, /*minEVT*/0.5f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.MARIGOLD, new int[] {0}, new String[] {"blockSoil","blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/3, /*dispersion*/2, /*rarity*/1284, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/13f, /*maxTemp*/28f, /*minRain*/350f, /*maxRain*/600f, /*minEVT*/1f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.MEDIUM_UNDERGROWTH, new int[] {0,1,2}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp","Salt Swamp"}, new String[]{"Americas", "Asia", "Africa", "Europe"},
				/*size*/6, /*dispersion*/4, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-6f, /*maxTemp*/40f, /*minRain*/350f, /*maxRain*/1000f, /*minEVT*/0f, /*maxEVT*/2f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.MOREL, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas", "Asia", "Europe","Africa"},
				/*size*/2, /*dispersion*/2, /*rarity*/9096, /*minAltitude*/0, /*maxAltitude*/145, /*minTemp*/-2f, /*maxTemp*/14f, /*minRain*/800f, /*maxRain*/4000f, /*minEVT*/2f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.OCOTILLO, new int[] {0}, new String[] {"blockSoil","blockSand","blockGravel"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge","Mountains","Mountain Range Edge"}, new String[]{"Americas"},
				/*size*/8, /*dispersion*/5, /*rarity*/2550, /*minAltitude*/0, /*maxAltitude*/220, /*minTemp*/13f, /*maxTemp*/24f, /*minRain*/95f, /*maxRain*/390f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.ORGAN_PIPE_CACTUS, new int[] {0}, new String[] {"blockSoil","blockSand","blockGravel"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge","Mountains","Mountain Range Edge"}, new String[]{"Americas"},
				/*size*/4, /*dispersion*/4, /*rarity*/680, /*minAltitude*/0, /*maxAltitude*/200, /*minTemp*/16f, /*maxTemp*/28f, /*minRain*/76f, /*maxRain*/330f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.OSTRICH_FERN, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia","Europe"},
				/*size*/20, /*dispersion*/3, /*rarity*/2868, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-5f, /*maxTemp*/20f, /*minRain*/800f, /*maxRain*/8000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.PAINTED_FERN, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/16, /*dispersion*/3, /*rarity*/6468, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/0f, /*maxTemp*/18f, /*minRain*/800f, /*maxRain*/6000f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.POKEWEED, new int[] {0}, new String[] {"blockSoil"}, new String[]{"Plains","Rolling Hills","High Plains","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/14, /*dispersion*/2, /*rarity*/3884, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/14f, /*minRain*/750f, /*maxRain*/2500f, /*minEVT*/1f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.POND_GRASS, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Mountains","Mountain Range Edge","Mountain Range","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Americas", "Asia"},
				/*size*/10, /*dispersion*/2, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/146, /*minTemp*/-15f, /*maxTemp*/40f, /*minRain*/250f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.PRAIRIE_GRASS, new int[] {0,1,2}, new String[] {"blockSoil"}, new String[]{"Plains","High Hills","Mountains","Rolling Hills","High Hills Edge","Foothills","Mountain Range Edge","Mountain Range","High Plains"}, new String[]{"Americas", "Asia", "Africa", "Europe"},
				/*size*/80, /*dispersion*/3, /*rarity*/4128, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/18f, /*minRain*/120f, /*maxRain*/750f, /*minEVT*/0f, /*maxEVT*/2f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.PRICKLY_PEAR, new int[] {0}, new String[] {"blockSoil","blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge"}, new String[]{"Americas"},
				/*size*/8, /*dispersion*/5, /*rarity*/680, /*minAltitude*/0, /*maxAltitude*/190, /*minTemp*/13f, /*maxTemp*/25f, /*minRain*/80f, /*maxRain*/360f, /*minEVT*/0f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.ROSEBUSH, new int[] {0}, new String[] {"blockSoil"}, new String[]{"Plains","Rolling Hills","Lakeshore","Riverbank","Swamp","High Hills","High Hills Edge"}, new String[]{"Africa","Americas","Asia","Europe"},
				/*size*/5, /*dispersion*/2, /*rarity*/5384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-5f, /*maxTemp*/22f, /*minRain*/700f, /*maxRain*/900f, /*minEVT*/1f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.ROUGH_HORSETAIL, new int[] {0}, new String[] {"blockSoil"}, new String[]{"Plains","Lake","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Americas", "Asia", "Europe"},
				/*size*/35, /*dispersion*/2, /*rarity*/9012, /*minAltitude*/0, /*maxAltitude*/150, /*minTemp*/-10f, /*maxTemp*/12f, /*minRain*/700f, /*maxRain*/16000f, /*minEVT*/3f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.SAGEBRUSH, new int[] {0,1,2}, new String[] {"blockSoil","blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge","Mountains","Mountain Range","Mountain Range Edge"}, new String[]{"Americas"},
				/*size*/30, /*dispersion*/4, /*rarity*/400, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/22f, /*minRain*/180f, /*maxRain*/450f, /*minEVT*/0f, /*maxEVT*/2.5f);
		athsPlantHelper(AthsGlobal.SAGUARO, new int[] {0}, new String[] {"blockSoil","blockSand"}, new String[]{"Plains","High Hills Edge","Rolling Hills","High Plains","Foothills"}, new String[]{"Americas"},
				/*size*/25, /*dispersion*/6, /*rarity*/580, /*minAltitude*/0, /*maxAltitude*/190, /*minTemp*/17f, /*maxTemp*/29f, /*minRain*/86f, /*maxRain*/400f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.SCALY_TREE_FERN, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia"},
				/*size*/9, /*dispersion*/10, /*rarity*/8084, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/10f, /*maxTemp*/30f, /*minRain*/1000f, /*maxRain*/16000f, /*minEVT*/2f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.SENSITIVE_FERN, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia","Americas"},
				/*size*/10, /*dispersion*/3, /*rarity*/8084, /*minAltitude*/0, /*maxAltitude*/180, /*minTemp*/0f, /*maxTemp*/14f, /*minRain*/900f, /*maxRain*/10000f, /*minEVT*/1f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.SHITAKE, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/4, /*dispersion*/2, /*rarity*/4096, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/12f, /*maxTemp*/24f, /*minRain*/800f, /*maxRain*/2000f, /*minEVT*/1f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.SORBUS, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Mountains","Mountains Edge","Mountain Range Edge","Mountain Range","High Hills Edge","High Plains","Foothills"}, new String[]{"Africa","Europe","Asia"},
				/*size*/1, /*dispersion*/1, /*rarity*/884, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-4f, /*maxTemp*/16f, /*minRain*/550f, /*maxRain*/1000f, /*minEVT*/0f, /*maxEVT*/8f);
		athsPlantHelper(AthsGlobal.SUMAC_SHORT, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/4, /*dispersion*/5, /*rarity*/4668, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/4f, /*maxTemp*/18f, /*minRain*/300f, /*maxRain*/1200f, /*minEVT*/0.25f, /*maxEVT*/2f);
		athsPlantHelper(AthsGlobal.SUMAC_TALL, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/4, /*dispersion*/5, /*rarity*/6868, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/4f, /*maxTemp*/18f, /*minRain*/300f, /*maxRain*/1200f, /*minEVT*/0.25f, /*maxEVT*/2f);
		athsPlantHelper(AthsGlobal.SUNDEW, new int[] {0}, new String[] {"blockSoil"}, new String[]{"Mountains","Mountain Range", "Swamp","Salt Swamp"}, new String[]{"Americas","Africa"},
				/*size*/3, /*dispersion*/1, /*rarity*/14384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/15f, /*minRain*/400f, /*maxRain*/750f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.SUNFLOWER, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/28, /*dispersion*/3, /*rarity*/6384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/22f, /*minRain*/400f, /*maxRain*/750f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.SWORD_FERN, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Swamp","Mountains","Mountains Edge","Mountain Range Edge","Mountain Range"}, new String[]{"Americas"},
				/*size*/40, /*dispersion*/3, /*rarity*/784, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-10f, /*maxTemp*/18f, /*minRain*/1000f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.THISTLE, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/7, /*dispersion*/5, /*rarity*/556, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-2f, /*maxTemp*/15f, /*minRain*/350f, /*maxRain*/750f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.TITAN_ARUM, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Asia"},
				/*size*/2, /*dispersion*/7, /*rarity*/17192, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/20f, /*maxTemp*/28f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/1f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.VENUS_FLY_TRAP, new int[] {0}, new String[] {"blockSoil"}, new String[]{"Lakeshore","Riverbank","Swamp","Salt Swamp"}, new String[]{"Americas"},
				/*size*/2, /*dispersion*/1, /*rarity*/4384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/13f, /*maxTemp*/16f, /*minRain*/800f, /*maxRain*/1000f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.VICTORIA_LILY_PAD, new int[] {0}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:Ice"}, new String[] {"Riverbank","Lakeshore"}, new String[]{"Americas"},
				/*size*/30, /*dispersion*/2, /*rarity*/8968, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/20f, /*maxTemp*/40f, /*minRain*/1500f, /*maxRain*/16000f, /*minEVT*/2f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.VOODOO_LILY, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Asia"},
				/*size*/3, /*dispersion*/6, /*rarity*/10096, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/16f, /*maxTemp*/25f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/1f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.WATER_HYACINTH, new int[] {0}, new String[] {"terrafirmacraftplus:FreshWaterStationary"}, new String[]{"Plains","Lakeshore","River","Riverbank"}, new String[]{"Americas"},
				/*size*/50, /*dispersion*/2, /*rarity*/7384, /*minAltitude*/0, /*maxAltitude*/170, /*minTemp*/23f, /*maxTemp*/35f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/2f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.WOOD_FERN, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/25, /*dispersion*/2, /*rarity*/4068, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-5f, /*maxTemp*/15f, /*minRain*/750f, /*maxRain*/6000f, /*minEVT*/0.5f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.YARROW, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Europe","Americas"},
				/*size*/12, /*dispersion*/3, /*rarity*/6384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-3f, /*maxTemp*/12f, /*minRain*/450f, /*maxRain*/700f, /*minEVT*/0.5f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.YUCCA, new int[] {0}, new String[] {"blockSoil","blockSand","blockGravel"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/35, /*dispersion*/8, /*rarity*/584, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/6f, /*maxTemp*/31f, /*minRain*/90f, /*maxRain*/500f, /*minEVT*/0f, /*maxEVT*/4f);
		
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

		AthsWorldGenPlants.plantList.put("undergrowth", getPlantData("undergrowth", Reference.MOD_ID+":"+TFCBlocks.undergrowth.getUnlocalizedName().substring(5), new int[] {0}, new String[] {"blockSoil"}, new String[] {"All", "!Hell"}, new String[]{"Americas", "Asia"}, 
				/*size*/3, /*dispersion*/1, /*rarity*/424, /*minAltitude*/0, /*maxAltitude*/180, /*minTemp*/0f, /*maxTemp*/40f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f));

		
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
