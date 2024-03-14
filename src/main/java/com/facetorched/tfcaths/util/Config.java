package com.facetorched.tfcaths.util;

import java.io.File;

import com.dunk.tfc.Reference;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.Enums.EnumTree;
import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.AthsGlobal;
import com.facetorched.tfcaths.AthsMod;
import com.facetorched.tfcaths.WorldGen.Generators.AthsWorldGenCrystals;
import com.facetorched.tfcaths.WorldGen.Generators.AthsWorldGenPlants;
import com.facetorched.tfcaths.WorldGen.Generators.CrystalSpawnData;
import com.facetorched.tfcaths.WorldGen.Generators.PlantSpawnData;
import com.facetorched.tfcaths.blocks.BlockCrystal;
import com.facetorched.tfcaths.blocks.BlockPlantCactus;
import com.facetorched.tfcaths.blocks.BlockPlantEpiphyte3d;
import com.facetorched.tfcaths.interfaces.ILilyPad;
import com.facetorched.tfcaths.interfaces.ITree;

import cpw.mods.fml.common.Loader;
import net.minecraft.init.Items;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;

public class Config {
	//configuration object
	public static Configuration config;
	
	//define configuration fields here
	public static int numCustomGenerators;
	//public static double[] cullSurfaceWeights;
	//public static Block [] cullSurfaceBlocks;
	public static float cullShrubs;
	
	public static boolean mushroomRecipes;
	public static boolean propagationRecipes;
	public static boolean miscRecipes;
	
	public static float rarityTree;
	public static float rarityLilyPad;
	public static float rarityEpiphyte;
	public static float rarityCactus;
	public static float rarityOther;
	
	public static boolean rockCrystalNetherQuartz;
	
	public static void preInit(File configDir){
		if (config != null) throw new IllegalStateException("Preinit can't be called twice.");
		config = new Configuration(new File(configDir,"TFCPlusAesthetics.cfg"));
	}
	
	public static void reload(){
		if (config == null) throw new IllegalStateException("Config reload attempt before preinit.");
		AthsLogger.info("Loading TFC+ Aesthetics Config");
		config.load();
		// set configs here
		String[] soilBlocks = AthsParser.prefix(new String[] {"Dirt", "Dirt2", "Grass", "Grass2", "DryGrass", "DryGrass2", "Clay", "Clay2", "ClayGrass", "ClayGrass2", "tilledSoil", "tilledSoil2"}, Reference.MOD_ID + ":");
		soilBlocks = config.get("_soil_ore_dict", "blockSoil", soilBlocks, "blocks to add to the ore dictionary 'blockSoil'. Leave empty to disable").getStringList();
		
		for(String soil : soilBlocks) {
			OreDictionary.registerOre("blockSoil", AthsParser.getBlockFromName(soil));
		}
		
		numCustomGenerators = config.getInt("numCustomGenerators", "_num_custom_generators", 1, 0, Integer.MAX_VALUE, "The number of custom plant generators to read from. The names of these generators are enumerated as \"_z[n]\"");
		
		cullShrubs = config.getFloat("cullShrubs", "_cull_shrubs", 0.0f, 0.0f, 1.0f, "The degree to which TFC+ shrubs should be culled from the world. Set to 0 to disable the culling.");
		/*
		String[] cullSurfaceBlockNames = config.getStringList("_cull_surface_blocks", "cullSurfaceBlocks", new String[] {"terrafirmacraftplus:shrub"}, "blocks to be culled from the surface during world generation");
		cullSurfaceBlocks = AthsParser.getBlockFromName(cullSurfaceBlockNames);
		cullSurfaceWeights = config.get("cullSurfaceWeights", "_cull_surface_weights", new double[] {0.2}, "The weights associated with each cullSurfaceBlock. The higher the weight the more the block will be culled", 0.0, 1.0).getDoubleList();
		
		if(cullSurfaceBlocks.length != cullSurfaceWeights.length) {
			throw new java.lang.IllegalArgumentException("cullSurfaceBlocks must have the same length as cullSurfaceWeights!");
		}
		*/
		
		mushroomRecipes = config.getBoolean("mushroomRecipes", "_mushroom_recipes", true, "Set to false to prevent fungi from being craftable into mushroom food items");
		propagationRecipes = config.getBoolean("propagationRecipes", "_propagation_recipes", true, "Set to false to prevent plants from being growable in a barrel");
		miscRecipes = config.getBoolean("miscRecipes", "_misc_recipes", true, "Set to false to prevent addition of various TFC+ styled plant-based recipes");
		
		rarityTree = config.getFloat("rarityTree", "_rarity_tree", 1f, 0f, 10000f, "The multiplier applied to tree-like plant rarity. Set to 0 to disable these from spawning entirely");
		rarityLilyPad = config.getFloat("rarityLilyPad", "_rarity_lily_pad", 1f, 0f, 10000f, "The multiplier applied to lilypad-like plant rarity (including algae). Set to 0 to disable these from spawning entirely");
		rarityEpiphyte = config.getFloat("rarityEpiphyte", "_rarity_epiphyte", 1f, 0f, 10000f, "The multiplier applied to epiphyte plant rarity. Set to 0 to disable these from spawning entirely");
		rarityCactus = config.getFloat("rarityCactus", "_rarity_cactus", 1f, 0f, 10000f, "The multiplier applied to cactus rarity. Set to 0 to disable these from spawning entirely");
		rarityOther = config.getFloat("rarityOther", "_rarity_other", 1f, 0f, 10000f, "The multiplier applied to non-categorized plants. Set to 0 to disable these from spawning entirely");
		
		rockCrystalNetherQuartz = config.getBoolean("rockCrystalNetherQuartz", "_rock_crystal_nether_quartz", true, "Set to false to prevent rock crystal from dropping nether quartz. Will drop quartzite rocks only.");
		if (rockCrystalNetherQuartz)
			((BlockCrystal)(AthsBlockSetup.rockCrystalCluster)).setItem(Items.quartz, 0);
		
		if (config.hasChanged()) config.save();
	}
	
	public static void reloadCrystals() {
		athsCrystalHelper(AthsGlobal.AGATE, new String[] {"Sed","Diorite","Granite","Rhyolite","Andesite"}, /*size*/1, /*dispersion*/1, /*rarity*/70);
        athsCrystalClusterHelper(AthsGlobal.AMETHYST, new String[] {"IgEx","Limestone","Granite"}, /*size*/20, /*dispersion*/1, /*rarity*/120);
        athsCrystalClusterHelper(AthsGlobal.BERYL, new String[] {"Granite","Rhyolite","Gneiss"}, /*size*/18, /*dispersion*/3, /*rarity*/300);
        athsCrystalClusterHelper(AthsGlobal.DIAMOND, new String[] {"Gabbro"}, /*size*/35, /*dispersion*/2, /*rarity*/280);
        athsCrystalClusterHelper(AthsGlobal.EMERALD, new String[] {"Granite","Rhyolite","Gneiss"}, /*size*/18, /*dispersion*/3, /*rarity*/350);
        athsCrystalClusterHelper(AthsGlobal.GARNET, new String[] {"Granite","MM","Sandstone","Shale","Claystone"}, /*size*/15, /*dispersion*/5, /*rarity*/300);
        athsCrystalHelper(AthsGlobal.JADE, new String[] {"MM"}, /*size*/1, /*dispersion*/1, /*rarity*/280);
        athsCrystalHelper(AthsGlobal.JASPER, new String[] {"Chert","Limestone","Dolomite","Schist","Gneiss","Phyllite","Slate"}, /*size*/1, /*dispersion*/1, /*rarity*/70);
        athsCrystalHelper(AthsGlobal.OPAL, new String[] {"All"}, /*size*/1, /*dispersion*/1, /*rarity*/480);
        athsCrystalClusterHelper(AthsGlobal.RUBY, new String[] {"IgIn","MM"}, /*size*/20, /*dispersion*/6, /*rarity*/350);
        athsCrystalClusterHelper(AthsGlobal.SAPPHIRE, new String[] {"IgIn","MM"}, /*size*/20, /*dispersion*/6, /*rarity*/350);
        athsCrystalClusterHelper(AthsGlobal.TOPAZ, new String[] {"Granite","Rhyolite"}, /*size*/18, /*dispersion*/3, /*rarity*/280);
        athsCrystalClusterHelper(AthsGlobal.TOURMALINE, new String[] {"Granite","Diorite","Gneiss","Phyllite","Quartzite"}, /*size*/18, /*dispersion*/3, /*rarity*/280);
        
        if (Loader.isModLoaded("teloaddon") && com.facetorched.teloaddon.util.Config.addFluorite) {
        	athsCrystalClusterHelper(AthsGlobal.FLUORITE, new String[] {"Limestone","Dolomite","Granite","Rhyolite","Sandstone"}, /*size*/20, /*dispersion*/1, /*rarity*/70);
        }
        
        athsCrystalClusterHelper(AthsGlobal.ROCK_CRYSTAL, new String[] {"MM"}, /*size*/20, /*dispersion*/1, /*rarity*/80);
        athsCrystalClusterHelper(AthsGlobal.GYPSUM, new String[] {"MM"}, /*size*/50, /*dispersion*/1, /*rarity*/100);
		
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
		
		athsPlantHelper(AthsGlobal.ADDERS_TONGUE_FERN, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Asia","Europe"},
				/*size*/5, /*dispersion*/1, /*rarity*/7568, /*minAltitude*/144, /*maxAltitude*/255, /*minTemp*/21f, /*maxTemp*/40f, /*minRain*/700f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/-0.4f);
		athsPlantHelper(AthsGlobal.AFRICAN_MILK_BARREL, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/12, /*dispersion*/6, /*rarity*/2984, /*minAltitude*/144, /*maxAltitude*/220, /*minTemp*/16f, /*maxTemp*/24f, /*minRain*/60f, /*maxRain*/160f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.AFRICAN_MILK_TREE, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/12, /*dispersion*/5, /*rarity*/3984, /*minAltitude*/144, /*maxAltitude*/220, /*minTemp*/19f, /*maxTemp*/40f, /*minRain*/120f, /*maxRain*/660f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.ALBANIAN_SPURGE, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/8, /*dispersion*/4, /*rarity*/6984, /*minAltitude*/144, /*maxAltitude*/220, /*minTemp*/15f, /*maxTemp*/20f, /*minRain*/150f, /*maxRain*/560f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.ALGAE_MAT_CYANOBACTERIA, new int[] {0,1,2}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:SaltWaterStationary","terrafirmacraftplus:Ice"}, AthsGlobal.SHALLOW_WATER_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/64, /*dispersion*/1, /*rarity*/2968, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-20f, /*maxTemp*/40f, /*minRain*/80f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.ALGAE_MAT_GREEN, new int[] {0,1,2}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:Ice"}, AthsGlobal.SHALLOW_WATER_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/64, /*dispersion*/1, /*rarity*/968, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-20f, /*maxTemp*/40f, /*minRain*/80f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.ALGAE_MAT_RED, new int[] {0,1,2}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:SaltWaterStationary","terrafirmacraftplus:Ice"}, AthsGlobal.SHALLOW_WATER_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/64, /*dispersion*/1, /*rarity*/9068, /*minAltitude*/144, /*maxAltitude*/255, /*minTemp*/-20f, /*maxTemp*/40f, /*minRain*/40f, /*maxRain*/800f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.ALOE_VERA, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills"}, new String[]{"Asia"},
				/*size*/3, /*dispersion*/2, /*rarity*/834, /*minAltitude*/156, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/40f, /*minRain*/45f, /*maxRain*/130f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.ANEMONE /*Balkan*/, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe","Asia"},
				/*size*/5, /*dispersion*/2, /*rarity*/5884, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/10f, /*maxTemp*/18f, /*minRain*/380f, /*maxRain*/1100f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/0.2f);
		athsPlantHelper(AthsGlobal.ANEMONE + "_Broadleaf", AthsGlobal.ANEMONE, new int[] {1}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/5, /*dispersion*/2, /*rarity*/5884, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/9f, /*maxTemp*/19f, /*minRain*/380f, /*maxRain*/1100f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/0.2f);
		athsPlantHelper(AthsGlobal.ANEMONE + "_Poppy", AthsGlobal.ANEMONE, new int[] {2}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/5, /*dispersion*/2, /*rarity*/4884, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/11f, /*maxTemp*/21f, /*minRain*/380f, /*maxRain*/1100f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/0.2f);
		athsPlantHelper(AthsGlobal.ANEMONE + "_Thimbleweed", AthsGlobal.ANEMONE, new int[] {3}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/5, /*dispersion*/2, /*rarity*/5884, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/10f, /*maxTemp*/17f, /*minRain*/680f, /*maxRain*/1600f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/0.2f);
		athsPlantHelper(AthsGlobal.ANGELS_TRUMPET /*Orange*/, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"Foothills","Mountains Edge","Mountains","Mountain Range","Mountain Range Edge"}, new String[]{"Americas"},
				/*size*/6, /*dispersion*/15, /*rarity*/1084, /*minAltitude*/150, /*maxAltitude*/220, /*minTemp*/25f, /*maxTemp*/40f, /*minRain*/1000f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.ANGELS_TRUMPET + "_Pink", AthsGlobal.ANGELS_TRUMPET, new int[] {1}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","High Hills Edge","Foothills","Mountains Edge","Mountains","Mountain Range","Mountain Range Edge"}, new String[]{"Americas"},
				/*size*/6, /*dispersion*/15, /*rarity*/1084, /*minAltitude*/170, /*maxAltitude*/220, /*minTemp*/22f, /*maxTemp*/40f, /*minRain*/900f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.ANGELS_TRUMPET + "_White", AthsGlobal.ANGELS_TRUMPET, new int[] {2}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","High Hills Edge","High Plains","Foothills","Mountains Edge","Mountains","Mountain Range","Mountain Range Edge"}, new String[]{"Americas"},
				/*size*/6, /*dispersion*/15, /*rarity*/1084, /*minAltitude*/150, /*maxAltitude*/220, /*minTemp*/20f, /*maxTemp*/35f, /*minRain*/1000f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.ANGEL_WING_CACTUS, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge"}, new String[]{"Americas"},
				/*size*/22, /*dispersion*/15, /*rarity*/980, /*minAltitude*/0, /*maxAltitude*/190, /*minTemp*/19f, /*maxTemp*/35f, /*minRain*/75f, /*maxRain*/210f, /*minEVT*/1f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.ASTER, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe","Asia"},
				/*size*/5, /*dispersion*/2, /*rarity*/3884, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/10f, /*maxTemp*/18f, /*minRain*/500f, /*maxRain*/900f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/0f);
		athsPlantHelper(AthsGlobal.ASTER + "_Alpine", AthsGlobal.ASTER, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"Mountains","Mountains Edge","Mountain Range","Mountain Range Edge","Foothills","High Hills"}, new String[]{"Europe","Americas"},
				/*size*/5, /*dispersion*/2, /*rarity*/7184, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-6f, /*maxTemp*/10f, /*minRain*/500f, /*maxRain*/900f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/0f);
		athsPlantHelper(AthsGlobal.AZALEA_DECIDUOUS /*Honeysuckle*/, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Riverbank","Swamp"}, new String[]{"Asia","Europe"},
				/*size*/6, /*dispersion*/6, /*rarity*/3980, /*minAltitude*/0, /*maxAltitude*/200, /*minTemp*/10f, /*maxTemp*/18f, /*minRain*/650f, /*maxRain*/1400f, /*minEVT*/1f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.AZALEA_DECIDUOUS + "_Swamp", AthsGlobal.AZALEA_DECIDUOUS, new int[] {1}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia","Europe"},
				/*size*/6, /*dispersion*/6, /*rarity*/3980, /*minAltitude*/0, /*maxAltitude*/200, /*minTemp*/5f, /*maxTemp*/15f, /*minRain*/750f, /*maxRain*/2000f, /*minEVT*/1f, /*maxEVT*/5f,/*forestGen*/0.5f);
		athsPlantHelper(AthsGlobal.AZALEA_EVERGREEN /*Flame*/, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","High Hills Edge","Mountains","Mountain Range","Mountain Range Edge","High Plains","Lake","Foothills"}, new String[]{"Americas"},
				/*size*/6, /*dispersion*/6, /*rarity*/3980, /*minAltitude*/150, /*maxAltitude*/255, /*minTemp*/5f, /*maxTemp*/15f, /*minRain*/750f, /*maxRain*/2000f, /*minEVT*/1f, /*maxEVT*/5f,/*forestGen*/0.5f);
		athsPlantHelper(AthsGlobal.AZALEA_EVERGREEN + "_Lapland", AthsGlobal.AZALEA_EVERGREEN, new int[] {1}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Mountains","Rolling Hills","Lakeshore","Riverbank","Swamp","Mountain Range","Mountain Range Edge","High Plains","Lake","Foothills"}, new String[]{"Americas"},
				/*size*/12, /*dispersion*/9, /*rarity*/4980, /*minAltitude*/150, /*maxAltitude*/255, /*minTemp*/-15f, /*maxTemp*/5f, /*minRain*/250f, /*maxRain*/850f, /*minEVT*/1f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.AZALEA_EVERGREEN + "_Satsuki", AthsGlobal.AZALEA_EVERGREEN, new int[] {2}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia"},
				/*size*/6, /*dispersion*/6, /*rarity*/3980, /*minAltitude*/0, /*maxAltitude*/200, /*minTemp*/11f, /*maxTemp*/19f, /*minRain*/750f, /*maxRain*/2000f, /*minEVT*/1f, /*maxEVT*/5f,/*forestGen*/0.5f);
		athsPlantHelper(AthsGlobal.BASKET_STINKHORN, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia","Europe"},
				/*size*/6, /*dispersion*/6, /*rarity*/3980, /*minAltitude*/0, /*maxAltitude*/200, /*minTemp*/0f, /*maxTemp*/16f, /*minRain*/550f, /*maxRain*/1600f, /*minEVT*/1.5f, /*maxEVT*/5f,/*forestGen*/0.1f);
		athsPlantHelper(AthsGlobal.BEAR_CORN, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/3, /*dispersion*/2, /*rarity*/8980, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-5f, /*maxTemp*/17f, /*minRain*/850f, /*maxRain*/2100f, /*minEVT*/1.5f, /*maxEVT*/5f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.BEECH_MUSHROOM, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia"},
				/*size*/3, /*dispersion*/2, /*rarity*/8980, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/2f, /*maxTemp*/17f, /*minRain*/850f, /*maxRain*/3500f, /*minEVT*/0f, /*maxEVT*/5f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.BIRD_OF_PARADISE, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/3, /*dispersion*/3, /*rarity*/7584, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/12f, /*maxTemp*/16f, /*minRain*/600f, /*maxRain*/3000f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.BIRDS_NEST_FERN, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa","Asia"},
				/*size*/5, /*dispersion*/3, /*rarity*/4584, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/40f, /*minRain*/1500f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.BLACK_BAT_FLOWER, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/3, /*dispersion*/3, /*rarity*/4584, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/17f, /*maxTemp*/28f, /*minRain*/1100f, /*maxRain*/4000f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.BLACK_EYED_SUSAN, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/15, /*dispersion*/3, /*rarity*/4884, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/16f, /*minRain*/400f, /*maxRain*/950f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/0.1f);
		athsPlantHelper(AthsGlobal.BLAZING_STAR, new int[] {0,1}, new String[] {"ore:blockSoil"}, new String[]{"Plains","Rolling Hills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/4, /*dispersion*/2, /*rarity*/4984, /*minAltitude*/0, /*maxAltitude*/160, /*minTemp*/-3f, /*maxTemp*/24f, /*minRain*/630f, /*maxRain*/790f, /*minEVT*/0.5f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.BLEEDING_HEARTS, new int[] {0}, new String[] {"ore:blockSoil","ore:blockGravel"}, new String[]{"Mountains","Mountains Edge","Mountain Range","Mountain Range Edge","Foothills","High Hills"}, new String[]{"Asia"},
				/*size*/5, /*dispersion*/2, /*rarity*/5184, /*minAltitude*/0, /*maxAltitude*/190, /*minTemp*/-6f, /*maxTemp*/10f, /*minRain*/900f, /*maxRain*/4000f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.BLOOD_LILY, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/8, /*dispersion*/3, /*rarity*/4584, /*minAltitude*/144, /*maxAltitude*/220, /*minTemp*/10f, /*maxTemp*/18f, /*minRain*/250f, /*maxRain*/760f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.BLUEBELL, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"Plains","Rolling Hills","High Plains","High Hills","High Hills Edge","Lakeshore","Riverbank","Swamp"}, new String[]{"Europe"},
				/*size*/200, /*dispersion*/1, /*rarity*/12884, /*minAltitude*/144, /*maxAltitude*/220, /*minTemp*/-5f, /*maxTemp*/17f, /*minRain*/850f, /*maxRain*/3060f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.BONATEA, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/7, /*dispersion*/3, /*rarity*/4584, /*minAltitude*/144, /*maxAltitude*/220, /*minTemp*/7f, /*maxTemp*/17f, /*minRain*/500f, /*maxRain*/800f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.BLUE_CEREUS_CACTUS, new int[] {0,1}, new String[] {"ore:blockSoil","ore:blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge"}, new String[]{"Americas"},
				/*size*/5, /*dispersion*/8, /*rarity*/1200, /*minAltitude*/0, /*maxAltitude*/210, /*minTemp*/17f, /*maxTemp*/34f, /*minRain*/55f, /*maxRain*/190f, /*minEVT*/0.5f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.BOLETUS, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas", "Asia", "Europe"},
				/*size*/3, /*dispersion*/2, /*rarity*/7884, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-5f, /*maxTemp*/15f, /*minRain*/700f, /*maxRain*/1000f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.BRACKEN_FERN, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Asia","Europe"},
				/*size*/22, /*dispersion*/8, /*rarity*/2568, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-10f, /*maxTemp*/30f, /*minRain*/500f, /*maxRain*/1000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/-0.4f);
		athsPlantHelper(AthsGlobal.BRIDAL_VEIL_STINKHORN, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia","Africa"},
				/*size*/2, /*dispersion*/1, /*rarity*/10096, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/20f, /*maxTemp*/32f, /*minRain*/1000f, /*maxRain*/8000f, /*minEVT*/1f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.BUCEPHALANDRA, new int[] {0}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:Ice"}, AthsGlobal.SHALLOW_WATER_BIOMES, new String[]{"Asia"},
				/*size*/64, /*dispersion*/1, /*rarity*/1068, /*minAltitude*/0, /*maxAltitude*/160, /*minTemp*/24f, /*maxTemp*/40f, /*minRain*/1000f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f, /*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.BURDOCK, new int[] {0,1}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Europe"},
				/*size*/8, /*dispersion*/9, /*rarity*/968, /*minAltitude*/144, /*maxAltitude*/255, /*minTemp*/-5f, /*maxTemp*/18f, /*minRain*/450f, /*maxRain*/1500f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.BURNING_BUSH, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Mountains","Rolling Hills","Lakeshore","Riverbank","Swamp","Mountain Range","Mountain Range Edge","High Plains","Lake","Foothills"}, new String[]{"Asia"},
				/*size*/12, /*dispersion*/10, /*rarity*/6980, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-11f, /*maxTemp*/13f, /*minRain*/450f, /*maxRain*/1050f, /*minEVT*/1f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.BUTTERCUP, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Europe"},
				/*size*/7, /*dispersion*/2, /*rarity*/3584, /*minAltitude*/0, /*maxAltitude*/195, /*minTemp*/-1f, /*maxTemp*/14f, /*minRain*/600f, /*maxRain*/2400f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/0.3f);
		athsPlantHelper(AthsGlobal.CAMAS_FLOWER, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/25, /*dispersion*/6, /*rarity*/6384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-13f, /*maxTemp*/12f, /*minRain*/300f, /*maxRain*/750f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(AthsGlobal.CANADA_WILD_GINGER, new int[] {0}, new String[] {"ore:blockSoil"}, new String[] {"High Hills","High Hills Edge","Plains","Rolling Hills","Estuary","Riverbank","Swamp","Lakeshore"}, new String[]{"Americas"},
				/*size*/43, /*dispersion*/0, /*rarity*/6984, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-2f, /*maxTemp*/14f, /*minRain*/850f, /*maxRain*/1200f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.CARALLUMA, new int[] {0}, new String[] {"ore:blockSoil", "ore:blockSand"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa", "Asia"},
				/*size*/14, /*dispersion*/5, /*rarity*/3584, /*minAltitude*/144, /*maxAltitude*/220, /*minTemp*/16f, /*maxTemp*/32f, /*minRain*/85f, /*maxRain*/300f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.CARNATIONS /*Wild*/, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/5, /*dispersion*/3, /*rarity*/6884, /*minAltitude*/0, /*maxAltitude*/210, /*minTemp*/12f, /*maxTemp*/28f, /*minRain*/420f, /*maxRain*/1100f, /*minEVT*/0.5f, /*maxEVT*/8f);
		athsPlantHelper(AthsGlobal.CARNATIONS + "_Northland", AthsGlobal.CARNATIONS, new int[] {1}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/5, /*dispersion*/3, /*rarity*/9884, /*minAltitude*/0, /*maxAltitude*/210, /*minTemp*/12f, /*maxTemp*/28f, /*minRain*/420f, /*maxRain*/1100f, /*minEVT*/0.5f, /*maxEVT*/8f);
		athsPlantHelper(AthsGlobal.CARNATIONS + "_Helena_Allwood", AthsGlobal.CARNATIONS, new int[] {2}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/5, /*dispersion*/3, /*rarity*/9984, /*minAltitude*/0, /*maxAltitude*/210, /*minTemp*/12f, /*maxTemp*/28f, /*minRain*/420f, /*maxRain*/1100f, /*minEVT*/0.5f, /*maxEVT*/8f);
		athsPlantHelper(AthsGlobal.CARNATIONS + "_Nahema", AthsGlobal.CARNATIONS, new int[] {3}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/5, /*dispersion*/3, /*rarity*/9684, /*minAltitude*/0, /*maxAltitude*/210, /*minTemp*/12f, /*maxTemp*/28f, /*minRain*/420f, /*maxRain*/1100f, /*minEVT*/0.5f, /*maxEVT*/8f);
		athsPlantHelper(AthsGlobal.CARNATIONS + "_Monty's_Pink", AthsGlobal.CARNATIONS, new int[] {4}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/5, /*dispersion*/3, /*rarity*/8484, /*minAltitude*/0, /*maxAltitude*/210, /*minTemp*/12f, /*maxTemp*/28f, /*minRain*/420f, /*maxRain*/1100f, /*minEVT*/0.5f, /*maxEVT*/8f);
		athsPlantHelper(AthsGlobal.CARNATIONS + "_Liberty", AthsGlobal.CARNATIONS, new int[] {5}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/5, /*dispersion*/3, /*rarity*/9284, /*minAltitude*/0, /*maxAltitude*/210, /*minTemp*/12f, /*maxTemp*/28f, /*minRain*/420f, /*maxRain*/1100f, /*minEVT*/0.5f, /*maxEVT*/8f);
		athsPlantHelper(AthsGlobal.CARNATIONS + "_Royal_Crimson", AthsGlobal.CARNATIONS, new int[] {6}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/5, /*dispersion*/3, /*rarity*/10284, /*minAltitude*/0, /*maxAltitude*/210, /*minTemp*/12f, /*maxTemp*/28f, /*minRain*/420f, /*maxRain*/1100f, /*minEVT*/0.5f, /*maxEVT*/8f);
		athsPlantHelper(AthsGlobal.CHANTERELLE, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Europe"},
				/*size*/4, /*dispersion*/1, /*rarity*/5684, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-4f, /*maxTemp*/18f, /*minRain*/750f, /*maxRain*/3000f, /*minEVT*/0f, /*maxEVT*/8f);
		athsPlantHelper(AthsGlobal.CHI_NGULU_NGULU, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/4, /*dispersion*/6, /*rarity*/11584, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/22f, /*maxTemp*/40f, /*minRain*/1000f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/0.4f);
		athsPlantHelper(AthsGlobal.CHIVES, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Europe","Asia","Americas"},
				/*size*/8, /*dispersion*/4, /*rarity*/984, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/3f, /*maxTemp*/16f, /*minRain*/600f, /*maxRain*/800f, /*minEVT*/1f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.CHRISTMAS_FERN, new int[] {0}, new String[] {"ore:blockSoil"}, new String[] {"High Hills","High Hills Edge","Plains","Rolling Hills","Estuary","Riverbank","Swamp","Lakeshore"}, new String[]{"Americas"},
				/*size*/13, /*dispersion*/4, /*rarity*/6084, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-1f, /*maxTemp*/18f, /*minRain*/900f, /*maxRain*/1400f, /*minEVT*/1f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.CLOVER, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa","Americas","Europe","Asia"},
				/*size*/20, /*dispersion*/4, /*rarity*/656, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-6f, /*maxTemp*/33f, /*minRain*/650f, /*maxRain*/950f, /*minEVT*/1f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.CHRYSANTHEMUM /*Clara_Curtis*/, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/6, /*dispersion*/4, /*rarity*/5584, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/2f, /*maxTemp*/17f, /*minRain*/500f, /*maxRain*/1800f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/0.1f);
		athsPlantHelper(AthsGlobal.CHRYSANTHEMUM + "_French_Vanilla", AthsGlobal.CHRYSANTHEMUM, new int[] {1}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/6, /*dispersion*/4, /*rarity*/5584, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/2f, /*maxTemp*/17f, /*minRain*/500f, /*maxRain*/1800f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/0.1f);
		athsPlantHelper(AthsGlobal.CHRYSANTHEMUM + "Harmony", AthsGlobal.CHRYSANTHEMUM, new int[] {2}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/6, /*dispersion*/4, /*rarity*/5584, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/2f, /*maxTemp*/17f, /*minRain*/500f, /*maxRain*/1800f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/0.1f);
		athsPlantHelper(AthsGlobal.CHRYSANTHEMUM + "Regal_Mistd", AthsGlobal.CHRYSANTHEMUM, new int[] {3}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/6, /*dispersion*/4, /*rarity*/5584, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/2f, /*maxTemp*/17f, /*minRain*/500f, /*maxRain*/1800f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/0.1f);
		athsPlantHelper(AthsGlobal.CINNAMON_FERN, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/22, /*dispersion*/6, /*rarity*/4068, /*minAltitude*/0, /*maxAltitude*/160, /*minTemp*/-5f, /*maxTemp*/20f, /*minRain*/850f, /*maxRain*/8000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/0.8f);
		athsPlantHelper(AthsGlobal.COMMON_CATCHFLY, new int[] {0,1}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe","Asia","Africa"},
				/*size*/12, /*dispersion*/4, /*rarity*/3284, /*minAltitude*/0, /*maxAltitude*/150, /*minTemp*/11f, /*maxTemp*/22f, /*minRain*/600f, /*maxRain*/890f, /*minEVT*/0.0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.COMMON_REEDS, new int[] {0}, new String[] {"ore:blockSoil", "terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:Peat"}, new String[]{"Lake","Estuary","Lakeshore","Riverbank","Swamp","River"}, new String[]{"Africa","Europe","Asia","Americas"},
				/*size*/150, /*dispersion*/3, /*rarity*/484, /*minAltitude*/0, /*maxAltitude*/145, /*minTemp*/0f, /*maxTemp*/30f, /*minRain*/350f, /*maxRain*/2400f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/-0.2f);
		athsPlantHelper(AthsGlobal.COMMON_STINKHORN, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas", "Asia", "Europe"},
				/*size*/2, /*dispersion*/2, /*rarity*/7296, /*minAltitude*/0, /*maxAltitude*/220, /*minTemp*/-5f, /*maxTemp*/13f, /*minRain*/670f, /*maxRain*/3000f, /*minEVT*/0.5f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.COLEUS /*Autumn_Rainbow*/, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","High Hills Edge","Mountain Range","Mountain Range Edge","High Plains","Mountains","Foothills"}, new String[]{"Africa", "Asia"},
				/*size*/10, /*dispersion*/5, /*rarity*/7068, /*minAltitude*/160, /*maxAltitude*/255, /*minTemp*/21f, /*maxTemp*/40f, /*minRain*/1150f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.COLEUS + "_Chocolate_Covered_Cherry", AthsGlobal.COLEUS, new int[] {1}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","High Hills Edge","Mountain Range","Mountain Range Edge","High Plains","Mountains","Foothills"}, new String[]{"Africa", "Asia"},
				/*size*/10, /*dispersion*/5, /*rarity*/8068, /*minAltitude*/160, /*maxAltitude*/255, /*minTemp*/21f, /*maxTemp*/40f, /*minRain*/1150f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.COLEUS + "_Chocolate_Mint", AthsGlobal.COLEUS, new int[] {2}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","High Hills Edge","Mountain Range","Mountain Range Edge","High Plains","Mountains","Foothills"}, new String[]{"Africa", "Asia"},
				/*size*/10, /*dispersion*/5, /*rarity*/7268, /*minAltitude*/160, /*maxAltitude*/255, /*minTemp*/21f, /*maxTemp*/40f, /*minRain*/1150f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.COLEUS + "_Crimson_Gold", AthsGlobal.COLEUS, new int[] {3}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","High Hills Edge","Mountain Range","Mountain Range Edge","High Plains","Mountains","Foothills"}, new String[]{"Africa", "Asia"},
				/*size*/10, /*dispersion*/5, /*rarity*/7068, /*minAltitude*/160, /*maxAltitude*/255, /*minTemp*/21f, /*maxTemp*/40f, /*minRain*/1150f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.COLEUS + "_Jade", AthsGlobal.COLEUS, new int[] {4}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","High Hills Edge","Mountain Range","Mountain Range Edge","High Plains","Mountains","Foothills"}, new String[]{"Africa", "Asia"},
				/*size*/10, /*dispersion*/5, /*rarity*/6068, /*minAltitude*/160, /*maxAltitude*/255, /*minTemp*/21f, /*maxTemp*/40f, /*minRain*/1150f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.COLEUS + "_Red_Velvet", AthsGlobal.COLEUS, new int[] {5}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","High Hills Edge","Mountain Range","Mountain Range Edge","High Plains","Mountains","Foothills"}, new String[]{"Africa", "Asia"},
				/*size*/10, /*dispersion*/5, /*rarity*/7568, /*minAltitude*/160, /*maxAltitude*/255, /*minTemp*/21f, /*maxTemp*/40f, /*minRain*/1150f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.COLEUS + "_Watermelon", AthsGlobal.COLEUS, new int[] {6}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","High Hills Edge","Mountain Range","Mountain Range Edge","High Plains","Mountains","Foothills"}, new String[]{"Africa", "Asia"},
				/*size*/10, /*dispersion*/5, /*rarity*/7068, /*minAltitude*/160, /*maxAltitude*/255, /*minTemp*/21f, /*maxTemp*/40f, /*minRain*/1150f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.COLEUS + "_Wild", AthsGlobal.COLEUS, new int[] {7}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","High Hills Edge","Mountain Range","Mountain Range Edge","High Plains","Mountains","Foothills"}, new String[]{"Africa", "Asia"},
				/*size*/10, /*dispersion*/5, /*rarity*/5068, /*minAltitude*/160, /*maxAltitude*/255, /*minTemp*/21f, /*maxTemp*/40f, /*minRain*/1150f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.CREEPING_BELLFLOWER, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe","Asia"},
				/*size*/25, /*dispersion*/3, /*rarity*/5884, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/24f, /*minRain*/550f, /*maxRain*/1400f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.CREEPING_CHARLIE, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/35, /*dispersion*/3, /*rarity*/1056, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-6f, /*maxTemp*/33f, /*minRain*/650f, /*maxRain*/1200f, /*minEVT*/0.5f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.CRETAN_BRAKE_FERN /*Normal*/, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa","Asia","Europe"},
				/*size*/5, /*dispersion*/3, /*rarity*/5584, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/16f, /*maxTemp*/40f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.CRETAN_BRAKE_FERN + "_Variegated", AthsGlobal.CRETAN_BRAKE_FERN, new int[] {1}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa","Asia","Europe"},
				/*size*/5, /*dispersion*/3, /*rarity*/7284, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/16f, /*maxTemp*/40f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.CROCUS, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe","Asia","Africa"},
				/*size*/3, /*dispersion*/2, /*rarity*/3084, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/11f, /*maxTemp*/23f, /*minRain*/310f, /*maxRain*/930f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/0f);
		athsPlantHelper(AthsGlobal.CUP_PLANT, new int[] {0,1}, new String[] {"ore:blockSoil"}, new String[]{"Plains","Rolling Hills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/7, /*dispersion*/2, /*rarity*/4384, /*minAltitude*/0, /*maxAltitude*/160, /*minTemp*/0f, /*maxTemp*/15f, /*minRain*/600f, /*maxRain*/750f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.CYCAD, new int[] {0,1}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia","Africa","Americas"},
		        /*size*/10, /*dispersion*/30, /*rarity*/7584, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/18f, /*maxTemp*/40f, /*minRain*/1000f, /*maxRain*/16000f, /*minEVT*/1f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.DAFFODIL, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/7, /*dispersion*/3, /*rarity*/7384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/12f, /*maxTemp*/28f, /*minRain*/280f, /*maxRain*/1300f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.DAYLILY /*Lemon*/, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia", "Europe"},
				/*size*/10, /*dispersion*/2, /*rarity*/6384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/6f, /*maxTemp*/16f, /*minRain*/480f, /*maxRain*/1100f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/0.2f);
		athsPlantHelper(AthsGlobal.DAYLILY + "_Nikkokisuge", AthsGlobal.DAYLILY, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","High Hills Edge","Mountains","Mounatains Edge","Mountain Range Edge","Mountain Range","Foothills","High Plains"}, new String[]{"Asia"},
				/*size*/10, /*dispersion*/2, /*rarity*/6984, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/7f, /*maxTemp*/18f, /*minRain*/480f, /*maxRain*/1100f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/0.2f);
		athsPlantHelper(AthsGlobal.DAYLILY + "_Tawny", AthsGlobal.DAYLILY, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/10, /*dispersion*/2, /*rarity*/5884, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/7f, /*maxTemp*/18f, /*minRain*/480f, /*maxRain*/1100f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/0.2f);
		athsPlantHelper(AthsGlobal.DEATH_CAP, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/2, /*dispersion*/2, /*rarity*/6596, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-6f, /*maxTemp*/13f, /*minRain*/700f, /*maxRain*/4000f, /*minEVT*/0.5f, /*maxEVT*/5f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.DEER_FERN, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","High Hills Edge","Foothills","Swamp","Mountains","Mountains Edge","Mountain Range Edge","Mountain Range"}, new String[]{"Americas","Asia","Europe"},
				/*size*/18, /*dispersion*/5, /*rarity*/3684, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-10f, /*maxTemp*/14f, /*minRain*/800f, /*maxRain*/2000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.DELTA_MAIDENHAIR_FERN, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/12, /*dispersion*/4, /*rarity*/3300, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/17f, /*maxTemp*/40f, /*minRain*/990f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);	
		athsPlantHelper(AthsGlobal.DESERT_ROSE, new int[] {0}, new String[] {"ore:blockSoil", "ore:blockSand"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Africa"},
				/*size*/6, /*dispersion*/5, /*rarity*/7584, /*minAltitude*/144, /*maxAltitude*/220, /*minTemp*/17f, /*maxTemp*/40f, /*minRain*/65f, /*maxRain*/200f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.DESTROYING_ANGEL /*American*/, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/2, /*dispersion*/2, /*rarity*/6596, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-14f, /*maxTemp*/19f, /*minRain*/700f, /*maxRain*/4000f, /*minEVT*/0.5f, /*maxEVT*/5f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.DESTROYING_ANGEL + "_European", AthsGlobal.DESTROYING_ANGEL, new int[] {1}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/2, /*dispersion*/2, /*rarity*/6596, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-14f, /*maxTemp*/19f, /*minRain*/700f, /*maxRain*/4000f, /*minEVT*/0.5f, /*maxEVT*/5f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.DEVILS_CLUB, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/30, /*dispersion*/4, /*rarity*/6084, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-10f, /*maxTemp*/9f, /*minRain*/1000f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.DEVILS_FINGERS, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia"},
				/*size*/4, /*dispersion*/1, /*rarity*/5384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-4f, /*maxTemp*/18f, /*minRain*/750f, /*maxRain*/3000f, /*minEVT*/0.5f, /*maxEVT*/8f);
		athsPlantHelper(AthsGlobal.DEVILS_TOUNGE, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia"},
				/*size*/2, /*dispersion*/6, /*rarity*/10096, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/20f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.DUCKWEED, new int[] {0}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:Ice"}, AthsGlobal.SHALLOW_WATER_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/64, /*dispersion*/1, /*rarity*/668, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-10f, /*maxTemp*/40f, /*minRain*/500f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.DUNE_GRASS, new int[] {0}, new String[] {"ore:blockSand","ore:blockGravel"}, new String[]{"Beach","Gravel Beach","Shore","Estuary"}, new String[]{"Americas"},
				/*size*/12, /*dispersion*/4, /*rarity*/256, /*minAltitude*/145, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/16f, /*minRain*/220f, /*maxRain*/1500f, /*minEVT*/0f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.DWARF_PALMETTO, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/15, /*dispersion*/16, /*rarity*/4969, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/18f, /*maxTemp*/26f, /*minRain*/600f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.EGYPTIAN_AUTUMN_CROCUS, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Africa","Europe","Asia"},
				/*size*/6, /*dispersion*/3, /*rarity*/3884, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/17f, /*maxTemp*/26f, /*minRain*/130f, /*maxRain*/300f, /*minEVT*/1f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.EARTHBALL, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe","Americas","Africa","Asia"},
				/*size*/7, /*dispersion*/4, /*rarity*/4996, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-10f, /*maxTemp*/40f, /*minRain*/500f, /*maxRain*/1400f, /*minEVT*/0.5f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.EARTHSTAR, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe","Americas","Africa","Asia"},
				/*size*/2, /*dispersion*/2, /*rarity*/6996, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/29f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/0.5f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.EASTERN_SKUNK_CABBAGE, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"Lake","Lakeshore","Riverbank","Swamp","River"}, new String[]{"Americas"},
				/*size*/43, /*dispersion*/2, /*rarity*/7012, /*minAltitude*/0, /*maxAltitude*/160, /*minTemp*/-1f, /*maxTemp*/15f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.ELEPHANT_GRASS, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains"}, new String[]{"Africa"},
				/*size*/200, /*dispersion*/2, /*rarity*/8384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/32f, /*minRain*/330f, /*maxRain*/700f, /*minEVT*/0f, /*maxEVT*/2f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.ELEPHANT_GRASS + "_Dense", AthsGlobal.ELEPHANT_GRASS, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains"}, new String[]{"Africa"},
				/*size*/120, /*dispersion*/3, /*rarity*/484, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/32f, /*minRain*/505f, /*maxRain*/520f, /*minEVT*/0f, /*maxEVT*/2f,/*forestGen*/-0.5f);
		athsPlantHelper(AthsGlobal.ENTOLOMA, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/5, /*dispersion*/3, /*rarity*/6096, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-5f, /*maxTemp*/15f, /*minRain*/900f, /*maxRain*/10000f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.EUROPEAN_BEDSTRAW, new int[] {0,1,2}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia", "Europe"},
				/*size*/50, /*dispersion*/3, /*rarity*/3228, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/16f, /*minRain*/750f, /*maxRain*/6000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.FIELD_HORSETAIL, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"Plains","Lake","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Americas", "Asia", "Europe"},
				/*size*/23, /*dispersion*/3, /*rarity*/4012, /*minAltitude*/0, /*maxAltitude*/150, /*minTemp*/-10f, /*maxTemp*/12f, /*minRain*/650f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.FIREWEED, new int[] {0}, new String[] {"ore:blockSoil","ore:stone"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Europe","Asia"},
				/*size*/60, /*dispersion*/3, /*rarity*/4384, /*minAltitude*/144, /*maxAltitude*/225, /*minTemp*/-13f, /*maxTemp*/8f, /*minRain*/550f, /*maxRain*/1300f, /*minEVT*/0.5f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.FLAMINGO_FLOWER, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/3, /*dispersion*/2, /*rarity*/6384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/24f, /*maxTemp*/35f, /*minRain*/950f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.FREESIA, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/6, /*dispersion*/5, /*rarity*/5384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/30f, /*minRain*/500f, /*maxRain*/1080f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.FOUNTAIN_GRASS /*Green*/, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa","Asia"},
				/*size*/120, /*dispersion*/4, /*rarity*/6784, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/13f, /*maxTemp*/38f, /*minRain*/350f, /*maxRain*/730f, /*minEVT*/0f, /*maxEVT*/2f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.FOUNTAIN_GRASS + "_Purple", AthsGlobal.FOUNTAIN_GRASS, new int[] {1}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa","Asia"},
				/*size*/120, /*dispersion*/4, /*rarity*/9784, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/13f, /*maxTemp*/38f, /*minRain*/350f, /*maxRain*/730f, /*minEVT*/0f, /*maxEVT*/2f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.FRINGED_ACALYPHA, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa","Asia"},
				/*size*/13, /*dispersion*/3, /*rarity*/2980, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/17f, /*maxTemp*/30f, /*minRain*/650f, /*maxRain*/900f, /*minEVT*/1f, /*maxEVT*/5f,/*forestGen*/0.2f);
		athsPlantHelper(AthsGlobal.GARLIC_MUSTARD, new int[] {0,1,2}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia", "Europe"},
				/*size*/70, /*dispersion*/3, /*rarity*/4228, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-5f, /*maxTemp*/19f, /*minRain*/750f, /*maxRain*/6000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.GIANT_HOGWEED, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"Mountains","High Hills","High Hills Edge","Mountains Edge","Foothills","Riverbank"}, new String[]{"Europe"},
				/*size*/13, /*dispersion*/8, /*rarity*/6384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/5f, /*maxTemp*/14f, /*minRain*/660f, /*maxRain*/930f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.GIFBOOM, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand"}, new String[]{"Plains","High Hills Edge","Rolling Hills","High Plains","Foothills"}, new String[]{"Africa"},
				/*size*/1, /*dispersion*/1, /*rarity*/656, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/31f, /*minRain*/78f, /*maxRain*/200f, /*minEVT*/1f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.GLADIOLUS /*Blue_Moon*/, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa","Europe"},
				/*size*/6, /*dispersion*/5, /*rarity*/5384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/9f, /*maxTemp*/21f, /*minRain*/500f, /*maxRain*/920f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.GLADIOLUS + "_Elvira", AthsGlobal.GLADIOLUS, new int[] {1}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa","Europe"},
				/*size*/6, /*dispersion*/5, /*rarity*/5374, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/9f, /*maxTemp*/21f, /*minRain*/500f, /*maxRain*/920f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.GLADIOLUS + "_Nathalie", AthsGlobal.GLADIOLUS, new int[] {2}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa","Europe"},
				/*size*/6, /*dispersion*/5, /*rarity*/6184, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/9f, /*maxTemp*/21f, /*minRain*/500f, /*maxRain*/920f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.GLADIOLUS + "_Yellowstone", AthsGlobal.GLADIOLUS, new int[] {3}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa","Europe"},
				/*size*/6, /*dispersion*/5, /*rarity*/5084, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/9f, /*maxTemp*/21f, /*minRain*/500f, /*maxRain*/920f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.GOLDEN_LEATHER_FERN, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand","terrafirmacraftplus:Peat","ore:block:Gravel"}, new String[]{"Salt_Swamp","Estuary","Riverbank"}, new String[]{"Asia","Americas","Europe","Africa"},
				/*size*/20, /*dispersion*/2, /*rarity*/4256, /*minAltitude*/0, /*maxAltitude*/153, /*minTemp*/21f, /*maxTemp*/40f, /*minRain*/800f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/8f);
		athsPlantHelper(AthsGlobal.GOLDEN_SPINDLES, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe","Americas","Asia"},
				/*size*/2, /*dispersion*/2, /*rarity*/6196, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-15f, /*maxTemp*/16f, /*minRain*/620f, /*maxRain*/2000f, /*minEVT*/0.5f, /*maxEVT*/5f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.GOLDEN_MILK_CAP, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/2, /*dispersion*/2, /*rarity*/5296, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-12f, /*maxTemp*/20f, /*minRain*/980f, /*maxRain*/4000f, /*minEVT*/0.5f, /*maxEVT*/5f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.GOLDEN_WAXCAP, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Asia"},
				/*size*/2, /*dispersion*/2, /*rarity*/7296, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-14f, /*maxTemp*/15f, /*minRain*/980f, /*maxRain*/4000f, /*minEVT*/0.5f, /*maxEVT*/9f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.GOLDEN_WAXCAP + "_Grassland", AthsGlobal.GOLDEN_WAXCAP, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/2, /*dispersion*/2, /*rarity*/7296, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-14f, /*maxTemp*/15f, /*minRain*/530f, /*maxRain*/710f, /*minEVT*/0.5f, /*maxEVT*/9f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.HARTS_TONGUE_FERN, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas","Africa","Europe"},
				/*size*/14, /*dispersion*/8, /*rarity*/9568, /*minAltitude*/0, /*maxAltitude*/160, /*minTemp*/3f, /*maxTemp*/22f, /*minRain*/750f, /*maxRain*/1300f, /*minEVT*/2f, /*maxEVT*/10f,/*forestGen*/-0.4f);
		athsPlantHelper(AthsGlobal.HEATHER /*Pink*/, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge"}, new String[]{"Europe"},
				/*size*/40, /*dispersion*/9, /*rarity*/484, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-5f, /*maxTemp*/20f, /*minRain*/160f, /*maxRain*/1000f, /*minEVT*/0f, /*maxEVT*/5f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.HEATHER + "_White", AthsGlobal.HEATHER, new int[] {1}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge"}, new String[]{"Europe"},
				/*size*/45, /*dispersion*/9, /*rarity*/384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-5f, /*maxTemp*/20f, /*minRain*/160f, /*maxRain*/1000f, /*minEVT*/0f, /*maxEVT*/5f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.HIBISCUS /*Orange*/, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/7, /*dispersion*/7, /*rarity*/6969, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/26f, /*maxTemp*/30f, /*minRain*/600f, /*maxRain*/1400f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.HIBISCUS + "_Pink", AthsGlobal.HIBISCUS, new int[] {1}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia"},
				/*size*/7, /*dispersion*/7, /*rarity*/6084, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/14f, /*maxTemp*/24f, /*minRain*/600f, /*maxRain*/1400f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.HIBISCUS + "_Red", AthsGlobal.HIBISCUS, new int[] {2}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia"},
				/*size*/7, /*dispersion*/7, /*rarity*/6684, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/17f, /*maxTemp*/30f, /*minRain*/600f, /*maxRain*/1400f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.HIBISCUS + "_White", AthsGlobal.HIBISCUS, new int[] {3}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/7, /*dispersion*/7, /*rarity*/6484, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/12f, /*maxTemp*/17f, /*minRain*/600f, /*maxRain*/1400f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.HOLLY, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Europe"},
		        /*size*/1, /*dispersion*/1, /*rarity*/3628, /*minAltitude*/144, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/15f, /*minRain*/650f, /*maxRain*/3000f, /*minEVT*/0.25f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.HOLLY + "_Thicket", AthsGlobal.HOLLY, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Europe"},
		        /*size*/50, /*dispersion*/3, /*rarity*/9528, /*minAltitude*/144, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/15f, /*minRain*/650f, /*maxRain*/3000f, /*minEVT*/0.25f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.HOSTA_HALCYON, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/8, /*dispersion*/4, /*rarity*/3628, /*minAltitude*/144, /*maxAltitude*/255, /*minTemp*/6f, /*maxTemp*/18f, /*minRain*/750f, /*maxRain*/4000f, /*minEVT*/0.5f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.HOSTA_PATRIOT, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/8, /*dispersion*/4, /*rarity*/3528, /*minAltitude*/144, /*maxAltitude*/255, /*minTemp*/6f, /*maxTemp*/18f, /*minRain*/750f, /*maxRain*/4000f, /*minEVT*/0.5f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.HOSTA_VULCAN, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/8, /*dispersion*/4, /*rarity*/3828, /*minAltitude*/144, /*maxAltitude*/255, /*minTemp*/6f, /*maxTemp*/18f, /*minRain*/750f, /*maxRain*/4000f, /*minEVT*/0.5f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.HOSTA_NARROW_LEAVED, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/8, /*dispersion*/4, /*rarity*/3828, /*minAltitude*/144, /*maxAltitude*/255, /*minTemp*/6f, /*maxTemp*/18f, /*minRain*/750f, /*maxRain*/4000f, /*minEVT*/0.5f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.HOSTA_TOUCH_OF_CLASS, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/8, /*dispersion*/4, /*rarity*/3828, /*minAltitude*/144, /*maxAltitude*/255, /*minTemp*/6f, /*maxTemp*/18f, /*minRain*/750f, /*maxRain*/4000f, /*minEVT*/0.5f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.HOSTA_SUM_AND_SUBSTANCE, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/8, /*dispersion*/4, /*rarity*/3828, /*minAltitude*/144, /*maxAltitude*/255, /*minTemp*/6f, /*maxTemp*/18f, /*minRain*/750f, /*maxRain*/4000f, /*minEVT*/0.5f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.HOSTA_FRANCES_WILLIAMS, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/8, /*dispersion*/4, /*rarity*/3828, /*minAltitude*/144, /*maxAltitude*/255, /*minTemp*/6f, /*maxTemp*/18f, /*minRain*/750f, /*maxRain*/4000f, /*minEVT*/0.5f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.HOSTA_ELEGANS, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/8, /*dispersion*/4, /*rarity*/3828, /*minAltitude*/144, /*maxAltitude*/255, /*minTemp*/6f, /*maxTemp*/18f, /*minRain*/750f, /*maxRain*/4000f, /*minEVT*/0.5f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.HYDRANGEA /*Anabelle*/, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"Plains","Rolling Hills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/4, /*dispersion*/4, /*rarity*/5884, /*minAltitude*/0, /*maxAltitude*/170, /*minTemp*/8f, /*maxTemp*/17f, /*minRain*/680f, /*maxRain*/2600f, /*minEVT*/1f, /*maxEVT*/6f,/*forestGen*/0.8f);
		athsPlantHelper(AthsGlobal.HYDRANGEA + "_Limelight", AthsGlobal.HYDRANGEA, new int[] {1}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"Plains","Rolling Hills","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia"},
				/*size*/4, /*dispersion*/4, /*rarity*/5284, /*minAltitude*/0, /*maxAltitude*/170, /*minTemp*/8f, /*maxTemp*/17f, /*minRain*/680f, /*maxRain*/2600f, /*minEVT*/1f, /*maxEVT*/6f,/*forestGen*/0.8f);
		athsPlantHelper(AthsGlobal.HYDRANGEA + "_Passion", AthsGlobal.HYDRANGEA, new int[] {2}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"Plains","Rolling Hills","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia"},
				/*size*/4, /*dispersion*/4, /*rarity*/6384, /*minAltitude*/0, /*maxAltitude*/170, /*minTemp*/8f, /*maxTemp*/17f, /*minRain*/680f, /*maxRain*/2600f, /*minEVT*/1f, /*maxEVT*/6f,/*forestGen*/0.8f);
		athsPlantHelper(AthsGlobal.HYDRANGEA + "_Nikko_Blue", AthsGlobal.HYDRANGEA, new int[] {3}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"Plains","Rolling Hills","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia"},
				/*size*/4, /*dispersion*/4, /*rarity*/6884, /*minAltitude*/0, /*maxAltitude*/170, /*minTemp*/8f, /*maxTemp*/17f, /*minRain*/680f, /*maxRain*/2600f, /*minEVT*/1f, /*maxEVT*/6f,/*forestGen*/0.8f);
		athsPlantHelper(AthsGlobal.INDIAN_PIPE, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Americas"},
				/*size*/3, /*dispersion*/1, /*rarity*/11384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/6f, /*maxTemp*/13f, /*minRain*/780f, /*maxRain*/10000f, /*minEVT*/0.5f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.INK_CAP, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Europe"},
				/*size*/2, /*dispersion*/2, /*rarity*/7896, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-6f, /*maxTemp*/15f, /*minRain*/480f, /*maxRain*/800f, /*minEVT*/0.5f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.INDIGO_MILK_CAP, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/3, /*dispersion*/4, /*rarity*/4512, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/24f, /*minRain*/750f, /*maxRain*/3000f, /*minEVT*/1f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.INTERRUPTED_FERN, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/25, /*dispersion*/6, /*rarity*/3768, /*minAltitude*/0, /*maxAltitude*/180, /*minTemp*/-6f, /*maxTemp*/18f, /*minRain*/770f, /*maxRain*/8000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/0.7f);	
		athsPlantHelper(AthsGlobal.IRIS, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Mountains","Mountain Range","Mountain range Edge","Foothills","Mountains Edge","High Plains","Riverbank"}, new String[]{"Americas","Asia","Europe"},
				/*size*/6, /*dispersion*/5, /*rarity*/5384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/13f, /*minRain*/400f, /*maxRain*/680f, /*minEVT*/0f, /*maxEVT*/2f);
		athsPlantHelper(AthsGlobal.JACK_IN_THE_PULPIT, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/3, /*dispersion*/4, /*rarity*/984, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/3f, /*maxTemp*/16f, /*minRain*/800f, /*maxRain*/2000f, /*minEVT*/3f, /*maxEVT*/8f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.JACK_O_LANTERN_MUSHROOM, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe","Asia","Americas"},
				/*size*/2, /*dispersion*/2, /*rarity*/8296, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-4f, /*maxTemp*/18f, /*minRain*/700f, /*maxRain*/4000f, /*minEVT*/0.5f, /*maxEVT*/5f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.JADE_PLANT, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/9, /*dispersion*/3, /*rarity*/1984, /*minAltitude*/144, /*maxAltitude*/220, /*minTemp*/15f, /*maxTemp*/21f, /*minRain*/60f, /*maxRain*/160f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.JAPANESE_STILTGRASS, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/52, /*dispersion*/1, /*rarity*/7884, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-2f, /*maxTemp*/14f, /*minRain*/880f, /*maxRain*/1600f, /*minEVT*/2f, /*maxEVT*/8f);
		athsPlantHelper(AthsGlobal.JIAN_CHUN_LUO, new int[] {0,1}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe","Asia","Africa"},
				/*size*/4, /*dispersion*/4, /*rarity*/7984, /*minAltitude*/0, /*maxAltitude*/150, /*minTemp*/-4f, /*maxTemp*/19f, /*minRain*/660f, /*maxRain*/1090f, /*minEVT*/1f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.LADY_FERN, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Asia","Europe"},
				/*size*/20, /*dispersion*/8, /*rarity*/5968, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-7f, /*maxTemp*/20f, /*minRain*/800f, /*maxRain*/4000f, /*minEVT*/1f, /*maxEVT*/8f,/*forestGen*/0.5f);
		athsPlantHelper(AthsGlobal.LAMBS_EAR, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/20, /*dispersion*/8, /*rarity*/5968, /*minAltitude*/144, /*maxAltitude*/255, /*minTemp*/7f, /*maxTemp*/17f, /*minRain*/230f, /*maxRain*/800f, /*minEVT*/1f, /*maxEVT*/8f,/*forestGen*/0.5f);
		athsPlantHelper(AthsGlobal.LAVENDER, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains"}, new String[]{"Africa","Asia","Europe"},
				/*size*/94, /*dispersion*/4, /*rarity*/8684, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/25f, /*minRain*/400f, /*maxRain*/700f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.LEAFY_LOW_UNDERGROWTH /*Normal*/, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","River","Lake","Riverbank","Swamp"}, new String[]{"Americas", "Asia", "Africa", "Europe"},
				/*size*/18, /*dispersion*/1, /*rarity*/488, /*minAltitude*/0, /*maxAltitude*/200, /*minTemp*/-2f, /*maxTemp*/30f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.LEAFY_LOW_UNDERGROWTH + "_Variegated", AthsGlobal.LEAFY_LOW_UNDERGROWTH, new int[] {1}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","River","Lake","Riverbank","Swamp"}, new String[]{"Americas", "Asia", "Africa", "Europe"},
				/*size*/18, /*dispersion*/1, /*rarity*/1888, /*minAltitude*/0, /*maxAltitude*/200, /*minTemp*/-2f, /*maxTemp*/30f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.LEAFY_UNDERGROWTH, new int[] {0,1,2}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia", "Africa", "Europe"},
				/*size*/15, /*dispersion*/5, /*rarity*/128, /*minAltitude*/0, /*maxAltitude*/230, /*minTemp*/0f, /*maxTemp*/40f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.LEOPARD_ORCHID, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Africa"},
				/*size*/3, /*dispersion*/6, /*rarity*/7096, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/16f, /*maxTemp*/25f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/1f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.LILY_PAD, new int[] {0,1,2}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:Ice"}, AthsGlobal.SHALLOW_WATER_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/18, /*dispersion*/3, /*rarity*/264, /*minAltitude*/140, /*maxAltitude*/160, /*minTemp*/-20f, /*maxTemp*/40f, /*minRain*/600f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.LITHOPS, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/24, /*dispersion*/8, /*rarity*/7984, /*minAltitude*/144, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/40f, /*minRain*/0f, /*maxRain*/180f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.LOBSTER_CLAWS, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/4, /*dispersion*/3, /*rarity*/3536, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/23f, /*maxTemp*/40f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/1f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.LOOSE_FLOWERED_ORCHID, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"High Plains","Estuary","Plains","Swamp","Salt Swamp","Riverbank","Lakeshore","Rolling Hills"}, new String[]{"Europe","Asia"},
				/*size*/5, /*dispersion*/9, /*rarity*/3556, /*minAltitude*/0, /*maxAltitude*/185, /*minTemp*/2f, /*maxTemp*/17f, /*minRain*/550f, /*maxRain*/850f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.LOTUS, new int[] {0,1,2}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:Ice"}, AthsGlobal.SHALLOW_WATER_BIOMES, new String[]{"Asia"},
				/*size*/60, /*dispersion*/4, /*rarity*/1264, /*minAltitude*/140, /*maxAltitude*/160, /*minTemp*/2f, /*maxTemp*/24f, /*minRain*/450f, /*maxRain*/6000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.LURID_BOLETE, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/2, /*dispersion*/2, /*rarity*/6596, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/2f, /*maxTemp*/16f, /*minRain*/700f, /*maxRain*/4000f, /*minEVT*/0.5f, /*maxEVT*/5f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.LUPINE /*Blue*/, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/35, /*dispersion*/4, /*rarity*/7384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-3f, /*maxTemp*/20f, /*minRain*/650f, /*maxRain*/850f, /*minEVT*/0.5f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.LUPINE + "_Purple", AthsGlobal.LUPINE, new int[] {1}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/35, /*dispersion*/4, /*rarity*/7384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-8f, /*maxTemp*/14f, /*minRain*/650f, /*maxRain*/850f, /*minEVT*/0.5f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.LUPINE + "_Red", AthsGlobal.LUPINE, new int[] {2}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","High Hills Edge","Foothills","Mountains","Mountain Range","Mountain Range Edge","Mountains Edge"}, new String[]{"Americas"},
				/*size*/35, /*dispersion*/4, /*rarity*/7384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/7f, /*maxTemp*/16f, /*minRain*/650f, /*maxRain*/850f, /*minEVT*/0.5f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.LUPINE + "_Yellow", AthsGlobal.LUPINE, new int[] {3}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","High Hills Edge","Foothills","Mountains","Mountain Range","Mountain Range Edge","Mountains Edge"}, new String[]{"Americas"},
				/*size*/35, /*dispersion*/4, /*rarity*/7384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/10f, /*maxTemp*/15f, /*minRain*/650f, /*maxRain*/850f, /*minEVT*/0.5f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.MADONNA_LILY, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/4, /*dispersion*/3, /*rarity*/4984, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/13f, /*maxTemp*/19f, /*minRain*/460f, /*maxRain*/790f, /*minEVT*/1f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.MAIDENHAIR_FERN, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia","Americas"},
				/*size*/40, /*dispersion*/3, /*rarity*/5484, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/3f, /*maxTemp*/17f, /*minRain*/750f, /*maxRain*/10000f, /*minEVT*/0f, /*maxEVT*/5f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.MALLOW, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"Lake","Estuary","Lakeshore","Riverbank","Swamp","River"}, new String[]{"Africa","Europe","Asia"},
				/*size*/70, /*dispersion*/4, /*rarity*/784, /*minAltitude*/0, /*maxAltitude*/148, /*minTemp*/5f, /*maxTemp*/30f, /*minRain*/450f, /*maxRain*/2400f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.MARIGOLD, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/10, /*dispersion*/6, /*rarity*/1284, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/13f, /*maxTemp*/28f, /*minRain*/140f, /*maxRain*/360f, /*minEVT*/1f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.MARTAGON_LILY /*Claude_Shride*/, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe","Asia"},
				/*size*/4, /*dispersion*/3, /*rarity*/4484, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/7f, /*maxTemp*/16f, /*minRain*/410f, /*maxRain*/720f, /*minEVT*/1f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.MARTAGON_LILY + "_Orange_Marmelade", AthsGlobal.MARTAGON_LILY, new int[] {1}, new String[] {"ore:blockSoil","ore:blockSand"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe","Asia"},
				/*size*/4, /*dispersion*/3, /*rarity*/3884, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/7f, /*maxTemp*/16f, /*minRain*/410f, /*maxRain*/720f, /*minEVT*/1f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.MARTAGON_LILY + "_Snowy_Morning", AthsGlobal.MARTAGON_LILY, new int[] {2}, new String[] {"ore:blockSoil","ore:blockSand"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe","Asia"},
				/*size*/4, /*dispersion*/3, /*rarity*/4184, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/7f, /*maxTemp*/16f, /*minRain*/410f, /*maxRain*/720f, /*minEVT*/1f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.MARTAGON_LILY + "_Chameleon", AthsGlobal.MARTAGON_LILY, new int[] {3}, new String[] {"ore:blockSoil","ore:blockSand"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe","Asia"},
				/*size*/4, /*dispersion*/3, /*rarity*/4984, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/7f, /*maxTemp*/16f, /*minRain*/410f, /*maxRain*/720f, /*minEVT*/1f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.MATONIA_FERN, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/19, /*dispersion*/4, /*rarity*/6584, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/23f, /*maxTemp*/40f, /*minRain*/1500f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.MAYAPPLE, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","River","Lake","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/24, /*dispersion*/1, /*rarity*/1288, /*minAltitude*/0, /*maxAltitude*/200, /*minTemp*/-2f, /*maxTemp*/15f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.MEDIUM_UNDERGROWTH, new int[] {0,1,2}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp","Salt Swamp"}, new String[]{"Americas", "Asia", "Africa", "Europe"},
				/*size*/8, /*dispersion*/4, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-6f, /*maxTemp*/40f, /*minRain*/350f, /*maxRain*/1000f, /*minEVT*/0f, /*maxEVT*/2f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.MOREL, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas", "Asia", "Europe","Africa"},
				/*size*/2, /*dispersion*/2, /*rarity*/9096, /*minAltitude*/0, /*maxAltitude*/220, /*minTemp*/-2f, /*maxTemp*/14f, /*minRain*/700f, /*maxRain*/4000f, /*minEVT*/0.5f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.NARBON_VETCH, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe","Asia"},
				/*size*/34, /*dispersion*/3, /*rarity*/5384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/6f, /*maxTemp*/22f, /*minRain*/450f, /*maxRain*/900f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.NETTLE, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe","Asia","Americas","Africa"},
				/*size*/25, /*dispersion*/3, /*rarity*/3484, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-6f, /*maxTemp*/23f, /*minRain*/470f, /*maxRain*/930f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.NIPA_PALM, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand","terrafirmacraftplus:Peat","ore:block:Gravel"}, new String[]{"Salt_Swamp","Estuary"}, new String[]{"Asia"},
				/*size*/25, /*dispersion*/3, /*rarity*/3756, /*minAltitude*/0, /*maxAltitude*/150, /*minTemp*/18f, /*maxTemp*/40f, /*minRain*/800f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/8f);
		athsPlantHelper(AthsGlobal.NORTHERN_BUSH_HONEYSUCKLE, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/15, /*dispersion*/2, /*rarity*/4484, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-8f, /*maxTemp*/15f, /*minRain*/650f, /*maxRain*/1300f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.NORTHERN_OAK_FERN, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe","Asia","Americas"},
				/*size*/38, /*dispersion*/4, /*rarity*/7784, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-12f, /*maxTemp*/13f, /*minRain*/680f, /*maxRain*/8000f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.NORTHERN_OAK_FERN + "_Oak", AthsGlobal.NORTHERN_OAK_FERN, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe","Asia","Americas"},
				/*size*/24, /*dispersion*/2, /*rarity*/4984, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/5f, /*maxTemp*/15f, /*minRain*/500f, /*maxRain*/1200f, /*minEVT*/0.5f, /*maxEVT*/2f);
		athsPlantHelper(AthsGlobal.OCOTILLO, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand","ore:blockGravel"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge","Mountains","Mountain Range Edge"}, new String[]{"Americas"},
				/*size*/10, /*dispersion*/17, /*rarity*/3050, /*minAltitude*/0, /*maxAltitude*/220, /*minTemp*/13f, /*maxTemp*/26f, /*minRain*/85f, /*maxRain*/230f, /*minEVT*/1f, /*maxEVT*/5.5f);
		athsPlantHelper(AthsGlobal.OLD_MAN_OF_THE_WOODS, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"High Hills","Mountains", "Mountains Edge","Foothills","Mountain Range Edge","Mountain Range"}, new String[]{"Americas","Europe"},
				/*size*/2, /*dispersion*/2, /*rarity*/5596, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-10f, /*maxTemp*/10f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/1f, /*maxEVT*/7f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.ORGAN_PIPE_CACTUS, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand","ore:blockGravel"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge","Mountains","Mountain Range Edge"}, new String[]{"Americas"},
				/*size*/6, /*dispersion*/24, /*rarity*/2048, /*minAltitude*/0, /*maxAltitude*/200, /*minTemp*/16f, /*maxTemp*/30f, /*minRain*/100f, /*maxRain*/190f, /*minEVT*/0f, /*maxEVT*/3.5f);
		athsPlantHelper(AthsGlobal.OSTRICH_FERN, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia","Europe"},
				/*size*/25, /*dispersion*/6, /*rarity*/4868, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-5f, /*maxTemp*/20f, /*minRain*/800f, /*maxRain*/8000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/0.3f);
		athsPlantHelper(AthsGlobal.PANTHER_MUSHROOM, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Europe"},
				/*size*/2, /*dispersion*/2, /*rarity*/6596, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/3f, /*maxTemp*/11f, /*minRain*/550f, /*maxRain*/1500f, /*minEVT*/1f, /*maxEVT*/7f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.PAINTED_FERN, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/16, /*dispersion*/4, /*rarity*/6468, /*minAltitude*/144, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/18f, /*minRain*/800f, /*maxRain*/6000f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/0.5f);
		athsPlantHelper(AthsGlobal.PAPYRUS, new int[] {0}, new String[] {"ore:blockSoil", "terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:Peat"}, new String[]{"Lake","Estuary","Lakeshore","Riverbank","River"}, new String[]{"Africa"},
				/*size*/150, /*dispersion*/3, /*rarity*/524, /*minAltitude*/0, /*maxAltitude*/145, /*minTemp*/17f, /*maxTemp*/40f, /*minRain*/80f, /*maxRain*/3400f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.PAPYRUS + "_Swamp", AthsGlobal.PAPYRUS, new int[] {0}, new String[] {"ore:blockSoil", "terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:Peat"}, new String[]{"Swamp"}, new String[]{"Africa"},
				/*size*/80, /*dispersion*/4, /*rarity*/1024, /*minAltitude*/0, /*maxAltitude*/145, /*minTemp*/17f, /*maxTemp*/40f, /*minRain*/100f, /*maxRain*/3400f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.PEACE_LILY, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/3, /*dispersion*/2, /*rarity*/6384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/21f, /*maxTemp*/33f, /*minRain*/1650f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.PERIWINKLE, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","River","Lake","Riverbank","Swamp"}, new String[]{"Europe"},
				/*size*/18, /*dispersion*/1, /*rarity*/3028, /*minAltitude*/0, /*maxAltitude*/200, /*minTemp*/8f, /*maxTemp*/18f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/1f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.PINEDROPS, new int[] {0}, new String[] {"ore:blockSoil"}, new String[] {"High Hills","High Hills Edge","Plains","Rolling Hills","Estuary","Riverbank","Salt Swamp","Swamp","Lakeshore"}, new String[]{"Americas"},
				/*size*/4, /*dispersion*/4, /*rarity*/5984, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-3f, /*maxTemp*/24f, /*minRain*/650f, /*maxRain*/1000f, /*minEVT*/0f, /*maxEVT*/8f);
		athsPlantHelper(AthsGlobal.PLANTAIN_WEED, new int[] {0}, new String[] {"ore:blockSoil"}, new String[] {"High Hills","High Hills Edge","Mountains","Mountain Range","Mountain Range Edge","Foothills","Mountains Edge","Estuary","Riverbank","Salt Swamp","Swamp","Lakeshore"}, new String[]{"Americas","Europe","Asia"},
				/*size*/23, /*dispersion*/2, /*rarity*/3984, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-5f, /*maxTemp*/35f, /*minRain*/450f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.POISON_IVY, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","River","Lake","Riverbank","Swamp"}, new String[]{"Americas", "Asia"},
				/*size*/18, /*dispersion*/1, /*rarity*/4888, /*minAltitude*/0, /*maxAltitude*/200, /*minTemp*/-2f, /*maxTemp*/22f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.GIANT_PHILODENDRON, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/8, /*dispersion*/6, /*rarity*/3784, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/23f, /*maxTemp*/40f, /*minRain*/1000f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/3f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.POINSETTIA, new int[] {0,1,2}, new String[] {"ore:blockSoil","ore:blockGravel"}, new String[]{"Mountains Edge","Foothills","Mountain Range Edge","High Plains","High Hills","High Hills Edge"}, new String[]{"Americas"},
				/*size*/9, /*dispersion*/6, /*rarity*/5384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/20f, /*maxTemp*/28f, /*minRain*/460f, /*maxRain*/740f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/0f);
		athsPlantHelper(AthsGlobal.POKEWEED, new int[] {0,1}, new String[] {"ore:blockSoil"}, new String[]{"Plains","Rolling Hills","High Plains","High Hills","High Hills Edge","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/14, /*dispersion*/3, /*rarity*/3084, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/14f, /*minRain*/700f, /*maxRain*/3000f, /*minEVT*/0.5f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.POND_GRASS, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Mountains","Mountain Range Edge","Mountain Range","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Americas", "Asia"},
				/*size*/20, /*dispersion*/2, /*rarity*/256, /*minAltitude*/0, /*maxAltitude*/146, /*minTemp*/-15f, /*maxTemp*/40f, /*minRain*/250f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.PRAIRIE_GRASS, new int[] {0,1,2}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"Plains","High Hills","Mountains","Rolling Hills","High Hills Edge","Foothills","Mountain Range Edge","Mountain Range","High Plains"}, new String[]{"Americas", "Asia", "Africa", "Europe"},
				/*size*/100, /*dispersion*/4, /*rarity*/4128, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/18f, /*minRain*/200f, /*maxRain*/750f, /*minEVT*/0f, /*maxEVT*/2f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.PRAIRIE_GRASS + "_DensePrairie", AthsGlobal.PRAIRIE_GRASS, new int[] {0,1,2}, new String[] {"ore:blockSoil"}, new String[]{"Plains","High Hills","Mountains","Rolling Hills","High Hills Edge","Foothills","Mountain Range Edge","Mountain Range","High Plains"}, new String[]{"Americas", "Asia", "Africa", "Europe"},
				/*size*/120, /*dispersion*/6, /*rarity*/530, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/18f, /*minRain*/250f, /*maxRain*/420f, /*minEVT*/0f, /*maxEVT*/2f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.PRICKLY_PEAR, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge"}, new String[]{"Americas"},
				/*size*/12, /*dispersion*/10, /*rarity*/980, /*minAltitude*/0, /*maxAltitude*/190, /*minTemp*/13f, /*maxTemp*/25f, /*minRain*/70f, /*maxRain*/200f, /*minEVT*/0f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.PUFFBALL, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe","Americas","Africa","Asia"},
				/*size*/7, /*dispersion*/4, /*rarity*/4496, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-5f, /*maxTemp*/17f, /*minRain*/550f, /*maxRain*/1700f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.PURPLE_FAIRY_CLUB, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"High Hills","Mountains", "Mountains Edge","Foothills","Mountain Range Edge","Mountain Range"}, new String[]{"Americas"},
				/*size*/4, /*dispersion*/2, /*rarity*/6596, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-4f, /*maxTemp*/16f, /*minRain*/530f, /*maxRain*/16000f, /*minEVT*/1f, /*maxEVT*/7f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.QUAQUA, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge"}, new String[]{"Africa"},
				/*size*/10, /*dispersion*/7, /*rarity*/1580, /*minAltitude*/0, /*maxAltitude*/190, /*minTemp*/14f, /*maxTemp*/29f, /*minRain*/70f, /*maxRain*/320f, /*minEVT*/0f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.RAFFLESIA, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Asia"},
				/*size*/3, /*dispersion*/6, /*rarity*/10096, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/16f, /*maxTemp*/25f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/1f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.RATTLESNAKE_PLANT, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains"}, new String[]{"Americas"},
				/*size*/16, /*dispersion*/5, /*rarity*/3580, /*minAltitude*/0, /*maxAltitude*/220, /*minTemp*/19f, /*maxTemp*/25f, /*minRain*/280f, /*maxRain*/820f, /*minEVT*/0f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.REED_CANARY_GRASS, new int[] {0}, new String[] {"ore:blockSoil", "terrafirmacraftplus:FreshWaterStationary", "terrafirmacraftplus:SaltWaterStationary","terrafirmacraftplus:Peat"}, new String[]{"Lake","Estuary","Lakeshore","Riverbank","Swamp","Salt Swamp","River"}, new String[]{"Europe","Asia","Americas"},
				/*size*/90, /*dispersion*/2, /*rarity*/684, /*minAltitude*/0, /*maxAltitude*/145, /*minTemp*/2f, /*maxTemp*/26f, /*minRain*/450f, /*maxRain*/5400f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/-0.2f);
		athsPlantHelper(AthsGlobal.RED_CORAL_FUNGUS, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/2, /*dispersion*/2, /*rarity*/8596, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/5f, /*maxTemp*/16f, /*minRain*/1050f, /*maxRain*/16000f, /*minEVT*/0.5f, /*maxEVT*/4f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.RED_GOYO, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand"}, AthsGlobal.LAND_BIOMES, new String[]{"Asiaa"},
				/*size*/2, /*dispersion*/1, /*rarity*/9580, /*minAltitude*/0, /*maxAltitude*/190, /*minTemp*/-6f, /*maxTemp*/10f, /*minRain*/0f, /*maxRain*/220f, /*minEVT*/1f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.RED_HOT_MILK_CAP, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Europe"},
				/*size*/2, /*dispersion*/2, /*rarity*/7596, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-10f, /*maxTemp*/6f, /*minRain*/750f, /*maxRain*/2200f, /*minEVT*/1f, /*maxEVT*/5f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.REINDEER_LICHEN, new int[] {0}, new String[] {"ore:blockSoil","ore:blockStone"}, new String[] {"Mountain Range","Mountain Range Edge","High Hills","Mountains"}, new String[]{"Americas","Europe"},
				/*size*/150, /*dispersion*/2, /*rarity*/5296, /*minAltitude*/220, /*maxAltitude*/255, /*minTemp*/-30f, /*maxTemp*/4f, /*minRain*/150f, /*maxRain*/2000f, /*minEVT*/1f, /*maxEVT*/5f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.REINDEER_LICHEN + "_Stone", AthsGlobal.REINDEER_LICHEN, new int[] {0}, new String[] {"ore:blockStone"}, new String[] {"Mountain Range","Mountain Range Edge","High Hills","Mountains"}, new String[]{"Americas","Europe"},
				/*size*/250, /*dispersion*/1, /*rarity*/2696, /*minAltitude*/220, /*maxAltitude*/255, /*minTemp*/-30f, /*maxTemp*/4f, /*minRain*/150f, /*maxRain*/2000f, /*minEVT*/1f, /*maxEVT*/5f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.ROSEBUSH, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"Plains","Rolling Hills","Lakeshore","Riverbank","Swamp","High Hills","High Hills Edge"}, new String[]{"Africa","Americas","Asia","Europe"},
				/*size*/8, /*dispersion*/3, /*rarity*/4384, /*minAltitude*/0, /*maxAltitude*/180, /*minTemp*/-5f, /*maxTemp*/22f, /*minRain*/600f, /*maxRain*/900f, /*minEVT*/0.5f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.ROUGH_HORSETAIL, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"Plains","Lake","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Americas", "Asia", "Europe"},
				/*size*/35, /*dispersion*/2, /*rarity*/5512, /*minAltitude*/0, /*maxAltitude*/150, /*minTemp*/-10f, /*maxTemp*/12f, /*minRain*/600f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.ROYAL_CATCHFLY, new int[] {0,1}, new String[] {"ore:blockSoil"}, new String[]{"Plains","Rolling Hills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/6, /*dispersion*/3, /*rarity*/4384, /*minAltitude*/0, /*maxAltitude*/200, /*minTemp*/8f, /*maxTemp*/19f, /*minRain*/430f, /*maxRain*/850f, /*minEVT*/0.5f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.ROYAL_FERN /*Eurasian*/, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/15, /*dispersion*/4, /*rarity*/4768, /*minAltitude*/0, /*maxAltitude*/180, /*minTemp*/-1f, /*maxTemp*/15f, /*minRain*/890f, /*maxRain*/1500f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/0.7f);	
		athsPlantHelper(AthsGlobal.ROYAL_FERN + "American", AthsGlobal.ROYAL_FERN, new int[] {1}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/15, /*dispersion*/4, /*rarity*/4768, /*minAltitude*/0, /*maxAltitude*/180, /*minTemp*/-1f, /*maxTemp*/15f, /*minRain*/890f, /*maxRain*/1500f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/0.7f);	
		athsPlantHelper(AthsGlobal.ROYAL_JASMINE, new int[] {0,1,2}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/50, /*dispersion*/3, /*rarity*/4028, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/13f, /*maxTemp*/30f, /*minRain*/580f, /*maxRain*/8000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.RUBBER_FIG, new int[] {0,1,2}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/4, /*dispersion*/7, /*rarity*/4028, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/18f, /*maxTemp*/31f, /*minRain*/2080f, /*maxRain*/16000f, /*minEVT*/1.5f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.SAGEBRUSH, new int[] {0,1,2}, new String[] {"ore:blockSoil","ore:blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge","Mountains","Mountain Range","Mountain Range Edge"}, new String[]{"Americas"},
				/*size*/100, /*dispersion*/8, /*rarity*/450, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/25f, /*minRain*/135f, /*maxRain*/200f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.SAGEBRUSH + "_Transition", AthsGlobal.SAGEBRUSH, new int[] {0,1,2}, new String[] {"ore:blockSoil","ore:blockSand"}, new String[]{"High Hills","High Hills Edge","High Plains","Foothills","Mountains Edge","Mountains","Mountain Range","Mountain Range Edge"}, new String[]{"Americas"},
				/*size*/20, /*dispersion*/20, /*rarity*/750, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/25f, /*minRain*/100f, /*maxRain*/135f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.SAGEBRUSH + "_Grassland", AthsGlobal.SAGEBRUSH, new int[] {0,1,2}, new String[] {"ore:blockSoil","ore:blockSand"}, new String[]{"High Hills","High Hills Edge","High Plains","Foothills","Mountains Edge","Mountains","Mountain Range","Mountain Range Edge"}, new String[]{"Americas"},
				/*size*/30, /*dispersion*/14, /*rarity*/6000, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/25f, /*minRain*/200f, /*maxRain*/300f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.SAGEBRUSH + "_Riverbank", AthsGlobal.SAGEBRUSH, new int[] {0,1,2}, new String[] {"ore:blockSoil","ore:blockSand"}, new String[]{"Riverbank"}, new String[]{"Americas"},
				/*size*/20, /*dispersion*/20, /*rarity*/750, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/25f, /*minRain*/25f, /*maxRain*/135f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/0.5f);
		athsPlantHelper(AthsGlobal.SAGUARO, new int[] {0,1,2,3,4}, new String[] {"ore:blockSoil","ore:blockSand"}, new String[]{"Plains","High Hills Edge","Rolling Hills","High Plains","Foothills"}, new String[]{"Americas"},
				/*size*/1, /*dispersion*/1, /*rarity*/456, /*minAltitude*/0, /*maxAltitude*/190, /*minTemp*/17f, /*maxTemp*/31f, /*minRain*/100f, /*maxRain*/200f, /*minEVT*/1f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.SAXAUL, new int[] {0,1,2,3}, new String[] {"ore:blockSoil","ore:blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge","Mountains","Mountain Range","Mountain Range Edge"}, new String[]{"Asia"},
				/*size*/25, /*dispersion*/18, /*rarity*/3600, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/30f, /*minRain*/110f, /*maxRain*/200f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.SAXAUL + "_ExtremeDesert", AthsGlobal.SAXAUL, new int[] {0,1,2,3}, new String[] {"ore:blockSoil","ore:blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge","Mountains","Mountain Range","Mountain Range Edge"}, new String[]{"Asia"},
				/*size*/15, /*dispersion*/24, /*rarity*/3900, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/30f, /*minRain*/60f, /*maxRain*/90f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.SAXAUL + "_Riverbank", AthsGlobal.SAXAUL, new int[] {0,1,2}, new String[] {"ore:blockSoil","ore:blockSand"}, new String[]{"Riverbank"}, new String[]{"Asia"},
				/*size*/12, /*dispersion*/10, /*rarity*/2050, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/30f, /*minRain*/25f, /*maxRain*/135f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/0.5f);
		athsPlantHelper(AthsGlobal.SALTWORT, new int[] {0,1}, new String[] {"ore:blockSoil","ore:blockSand","terrafirmacraftplus:Peat"}, new String[]{"Salt Swamp","Estuary"}, new String[]{"Africa","Asia","Europe","Americas"},
				/*size*/12, /*dispersion*/5, /*rarity*/750, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/30f, /*minRain*/100f, /*maxRain*/3000f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.SALTWORT + "_AfricaDesert", AthsGlobal.SALTWORT, new int[] {0,1}, new String[] {"ore:blockSoil","ore:blockSand","terrafirmacraftplus:Peat"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge","Mountains","Mountain Range","Mountain Range Edge"}, new String[]{"Africa"},
				/*size*/30, /*dispersion*/9, /*rarity*/4800, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/30f, /*minRain*/120f, /*maxRain*/200f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.SALTWORT + "_Riverbank", AthsGlobal.SALTWORT, new int[] {0,1}, new String[] {"ore:blockSoil","ore:blockSand","terrafirmacraftplus:Peat"}, new String[]{"Riverbank"}, new String[]{"Americas"},
				/*size*/15, /*dispersion*/10, /*rarity*/1550, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/30f, /*minRain*/25f, /*maxRain*/135f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/0.5f);
		athsPlantHelper(AthsGlobal.SALTWORT + "_AfricaExtremeDesert", AthsGlobal.SALTWORT, new int[] {0,1}, new String[] {"ore:blockSoil","ore:blockSand","terrafirmacraftplus:Peat"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge","Mountains","Mountain Range","Mountain Range Edge"}, new String[]{"Africa"},
				/*size*/20, /*dispersion*/12, /*rarity*/6950, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/30f, /*minRain*/70f, /*maxRain*/120f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.SCALY_TREE_FERN, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia"},
				/*size*/10, /*dispersion*/30, /*rarity*/8084, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/10f, /*maxTemp*/30f, /*minRain*/1000f, /*maxRain*/16000f, /*minEVT*/2f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.SCARLET_ELFCUP, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Europe","Africa","Asia"},
				/*size*/3, /*dispersion*/1, /*rarity*/7796, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-5f, /*maxTemp*/35f, /*minRain*/900f, /*maxRain*/16000f, /*minEVT*/0.5f, /*maxEVT*/4f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.SENSITIVE_FERN, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Asia","Americas"},
				/*size*/17, /*dispersion*/5, /*rarity*/6084, /*minAltitude*/0, /*maxAltitude*/200, /*minTemp*/0f, /*maxTemp*/14f, /*minRain*/750f, /*maxRain*/10000f, /*minEVT*/0f, /*maxEVT*/5f,/*forestGen*/0.5f);
		athsPlantHelper(AthsGlobal.SESAME, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa", "Asia"},
				/*size*/95, /*dispersion*/6, /*rarity*/4556, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/30f, /*minRain*/250f, /*maxRain*/750f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.SHAGGY_MANE, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Europe"},
				/*size*/3, /*dispersion*/2, /*rarity*/6796, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/3f, /*maxTemp*/17f, /*minRain*/600f, /*maxRain*/950f, /*minEVT*/0.5f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.SHITAKE, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/4, /*dispersion*/2, /*rarity*/4896, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/12f, /*maxTemp*/24f, /*minRain*/800f, /*maxRain*/2000f, /*minEVT*/0.5f, /*maxEVT*/6f);
		athsPlantHelper(AthsGlobal.SIBERIAN_SQUILL, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","High Hills Edge","High Plains","Mountains","Mountain Range Edge","Mountain Range","Foothills"}, new String[]{"Asia"},
				/*size*/30, /*dispersion*/1, /*rarity*/6684, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/22f, /*minRain*/440f, /*maxRain*/980f, /*minEVT*/0f, /*maxEVT*/3f, /*forestGen*/-0.4f);
		athsPlantHelper(AthsGlobal.SNAKE_SANSEVERIA, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge"}, new String[]{"Africa","Asia"},
				/*size*/60, /*dispersion*/5, /*rarity*/6980, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/22f, /*maxTemp*/40f, /*minRain*/170f, /*maxRain*/2440f, /*minEVT*/0f, /*maxEVT*/5f/*forestGen*/-0.2f);
		athsPlantHelper(AthsGlobal.SNAKE_SANSEVERIA + "_Variegated", AthsGlobal.SNAKE_SANSEVERIA, new int[] {1}, new String[] {"ore:blockSoil","ore:blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge"}, new String[]{"Africa","Asia"},
				/*size*/60, /*dispersion*/5, /*rarity*/6980, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/22f, /*maxTemp*/40f, /*minRain*/170f, /*maxRain*/2440f, /*minEVT*/0f, /*maxEVT*/5f/*forestGen*/-0.2f);
		athsPlantHelper(AthsGlobal.SOLOMONS_SEAL /*Normal*/, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Americas","Europe"},
				/*size*/10, /*dispersion*/3, /*rarity*/4684, /*minAltitude*/0, /*maxAltitude*/200, /*minTemp*/-7f, /*maxTemp*/18f, /*minRain*/750f, /*maxRain*/2000f, /*minEVT*/0f, /*maxEVT*/5f,/*forestGen*/1f);
		athsPlantHelper(AthsGlobal.SOLOMONS_SEAL + "_Variegated", AthsGlobal.SOLOMONS_SEAL, new int[] {1}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Americas","Europe"},
				/*size*/10, /*dispersion*/3, /*rarity*/6684, /*minAltitude*/0, /*maxAltitude*/200, /*minTemp*/-7f, /*maxTemp*/18f, /*minRain*/750f, /*maxRain*/2000f, /*minEVT*/0f, /*maxEVT*/5f,/*forestGen*/1f);
		athsPlantHelper(AthsGlobal.SORBUS, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Mountains","Mountains Edge","Mountain Range Edge","Mountain Range","High Hills Edge","High Plains","Foothills"}, new String[]{"Africa","Europe","Asia"},
				/*size*/1, /*dispersion*/1, /*rarity*/884, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-4f, /*maxTemp*/16f, /*minRain*/550f, /*maxRain*/1000f, /*minEVT*/0f, /*maxEVT*/8f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.SPIDER_PLANT, new int[] {0,1}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/16, /*dispersion*/4, /*rarity*/4784, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/20f, /*maxTemp*/40f, /*minRain*/750f, /*maxRain*/2300f, /*minEVT*/0f, /*maxEVT*/3f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.SPLIT_LEAF_MONSTERA, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/6, /*dispersion*/5, /*rarity*/5784, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/24f, /*maxTemp*/40f, /*minRain*/1050f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/3f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.STARFISH_PLANT, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge"}, new String[]{"Africa"},
				/*size*/6, /*dispersion*/5, /*rarity*/3980, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/20f, /*minRain*/100f, /*maxRain*/240f, /*minEVT*/0f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.STRAWBERRIES_AND_CREAM_MUSHROOM, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Europe"},
				/*size*/2, /*dispersion*/2, /*rarity*/8796, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/5f, /*maxTemp*/18f, /*minRain*/950f, /*maxRain*/16000f, /*minEVT*/0.5f, /*maxEVT*/4f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.STRAW_MUSHROOM, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/2, /*dispersion*/2, /*rarity*/6796, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/32f, /*minRain*/720f, /*maxRain*/1600f, /*minEVT*/0.5f, /*maxEVT*/4f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.STRICT_BRANCH_CORAL_FUNGUS, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Europe","Africa","Asia"},
				/*size*/2, /*dispersion*/2, /*rarity*/6396, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-4f, /*maxTemp*/15f, /*minRain*/960f, /*maxRain*/16000f, /*minEVT*/0.5f, /*maxEVT*/4f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.SUEDE_BOLETE, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Europe","Asia"},
				/*size*/2, /*dispersion*/2, /*rarity*/6796, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-9f, /*maxTemp*/13f, /*minRain*/750f, /*maxRain*/2600f, /*minEVT*/0.5f, /*maxEVT*/4f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.SULFUR_TUFT, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Europe","Asia"},
				/*size*/2, /*dispersion*/2, /*rarity*/7796, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/10f, /*maxTemp*/23f, /*minRain*/750f, /*maxRain*/1900f, /*minEVT*/0.5f, /*maxEVT*/4f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.SUMAC, new int[] {0,1}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/15, /*dispersion*/6, /*rarity*/4668, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/4f, /*maxTemp*/18f, /*minRain*/300f, /*maxRain*/1200f, /*minEVT*/0.25f, /*maxEVT*/2f);
		athsPlantHelper(AthsGlobal.SUNDEW, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"Mountains","Mountain Range", "Swamp","Salt Swamp"}, new String[]{"Americas","Africa"},
				/*size*/3, /*dispersion*/1, /*rarity*/14384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/15f, /*minRain*/400f, /*maxRain*/750f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.SUNFLOWER, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas"},
				/*size*/28, /*dispersion*/5, /*rarity*/6384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/22f, /*minRain*/400f, /*maxRain*/750f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.SWEET_JOE_PYE_WEED /*White*/, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/25, /*dispersion*/6, /*rarity*/6384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/1f, /*maxTemp*/16f, /*minRain*/380f, /*maxRain*/700f, /*minEVT*/0f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.SWEET_JOE_PYE_WEED + "_Pink", AthsGlobal.SWEET_JOE_PYE_WEED, new int[] {1}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/25, /*dispersion*/6, /*rarity*/4384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/1f, /*maxTemp*/16f, /*minRain*/380f, /*maxRain*/700f, /*minEVT*/0f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.SWORD_FERN, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Swamp","Mountains","Mountains Edge","Mountain Range Edge","Mountain Range"}, new String[]{"Americas"},
				/*size*/25, /*dispersion*/6, /*rarity*/1684, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-10f, /*maxTemp*/18f, /*minRain*/1000f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.SWORD_SANSEVERIA, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Foothills","Mountains Edge"}, new String[]{"Africa","Asia"},
				/*size*/80, /*dispersion*/5, /*rarity*/6980, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/19f, /*maxTemp*/36f, /*minRain*/55f, /*maxRain*/440f, /*minEVT*/0f, /*maxEVT*/5f/*forestGen*/-0.5f);
		athsPlantHelper(AthsGlobal.TEDDY_BEAR_CACTUS, new int[] {0,1}, new String[] {"ore:blockSand","ore:block"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/28, /*dispersion*/6, /*rarity*/5024, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/16f, /*maxTemp*/24f, /*minRain*/70f, /*maxRain*/245f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.THALE_CRESS, new int[] {0}, new String[] {"ore:blockSoil","ore:blockGravel","ore:blockSand","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe","Asia","Africa"},
				/*size*/35, /*dispersion*/4, /*rarity*/4556, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-8f, /*maxTemp*/23f, /*minRain*/350f, /*maxRain*/860f, /*minEVT*/0.5f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.THISTLE, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/7, /*dispersion*/11, /*rarity*/556, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-2f, /*maxTemp*/15f, /*minRain*/350f, /*maxRain*/750f, /*minEVT*/0f, /*maxEVT*/3f);
		athsPlantHelper(AthsGlobal.TITAN_ARUM, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Asia"},
				/*size*/2, /*dispersion*/7, /*rarity*/17192, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/20f, /*maxTemp*/28f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/1f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.TRAVELLERS_PALM, new int[] {0,1,2}, new String[] {"ore:blockSoil","ore:blockSand"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/9, /*dispersion*/12, /*rarity*/4156, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/20f, /*maxTemp*/40f, /*minRain*/600f, /*maxRain*/16000f, /*minEVT*/0.5f, /*maxEVT*/9f);
		athsPlantHelper(AthsGlobal.TRILLIUM, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/27, /*dispersion*/2, /*rarity*/2556, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-1f, /*maxTemp*/17f, /*minRain*/750f, /*maxRain*/2150f, /*minEVT*/0f, /*maxEVT*/7f);
		athsPlantHelper(AthsGlobal.VIOLET, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia","Africa","Europe"},
				/*size*/8, /*dispersion*/2, /*rarity*/1856, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-3f, /*maxTemp*/16f, /*minRain*/550f, /*maxRain*/2050f, /*minEVT*/0f, /*maxEVT*/7f);
		athsPlantHelper(AthsGlobal.VENUS_FLYTRAP, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"Lakeshore","Riverbank","Swamp","Salt Swamp"}, new String[]{"Americas"},
				/*size*/2, /*dispersion*/1, /*rarity*/4384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/10f, /*maxTemp*/16f, /*minRain*/700f, /*maxRain*/1000f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.VICTORIA_LILY_PAD, new int[] {0}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:Ice"}, new String[] {"Riverbank","Lakeshore"}, new String[]{"Americas"},
				/*size*/30, /*dispersion*/4, /*rarity*/4968, /*minAltitude*/140, /*maxAltitude*/160, /*minTemp*/20f, /*maxTemp*/40f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.VOMITING_RUSSULA, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Europe","Africa","Asia"},
				/*size*/2, /*dispersion*/2, /*rarity*/4196, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-11f, /*maxTemp*/12f, /*minRain*/830f, /*maxRain*/16000f, /*minEVT*/0.5f, /*maxEVT*/4f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.VOODOO_LILY, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary"}, new String[]{"Asia"},
				/*size*/3, /*dispersion*/6, /*rarity*/10096, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/16f, /*maxTemp*/25f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/1f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.WATER_HYACINTH, new int[] {0}, new String[] {"terrafirmacraftplus:FreshWaterStationary"}, new String[]{"Plains","Lakeshore","River","Riverbank"}, new String[]{"Americas"},
				/*size*/80, /*dispersion*/3, /*rarity*/1084, /*minAltitude*/0, /*maxAltitude*/170, /*minTemp*/18f, /*maxTemp*/35f, /*minRain*/650f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.WATER_PLANTAIN, new int[] {0}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:Ice","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/9, /*dispersion*/3, /*rarity*/204, /*minAltitude*/140, /*maxAltitude*/160, /*minTemp*/-20f, /*maxTemp*/40f, /*minRain*/600f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.WATER_PLANTAIN + "_River", AthsGlobal.WATER_PLANTAIN, new int[] {0}, new String[] {"terrafirmacraftplus:FreshWaterStationary","terrafirmacraftplus:Ice","terrafirmacraftplus:Peat"}, new String[]{"Riverbank","River","Lakeshore"}, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/9, /*dispersion*/3, /*rarity*/104, /*minAltitude*/140, /*maxAltitude*/160, /*minTemp*/-20f, /*maxTemp*/40f, /*minRain*/600f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.WEEPING_MILK_CAP, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Europe"},
				/*size*/2, /*dispersion*/2, /*rarity*/8796, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/3f, /*maxTemp*/15f, /*minRain*/750f, /*maxRain*/3000f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.WEEPING_MILK_CAP + "_Asian", AthsGlobal.WEEPING_MILK_CAP, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/2, /*dispersion*/2, /*rarity*/7796, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/5f, /*maxTemp*/27f, /*minRain*/750f, /*maxRain*/3000f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.WESTERN_SKUNK_CABBAGE, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"Lake","Lakeshore","Riverbank","Swamp","River","Mountain Range","Mountain Range Edge","Foothills","Mountais","Mountains Edge"}, new String[]{"Americas"},
				/*size*/43, /*dispersion*/2, /*rarity*/6012, /*minAltitude*/0, /*maxAltitude*/190, /*minTemp*/10f, /*maxTemp*/19f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/1f, /*maxEVT*/4f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.WELWITSCHIA, new int[] {0,1}, new String[] {"ore:blockSand"}, new String[]{"Beach","Gravel Beach","Lake","Lakeshore","Riverbank","Swamp","Salt Swamp","Estuary","Plains","High Plains"}, new String[]{"Africa"},
				/*size*/1, /*dispersion*/1, /*rarity*/1024, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/16f, /*maxTemp*/40f, /*minRain*/0f, /*maxRain*/45f, /*minEVT*/1f, /*maxEVT*/10f);
		athsPlantHelper(AthsGlobal.WHITE_SKUNK_CABBAGE, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, new String[]{"Lake","Lakeshore","Riverbank","Swamp","River"}, new String[]{"Asia"},
				/*size*/43, /*dispersion*/2, /*rarity*/7012, /*minAltitude*/0, /*maxAltitude*/160, /*minTemp*/-12f, /*maxTemp*/13f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.WINE_CAP, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Europe"},
				/*size*/2, /*dispersion*/2, /*rarity*/6396, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-2f, /*maxTemp*/14f, /*minRain*/730f, /*maxRain*/2400f, /*minEVT*/0f, /*maxEVT*/3f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.WOOD_BITTER_VETCH, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/34, /*dispersion*/3, /*rarity*/4984, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-2f, /*maxTemp*/20f, /*minRain*/450f, /*maxRain*/900f, /*minEVT*/0f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.WOOD_BLEWIT, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Europe"},
				/*size*/2, /*dispersion*/2, /*rarity*/5896, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-4f, /*maxTemp*/16f, /*minRain*/750f, /*maxRain*/2800f, /*minEVT*/0f, /*maxEVT*/3f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.WOOD_FERN, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/20, /*dispersion*/8, /*rarity*/4068, /*minAltitude*/144, /*maxAltitude*/160, /*minTemp*/-5f, /*maxTemp*/15f, /*minRain*/750f, /*maxRain*/6000f, /*minEVT*/0.5f, /*maxEVT*/6f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.WOOD_LILY /*Orange*/, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/6, /*dispersion*/3, /*rarity*/3884, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/17f, /*minRain*/510f, /*maxRain*/1230f, /*minEVT*/1f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.WOOD_LILY + "_Red", AthsGlobal.WOOD_LILY, new int[] {1}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/6, /*dispersion*/3, /*rarity*/4884, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/17f, /*minRain*/510f, /*maxRain*/1230f, /*minEVT*/1f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.WOOD_LILY + "_Yellow", AthsGlobal.WOOD_LILY, new int[] {2}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/6, /*dispersion*/3, /*rarity*/9884, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/17f, /*minRain*/510f, /*maxRain*/1230f, /*minEVT*/1f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.YARROW, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Europe","Americas"},
				/*size*/17, /*dispersion*/5, /*rarity*/6384, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-3f, /*maxTemp*/12f, /*minRain*/450f, /*maxRain*/700f, /*minEVT*/0.5f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.YUCCA, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand","ore:blockGravel"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/20, /*dispersion*/12, /*rarity*/3284, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/10f, /*maxTemp*/38f, /*minRain*/105f, /*maxRain*/680f, /*minEVT*/0.5f, /*maxEVT*/4f,/*forestGen*/-1.0f);
		athsPlantHelper(AthsGlobal.WOOD_POPPY, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/16, /*dispersion*/4, /*rarity*/8384, /*minAltitude*/0, /*maxAltitude*/160, /*minTemp*/3f, /*maxTemp*/13f, /*minRain*/750f, /*maxRain*/2110f, /*minEVT*/1f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.WOOD_POPPY + "_Calcareous", AthsGlobal.WOOD_POPPY, new int[] {0}, new String[] {"-placeholder for calcareous soil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/16, /*dispersion*/4, /*rarity*/2784, /*minAltitude*/0, /*maxAltitude*/160, /*minTemp*/3f, /*maxTemp*/13f, /*minRain*/750f, /*maxRain*/2110f, /*minEVT*/1f, /*maxEVT*/4f);
		athsPlantHelper(AthsGlobal.WOOLY_CHANTERELLE, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Asia"},
				/*size*/2, /*dispersion*/2, /*rarity*/6096, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-9f, /*maxTemp*/14f, /*minRain*/850f, /*maxRain*/6800f, /*minEVT*/0.5f, /*maxEVT*/4f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.WOOLY_MILK_CAP, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Europe","Africa","Asia"},
				/*size*/2, /*dispersion*/2, /*rarity*/6796, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-18f, /*maxTemp*/12f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.YELLOW_PARASOL_MUSHROOM, new int[] {0}, new String[] {"ore:blockSoil","terrafirmacraftplus:Peat"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Europe","Africa","Asia"},
				/*size*/2, /*dispersion*/2, /*rarity*/7796, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/18f, /*maxTemp*/40f, /*minRain*/930f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.ZZ_PLANT, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/12, /*dispersion*/4, /*rarity*/7296, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/22f, /*maxTemp*/40f, /*minRain*/370f, /*maxRain*/910f, /*minEVT*/0f, /*maxEVT*/6f,/*forestGen*/1.0f);
		athsTreeHelper(AthsGlobal.YOUNG_ACACIA /*Utacacia*/, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.UTACACIA);
		athsTreeHelper(AthsGlobal.YOUNG_ACACIA + "_Koa", AthsGlobal.YOUNG_ACACIA, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.KOA);
		athsTreeHelper(AthsGlobal.YOUNG_ASH, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia","Europe","Africa"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.ASH);
		athsTreeHelper(AthsGlobal.YOUNG_ASPEN, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia","Europe"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.ASPEN);
		athsTreeHelper(AthsGlobal.YOUNG_BAOBAB, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.BAOBAB);
		athsTreeHelper(AthsGlobal.YOUNG_BIRCH, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia", "Europe"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.BIRCH);
		athsTreeHelper(AthsGlobal.YOUNG_CHESTNUT, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia","Europe"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.CHESTNUT);
		athsTreeHelper(AthsGlobal.YOUNG_DOUGLAS_FIR, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.DOUGLASFIR);
		athsTreeHelper(AthsGlobal.YOUNG_EBONY, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa", "Asia"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.EBONY);
		athsTreeHelper(AthsGlobal.YOUNG_FEVER, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.FEVERTREE);
		athsTreeHelper(AthsGlobal.YOUNG_GHAF, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.GHAF);
		athsTreeHelper(AthsGlobal.YOUNG_GINGKO, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.GINGKO);
		athsTreeHelper(AthsGlobal.YOUNG_HICKORY, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.HICKORY);
		athsTreeHelper(AthsGlobal.YOUNG_KAPOK, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.KAPOK);
		athsTreeHelper(AthsGlobal.YOUNG_LAUREL, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/1, /*dispersion*/5, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.LAUREL);
		athsTreeHelper(AthsGlobal.YOUNG_LIMBA, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.LIMBA);
		athsTreeHelper(AthsGlobal.YOUNG_MAHOE, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.MAHOE);
		athsTreeHelper(AthsGlobal.YOUNG_MAHOGANY, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Africa"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.MAHOGANY);
		athsTreeHelper(AthsGlobal.YOUNG_MAPLE, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia", "Africa"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.MAPLE);
		athsTreeHelper(AthsGlobal.YOUNG_OAK, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia" ,"Africa", "Europe"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.OAK);
		athsTreeHelper(AthsGlobal.YOUNG_PINE, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia", "Africa", "Europe"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.PINE);
		athsTreeHelper(AthsGlobal.YOUNG_SEQUOIA, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.REDWOOD);
		athsTreeHelper(AthsGlobal.YOUNG_SPRUCE, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia", "Africa", "Europe"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.SPRUCE);
		athsTreeHelper(AthsGlobal.YOUNG_SYCAMORE, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe", "Asia"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.SYCAMORE);
		athsTreeHelper(AthsGlobal.YOUNG_TEAK, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.TEAK);
		athsTreeHelper(AthsGlobal.YOUNG_WHITE_CEDAR, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.WHITECEDAR);
		athsTreeHelper(AthsGlobal.YOUNG_WHITE_ELM, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe", "Asia"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.WHITEELM);
		athsTreeHelper(AthsGlobal.YOUNG_WILLOW, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas", "Asia", "Europe", "Africa"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.WILLOW);
		athsTreeHelper(AthsGlobal.YOUNG_YEW, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe", "Asia"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.YEW);
		athsTreeHelper(AthsGlobal.YOUNG_YEW + "_Africa", AthsGlobal.YOUNG_YEW, new int[] {0}, new String[] {"ore:blockSoil"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/1, /*dispersion*/1, /*minAltitude*/0, /*maxAltitude*/255, EnumTree.AFRICANYEW);
		
		athsPlantHelper(AthsGlobal.ARPOPHYLLUM_GIGANTEUM, new int[] {0}, new String[] {"alltrees"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
		        /*size*/1, /*dispersion*/1, /*rarity*/2300, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/20f, /*maxTemp*/40f, /*minRain*/980f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.SHAGGY_BRACKET, new int[] {0}, new String[] {"oak","birch","maple","hickory","ash","chestnut","aspen","gingko","sycamore","whiteelm","willow"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Americas","Europe"},
				/*size*/1, /*dispersion*/1, /*rarity*/4000, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-3f, /*maxTemp*/22f, /*minRain*/600f, /*maxRain*/10000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.ARTISTS_CONK, new int[] {0}, new String[] {"oak","maple","ash","sycamore","whiteelm","willow","spruce","douglasfir"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Americas","Europe"},
				/*size*/1, /*dispersion*/1, /*rarity*/4000, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/18f, /*minRain*/500f, /*maxRain*/5000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.CEYLON_CREEPER, new int[] {0}, new String[] {"alltrees", "ore:stone"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/15, /*dispersion*/1, /*rarity*/2100, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/19f, /*maxTemp*/40f, /*minRain*/950f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);	
		athsPlantHelper(AthsGlobal.CEYLON_CREEPER + "_Variegated", AthsGlobal.CEYLON_CREEPER, new int[] {1}, new String[] {"alltrees", "ore:stone"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/15, /*dispersion*/1, /*rarity*/3100, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/19f, /*maxTemp*/40f, /*minRain*/950f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);	
		athsPlantHelper(AthsGlobal.CANARY_CREEPER, new int[] {0}, new String[] {"alltrees", "ore:stone"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/15, /*dispersion*/1, /*rarity*/2100, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/32f, /*maxTemp*/40f, /*minRain*/950f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);	
		athsPlantHelper(AthsGlobal.BLACK_EYED_SUSAN_VINE, new int[] {0}, new String[] {"alltrees", "ore:stone"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/15, /*dispersion*/1, /*rarity*/2300, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/17f, /*maxTemp*/40f, /*minRain*/950f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);	
		athsPlantHelper(AthsGlobal.TRUMPET_VINE /*American*/, new int[] {0}, new String[] {"alltrees"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/15, /*dispersion*/1, /*rarity*/3400, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/13f, /*maxTemp*/21f, /*minRain*/650f, /*maxRain*/1200f, /*minEVT*/1f, /*maxEVT*/10f,/*forestGen*/1.0f);	
		athsPlantHelper(AthsGlobal.TRUMPET_VINE + "_Chinese", AthsGlobal.TRUMPET_VINE, new int[] {1}, new String[] {"alltrees"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/15, /*dispersion*/1, /*rarity*/3400, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/14f, /*maxTemp*/23f, /*minRain*/650f, /*maxRain*/1200f, /*minEVT*/1f, /*maxEVT*/10f,/*forestGen*/1.0f);	
		athsPlantHelper(AthsGlobal.GRIFFONIA_SIMPLICIFOLIA, new int[] {0}, new String[] {"alltrees"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/15, /*dispersion*/1, /*rarity*/2100, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/17f, /*maxTemp*/40f, /*minRain*/720f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);	
		athsPlantHelper(AthsGlobal.VANILLA_ORCHID, new int[] {0}, new String[] {"alltrees", "ore:stone"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/15, /*dispersion*/1, /*rarity*/2000, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/22f, /*maxTemp*/40f, /*minRain*/950f, /*maxRain*/16000f, /*minEVT*/0.5f, /*maxEVT*/10f,/*forestGen*/1.0f);	
		athsPlantHelper(AthsGlobal.BURMA_CREEPER, new int[] {0}, new String[] {"alltrees", "ore:stone"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/15, /*dispersion*/1, /*rarity*/2100, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/21f, /*maxTemp*/40f, /*minRain*/1050f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);	
		athsPlantHelper(AthsGlobal.JAPANESE_MOUNTAIN_YAM, new int[] {0}, new String[] {"alltrees", "ore:stone"}, new String [] {"Mountains","Mountain Range","Mountains Edge","Mountain Range Edge","Foothills","High Hills","High Hills Edge"}, new String[]{"Asia"},
				/*size*/15, /*dispersion*/1, /*rarity*/2100, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/7f, /*maxTemp*/16f, /*minRain*/650f, /*maxRain*/1500f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);	
		athsPlantHelper(AthsGlobal.WISTERIA, new int[] {0}, new String[] {"alltrees"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Asia"},
				/*size*/35, /*dispersion*/1, /*rarity*/3600, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/17f, /*minRain*/750f, /*maxRain*/1200f, /*minEVT*/1.5f, /*maxEVT*/10f,/*forestGen*/1.0f);	
		athsPlantHelper(AthsGlobal.BRIDAL_CREEPER, new int[] {0}, new String[] {"alltrees"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/15, /*dispersion*/1, /*rarity*/2100, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/9f, /*maxTemp*/19f, /*minRain*/450f, /*maxRain*/920f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.PORCELAINFLOWER, new int[] {0}, new String[] {"alltrees", "ore:stone"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/15, /*dispersion*/1, /*rarity*/2000, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/16f, /*maxTemp*/26f, /*minRain*/1100f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/1.0f);	
		athsPlantHelper(AthsGlobal.PORCELAINFLOWER + "_Variegated", AthsGlobal.PORCELAINFLOWER, new int[] {1}, new String[] {"alltrees", "ore:stone"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/15, /*dispersion*/1, /*rarity*/2000, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/16f, /*maxTemp*/26f, /*minRain*/1100f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/1.0f);	
		athsPlantHelper(AthsGlobal.CLIMBING_ROSE, new int[] {0}, new String[] {"alltrees", "ore:stone"}, new String[]{"Plains","Rolling Hills","Lakeshore","Riverbank","Swamp","High Hills","High Hills Edge"}, new String[]{"Americas","Asia","Africa","Europe"},
				/*size*/20, /*dispersion*/1, /*rarity*/3600, /*minAltitude*/0, /*maxAltitude*/180, /*minTemp*/-5f, /*maxTemp*/22f, /*minRain*/600f, /*maxRain*/900f, /*minEVT*/0.5f, /*maxEVT*/5f);	
		athsPlantHelper(AthsGlobal.DELTA_MAIDENHAIR_FERN_EPIPHYTE, new int[] {0}, new String[] {"alltrees", "ore:stone"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/1, /*dispersion*/1, /*rarity*/1200, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/17f, /*maxTemp*/40f, /*minRain*/990f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);	
		athsPlantHelper(AthsGlobal.BEEFSTEAK_FUNGUS, new int[] {0}, new String[] {"oak","chestnut"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa","Americas","Europe"},
				/*size*/1, /*dispersion*/1, /*rarity*/2400, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/7f, /*maxTemp*/35f, /*minRain*/700f, /*maxRain*/4500f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.BIRCH_POLYPORE, new int[] {0}, new String[] {"birch"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Americas","Europe"},
				/*size*/1, /*dispersion*/1, /*rarity*/4500, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-8f, /*maxTemp*/13f, /*minRain*/300f, /*maxRain*/1000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.DRYADS_SADDLE, new int[] {0}, new String[] {"whiteelm","ash","maple","willow"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Americas","Europe"},
				/*size*/1, /*dispersion*/1, /*rarity*/6000, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/1f, /*maxTemp*/16f, /*minRain*/700f, /*maxRain*/2000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.ENOKI /*Enokitake*/, new int[] {0}, new String[] {"oak","ash","birch","whiteelm"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/1, /*dispersion*/1, /*rarity*/6200, /*minAltitude*/0, /*maxAltitude*/185, /*minTemp*/3f, /*maxTemp*/17f, /*minRain*/750f, /*maxRain*/1800f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.ENOKI + "_Velvet_Shank", AthsGlobal.ENOKI, new int[] {1}, new String[] {"whiteelm","willow","aspen","maple","birch"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Europe"},
				/*size*/1, /*dispersion*/1, /*rarity*/6200, /*minAltitude*/0, /*maxAltitude*/185, /*minTemp*/3f, /*maxTemp*/17f, /*minRain*/750f, /*maxRain*/1800f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.ENGLISH_IVY, new int[] {0}, new String[] {"alltrees", "ore:stone"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/15, /*dispersion*/1, /*rarity*/1900, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-8f, /*maxTemp*/19f, /*minRain*/750f, /*maxRain*/3000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);	
		athsPlantHelper(AthsGlobal.PERSIAN_IVY /*Normal*/, new int[] {0}, new String[] {"alltrees", "ore:stone"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/15, /*dispersion*/1, /*rarity*/2200, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/9f, /*maxTemp*/24f, /*minRain*/190f, /*maxRain*/900f, /*minEVT*/0f, /*maxEVT*/10f);	
		athsPlantHelper(AthsGlobal.PERSIAN_IVY +"_Variegated", AthsGlobal.PERSIAN_IVY, new int[] {1}, new String[] {"alltrees", "ore:stone"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/15, /*dispersion*/1, /*rarity*/3400, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/9f, /*maxTemp*/24f, /*minRain*/190f, /*maxRain*/900f, /*minEVT*/0f, /*maxEVT*/10f);	
		athsPlantHelper(AthsGlobal.VIRGINIA_CREEPER, new int[] {0}, new String[] {"alltrees", "ore:stone"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/15, /*dispersion*/1, /*rarity*/1900, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-12f, /*maxTemp*/22f, /*minRain*/750f, /*maxRain*/3000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.GIANT_PHILODENDRON_EPIPHYTE, new int[] {0}, new String[] {"alltrees"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/1, /*dispersion*/1, /*rarity*/3784, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/23f, /*maxTemp*/40f, /*minRain*/1000f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/3f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.HEART_LEAF_PHILODENDRON, new int[] {0}, new String[] {"alltrees", "ore:stone"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/15, /*dispersion*/1, /*rarity*/3284, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/21f, /*maxTemp*/40f, /*minRain*/950f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/3f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.REISHI, new int[] {0}, new String[] {"maple","sycamore","pine","spruce","redwood"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/1, /*dispersion*/1, /*rarity*/7500, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/10f, /*maxTemp*/25f, /*minRain*/750f, /*maxRain*/8000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.WOOD_EAR, new int[] {0}, new String[] {"maple","ash","whiteelm","hickory","chestnut"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/1, /*dispersion*/1, /*rarity*/9500, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/5f, /*maxTemp*/17f, /*minRain*/750f, /*maxRain*/10000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.WOOD_EAR + "_Oak", AthsGlobal.WOOD_EAR, new int[] {0}, new String[] {"oak"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/1, /*dispersion*/1, /*rarity*/6500, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/5f, /*maxTemp*/17f, /*minRain*/750f, /*maxRain*/10000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		
		athsPlantHelper(AthsGlobal.SULPHUR_SHELF, new int[] {0}, new String[] {"oak","willow","yew","whitecedar","douglasfir","pine","spruce"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Europe"},
				/*size*/1, /*dispersion*/1, /*rarity*/6600, /*minAltitude*/0, /*maxAltitude*/185, /*minTemp*/-1f, /*maxTemp*/14f, /*minRain*/750f, /*maxRain*/1300f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.TURKEY_TAIL, new int[] {0}, new String[] {"alltrees"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Americas","Europe"},
				/*size*/1, /*dispersion*/1, /*rarity*/4000, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/17f, /*minRain*/750f, /*maxRain*/2000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.TILLANDSIA_BROMELIAD, new int[] {0}, new String[] {"alltrees", "ore:stone"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/1, /*dispersion*/1, /*rarity*/2000, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/18f, /*maxTemp*/30f, /*minRain*/950f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.HEDGEHOG_LIP_ORCHID, new int[] {0}, new String[] {"alltrees"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/1, /*dispersion*/1, /*rarity*/2500, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/22f, /*maxTemp*/33f, /*minRain*/1000f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.PALE_UMBRELLA_ORCHID, new int[] {0}, new String[] {"alltrees"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Africa"},
				/*size*/1, /*dispersion*/1, /*rarity*/2500, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/20f, /*maxTemp*/30f, /*minRain*/950f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.PURPUREORACHIS, new int[] {0}, new String[] {"alltrees"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/1, /*dispersion*/1, /*rarity*/2500, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/18f, /*maxTemp*/35f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.LEOPARD_ORCHID_EPIPHYTE, new int[] {0}, new String[] {"alltrees"}, AthsGlobal.LAND_BIOMES, new String[]{"Africa"},
				/*size*/1, /*dispersion*/1, /*rarity*/3500, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/16f, /*maxTemp*/25f, /*minRain*/1200f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.MISTLETOE /*American*/, new int[] {0}, new String[] {"alltrees"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/1, /*dispersion*/1, /*rarity*/2500, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/12f, /*maxTemp*/20f, /*minRain*/550f, /*maxRain*/2000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.MISTLETOE + "_Desert", AthsGlobal.MISTLETOE, new int[] {1}, new String[] {"alltrees"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/1, /*dispersion*/1, /*rarity*/750, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/11f, /*maxTemp*/25f, /*minRain*/85f, /*maxRain*/200f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.MISTLETOE + "_Dwarf", AthsGlobal.MISTLETOE, new int[] {2}, new String[] {"alltrees"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Europe","Americas","Africa"},
				/*size*/1, /*dispersion*/1, /*rarity*/2700, /*minAltitude*/155, /*maxAltitude*/255, /*minTemp*/-6f, /*maxTemp*/15f, /*minRain*/500f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.MISTLETOE + "_European", AthsGlobal.MISTLETOE, new int[] {3}, new String[] {"alltrees"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Europe"},
				/*size*/1, /*dispersion*/1, /*rarity*/2500, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/18f, /*minRain*/550f, /*maxRain*/1500f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.STAGHORN_FERN, new int[] {0}, new String[] {"alltrees"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Africa"},
				/*size*/1, /*dispersion*/1, /*rarity*/3000, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/16f, /*maxTemp*/40f, /*minRain*/1000f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.SQUIRRELS_FOOT_FERN, new int[] {0}, new String[] {"alltrees", "ore:stone"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/1, /*dispersion*/1, /*rarity*/1000, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/28f, /*minRain*/800f, /*maxRain*/10000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.BOSTON_FERN, new int[] {0}, new String[] {"alltrees", "ore:stone"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Americas","Europe","Africa"},
				/*size*/1, /*dispersion*/1, /*rarity*/1000, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/40f, /*minRain*/950f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.BULBLET_FERN, new int[] {0}, new String[] {"ore:stone"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/5, /*dispersion*/2, /*rarity*/650, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-4f, /*maxTemp*/16f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.BLACK_SPLEENWORT, new int[] {0}, new String[] {"alltrees", "ore:stone"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Europe","Americas","Africa"},
				/*size*/1, /*dispersion*/1, /*rarity*/1000, /*minAltitude*/0, /*maxAltitude*/190, /*minTemp*/4f, /*maxTemp*/17f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.MAIDENHAIR_SPLEENWORT, new int[] {0}, new String[] {"ore:stone"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Europe","Americas","Africa"},
				/*size*/3, /*dispersion*/1, /*rarity*/9000, /*minAltitude*/0, /*maxAltitude*/190, /*minTemp*/-15f, /*maxTemp*/40f, /*minRain*/650f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.ROCK_CAP_FERN, new int[] {0}, new String[] {"alltrees", "ore:stone"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Europe","Americas","Africa"},
				/*size*/1, /*dispersion*/1, /*rarity*/950, /*minAltitude*/0, /*maxAltitude*/210, /*minTemp*/-10f, /*maxTemp*/15f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.WALKING_FERN /*American*/, new int[] {0}, new String[] {"ore:stone"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/15, /*dispersion*/1, /*rarity*/3650, /*minAltitude*/0, /*maxAltitude*/210, /*minTemp*/-7f, /*maxTemp*/19f, /*minRain*/750f, /*maxRain*/2500f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.WALKING_FERN + "_Asian", AthsGlobal.WALKING_FERN, new int[] {1}, new String[] {"ore:stone"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia"},
				/*size*/15, /*dispersion*/1, /*rarity*/3650, /*minAltitude*/0, /*maxAltitude*/210, /*minTemp*/-7f, /*maxTemp*/19f, /*minRain*/750f, /*maxRain*/2500f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.BITTER_OYSTER, new int[] {0}, new String[] {"oak","birch","maple","hickory","ash","chestnut","pine"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Americas","Europe"},
				/*size*/1, /*dispersion*/1, /*rarity*/5500, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/16f, /*minRain*/800f, /*maxRain*/10000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.OYSTER_MUSHROOM, new int[] {0}, new String[] {"oak","aspen","maple","whiteelm","pine"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Americas","Europe","Africa"},
				/*size*/1, /*dispersion*/1, /*rarity*/6500, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/5f, /*maxTemp*/34f, /*minRain*/820f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.CHLOROPHOS_FOXFIRE, new int[] {0}, new String[] {"ore:blockSoil"}, new String[]{"High Hills","Plains","High Hills Edge","Rolling Hills","High Plains","Lake","Foothills","Lakeshore","Riverbank","Swamp"}, new String[]{"Americas","Asia"},
				/*size*/1, /*dispersion*/1, /*rarity*/4412, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/26f, /*minRain*/850f, /*maxRain*/16000f, /*minEVT*/0.5f, /*maxEVT*/7f);
		athsPlantHelper(AthsGlobal.CHAGA, new int[] {0}, new String[] {"birch", "oak"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Americas","Europe"},
				/*size*/1, /*dispersion*/1, /*rarity*/5500, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-5f, /*maxTemp*/13f, /*minRain*/450f, /*maxRain*/2000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.LIONS_MANE, new int[] {0}, new String[] {"oak","birch","maple","hickory","ash","chestnut","aspen","gingko","sycamore","whiteelm","willow"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Americas","Europe"},
				/*size*/1, /*dispersion*/1, /*rarity*/7500, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/3f, /*maxTemp*/17f, /*minRain*/750f, /*maxRain*/10000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.JELLY_FUNGUS /*Black_Jelly_Roll*/, new int[] {0}, new String[] {"alltrees"}, AthsGlobal.LAND_BIOMES, new String[]{"Europe"},
				/*size*/1, /*dispersion*/1, /*rarity*/5800, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-6f, /*maxTemp*/14f, /*minRain*/750f, /*maxRain*/4000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.JELLY_FUNGUS + "_Apricot_Jelly", AthsGlobal.JELLY_FUNGUS, new int[] {1}, new String[] {"alltrees"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Asia","Europe"},
				/*size*/1, /*dispersion*/1, /*rarity*/5800, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/2f, /*maxTemp*/31f, /*minRain*/750f, /*maxRain*/4000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.JELLY_FUNGUS + "_Orange_Jelly", AthsGlobal.JELLY_FUNGUS, new int[] {2}, new String[] {"alltrees"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Asia","Europe"},
				/*size*/1, /*dispersion*/1, /*rarity*/5800, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/-4f, /*maxTemp*/35f, /*minRain*/750f, /*maxRain*/1200f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.JELLY_FUNGUS + "_Witchs_Butter", AthsGlobal.JELLY_FUNGUS, new int[] {3}, new String[] {"alltrees"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Asia","Europe"},
				/*size*/1, /*dispersion*/1, /*rarity*/5800, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/0f, /*maxTemp*/28f, /*minRain*/750f, /*maxRain*/4000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.JELLY_FUNGUS + "_Snow_Fungus", AthsGlobal.JELLY_FUNGUS, new int[] {4}, new String[] {"alltrees"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas","Africa","Asia"},
				/*size*/1, /*dispersion*/1, /*rarity*/5800, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/5f, /*maxTemp*/33f, /*minRain*/750f, /*maxRain*/4000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.SPLIT_LEAF_MONSTERA_EPIPHYTE, new int[] {0}, new String[] {"alltrees"}, AthsGlobal.LAND_BIOMES, new String[]{"Americas"},
				/*size*/1, /*dispersion*/1, /*rarity*/5784, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/24f, /*maxTemp*/40f, /*minRain*/1050f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/3f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.QUEEN_OF_THE_ANDES, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand","ore:blockGravel"}, new String[]{"Foothills","Mountains Edge","Mountains","Mountain Range Edge","Mountain Range"}, new String[]{"Americas"},
				/*size*/8, /*dispersion*/18, /*rarity*/1580, /*minAltitude*/210, /*maxAltitude*/255, /*minTemp*/16f, /*maxTemp*/27f, /*minRain*/200f, /*maxRain*/920f, /*minEVT*/0f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.SAPPHIRE_TOWER, new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand","ore:blockGravel"}, new String[]{"Foothills","Mountains Edge","Mountains","Mountain Range Edge","Mountain Range","High Hills","High Hills Edge"}, new String[]{"Americas"},
					/*size*/13, /*dispersion*/9, /*rarity*/10580, /*minAltitude*/175, /*maxAltitude*/255, /*minTemp*/11f, /*maxTemp*/21f, /*minRain*/200f, /*maxRain*/720f, /*minEVT*/0f, /*maxEVT*/5f);
		athsPlantHelper(AthsGlobal.OLD_MANS_BEARD_LICHEN, new int[] {0}, new String[] {"alltrees"}, AthsGlobal.LAND_BIOMES, new String[]{"Asia","Africa","Europe","Americas"},
					/*size*/11, /*dispersion*/1, /*rarity*/4500, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/4f, /*maxTemp*/40f, /*minRain*/950f, /*maxRain*/16000f, /*minEVT*/1f, /*maxEVT*/10f,/*forestGen*/1.0f);
		athsPlantHelper(AthsGlobal.SPANISH_MOSS, new int[] {0}, new String[] {"alltrees"}, new String [] {"Swamp","Estuary","Salt Swamp", "River","Riverbank","Lake","Lakeshore"}, new String[]{"Americas"},
					/*size*/15, /*dispersion*/1, /*rarity*/1100, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/15f, /*maxTemp*/23f, /*minRain*/1050f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f);
		
		AthsWorldGenPlants.plantList.put("shrub", getPlantData("shrub", Reference.MOD_ID+":"+TFCBlocks.shrub.getUnlocalizedName().substring(5), new int[] {0}, new String[] {"ore:blockSoil","ore:blockSand"}, new String[]{"Riverbank"}, new String[]{"Americas","Europe","Africa","Asia"},
		        /*size*/15, /*dispersion*/10, /*rarity*/1550, /*minAltitude*/0, /*maxAltitude*/255, /*minTemp*/8f, /*maxTemp*/30f, /*minRain*/25f, /*maxRain*/135f, /*minEVT*/0f, /*maxEVT*/4f,/*forestGen*/1.0f));
		
		for(int i = 0; i < numCustomGenerators; i++) {
			String name = "_z" + i;
			AthsWorldGenPlants.plantList.put(name, getCustomPlantData(name, Reference.MOD_ID+":"+TFCBlocks.undergrowth.getUnlocalizedName().substring(5), new int[] {0}, new String[] {"ore:blockSoil"}, new String[] {"All", "!Hell"}, new String[]{"Americas", "Asia"}, 
					/*size*/3, /*dispersion*/1, /*rarity*/424, /*minAltitude*/0, /*maxAltitude*/180, /*minTemp*/0f, /*maxTemp*/40f, /*minRain*/750f, /*maxRain*/16000f, /*minEVT*/0f, /*maxEVT*/10f,/*forestGen*/1.0f));
		}
		
		for(PlantSpawnData d : AthsWorldGenPlants.plantList.values()) {
			if(d.block instanceof BlockPlantCactus) {
				rarityHelper(d, rarityCactus);
			}
			else if(d.block instanceof BlockPlantEpiphyte3d) {
				rarityHelper(d, rarityEpiphyte);
			}
			else if(d.block instanceof ILilyPad) {
				rarityHelper(d, rarityLilyPad);
			}
			else if(d.block instanceof ITree) {
				rarityHelper(d, rarityTree);
			}
			else {
				rarityHelper(d, rarityOther);
			}
		}

		
		if (config.hasChanged())
			config.save();
	}

	private static void rarityHelper(PlantSpawnData d, float rarity) {
		if(rarity == 1f){
			return;
		}
		else if (rarity <= 0f) {
			d.size = 0;
		}
		else {
			d.rarity *= rarity;
		}
	}
	
	private static PlantSpawnData getPlantData(String category, String blockName, int[] metas, String[] growOnBlocks, String[] biomes, String[] regions, int size, int dispersion,
			int rarity, int minAltitude, int maxAltitude, float minTemp, float maxTemp, float minRainfall, float maxRainfall, float minEVT, float maxEVT, float forestGen){
		return new PlantSpawnData(
			blockName,
			config.get(category, "metas", metas).getIntList(),
			config.get(category, "growOnBlocks", growOnBlocks).getStringList(),
			config.get(category, "biomes", biomes).setValidValues(AthsGlobal.ALLOWED_BIOMES).getStringList(),
			config.get(category, "regions", regions).getStringList(),
			config.get(category, "size", size).setMinValue(0).getInt(),
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
	
	private static PlantSpawnData getCustomPlantData(String category, String blockName, int[] metas, String[] growOnBlocks, String[] biomes, String[] regions, int size, int dispersion,
			int rarity, int minAltitude, int maxAltitude, float minTemp, float maxTemp, float minRainfall, float maxRainfall, float minEVT, float maxEVT, float forestGen){
		return new PlantSpawnData(
			config.get(category, "blockName", blockName).getString(),
			config.get(category, "metas", metas).getIntList(),
			config.get(category, "growOnBlocks", growOnBlocks).getStringList(),
			config.get(category, "biomes", biomes).setValidValues(AthsGlobal.ALLOWED_BIOMES).getStringList(),
			config.get(category, "regions", regions).getStringList(),
			config.get(category, "size", size).setMinValue(0).getInt(),
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
		athsPlantHelper(name, name, metas, growOnBlocks, biomes, regions, size, dispersion,
				rarity, minAltitude, maxAltitude, minTemp, maxTemp, minRainfall, maxRainfall, minEVT, maxEVT, 0.0f);
	}
	//category version
	public static void athsPlantHelper(String category, String name, int[] metas, String[] growOnBlocks, String[] biomes, String[] regions, int size, int dispersion,
			int rarity, int minAltitude, int maxAltitude, float minTemp, float maxTemp, float minRainfall, float maxRainfall, float minEVT, float maxEVT) {
		athsPlantHelper(category, name, metas, growOnBlocks, biomes, regions, size, dispersion,
				rarity, minAltitude, maxAltitude, minTemp, maxTemp, minRainfall, maxRainfall, minEVT, maxEVT, 0.0f);
	}
	
	/* forestGen version*/
	public static void athsPlantHelper(String name, int[] metas, String[] growOnBlocks, String[] biomes, String[] regions, int size, int dispersion,
			int rarity, int minAltitude, int maxAltitude, float minTemp, float maxTemp, float minRainfall, float maxRainfall, float minEVT, float maxEVT, float forestGen) {
		athsPlantHelper(name, name, metas, growOnBlocks, biomes, regions, size, dispersion,
				rarity, minAltitude, maxAltitude, minTemp, maxTemp, minRainfall, maxRainfall, minEVT, maxEVT, forestGen);
	}
	
	/* forestGen category version*/
	public static void athsPlantHelper(String category, String name, int[] metas, String[] growOnBlocks, String[] biomes, String[] regions, int size, int dispersion,
			int rarity, int minAltitude, int maxAltitude, float minTemp, float maxTemp, float minRainfall, float maxRainfall, float minEVT, float maxEVT, float forestGen) {
		AthsWorldGenPlants.plantList.put(category, getPlantData(category, AthsMod.MODID+":"+name, metas, growOnBlocks, biomes, regions, size, dispersion,
				rarity, minAltitude, maxAltitude, minTemp, maxTemp, minRainfall, maxRainfall, minEVT, maxEVT, forestGen));
	}
	
	public static void athsTreeHelper(String name, int[] metas, String[] growOnBlocks, String[] biomes, String[] regions, int size, int dispersion,
			int minAltitude, int maxAltitude, EnumTree tree) {
		athsTreeHelper(name, name, metas, growOnBlocks, biomes, regions, size, dispersion, minAltitude, maxAltitude, tree);
	}
	
	/* alternate spawn parameters for same tree*/
	public static void athsTreeHelper(String category, String name, int[] metas, String[] growOnBlocks, String[] biomes, String[] regions, int size, int dispersion,
			int minAltitude, int maxAltitude, EnumTree tree) {
		AthsWorldGenPlants.plantList.put(category, getPlantData(category, AthsMod.MODID+":"+name, metas, growOnBlocks, biomes, regions, size, dispersion,
				(int)(AthsGlobal.TREE_BASE_RARITY / tree.rarity), minAltitude, maxAltitude, tree.minTemp, tree.maxTemp, tree.minRain, tree.maxRain, tree.minEVT, tree.maxEVT, 1.0f));
	}
}
