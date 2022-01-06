package com.facetorched.tfcaths;

import com.dunk.tfc.WorldGen.DataLayer;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.Constant.Global;
import com.facetorched.tfcaths.util.AthsParser;
import com.facetorched.tfcaths.util.Point3D;

import net.minecraft.block.Block;

public class AthsGlobal {
	public static final String AGATE = "Agate";
	public static final String AMETHYST = "Amethyst";
	public static final String BERYL = "Beryl";
	public static final String DIAMOND = "Diamond";
	public static final String EMERALD = "Emerald";
	public static final String GARNET = "Garnet";
	public static final String JADE = "Jade";
	public static final String JASPER = "Jasper";
	public static final String OPAL = "Opal";
	public static final String RUBY = "Ruby";
	public static final String SAPPHIRE = "Sapphire";
	public static final String TOPAZ = "Topaz";
	public static final String TOURMALINE = "Tourmaline";
	
	public static final String ROCK_CRYSTAL = "Rock_Crystal";
	public static final String GYPSUM = "Gypsum";
	
	public static final String ALGAE_MAT = "Algae_Mat";
	public static final String ALGAE_MAT_BROWN = "Algae_Mat_Brown";
	public static final String ALGAE_MAT_CYANOBACTERIA = "Algae_Mat_Cyanobacteria";
	public static final String ALGAE_MAT_GREEN = "Algae_Mat_Green";
	public static final String ALGAE_MAT_RED = "Algae_Mat_Red";
	public static final String ALGAE_MAT_YELLOW = "Algae_Mat_Yellow";
	public static final String ALOE_VERA = "Aloe_Vera";
	public static final String ANGELS_TRUMPET = "Angels_Trumpet";
	public static final String ANGEL_WING_CACTUS = "Angel_Wing_Cactus";
	public static final String BIRD_OF_PARADISE = "Bird_of_Paradise";
	public static final String BLUE_CEREUS_CACTUS = "Blue_Cereus_Cactus";
	public static final String BOLETUS = "Boletus";
	public static final String BRACKEN_FERN = "Bracken_Fern";
	public static final String BRIDAL_VEIL_STINKHORN = "Bridal_Veil_Stinkhorn";
	public static final String BURDOCK = "Burdock";
	public static final String CAMAS_FLOWER = "Camas_Flower";
	public static final String CHANTERELLE = "Chanterelle";
	public static final String CHIVES = "Chives";
	public static final String CLOVER = "Clover";
	public static final String COMMON_REEDS = "Common_Reeds";
	public static final String CREEPING_CHARLIE = "Creeping_Charlie";
	public static final String CUP_PLANT = "Cup_Plant";
	public static final String DAFFODIL = "Daffodil";
	public static final String DEVILS_CLUB = "Devils_Club";
	public static final String DEVILS_FINGERS = "Devils_Fingers";
	public static final String DEVILS_TOUNGE = "Devils_Tongue";
	public static final String DUCKWEED = "Duckweed";
	public static final String DUNE_GRASS = "Dune_Grass";
	public static final String ELEPHANT_GRASS = "Elephant_Grass";
	public static final String ENTOLOMA = "Entoloma";
	public static final String FIELD_HORSETAIL = "Field_Horsetail";
	public static final String FIREWEED = "Fireweed";
	public static final String GIANT_HOGWEED = "Giant_Hogweed";
	public static final String HEATHER = "Heather";
	public static final String HIBISCUS = "Hibiscus";
	public static final String HOSTA_HALCYON = "Hosta_Halcyon";
	public static final String HOSTA_PATRIOT = "Hosta_Patriot";
	public static final String HOSTA_VULCAN = "Hosta_Vulcan";
	public static final String INDIAN_PIPE = "Indian_Pipe";
	public static final String INDIGO_MILK_CAP = "Indigo_Milk_Cap";
	public static final String IRIS = "Iris";
	public static final String JACK_IN_THE_PULPIT = "Jack_In_The_Pulpit";
	public static final String LADY_FERN = "Lady_Fern";
	public static final String LAVENDER = "Lavender";
	public static final String LEAFY_LOW_UNDERGROWTH = "Leafy_Low_Undergrowth";
	public static final String LEAFY_UNDERGROWTH = "Leafy_Undergrowth";
	public static final String LILY_PAD = "Lily_Pad";
	public static final String LOBSTER_CLAWS = "Lobster_Claws";
	public static final String LUPINE = "Lupine";
	public static final String MARIGOLD = "Marigold";
	public static final String MEDIUM_UNDERGROWTH = "Medium_Undergrowth";
	public static final String MOREL = "Morel";
	public static final String OCOTILLO = "Ocotillo";
	public static final String ORGAN_PIPE_CACTUS = "Organ_Pipe_Cactus";
	public static final String OSTRICH_FERN = "Ostrich_Fern";
	public static final String PAINTED_FERN = "Painted_Fern";
	public static final String POKEWEED = "Pokeweed";
	public static final String POND_GRASS = "Pond_Grass";
	public static final String PRAIRIE_GRASS = "Prairie_Grass";
	public static final String PRICKLY_PEAR = "Prickly_Pear";
	public static final String ROSEBUSH = "Rosebush";
	public static final String ROUGH_HORSETAIL = "Rough_Horsetail";
	public static final String SAGUARO = "Saguaro";
	public static final String SCALY_TREE_FERN = "Scaly_Tree_Fern";
	public static final String SENSITIVE_FERN = "Sensitive_Fern";
	public static final String SHITAKE = "Shitake";
	public static final String SORBUS = "Sorbus";
	public static final String SUMAC = "Sumac";
	public static final String SUNDEW = "Sundew";
	public static final String SUNFLOWER = "Sunflower";
	public static final String SWORD_FERN = "Sword_Fern";
	public static final String THISTLE = "Thistle";
	public static final String TITAN_ARUM = "Titan_Arum";
	public static final String VENUS_FLYTRAP = "Venus_Flytrap";
	public static final String VICTORIA_LILY_PAD = "Victoria_Lily_Pad";
	public static final String VOODOO_LILY = "Voodoo_Lily";
	public static final String WATER_HYACINTH = "Water_Hyacinth";
	public static final String WOOD_FERN = "Wood_Fern";
	public static final String YARROW = "Yarrow";
	public static final String YOUNG_ACACIA = "Young_Acacia";
	public static final String YOUNG_ASH = "Young_Ash";
	public static final String YOUNG_ASPEN = "Young_Aspen";
	public static final String YOUNG_BAOBAB = "Young_Baobab";
	public static final String YOUNG_BIRCH = "Young_Birch";
	public static final String YOUNG_CHESTNUT = "Young_Chestnut";
	public static final String YOUNG_DOUGLAS_FIR = "Young_Douglas_Fir";
	public static final String YOUNG_EBONY = "Young_Ebony";
	public static final String YOUNG_FEVER = "Young_Fever";
	public static final String YOUNG_GHAF = "Young_Ghaf";
	public static final String YOUNG_GINGKO = "Young_Gingko"; //match dunk's spelling
	public static final String YOUNG_HICKORY = "Young_Hickory";
	public static final String YOUNG_KAPOK = "Young_Kapok";
	public static final String YOUNG_LAUREL = "Young_Laurel";
	public static final String YOUNG_LIMBA = "Young_Limba";
	public static final String YOUNG_MAHOE = "Young_Mahoe";
	public static final String YOUNG_MAHOGANY = "Young_Mahogany";
	public static final String YOUNG_MAPLE = "Young_Maple";
	public static final String YOUNG_OAK = "Young_Oak";
	public static final String YOUNG_PINE = "Young_Pine";
	public static final String YOUNG_SEQUOIA = "Young_Sequoia";
	public static final String YOUNG_SPRUCE = "Young_Spruce";
	public static final String YOUNG_SYCAMORE = "Young_Sycamore";
	public static final String YOUNG_TEAK = "Young_Teak";
	public static final String YOUNG_WHITE_CEDAR = "Young_White_Cedar";
	public static final String YOUNG_WHITE_ELM = "Young_White_Elm";
	public static final String YOUNG_WILLOW = "Young_Willow";
	public static final String YOUNG_YEW = "Young_Yew";
	public static final String YUCCA = "Yucca";
	public static final String SAGEBRUSH = "Sagebrush";
	
	public static final String AFRICAN_MILK_BARREL = "African_Milk_Barrel";
	public static final String AFRICAN_MILK_TREE = "African_Milk_Tree";
	public static final String ALBANIAN_SPURGE = "Albanian_Spurge";
	public static final String BLOOD_LILY = "Blood_Lily";
	public static final String BLUEBELL = "Bluebell";
	public static final String BONATEA = "Bonatea";
	public static final String CARALUMA = "Caralluma";
	public static final String DESERT_ROSE = "Desert_Rose";
	public static final String EGYPTIAN_AUTUMN_CROCUS = "Egyptian_Autumn_Crocus";
	public static final String EUROPEAN_BEDSTRAW= "European_Bedstraw";
	public static final String FREESIA = "Freesia";
	public static final String GARLIC_MUSTARD = "Garlic_Mustard";
	public static final String GIFBOOM = "Gifboom";
	public static final String LEOPARD_ORCHID = "Leopard_Orchid";
	public static final String LOOSE_FLOWERED_ORCHID = "Loose_Flowered_Orchid";
	public static final String LOTUS = "Lotus";
	public static final String MALLOW = "Mallow";
	public static final String NARBON_VETCH = "Narbon_Vetch";
	public static final String PAPYRUS = "Papyrus";
	public static final String PERIWINKLE = "Periwinkle";
	public static final String QUAQUA = "Quaqua";
	public static final String RAFFLESIA = "Rafflesia";
	public static final String ROYAL_JASMINE = "Royal_Jasmine";
	public static final String SALTWORT = "Saltwort";
	public static final String SAXAUL = "Saxaul";
	public static final String SESAME = "Sesame";
	public static final String SNAKE_SANSEVERIA = "Snake_Sanseveria";
	public static final String STARFISH_PLANT = "Starfish_Plant";
	public static final String SWORD_SANSEVERIA = "Sword_Sanseveria";
	public static final String WATER_PLANTAIN = "Water_Plantain";
	public static final String WELWITSCHIA = "Welwitschia";
	public static final String WOOD_BITTER_VETCH = "Wood_Bitter_Vetch";
	
	public static final String CARALLUMA = "Caralluma";
	
	public static final String[] SHALLOW_WATER_BIOMES = new String[]{"River","Beach","Gravel Beach","Swamp","Lake","Shore","Salt Swamp","Lakeshore","Riverbank","Estuary"};
	public static final String[] LAND_BIOMES = new String[] {"All","!Ocean","!Hell","!Deep Ocean"};
	public static final String[] ALL_BIOMES = AthsParser.getBiomes();
	
	public static final DataLayer[] ROCKS = new DataLayer[] {DataLayer.GRANITE, DataLayer.DIORITE, DataLayer.GABBRO, DataLayer.SHALE, DataLayer.CLAYSTONE, DataLayer.ROCKSALT, DataLayer.LIMESTONE, DataLayer.CONGLOMERATE, DataLayer.DOLOMITE,
			DataLayer.CHERT, DataLayer.CHALK, DataLayer.RHYOLITE, DataLayer.BASALT, DataLayer.ANDESITE, DataLayer.DACITE, DataLayer.QUARTZITE, DataLayer.SLATE, DataLayer.PHYLLITE, DataLayer.SCHIST, DataLayer.GNEISS, DataLayer.MARBLE};
	public static final String[] ROCKTYPES = new String[] {"IgEx","IgIn","Sed","MM"};
	public static final String[][] ROCKTYPES_NAMES = new String[][] {Global.STONE_IGEX, Global.STONE_IGIN, Global.STONE_SED, Global.STONE_MM};
	public static final Block[] ROCKTYPES_BLOCKS = new Block[] {TFCBlocks.stoneIgEx, TFCBlocks.stoneIgIn, TFCBlocks.stoneSed, TFCBlocks.stoneMM};
	
	public static final String[] ALLOWED_REGIONS = new String[] {"Americas","Europe","Africa","Asia"};
	public static final String[] ALLOWED_BIOMES = AthsParser.add(AthsParser.append(ALL_BIOMES, AthsParser.prefix(ALL_BIOMES, "!")), "All");
	public static final String[] ALLOWED_ROCKS = AthsParser.add(AthsParser.append(Global.STONE_ALL, ROCKTYPES), "All");
	
	// some large prime numbers
	public static final int PRIME_1 = 83;
	public static final int PRIME_2 = 139;
	
	public static final float TREE_SCALE = 4.0f;
	public static final int TREE_BASE_RARITY = 1000;
	
	public static final Point3D[] NEIGHBORS = new Point3D[] {
			new Point3D(-1,-1,0),
			new Point3D(-1,0,-1),
			new Point3D(-1,0,0),
			new Point3D(-1,0,1),
			new Point3D(-1,1,0),
			new Point3D(0,-1,-1),
			new Point3D(0,-1,0),
			new Point3D(0,-1,1),
			new Point3D(0,0,-1),
			new Point3D(0,0,1),
			new Point3D(0,1,-1),
			new Point3D(0,1,0),
			new Point3D(0,1,1),
			new Point3D(1,-1,0),
			new Point3D(1,0,-1),
			new Point3D(1,0,0),
			new Point3D(1,0,1),
			new Point3D(1,1,0)};
}
