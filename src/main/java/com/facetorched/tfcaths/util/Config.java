package com.facetorched.tfcaths.util;

import java.io.File;

import com.dunk.tfc.Reference;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.Enums.EnumTree;
import com.facetorched.tfcaths.AthsGlobal;
import com.facetorched.tfcaths.AthsMod;
import com.facetorched.tfcaths.WorldGen.Generators.AthsWorldGenCrystals;
import com.facetorched.tfcaths.WorldGen.Generators.AthsWorldGenPlants;
import com.facetorched.tfcaths.WorldGen.Generators.CrystalSpawnData;
import com.facetorched.tfcaths.WorldGen.Generators.PlantSpawnData;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;

public class Config {
	//configuration object
	public static Configuration config;
	
	//define configuration fields here
	public static int numCustomGenerators;
	public static boolean disable3DPlants;
	
	public static void preInit(File configDir)
	{
		if (config != null) throw new IllegalStateException("Preinit can't be called twice.");
		config = new Configuration(new File(configDir,"TFC+Aesthetics-" + AthsMod.VERSION + ".cfg"));
	}
	public static void reload()
	{
		if (config == null) throw new IllegalStateException("Config reload attempt before preinit.");
		AthsLogger.info("Loading TFC+ Aesthetics Config");
		config.load();
		// set configs here
		String[] soilBlocks = AthsParser.prefix(new String[] {"Dirt", "Dirt2", "Grass", "Grass2", "DryGrass", "DryGrass2", "Clay", "Clay2", "ClayGrass", "ClayGrass2", "tilledSoil", "tilledSoil2", "Peat"}, Reference.MOD_ID + ":");
		soilBlocks = config.get("_soil_ore_dict", "blockSoil", soilBlocks, "blocks to add to the ore dictionary 'blockSoil'. Leave empty to disable").getStringList();
		
		for(String soil : soilBlocks) {
			OreDictionary.registerOre("blockSoil", AthsParser.getBlockFromName(soil));
		}
		
		numCustomGenerators = config.getInt("numCustomGenerators", "_num_custom_generators", 1, 0, Integer.MAX_VALUE, "The number of custom plant generators to read from. The names of these generators are enumerated as \"plant_[n]\"");
		disable3DPlants = config.getBoolean("disable3DPlants", "_disable_3d_plants", false, "Set to true to disable generation of plants with 3D models since they cause lag for some clients");
		
		if (config.hasChanged()) config.save();
	}
	
	public static void reloadCrystals() {
		athsCrystalHelper(AthsGlobal.AGATE, new String[] {"Sed","Diorite","Granite","Rhyolite","Andesite"}, /*size*/1, /*dispersion*/1, /*rarity*/100);
        athsCrystalClusterHelper(AthsGlobal.AMETHYST, new String[] {"IgEx","Limestone","Granite"}, /*size*/20, /*dispersion*/1, /*rarity*/160);
        athsCrystalClusterHelper(AthsGlobal.BERYL, new String[] {"Granite","Rhyolite","Gneiss"}, /*size*/18, /*dispersion*/3, /*rarity*/420);
        athsCrystalClusterHelper(AthsGlobal.DIAMOND, new String[] {"Gabbro"}, /*size*/35, /*dispersion*/2, /*rarity*/350);
        athsCrystalClusterHelper(AthsGlobal.EMERALD, new String[] {"Granite","Rhyolite","Gneiss"}, /*size*/18, /*dispersion*/3, /*rarity*/500);
        athsCrystalClusterHelper(AthsGlobal.GARNET, new String[] {"Granite","MM","Conglomerate","Shale","Claystone"}, /*size*/15, /*dispersion*/5, /*rarity*/400);
        athsCrystalHelper(AthsGlobal.JADE, new String[] {"MM"}, /*size*/1, /*dispersion*/1, /*rarity*/350);
        athsCrystalHelper(AthsGlobal.JASPER, new String[] {"Chert","Limestone","Dolomite","Schist","Gneiss","Phyllite","Slate"}, /*size*/1, /*dispersion*/1, /*rarity*/100);
        athsCrystalHelper(AthsGlobal.OPAL, new String[] {"All"}, /*size*/1, /*dispersion*/1, /*rarity*/650);
        athsCrystalClusterHelper(AthsGlobal.RUBY, new String[] {"IgIn","MM"}, /*size*/20, /*dispersion*/6, /*rarity*/500);
        athsCrystalClusterHelper(AthsGlobal.SAPPHIRE, new String[] {"IgIn","MM"}, /*size*/20, /*dispersion*/6, /*rarity*/500);
        athsCrystalClusterHelper(AthsGlobal.TOPAZ, new String[] {"Granite","Rhyolite"}, /*size*/18, /*dispersion*/3, /*rarity*/400);
        athsCrystalClusterHelper(AthsGlobal.TOURMALINE, new String[] {"Granite","Diorite","Gneiss","Phyllite","Quartzite"}, /*size*/18, /*dispersion*/3, /*rarity*/400);
        
        athsCrystalHelper(AthsGlobal.ROCK_CRYSTAL, new String[] {"MM"}, /*size*/20, /*dispersion*/1, /*rarity*/120);
        athsCrystalHelper(AthsGlobal.GYPSUM, new String[] {"MM"}, /*size*/50, /*dispersion*/1, /*rarity*/140);
		
		if (config.hasChanged()) 
			config.save();
	}
	
	//this must be run in the init phase (after blocks setup but before world gen)
	public static void reloadPlants() {
		//System.out.println(Arrays.toString(AthsParser.getBiomeStringList()));
		
		//int[] p = new int[AthsParser.getBiomeStringList().length];
		//for(int i = 0; i < AthsParser.getBiomeStringList().length; i++)
		//	p[i] = TFCBiome.getBiomeByName(AthsParser.getBiomeStringList()[i]).biomeID;
		//System.out.println(Arrays.toString(p));
		
		athsPlantHelper(AthsGlobal.AFRICAN_MILK_BARREL, new int[] {0}, new String[] {"blockSoil","blockSand"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/12, /*dispersion*/6, /*rarity*/2984, /*minAltitude*/144, /*maxAltitude*/220, /*minTemp*/16f, /*maxTemp*/24f, /*minRain*/60f, /*maxRain*/160f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.AFRICAN_MILK_TREE, new int[] {0}, new String[] {"blockSoil","blockSand"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/12, /*dispersion*/5, /*rarity*/3984, /*minAltitude*/144, /*maxAltitude*/220, /*minTemp*/19f, /*maxTemp*/40f, /*minRain*/120f, /*maxRain*/660f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.ALBANIAN_SPURGE, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/8, /*dispersion*/4, /*rarity*/6984, /*minAltitude*/144, /*maxAltitude*/220, /*minTemp*/15f, /*maxTemp*/20f, /*minRain*/150f, /*maxRain*/560f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.ALGAE_MAT_CYANOBACTERIA, new int[] {0,1,2}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:SaltWaterStationary","terrafirmacraftplus:Ice"}, AthsGlobal.SHALLOW_WATER_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/64, /*dispersion*/1, /*rarity*/2968, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-20f, /*maxTemp*/40f, /*minRain*/80f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.ALGAE_MAT_GREEN, new int[] {0,1,2}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:Ice"}, AthsGlobal.SHALLOW_WATER_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/64, /*dispersion*/1, /*rarity*/968, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-20f, /*maxTemp*/40f, /*minRain*/80f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.ALGAE_MAT_RED, new int[] {0,1,2}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:SaltWaterStationary","terrafirmacraftplus:Ice"}, AthsGlobal.SHALLOW_WATER_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/64, /*dispersion*/1, /*rarity*/9068, /*minAltitude*/144, /*maxAltitude*/255, /*minTemp*/-20f, /*maxTemp*/40f, /*minRain*/40f, /*maxRain*/800f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.ALOE_VERA, new int[] {0}, new String[] {"blockSoil","blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills"}, new String[]{"Asia"},
				/*size*/3, /*dispersion*/2, /*rarity*/834, /*minAltitude*/156, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/40f, /*minRain*/45f, /*maxRain*/130f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.ANGELS_TRUMPET + "_Orange", AthsGlobal.ANGELS_TRUMPET, new int[] {0}, new String[] {"blockSoil"}, new String[]{"Foothills","Mountains Edge","Mountains","Mountain Range","Mountain Range Edge"}, new String[]{"Americas"},
				/*size*/6, /*dispersion*/15, /*rarity*/1084, /*minAltitude*/150, /*maxAltitude*/220, /*minTemp*/25f, /*maxTemp*/40f, /*minRain*/1000f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.ANGELS_TRUMPET + "_Pink", AthsGlobal.ANGELS_TRUMPET, new int[] {1}, new String[] {"blockSoil"}, new String[]{"High Hills","High Hills Edge","Foothills","Mountains Edge","Mountains","Mountain Range","Mountain Range Edge"}, new String[]{"Americas"},
				/*size*/6, /*dispersion*/15, /*rarity*/1084, /*minAltitude*/170, /*maxAltitude*/220, /*minTemp*/22f, /*maxTemp*/40f, /*minRain*/900f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.ANGELS_TRUMPET + "_White", AthsGlobal.ANGELS_TRUMPET, new int[] {2}, new String[] {"blockSoil"}, new String[]{"High Hills","High Hills Edge","High Plains","Foothills","Mountains Edge","Mountains","Mountain Range","Mountain Range Edge"}, new String[]{"Americas"},
				/*size*/6, /*dispersion*/15, /*rarity*/1084, /*minAltitude*/150, /*maxAltitude*/220, /*minTemp*/20f, /*maxTemp*/35f, /*minRain*/1000f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.ANGEL_WING_CACTUS, new int[] {0}, new String[] {"blockSoil","blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge"}, new String[]{"Americas"},
				/*size*/22, /*dispersion*/15, /*rarity*/980, /*minAltitude*/0, /*maxAltitude*/190, /*minTemp*/19f, /*maxTemp*/35f, /*minRain*/75f, /*maxRain*/210f, /*minEVT*/1f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.BIRD_OF_PARADISE, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/3, /*dispersion*/3, /*rarity*/7584, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/12f, /*maxTemp*/16f, /*minRain*/600f, /*maxRain*/3000f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.BLOOD_LILY, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/8, /*dispersion*/3, /*rarity*/4584, /*minAltitude*/144, /*maxAltitude*/220, /*minTemp*/10f, /*maxTemp*/18f, /*minRain*/250f, /*maxRain*/760f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.BLUEBELL, new int[] {0}, new String[] {"blockSoil"}, new String[]{"Plains","Rolling Hills","High Plains","High Hills","High Hills Edge","Lakeshore","Riverbank","Swamp"}, new String[]{"Europe"},
				/*size*/200, /*dispersion*/1, /*rarity*/12884, /*minAltitude*/144, /*maxAltitude*/220, /*minTemp*/-5f, /*maxTemp*/17f, /*minRain*/850f, /*maxRain*/3060f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.BONATEA, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/7, /*dispersion*/3, /*rarity*/4584, /*minAltitude*/144, /*maxAltitude*/220, /*minTemp*/7f, /*maxTemp*/17f, /*minRain*/500f, /*maxRain*/800f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.BLUE_CEREUS_CACTUS, new int[] {0,1}, new String[] {"blockSoil","blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge"}, new String[]{"Americas"},
				/*size*/5, /*dispersion*/8, /*rarity*/1200, /*minAltitude*/0, /*maxAltitude*/210, /*minTemp*/17f, /*maxTemp*/34f, /*minRain*/55f, /*maxRain*/190f, /*minEVT*/0.5f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.BOLETUS, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas", "Asia", "Europe"},
				/*size*/3, /*dispersion*/2, /*rarity*/7484, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-5f, /*maxTemp*/15f, /*minRain*/700f, /*maxRain*/1000f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.BRACKEN_FERN, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Asia","Europe"},
				/*size*/22, /*dispersion*/8, /*rarity*/2568, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-10f, /*maxTemp*/30f, /*minRain*/500f, /*maxRain*/1000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/-0.4f);
		athsPlantHelper(AthsGlobal.BRIDAL_VEIL_STINKHORN, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia","Africa"},
				/*size*/2, /*dispersion*/1, /*rarity*/10096, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/20f, /*maxTemp*/32f, /*minRain*/1000f, /*maxRain*/8000f, /*minEVT*/1f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.BURDOCK, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Europe"},
				/*size*/8, /*dispersion*/9, /*rarity*/968, /*minAltitude*/144, /*maxAltitude*/255, /*minTemp*/-5f, /*maxTemp*/18f, /*minRain*/450f, /*maxRain*/1500f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.CAMAS_FLOWER, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/25, /*dispersion*/6, /*rarity*/6384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-13f, /*maxTemp*/12f, /*minRain*/300f, /*maxRain*/750f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(AthsGlobal.CARALLUMA, new int[] {0}, new String[] {"blockSoil", "blockSand"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa", "Asia"},
				/*size*/14, /*dispersion*/5, /*rarity*/3584, /*minAltitude*/144, /*maxAltitude*/220, /*minTemp*/16f, /*maxTemp*/32f, /*minRain*/85f, /*maxRain*/300f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.CHANTERELLE, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Europe"},
				/*size*/4, /*dispersion*/1, /*rarity*/5384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-4f, /*maxTemp*/18f, /*minRain*/750f, /*maxRain*/3000f, /*minEVT*/0f, /*maxEVT*/8f);
		athsPlantHelper(AthsGlobal.CHIVES, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Europe","Asia","Americas"},
				/*size*/8, /*dispersion*/4, /*rarity*/984, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/3f, /*maxTemp*/16f, /*minRain*/600f, /*maxRain*/800f, /*minEVT*/1f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.CLOVER, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa","Americas","Europe","Asia"},
				/*size*/20, /*dispersion*/4, /*rarity*/656, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-6f, /*maxTemp*/33f, /*minRain*/650f, /*maxRain*/950f, /*minEVT*/1f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.COMMON_REEDS, new int[] {0}, new String[] {"blockSoil", "terrafirmacraftplus:FreshWaterStationary"}, new String[]{"Lake","Estuary","Lakeshore","Riverbank","Swamp","River"}, new String[]{"Africa","Europe","Asia","Americas"},
				/*size*/150, /*dispersion*/3, /*rarity*/484, /*minAltitude*/0, /*maxAltitude*/145, /*minTemp*/0f, /*maxTemp*/30f, /*minRain*/350f, /*maxRain*/2400f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/-0.2f);
		athsPlantHelper(AthsGlobal.CREEPING_CHARLIE, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/35, /*dispersion*/3, /*rarity*/1056, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-6f, /*maxTemp*/33f, /*minRain*/650f, /*maxRain*/1200f, /*minEVT*/0.5f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.CUP_PLANT, new int[] {0,1}, new String[] {"blockSoil"}, new String[]{"Plains","Rolling Hills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/7, /*dispersion*/2, /*rarity*/4384, /*minAltitude*/0, /*maxAltitude*/160, /*minTemp*/0f, /*maxTemp*/15f, /*minRain*/600f, /*maxRain*/750f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.DAFFODIL, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/7, /*dispersion*/3, /*rarity*/7384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/12f, /*maxTemp*/28f, /*minRain*/280f, /*maxRain*/1300f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.DESERT_ROSE, new int[] {0}, new String[] {"blockSoil", "blockSand"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/6, /*dispersion*/5, /*rarity*/7584, /*minAltitude*/144, /*maxAltitude*/220, /*minTemp*/17f, /*maxTemp*/40f, /*minRain*/65f, /*maxRain*/200f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.DEVILS_CLUB, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/30, /*dispersion*/4, /*rarity*/6084, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-10f, /*maxTemp*/9f, /*minRain*/1000f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.DEVILS_FINGERS, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Europe","Americas", "Asia", "Africa"},
				/*size*/4, /*dispersion*/1, /*rarity*/5384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-4f, /*maxTemp*/18f, /*minRain*/750f, /*maxRain*/3000f, /*minEVT*/0.5f, /*maxEVT*/8f);
		athsPlantHelper(AthsGlobal.DEVILS_TOUNGE, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia"},
				/*size*/2, /*dispersion*/6, /*rarity*/10096, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/20f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.DUCKWEED, new int[] {0}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:Ice"}, AthsGlobal.SHALLOW_WATER_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/64, /*dispersion*/1, /*rarity*/668, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-10f, /*maxTemp*/40f, /*minRain*/500f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.DUNE_GRASS, new int[] {0}, new String[] {"blockSand","blockGravel"}, new String[]{"Beach","Gravel Beach","Shore","Estuary"}, new String[]{"Americas"},
				/*size*/12, /*dispersion*/4, /*rarity*/256, /*minAltitude*/145, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/16f, /*minRain*/220f, /*maxRain*/1500f, /*minEVT*/0f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.EGYPTIAN_AUTUMN_CROCUS, new int[] {0}, new String[] {"blockSoil","blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Africa","Europe","Asia"},
				/*size*/6, /*dispersion*/3, /*rarity*/3884, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/17f, /*maxTemp*/26f, /*minRain*/130f, /*maxRain*/300f, /*minEVT*/1f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.ELEPHANT_GRASS, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains"}, new String[]{"Africa"},
				/*size*/200, /*dispersion*/2, /*rarity*/8384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/32f, /*minRain*/330f, /*maxRain*/700f, /*minEVT*/0f, /*maxEVT*/2f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.ELEPHANT_GRASS + "_Dense", AthsGlobal.ELEPHANT_GRASS, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains"}, new String[]{"Africa"},
				/*size*/120, /*dispersion*/3, /*rarity*/484, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/32f, /*minRain*/505f, /*maxRain*/520f, /*minEVT*/0f, /*maxEVT*/2f,/*forestGen*/-0.5f);
		athsPlantHelper(AthsGlobal.ENTOLOMA, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/5, /*dispersion*/3, /*rarity*/6096, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-5f, /*maxTemp*/15f, /*minRain*/900f, /*maxRain*/10000f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.EUROPEAN_BEDSTRAW, new int[] {0,1,2}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia", "Europe"},
				/*size*/50, /*dispersion*/3, /*rarity*/3228, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/16f, /*minRain*/750f, /*maxRain*/6000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.FIELD_HORSETAIL, new int[] {0}, new String[] {"blockSoil"}, new String[]{"Plains","Lake","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Americas", "Asia", "Europe"},
				/*size*/23, /*dispersion*/3, /*rarity*/4012, /*minAltitude*/0, /*maxAltitude*/150, /*minTemp*/-10f, /*maxTemp*/12f, /*minRain*/650f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.FIREWEED, new int[] {0}, new String[] {"blockSoil","blockStone"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Europe","Asia"},
				/*size*/60, /*dispersion*/3, /*rarity*/4384, /*minAltitude*/144, /*maxAltitude*/225, /*minTemp*/-13f, /*maxTemp*/8f, /*minRain*/550f, /*maxRain*/1300f, /*minEVT*/0.5f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.FREESIA, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/6, /*dispersion*/5, /*rarity*/5384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/30f, /*minRain*/500f, /*maxRain*/1080f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.GARLIC_MUSTARD, new int[] {0,1,2}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia", "Europe"},
				/*size*/70, /*dispersion*/3, /*rarity*/4228, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-5f, /*maxTemp*/19f, /*minRain*/750f, /*maxRain*/6000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.GIANT_HOGWEED, new int[] {0}, new String[] {"blockSoil"}, new String[]{"Mountains","High Hills","High Hills Edge","Mountains Edge","Foothills","Riverbank"}, new String[]{"Europe"},
				/*size*/13, /*dispersion*/8, /*rarity*/6384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/5f, /*maxTemp*/14f, /*minRain*/660f, /*maxRain*/930f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.GIFBOOM, new int[] {0}, new String[] {"blockSoil","blockSand"}, new String[]{"Plains","High Hills Edge","Rolling Hills","High Plains","Foothills"}, new String[]{"Africa"},
				/*size*/1, /*dispersion*/1, /*rarity*/656, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/31f, /*minRain*/78f, /*maxRain*/200f, /*minEVT*/1f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.HEATHER + "_Pink", AthsGlobal.HEATHER, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge"}, new String[]{"Europe"},
				/*size*/40, /*dispersion*/9, /*rarity*/484, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-5f, /*maxTemp*/20f, /*minRain*/160f, /*maxRain*/1000f, /*minEVT*/0f, /*maxEVT*/5f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.HEATHER + "_White", AthsGlobal.HEATHER, new int[] {1}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge"}, new String[]{"Europe"},
				/*size*/45, /*dispersion*/9, /*rarity*/384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-5f, /*maxTemp*/20f, /*minRain*/160f, /*maxRain*/1000f, /*minEVT*/0f, /*maxEVT*/5f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.HIBISCUS + "_Orange", AthsGlobal.HIBISCUS, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/7, /*dispersion*/7, /*rarity*/6969, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/26f, /*maxTemp*/30f, /*minRain*/600f, /*maxRain*/1400f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.HIBISCUS + "_Pink", AthsGlobal.HIBISCUS, new int[] {1}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia"},
				/*size*/7, /*dispersion*/7, /*rarity*/6084, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/14f, /*maxTemp*/24f, /*minRain*/600f, /*maxRain*/1400f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.HIBISCUS + "_Red", AthsGlobal.HIBISCUS, new int[] {2}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia"},
				/*size*/7, /*dispersion*/7, /*rarity*/6684, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/17f, /*maxTemp*/30f, /*minRain*/600f, /*maxRain*/1400f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.HIBISCUS + "_White", AthsGlobal.HIBISCUS, new int[] {3}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/7, /*dispersion*/7, /*rarity*/6484, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/12f, /*maxTemp*/17f, /*minRain*/600f, /*maxRain*/1400f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.HOSTA_HALCYON, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/8, /*dispersion*/4, /*rarity*/3628, /*minAltitude*/144, /*maxAltitude*/255, /*minTemp*/6f, /*maxTemp*/18f, /*minRain*/750f, /*maxRain*/4000f, /*minEVT*/0.5f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.HOSTA_PATRIOT, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/8, /*dispersion*/4, /*rarity*/3528, /*minAltitude*/144, /*maxAltitude*/255, /*minTemp*/6f, /*maxTemp*/18f, /*minRain*/750f, /*maxRain*/4000f, /*minEVT*/0.5f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.HOSTA_VULCAN, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/8, /*dispersion*/4, /*rarity*/3828, /*minAltitude*/144, /*maxAltitude*/255, /*minTemp*/6f, /*maxTemp*/18f, /*minRain*/750f, /*maxRain*/4000f, /*minEVT*/0.5f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.INDIAN_PIPE, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Americas"},
				/*size*/3, /*dispersion*/1, /*rarity*/11384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/6f, /*maxTemp*/13f, /*minRain*/780f, /*maxRain*/10000f, /*minEVT*/0.5f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.INDIGO_MILK_CAP, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/3, /*dispersion*/4, /*rarity*/512, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/24f, /*minRain*/750f, /*maxRain*/3000f, /*minEVT*/1f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.IRIS, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Mountains","Mountain Range","Mountain range Edge","Foothills","Mountains Edge","High Plains","Riverbank"}, new String[]{"Americas","Asia","Europe"},
				/*size*/6, /*dispersion*/5, /*rarity*/5384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/13f, /*minRain*/400f, /*maxRain*/680f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(AthsGlobal.JACK_IN_THE_PULPIT, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/3, /*dispersion*/4, /*rarity*/984, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/3f, /*maxTemp*/16f, /*minRain*/800f, /*maxRain*/2000f, /*minEVT*/3f, /*maxEVT*/8f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.LADY_FERN, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Asia","Europe"},
				/*size*/20, /*dispersion*/8, /*rarity*/5968, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-7f, /*maxTemp*/20f, /*minRain*/800f, /*maxRain*/4000f, /*minEVT*/1f, /*maxEVT*/8f,/*forestGen*/0.5f);
		athsPlantHelper(AthsGlobal.LAVENDER, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains"}, new String[]{"Africa","Asia","Europe"},
				/*size*/94, /*dispersion*/4, /*rarity*/8684, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/25f, /*minRain*/400f, /*maxRain*/700f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.LEAFY_LOW_UNDERGROWTH, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","River","Lake","Riverbank","Swamp"}, new String[]{"Americas", "Asia", "Africa", "Europe"},
				/*size*/18, /*dispersion*/1, /*rarity*/728, /*minAltitude*/0, /*maxAltitude*/200, /*minTemp*/-2f, /*maxTemp*/30f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/1f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.LEAFY_UNDERGROWTH, new int[] {0,1,2}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia", "Africa", "Europe"},
				/*size*/15, /*dispersion*/5, /*rarity*/128, /*minAltitude*/0, /*maxAltitude*/230, /*minTemp*/0f, /*maxTemp*/40f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.LEOPARD_ORCHID, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Africa"},
				/*size*/3, /*dispersion*/6, /*rarity*/7096, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/16f, /*maxTemp*/25f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/1f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.LILY_PAD, new int[] {0,1,2}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:Ice"}, AthsGlobal.SHALLOW_WATER_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/18, /*dispersion*/3, /*rarity*/264, /*minAltitude*/140, /*maxAltitude*/160, /*minTemp*/-20f, /*maxTemp*/40f, /*minRain*/600f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.LOBSTER_CLAWS, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/4, /*dispersion*/3, /*rarity*/3536, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/23f, /*maxTemp*/40f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/1f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.LOOSE_FLOWERED_ORCHID, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Plains","Estuary","Plains","Swamp","Salt Swamp","Riverbank","Lakeshore","Rolling Hills"}, new String[]{"Europe","Asia"},
				/*size*/5, /*dispersion*/9, /*rarity*/3556, /*minAltitude*/0, /*maxAltitude*/185, /*minTemp*/2f, /*maxTemp*/17f, /*minRain*/550f, /*maxRain*/850f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.LOTUS, new int[] {0,1,2}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:Ice"}, AthsGlobal.SHALLOW_WATER_BIOMES, new String[]{"Asia"},
				/*size*/60, /*dispersion*/4, /*rarity*/1264, /*minAltitude*/140, /*maxAltitude*/160, /*minTemp*/2f, /*maxTemp*/24f, /*minRain*/450f, /*maxRain*/6000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.LUPINE + "_Blue", AthsGlobal.LUPINE, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/35, /*dispersion*/4, /*rarity*/7384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-3f, /*maxTemp*/20f, /*minRain*/650f, /*maxRain*/850f, /*minEVT*/0.5f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.LUPINE + "_Purple", AthsGlobal.LUPINE, new int[] {1}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/35, /*dispersion*/4, /*rarity*/7384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-8f, /*maxTemp*/14f, /*minRain*/650f, /*maxRain*/850f, /*minEVT*/0.5f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.LUPINE + "_Red", AthsGlobal.LUPINE, new int[] {2}, new String[] {"blockSoil"}, new String[]{"High Hills","High Hills Edge","Foothills","Mountains","Mountain Range","Mountain Range Edge","Mountains Edge"}, new String[]{"Americas"},
				/*size*/35, /*dispersion*/4, /*rarity*/7384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/7f, /*maxTemp*/16f, /*minRain*/650f, /*maxRain*/850f, /*minEVT*/0.5f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.LUPINE + "_Yellow", AthsGlobal.LUPINE, new int[] {3}, new String[] {"blockSoil"}, new String[]{"High Hills","High Hills Edge","Foothills","Mountains","Mountain Range","Mountain Range Edge","Mountains Edge"}, new String[]{"Americas"},
				/*size*/35, /*dispersion*/4, /*rarity*/7384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/10f, /*maxTemp*/15f, /*minRain*/650f, /*maxRain*/850f, /*minEVT*/0.5f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.MALLOW, new int[] {0}, new String[] {"blockSoil"}, new String[]{"Lake","Estuary","Lakeshore","Riverbank","Swamp","River"}, new String[]{"Africa","Europe","Asia","Africa"},
				/*size*/70, /*dispersion*/4, /*rarity*/784, /*minAltitude*/0, /*maxAltitude*/148, /*minTemp*/5f, /*maxTemp*/30f, /*minRain*/450f, /*maxRain*/2400f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.MARIGOLD, new int[] {0}, new String[] {"blockSoil","blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/10, /*dispersion*/6, /*rarity*/1284, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/13f, /*maxTemp*/28f, /*minRain*/140f, /*maxRain*/360f, /*minEVT*/1f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.MEDIUM_UNDERGROWTH, new int[] {0,1,2}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp","Salt Swamp"}, new String[]{"Americas", "Asia", "Africa", "Europe"},
				/*size*/8, /*dispersion*/4, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-6f, /*maxTemp*/40f, /*minRain*/350f, /*maxRain*/1000f, /*minEVT*/0f, /*maxEVT*/2f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.MOREL, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas", "Asia", "Europe","Africa"},
				/*size*/2, /*dispersion*/2, /*rarity*/9096, /*minAltitude*/0, /*maxAltitude*/220, /*minTemp*/-2f, /*maxTemp*/14f, /*minRain*/700f, /*maxRain*/4000f, /*minEVT*/0.5f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.NARBON_VETCH, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe","Asia"},
				/*size*/34, /*dispersion*/3, /*rarity*/5384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/6f, /*maxTemp*/22f, /*minRain*/450f, /*maxRain*/900f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.OCOTILLO, new int[] {0}, new String[] {"blockSoil","blockSand","blockGravel"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge","Mountains","Mountain Range Edge"}, new String[]{"Americas"},
				/*size*/10, /*dispersion*/17, /*rarity*/3050, /*minAltitude*/0, /*maxAltitude*/220, /*minTemp*/13f, /*maxTemp*/26f, /*minRain*/85f, /*maxRain*/230f, /*minEVT*/1f, /*maxEVT*/5.5f);
		athsPlantHelper(AthsGlobal.ORGAN_PIPE_CACTUS, new int[] {0}, new String[] {"blockSoil","blockSand","blockGravel"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge","Mountains","Mountain Range Edge"}, new String[]{"Americas"},
				/*size*/8, /*dispersion*/24, /*rarity*/880, /*minAltitude*/0, /*maxAltitude*/200, /*minTemp*/16f, /*maxTemp*/30f, /*minRain*/100f, /*maxRain*/190f, /*minEVT*/0f, /*maxEVT*/3.5f);
		athsPlantHelper(AthsGlobal.OSTRICH_FERN, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia","Europe"},
				/*size*/25, /*dispersion*/6, /*rarity*/4868, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-5f, /*maxTemp*/20f, /*minRain*/800f, /*maxRain*/8000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/0.3f);
		athsPlantHelper(AthsGlobal.PAINTED_FERN, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/16, /*dispersion*/4, /*rarity*/6468, /*minAltitude*/144, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/18f, /*minRain*/800f, /*maxRain*/6000f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/0.5f);
		athsPlantHelper(AthsGlobal.PAPYRUS, new int[] {0}, new String[] {"blockSoil", "terrafirmacraftplus:FreshWaterStationary"}, new String[]{"Lake","Estuary","Lakeshore","Riverbank","River"}, new String[]{"Africa"},
				/*size*/150, /*dispersion*/3, /*rarity*/524, /*minAltitude*/0, /*maxAltitude*/145, /*minTemp*/17f, /*maxTemp*/40f, /*minRain*/80f, /*maxRain*/3400f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.PAPYRUS + "_Swamp", AthsGlobal.PAPYRUS, new int[] {0}, new String[] {"blockSoil", "terrafirmacraftplus:FreshWaterStationary"}, new String[]{"Swamp"}, new String[]{"Africa"},
				/*size*/80, /*dispersion*/4, /*rarity*/1024, /*minAltitude*/0, /*maxAltitude*/145, /*minTemp*/17f, /*maxTemp*/40f, /*minRain*/100f, /*maxRain*/3400f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.PERIWINKLE, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","River","Lake","Riverbank","Swamp"}, new String[]{"Europe"},
				/*size*/18, /*dispersion*/1, /*rarity*/3028, /*minAltitude*/0, /*maxAltitude*/200, /*minTemp*/8f, /*maxTemp*/18f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/1f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.POKEWEED, new int[] {0,1}, new String[] {"blockSoil"}, new String[]{"Plains","Rolling Hills","High Plains","High Hills","High Hills Edge","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/14, /*dispersion*/3, /*rarity*/3084, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/14f, /*minRain*/700f, /*maxRain*/3000f, /*minEVT*/0.5f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.POND_GRASS, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Mountains","Mountain Range Edge","Mountain Range","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Americas", "Asia"},
				/*size*/20, /*dispersion*/2, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/146, /*minTemp*/-15f, /*maxTemp*/40f, /*minRain*/250f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.PRAIRIE_GRASS, new int[] {0,1,2}, new String[] {"blockSoil"}, new String[]{"Plains","High Hills","Mountains","Rolling Hills","High Hills Edge","Foothills","Mountain Range Edge","Mountain Range","High Plains"}, new String[]{"Americas", "Asia", "Africa", "Europe"},
				/*size*/100, /*dispersion*/4, /*rarity*/4128, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/18f, /*minRain*/200f, /*maxRain*/750f, /*minEVT*/0f, /*maxEVT*/2f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.PRAIRIE_GRASS + "_DensePrairie", AthsGlobal.PRAIRIE_GRASS, new int[] {0,1,2}, new String[] {"blockSoil"}, new String[]{"Plains","High Hills","Mountains","Rolling Hills","High Hills Edge","Foothills","Mountain Range Edge","Mountain Range","High Plains"}, new String[]{"Americas", "Asia", "Africa", "Europe"},
				/*size*/120, /*dispersion*/6, /*rarity*/530, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/18f, /*minRain*/250f, /*maxRain*/420f, /*minEVT*/0f, /*maxEVT*/2f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.PRICKLY_PEAR, new int[] {0}, new String[] {"blockSoil","blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge"}, new String[]{"Americas"},
				/*size*/12, /*dispersion*/10, /*rarity*/980, /*minAltitude*/0, /*maxAltitude*/190, /*minTemp*/13f, /*maxTemp*/25f, /*minRain*/70f, /*maxRain*/200f, /*minEVT*/0f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.QUAQUA, new int[] {0}, new String[] {"blockSoil","blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge"}, new String[]{"Africa"},
				/*size*/10, /*dispersion*/7, /*rarity*/1580, /*minAltitude*/0, /*maxAltitude*/190, /*minTemp*/14f, /*maxTemp*/29f, /*minRain*/70f, /*maxRain*/320f, /*minEVT*/0f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.RAFFLESIA, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Asia"},
				/*size*/3, /*dispersion*/6, /*rarity*/10096, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/16f, /*maxTemp*/25f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/1f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.ROSEBUSH, new int[] {0}, new String[] {"blockSoil"}, new String[]{"Plains","Rolling Hills","Lakeshore","Riverbank","Swamp","High Hills","High Hills Edge"}, new String[]{"Africa","Americas","Asia","Europe"},
				/*size*/8, /*dispersion*/3, /*rarity*/4384, /*minAltitude*/0, /*maxAltitude*/180, /*minTemp*/-5f, /*maxTemp*/22f, /*minRain*/600f, /*maxRain*/900f, /*minEVT*/0.5f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.ROUGH_HORSETAIL, new int[] {0}, new String[] {"blockSoil"}, new String[]{"Plains","Lake","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Americas", "Asia", "Europe"},
				/*size*/35, /*dispersion*/2, /*rarity*/5512, /*minAltitude*/0, /*maxAltitude*/150, /*minTemp*/-10f, /*maxTemp*/12f, /*minRain*/600f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.ROYAL_JASMINE, new int[] {0,1,2}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/50, /*dispersion*/3, /*rarity*/4028, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/13f, /*maxTemp*/30f, /*minRain*/580f, /*maxRain*/8000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.SAGEBRUSH, new int[] {0,1,2}, new String[] {"blockSoil","blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge","Mountains","Mountain Range","Mountain Range Edge"}, new String[]{"Americas"},
				/*size*/100, /*dispersion*/8, /*rarity*/450, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/25f, /*minRain*/135f, /*maxRain*/200f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.SAGEBRUSH + "_Transition", AthsGlobal.SAGEBRUSH, new int[] {0,1,2}, new String[] {"blockSoil","blockSand"}, new String[]{"High Hills","High Hills Edge","High Plains","Foothills","Mountains Edge","Mountains","Mountain Range","Mountain Range Edge"}, new String[]{"Americas"},
				/*size*/20, /*dispersion*/20, /*rarity*/750, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/25f, /*minRain*/100f, /*maxRain*/135f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.SAGEBRUSH + "_Grassland", AthsGlobal.SAGEBRUSH, new int[] {0,1,2}, new String[] {"blockSoil","blockSand"}, new String[]{"High Hills","High Hills Edge","High Plains","Foothills","Mountains Edge","Mountains","Mountain Range","Mountain Range Edge"}, new String[]{"Americas"},
				/*size*/30, /*dispersion*/14, /*rarity*/6000, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/25f, /*minRain*/200f, /*maxRain*/300f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.SAGEBRUSH + "_Riverbank", AthsGlobal.SAGEBRUSH, new int[] {0,1,2}, new String[] {"blockSoil","blockSand"}, new String[]{"Riverbank"}, new String[]{"Americas"},
				/*size*/20, /*dispersion*/20, /*rarity*/750, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/25f, /*minRain*/25f, /*maxRain*/135f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/0.5f);
		athsPlantHelper(AthsGlobal.SAGUARO, new int[] {0,1,2,3,4}, new String[] {"blockSoil","blockSand"}, new String[]{"Plains","High Hills Edge","Rolling Hills","High Plains","Foothills"}, new String[]{"Americas"},
				/*size*/1, /*dispersion*/1, /*rarity*/456, /*minAltitude*/0, /*maxAltitude*/190, /*minTemp*/17f, /*maxTemp*/31f, /*minRain*/100f, /*maxRain*/200f, /*minEVT*/1f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.SAXAUL, new int[] {0,1,2,3}, new String[] {"blockSoil","blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge","Mountains","Mountain Range","Mountain Range Edge"}, new String[]{"Asia"},
				/*size*/30, /*dispersion*/17, /*rarity*/2600, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/30f, /*minRain*/110f, /*maxRain*/200f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.SAXAUL + "_ExtremeDesert", AthsGlobal.SAXAUL, new int[] {0,1,2,3}, new String[] {"blockSoil","blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge","Mountains","Mountain Range","Mountain Range Edge"}, new String[]{"Asia"},
				/*size*/15, /*dispersion*/24, /*rarity*/3900, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/30f, /*minRain*/60f, /*maxRain*/90f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.SAXAUL + "_Riverbank", AthsGlobal.SAXAUL, new int[] {0,1,2}, new String[] {"blockSoil","blockSand"}, new String[]{"Riverbank"}, new String[]{"Asia"},
				/*size*/12, /*dispersion*/10, /*rarity*/2050, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/30f, /*minRain*/25f, /*maxRain*/135f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/0.5f);
		athsPlantHelper(AthsGlobal.SALTWORT, new int[] {0,1}, new String[] {"blockSoil","blockSand"}, new String[]{"Salt Swamp","Estuary"}, new String[]{"Africa","Asia","Europe","Americas"},
				/*size*/12, /*dispersion*/5, /*rarity*/550, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/30f, /*minRain*/100f, /*maxRain*/3000f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.SALTWORT + "_AfricaDesert", AthsGlobal.SALTWORT, new int[] {0,1}, new String[] {"blockSoil","blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge","Mountains","Mountain Range","Mountain Range Edge"}, new String[]{"Africa"},
				/*size*/40, /*dispersion*/9, /*rarity*/800, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/30f, /*minRain*/100f, /*maxRain*/200f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.SALTWORT + "_Riverbank", AthsGlobal.SALTWORT, new int[] {0,1}, new String[] {"blockSoil","blockSand"}, new String[]{"Riverbank"}, new String[]{"Americas"},
				/*size*/15, /*dispersion*/10, /*rarity*/1550, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/30f, /*minRain*/25f, /*maxRain*/135f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/0.5f);
		athsPlantHelper(AthsGlobal.SALTWORT + "_AfricaExtremeDesert", AthsGlobal.SALTWORT, new int[] {0,1}, new String[] {"blockSoil","blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge","Mountains","Mountain Range","Mountain Range Edge"}, new String[]{"Africa"},
				/*size*/35, /*dispersion*/12, /*rarity*/950, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/30f, /*minRain*/70f, /*maxRain*/100f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.SCALY_TREE_FERN, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia"},
				/*size*/10, /*dispersion*/30, /*rarity*/8084, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/10f, /*maxTemp*/30f, /*minRain*/1000f, /*maxRain*/16000f, /*minEVT*/2f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.SENSITIVE_FERN, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia","Americas"},
				/*size*/17, /*dispersion*/5, /*rarity*/6084, /*minAltitude*/0, /*maxAltitude*/200, /*minTemp*/0f, /*maxTemp*/14f, /*minRain*/750f, /*maxRain*/10000f, /*minEVT*/0f, /*maxEVT*/5f,/*forestGen*/0.5f);
		athsPlantHelper(AthsGlobal.SESAME, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa", "Asia"},
				/*size*/95, /*dispersion*/6, /*rarity*/4556, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/30f, /*minRain*/250f, /*maxRain*/750f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.SHITAKE, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/4, /*dispersion*/2, /*rarity*/4096, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/12f, /*maxTemp*/24f, /*minRain*/800f, /*maxRain*/2000f, /*minEVT*/0.5f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.SNAKE_SANSEVERIA, new int[] {0}, new String[] {"blockSoil","blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge"}, new String[]{"Africa","Asia"},
				/*size*/60, /*dispersion*/5, /*rarity*/6980, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/22f, /*maxTemp*/40f, /*minRain*/170f, /*maxRain*/2440f, /*minEVT*/0f, /*maxEVT*/5f/*forestGen*/-0.2f);
		athsPlantHelper(AthsGlobal.SORBUS, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Mountains","Mountains Edge","Mountain Range Edge","Mountain Range","High Hills Edge","High Plains","Foothills"}, new String[]{"Africa","Europe","Asia"},
				/*size*/1, /*dispersion*/1, /*rarity*/884, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-4f, /*maxTemp*/16f, /*minRain*/550f, /*maxRain*/1000f, /*minEVT*/0f, /*maxEVT*/8f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.STARFISH_PLANT, new int[] {0}, new String[] {"blockSoil","blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge"}, new String[]{"Africa"},
				/*size*/6, /*dispersion*/5, /*rarity*/3980, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/20f, /*minRain*/100f, /*maxRain*/240f, /*minEVT*/0f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.SUMAC, new int[] {0,1}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/15, /*dispersion*/6, /*rarity*/4668, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/4f, /*maxTemp*/18f, /*minRain*/300f, /*maxRain*/1200f, /*minEVT*/0.25f, /*maxEVT*/2f);
		athsPlantHelper(AthsGlobal.SUNDEW, new int[] {0}, new String[] {"blockSoil"}, new String[]{"Mountains","Mountain Range", "Swamp","Salt Swamp"}, new String[]{"Americas","Africa"},
				/*size*/3, /*dispersion*/1, /*rarity*/14384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/15f, /*minRain*/400f, /*maxRain*/750f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.SUNFLOWER, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/28, /*dispersion*/5, /*rarity*/6384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/22f, /*minRain*/400f, /*maxRain*/750f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.SWORD_FERN, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Swamp","Mountains","Mountains Edge","Mountain Range Edge","Mountain Range"}, new String[]{"Americas"},
				/*size*/25, /*dispersion*/6, /*rarity*/1684, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-10f, /*maxTemp*/18f, /*minRain*/1000f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.SWORD_SANSEVERIA, new int[] {0}, new String[] {"blockSoil","blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge"}, new String[]{"Africa","Asia"},
				/*size*/80, /*dispersion*/5, /*rarity*/6980, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/19f, /*maxTemp*/36f, /*minRain*/55f, /*maxRain*/440f, /*minEVT*/0f, /*maxEVT*/5f/*forestGen*/-0.5f);
		athsPlantHelper(AthsGlobal.THISTLE, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/7, /*dispersion*/11, /*rarity*/556, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-2f, /*maxTemp*/15f, /*minRain*/350f, /*maxRain*/750f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.TITAN_ARUM, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Asia"},
				/*size*/2, /*dispersion*/7, /*rarity*/17192, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/20f, /*maxTemp*/28f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/1f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.VENUS_FLYTRAP, new int[] {0}, new String[] {"blockSoil"}, new String[]{"Lakeshore","Riverbank","Swamp","Salt Swamp"}, new String[]{"Americas"},
				/*size*/2, /*dispersion*/1, /*rarity*/4384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/10f, /*maxTemp*/16f, /*minRain*/700f, /*maxRain*/1000f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.VICTORIA_LILY_PAD, new int[] {0}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:Ice"}, new String[] {"Riverbank","Lakeshore"}, new String[]{"Americas"},
				/*size*/30, /*dispersion*/4, /*rarity*/4968, /*minAltitude*/140, /*maxAltitude*/160, /*minTemp*/20f, /*maxTemp*/40f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.VOODOO_LILY, new int[] {0}, new String[] {"blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Asia"},
				/*size*/3, /*dispersion*/6, /*rarity*/10096, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/16f, /*maxTemp*/25f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/1f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.WATER_HYACINTH, new int[] {0}, new String[] {"terrafirmacraftplus:FreshWaterStationary"}, new String[]{"Plains","Lakeshore","River","Riverbank"}, new String[]{"Americas"},
				/*size*/80, /*dispersion*/3, /*rarity*/1084, /*minAltitude*/0, /*maxAltitude*/170, /*minTemp*/18f, /*maxTemp*/35f, /*minRain*/650f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.WATER_PLANTAIN, new int[] {0}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:Ice"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/9, /*dispersion*/3, /*rarity*/204, /*minAltitude*/140, /*maxAltitude*/160, /*minTemp*/-20f, /*maxTemp*/40f, /*minRain*/600f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.WATER_PLANTAIN + "_River", AthsGlobal.WATER_PLANTAIN, new int[] {0}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:Ice"}, new String[]{"Riverbank","River","Lakeshore"}, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/9, /*dispersion*/3, /*rarity*/104, /*minAltitude*/140, /*maxAltitude*/160, /*minTemp*/-20f, /*maxTemp*/40f, /*minRain*/600f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.WELWITSCHIA, new int[] {0,1}, new String[] {"blockSand"}, new String[]{"Beach","Gravel Beach","Lake","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary","Plains","High Plains"}, new String[]{"Africa"},
				/*size*/1, /*dispersion*/1, /*rarity*/1024, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/16f, /*maxTemp*/40f, /*minRain*/0f, /*maxRain*/45f, /*minEVT*/1f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.WOOD_BITTER_VETCH, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/34, /*dispersion*/3, /*rarity*/4984, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-2f, /*maxTemp*/20f, /*minRain*/450f, /*maxRain*/900f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.WOOD_FERN, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/20, /*dispersion*/8, /*rarity*/4068, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-5f, /*maxTemp*/15f, /*minRain*/750f, /*maxRain*/6000f, /*minEVT*/0.5f, /*maxEVT*/6f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.YARROW, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Europe","Americas"},
				/*size*/17, /*dispersion*/5, /*rarity*/6384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-3f, /*maxTemp*/12f, /*minRain*/450f, /*maxRain*/700f, /*minEVT*/0.5f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.YUCCA, new int[] {0}, new String[] {"blockSoil","blockSand","blockGravel"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/20, /*dispersion*/12, /*rarity*/3284, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/10f, /*maxTemp*/38f, /*minRain*/105f, /*maxRain*/680f, /*minEVT*/0.5f, /*maxEVT*/4f,/*forestGen*/-1.0f);
		
		athsTreeHelper(AthsGlobal.YOUNG_ACACIA, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.UTACACIA);
		athsTreeHelper(AthsGlobal.YOUNG_ACACIA + "_America", AthsGlobal.YOUNG_ACACIA, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
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
		athsTreeHelper(AthsGlobal.YOUNG_YEW + "_Africa", AthsGlobal.YOUNG_YEW, new int[] {0}, new String[] {"blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.AFRICANYEW);
		
		AthsWorldGenPlants.plantList.put("shrub", getPlantData("shrub", Reference.MOD_ID+":"+TFCBlocks.shrub.getUnlocalizedName().substring(5), new int[] {0}, new String[] {"blockSoil","blockSand"}, new String[]{"Riverbank"}, new String[]{"Americas","Europe","Africa","Asia"},
		        /*size*/15, /*dispersion*/10, /*rarity*/1550, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/30f, /*minRain*/25f, /*maxRain*/135f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/1.0f));
		
		for(int i = 0; i < numCustomGenerators; i++) {
			String name = "plant_" + i;
			AthsWorldGenPlants.plantList.put(name, getPlantData(name, Reference.MOD_ID+":"+TFCBlocks.undergrowth.getUnlocalizedName().substring(5), new int[] {0}, new String[] {"blockSoil"}, new String[] {"All", "!Hell"}, new String[]{"Americas", "Asia"}, 
					/*size*/3, /*dispersion*/1, /*rarity*/424, /*minAltitude*/0, /*maxAltitude*/180, /*minTemp*/0f, /*maxTemp*/40f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f));
		}
		
		if (config.hasChanged()) 
			config.save();
	}
	
	private static PlantSpawnData getPlantData(String category, String blockName, int[] metas, String[] growOnBlocks, String[] biomes, String[] regions, int size, int dispersion,
			int rarity, int minAltitude, int maxAltitude, float minTemp, float maxTemp, float minRainfall, float maxRainfall, float minEVT, float maxEVT, float forestGen){
		return new PlantSpawnData(
			config.get(category, "blockName", blockName).getString(),
			config.get(category, "metas", metas).getIntList(),
			config.get(category, "growOnBlocks", growOnBlocks).getStringList(),
			config.get(category, "biomes", biomes).setValidValues(AthsGlobal.ALLOWED_BIOMES).getStringList(),
			config.get(category, "regions", regions).getStringList(),
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
			(float)config.get(category, "forestGen", forestGen).getDouble());
	}
	
	private static CrystalSpawnData getCrystalData(String category, String blockName, String blockName2, String[] growOnBlocks, int size, int dispersion, int rarity) {
		return new CrystalSpawnData(
			config.get(category, "blockName", blockName).getString(),
			config.get(category, "blockName2", blockName2).getString(),
			config.get(category, "growOnBlocks", growOnBlocks).setValidValues(AthsGlobal.ALLOWED_ROCKS).getStringList(),
			config.get(category, "size", size).setMinValue(1).getInt(),
			config.get(category, "dispersion", dispersion).setMinValue(1).getInt(),
			config.get(category, "rarity", rarity).setMinValue(1).getInt());
	}
	
	public static void athsCrystalHelper(String name, String[] growOnBlocks, int size, int dispersion, int rarity) {
		AthsWorldGenCrystals.crystalList.put(name, getCrystalData("~" + name, AthsMod.MODID+":"+name, "", growOnBlocks, size, dispersion, rarity));
	}
	
	public static void athsCrystalClusterHelper(String name, String[] growOnBlocks, int size, int dispersion, int rarity) {
		AthsWorldGenCrystals.crystalList.put(name, getCrystalData("~" + name, AthsMod.MODID+":"+name, AthsMod.MODID+":"+name+"_Cluster", growOnBlocks, size, dispersion, rarity));
	}
	
	public static void athsPlantHelper(String name, int[] metas, String[] growOnBlocks, String[] biomes, String[] regions, int size, int dispersion,
			int rarity, int minAltitude, int maxAltitude, float minTemp, float maxTemp, float minRainfall, float maxRainfall, float minEVT, float maxEVT) {
		AthsWorldGenPlants.plantList.put(name, getPlantData(name, AthsMod.MODID+":"+name, metas, growOnBlocks, biomes, regions, size, dispersion,
				rarity, minAltitude, maxAltitude, minTemp, maxTemp, minRainfall, maxRainfall, minEVT, maxEVT, 0.0f));
	}
	//category version
	public static void athsPlantHelper(String category, String name, int[] metas, String[] growOnBlocks, String[] biomes, String[] regions, int size, int dispersion,
			int rarity, int minAltitude, int maxAltitude, float minTemp, float maxTemp, float minRainfall, float maxRainfall, float minEVT, float maxEVT) {
		AthsWorldGenPlants.plantList.put(name, getPlantData(category, AthsMod.MODID+":"+name, metas, growOnBlocks, biomes, regions, size, dispersion,
				rarity, minAltitude, maxAltitude, minTemp, maxTemp, minRainfall, maxRainfall, minEVT, maxEVT, 0.0f));
	}
	
	/* forestGen version*/
	public static void athsPlantHelper(String name, int[] metas, String[] growOnBlocks, String[] biomes, String[] regions, int size, int dispersion,
			int rarity, int minAltitude, int maxAltitude, float minTemp, float maxTemp, float minRainfall, float maxRainfall, float minEVT, float maxEVT, float forestGen) {
		AthsWorldGenPlants.plantList.put(name, getPlantData(name, AthsMod.MODID+":"+name, metas, growOnBlocks, biomes, regions, size, dispersion,
				rarity, minAltitude, maxAltitude, minTemp, maxTemp, minRainfall, maxRainfall, minEVT, maxEVT, forestGen));
	}
	
	/* forestGen category version*/
	public static void athsPlantHelper(String category, String name, int[] metas, String[] growOnBlocks, String[] biomes, String[] regions, int size, int dispersion,
			int rarity, int minAltitude, int maxAltitude, float minTemp, float maxTemp, float minRainfall, float maxRainfall, float minEVT, float maxEVT, float forestGen) {
		AthsWorldGenPlants.plantList.put(name, getPlantData(category, AthsMod.MODID+":"+name, metas, growOnBlocks, biomes, regions, size, dispersion,
				rarity, minAltitude, maxAltitude, minTemp, maxTemp, minRainfall, maxRainfall, minEVT, maxEVT, forestGen));
	}
	
	public static void athsTreeHelper(String name, int[] metas, String[] growOnBlocks, String[] biomes, String[] regions, int size, int dispersion,
			int minAltitude, int maxAltitude, EnumTree tree) {
		AthsWorldGenPlants.plantList.put(name, getPlantData(name, AthsMod.MODID+":"+name, metas, growOnBlocks, biomes, regions, size, dispersion,
				(int)(AthsGlobal.TREE_BASE_RARITY / tree.rarity), minAltitude, maxAltitude, tree.minTemp, tree.maxTemp, tree.minRain, tree.maxRain, tree.minEVT, tree.maxEVT, 1.0f));
	}
	
	/* alternate spawn parameters for same tree*/
	public static void athsTreeHelper(String category, String name, int[] metas, String[] growOnBlocks, String[] biomes, String[] regions, int size, int dispersion,
			int minAltitude, int maxAltitude, EnumTree tree) {
		AthsWorldGenPlants.plantList.put(name, getPlantData(category, AthsMod.MODID+":"+name, metas, growOnBlocks, biomes, regions, size, dispersion,
				(int)(AthsGlobal.TREE_BASE_RARITY / tree.rarity), minAltitude, maxAltitude, tree.minTemp, tree.maxTemp, tree.minRain, tree.maxRain, tree.minEVT, tree.maxEVT, 1.0f));
	}
}
