package com.facetorched.tfcaths;

import java.util.ArrayList;
import java.util.Arrays;

import com.dunk.tfc.BlockSetup;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.Constant.Global;
import com.facetorched.tfcaths.util.AthsParser;
import com.facetorched.tfcaths.util.BlockMetaPair;
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
	public static final String FLUORITE = "Fluorite";
	
	public static final String ROCK_CRYSTAL = "Rock_Crystal";
	public static final String GYPSUM = "Gypsum";
	
	public static final String ADDERS_TONGUE_FERN = "Adders_Tongue_Fern";
	public static final String ALGAE_MAT = "Algae_Mat";
	public static final String ALGAE_MAT_BROWN = "Algae_Mat_Brown";
	public static final String ALGAE_MAT_CYANOBACTERIA = "Algae_Mat_Cyanobacteria";
	public static final String ALGAE_MAT_GREEN = "Algae_Mat_Green";
	public static final String ALGAE_MAT_RED = "Algae_Mat_Red";
	public static final String ALGAE_MAT_YELLOW = "Algae_Mat_Yellow";
	public static final String ALOE_VERA = "Aloe_Vera";
	public static final String ANEMONE = "Anemone";
	public static final String ANGELS_TRUMPET = "Angels_Trumpet";
	public static final String ANGEL_WING_CACTUS = "Angel_Wing_Cactus";
	public static final String ASTER = "Aster";
	public static final String AZALEA_EVERGREEN = "Azalea_Evergreen";
	public static final String AZALEA_DECIDUOUS = "Azalea_Deciduous";
	public static final String BASKET_STINKHORN = "Basket_Stinkhorn";
	public static final String BEAR_CORN = "Bear_Corn";
	public static final String BEECH_MUSHROOM = "Beech_Mushroom";
	public static final String BIRD_OF_PARADISE = "Bird_of_Paradise";
	public static final String BIRDS_NEST_FERN = "Birds_Nest_Fern";
	public static final String BLACK_BAT_FLOWER = "Black_Bat_Flower";
	public static final String BLACK_EYED_SUSAN = "Black_Eyed_Susan";
	public static final String BLACK_EYED_SUSAN_VINE = "Black_Eyed_Susan_Vine";
	public static final String BLAZING_STAR = "Blazing_Star";
	public static final String BLEEDING_HEARTS = "Bleeding_Hearts";
	public static final String BLUE_CEREUS_CACTUS = "Blue_Cereus_Cactus";
	public static final String BOLETUS = "Boletus";
	public static final String BRACKEN_FERN = "Bracken_Fern";
	public static final String BRIDAL_CREEPER = "Bridal_Creeper";
	public static final String BRIDAL_VEIL_STINKHORN = "Bridal_Veil_Stinkhorn";
	public static final String BUCEPHALANDRA = "Bucephalandra";
	public static final String BULBLET_FERN = "Bulblet_Fern";
	public static final String BURDOCK = "Burdock";
	public static final String BURMA_CREEPER = "Burma_Creeper";
	public static final String BURNING_BUSH = "Burning_Bush";
	public static final String BUTTERCUP = "Buttercup";
	public static final String CAMAS_FLOWER = "Camas_Flower";
	public static final String CANADA_WILD_GINGER = "Canada_Wild_Ginger";
	public static final String CANARY_CREEPER = "Canary_Creeper";
	public static final String CARNATIONS = "Carnations";
	public static final String CEYLON_CREEPER = "Ceylon_Creeper";
	public static final String CHI_NGULU_NGULU = "Chi_Ngulu_Ngulu";
	public static final String CHANTERELLE = "Chanterelle";
	public static final String CHIVES = "Chives";
	public static final String CHRISTMAS_FERN = "Christmas_Fern";
	public static final String CHRYSANTHEMUM = "Chrysanthemum";
	public static final String CINNAMON_FERN = "Cinnamon_Fern";
	public static final String CLIMBING_ROSE = "Climbing_Rose";
	public static final String CLOVER = "Clover";
	public static final String COLEUS = "Coleus";
	public static final String COMMON_CATCHFLY = "Common_Catchfly";
	public static final String COMMON_REEDS = "Common_Reeds";
	public static final String COMMON_STINKHORN = "Common_Stinkhorn";
	public static final String CREEPING_BELLFLOWER = "Creeping_Bellflower";
	public static final String CREEPING_CHARLIE = "Creeping_Charlie";
	public static final String CRETAN_BRAKE_FERN = "Cretan_Brake_Fern";
	public static final String CROCUS = "Crocus";
	public static final String CUP_PLANT = "Cup_Plant";
	public static final String DAFFODIL = "Daffodil";
	public static final String DAYLILY = "Daylily";
	public static final String DEATH_CAP = "Death_Cap";
	public static final String DEER_FERN = "Deer_Fern";
	public static final String DELTA_MAIDENHAIR_FERN = "Delta_Maidenhair_Fern";
	public static final String DELTA_MAIDENHAIR_FERN_EPIPHYTE = "Delta_Maidenhair_Fern_Epiphyte";
	public static final String DESTROYING_ANGEL = "Destroying_Angel";
	public static final String DEVILS_CLUB = "Devils_Club";
	public static final String DEVILS_FINGERS = "Devils_Fingers";
	public static final String DEVILS_TOUNGE = "Devils_Tongue";
	public static final String DUCKWEED = "Duckweed";
	public static final String DUNE_GRASS = "Dune_Grass";
	public static final String DWARF_PALMETTO = "Dwarf_Palmetto";
	public static final String EARTHBALL = "Earthball";
	public static final String EARTHSTAR = "Earthstar";
	public static final String EASTERN_SKUNK_CABBAGE = "Eastern_Skunk_Cabbage";
	public static final String ELEPHANT_GRASS = "Elephant_Grass";
	public static final String ENTOLOMA = "Entoloma";
	public static final String ENOKI = "Enoki";
	public static final String FIELD_HORSETAIL = "Field_Horsetail";
	public static final String FIREWEED = "Fireweed";
	public static final String FLAMINGO_FLOWER = "Flamingo_Flower";
	public static final String FOUNTAIN_GRASS = "Fountain_Grass";
	public static final String FRINGED_ACALYPHA = "Fringed_Acalypha";
	public static final String GIANT_HOGWEED = "Giant_Hogweed";
	public static final String GLADIOLUS = "Gladiolus";
	public static final String GOLDEN_LEATHER_FERN = "Golden_Leather_Fern";
	public static final String GOLDEN_SPINDLES = "Golden_Spindles";
	public static final String GOLDEN_MILK_CAP = "Golden_Milk_Cap";
	public static final String GOLDEN_WAXCAP = "Golden_Waxcap";
	public static final String GRIFFONIA_SIMPLICIFOLIA = "Griffonia_Simplicifolia";
	public static final String HARTS_TONGUE_FERN = "Harts_Tongue_Fern";
	public static final String HEATHER = "Heather";
	public static final String HIBISCUS = "Hibiscus";
	public static final String HOLLY = "Holly";
	public static final String HOSTA_HALCYON = "Hosta_Halcyon";
	public static final String HOSTA_PATRIOT = "Hosta_Patriot";
	public static final String HOSTA_VULCAN = "Hosta_Vulcan";
	public static final String HOSTA_NARROW_LEAVED = "Hosta_Narrow_Leaved";
	public static final String HOSTA_TOUCH_OF_CLASS = "Hosta_Touch_of_Class";
	public static final String HOSTA_SUM_AND_SUBSTANCE = "Hosta_Sum_And_Substance";
	public static final String HOSTA_FRANCES_WILLIAMS = "Hosta_Frances_Williams";
	public static final String HYDRANGEA = "Hydrangea";
	public static final String HOSTA_ELEGANS = "Hosta_Elegans";
	public static final String INDIAN_PIPE = "Indian_Pipe";
	public static final String INK_CAP = "Ink_Cap";
	public static final String INDIGO_MILK_CAP = "Indigo_Milk_Cap";
	public static final String INTERRUPTED_FERN = "Interrupted_Fern";
	public static final String IRIS = "Iris";
	public static final String ENGLISH_IVY = "English_Ivy";
	public static final String PERSIAN_IVY = "Persian_Ivy";
	public static final String JACK_IN_THE_PULPIT = "Jack_In_The_Pulpit";
	public static final String JACK_O_LANTERN_MUSHROOM = "Jack_o_Lantern_Mushroom";
	public static final String JADE_PLANT = "Jade_Plant";
	public static final String JAPANESE_STILTGRASS = "Japanese_Stiltgrass";
	public static final String JAPANESE_MOUNTAIN_YAM = "Japanese_Mountain_Yam";
	public static final String JIAN_CHUN_LUO = "Jian_Chun_Luo";
	public static final String JELLY_FUNGUS = "Jelly_Fungus";
	public static final String LADY_FERN = "Lady_Fern";
	public static final String LAMBS_EAR = "Lambs_Ear";
	public static final String LAVENDER = "Lavender";
	public static final String LEAFY_LOW_UNDERGROWTH = "Leafy_Low_Undergrowth";
	public static final String LEAFY_UNDERGROWTH = "Leafy_Undergrowth";
	public static final String LILY_PAD = "Lily_Pad";
	public static final String LITHOPS = "Lithops";
	public static final String LOBSTER_CLAWS = "Lobster_Claws";
	public static final String LURID_BOLETE = "Lurid_Bolete";
	public static final String LUPINE = "Lupine";
	public static final String MAIDENHAIR_SPLEENWORT = "Maidenhair_Spleenwort";
	public static final String MADONNA_LILY = "Madonna_Lily";
	public static final String MARIGOLD = "Marigold";
	public static final String MARTAGON_LILY = "Martagon_Lily";
	public static final String MATONIA_FERN = "Matonia_Fern";
	public static final String MAYAPPLE = "Mayapple";
	public static final String MEDIUM_UNDERGROWTH = "Medium_Undergrowth";
	public static final String MOREL = "Morel";
	public static final String NETTLE = "Nettle";
	public static final String NIPA_PALM = "Nipa_Palm";
	public static final String NORTHERN_BUSH_HONEYSUCKLE = "Northern_Bush_Honeysuckle";
	public static final String NORTHERN_OAK_FERN = "Northern_Oak_Fern";
	public static final String OCOTILLO = "Ocotillo";
	public static final String OLD_MAN_OF_THE_WOODS = "Old_Man_of_The_Woods";
	public static final String ORGAN_PIPE_CACTUS = "Organ_Pipe_Cactus";
	public static final String OYSTER_MUSHROOM = "Oyster_Mushroom";
	public static final String OSTRICH_FERN = "Ostrich_Fern";
	public static final String PAINTED_FERN = "Painted_Fern";
	public static final String PANTHER_MUSHROOM = "Panther_Mushroom";
	public static final String PEACE_LILY = "Peace_Lily";
	public static final String PINEDROPS = "Pinedrops";
	public static final String POINSETTIA = "Poinsettia";
	public static final String PORCELAINFLOWER = "Porcelainflower";
	public static final String GIANT_PHILODENDRON = "Giant_Philodendron";
	public static final String GIANT_PHILODENDRON_EPIPHYTE = "Giant_Philodendron_Epiphyte";
	public static final String HEART_LEAF_PHILODENDRON = "Heart_Leaf_Philodendron";
	public static final String PLANTAIN_WEED = "Plantain_Weed";
	public static final String POISON_IVY = "Poison_Ivy";
	public static final String POKEWEED = "Pokeweed";
	public static final String POND_GRASS = "Pond_Grass";
	public static final String PRAIRIE_GRASS = "Prairie_Grass";
	public static final String PRICKLY_PEAR = "Prickly_Pear";
	public static final String PUFFBALL = "Puffball";
	public static final String PURPLE_FAIRY_CLUB = "Purple_Fairy_Club";
	public static final String RATTLESNAKE_PLANT = "Rattlesnake_Plant";
	public static final String REED_CANARY_GRASS = "Reed_Canary_Grass";
	public static final String RED_CORAL_FUNGUS = "Red_Coral_Fungus";
	public static final String RED_GOYO = "Red_Goyo";
	public static final String RED_HOT_MILK_CAP = "Red_Hot_Milk_Cap";
	public static final String REINDEER_LICHEN = "Reindeer_Lichen";
	public static final String ROCK_CAP_FERN = "Rock_Cap_Fern";
	public static final String ROSEBUSH = "Rosebush";
	public static final String ROUGH_HORSETAIL = "Rough_Horsetail";
	public static final String ROYAL_CATCHFLY = "Royal_Catchfly";
	public static final String ROYAL_FERN = "Royal_Fern";
	public static final String RUBBER_FIG = "Rubber_Fig";
	public static final String SAGUARO = "Saguaro";
	public static final String SCALY_TREE_FERN = "Scaly_Tree_Fern";
	public static final String SCARLET_ELFCUP = "Scarlet_Elfcup";
	public static final String SENSITIVE_FERN = "Sensitive_Fern";
	public static final String SHAGGY_MANE = "Shaggy_Mane";
	public static final String SHITAKE = "Shitake";
	public static final String SIBERIAN_SQUILL = "Siberian_Squill";
	public static final String SOLOMONS_SEAL = "Solomons_Seal";
	public static final String SORBUS = "Sorbus";
	public static final String SPIDER_PLANT = "Spider_Plant";
	public static final String SPLIT_LEAF_MONSTERA = "Split_Leaf_Monstera";
	public static final String SPLIT_LEAF_MONSTERA_EPIPHYTE = "Split_Leaf_Monstera_Epiphyte";
	public static final String STRAWBERRIES_AND_CREAM_MUSHROOM = "Strawberries_and_Cream_Mushroom";
	public static final String STRAW_MUSHROOM = "Straw_Mushroom";
	public static final String STRICT_BRANCH_CORAL_FUNGUS = "Strict_Branch_Coral_Fungus";
	public static final String SUEDE_BOLETE = "Suede_Bolete";
	public static final String SULFUR_TUFT = "Sulfur_Tuft";
	public static final String SUMAC = "Sumac";
	public static final String SUNDEW = "Sundew";
	public static final String SUNFLOWER = "Sunflower";
	public static final String SWEET_JOE_PYE_WEED = "Sweet_Joe_Pye_Weed";
	public static final String SWORD_FERN = "Sword_Fern";
	public static final String TEDDY_BEAR_CACTUS = "Teddy_Bear_Cactus";
	public static final String THALE_CRESS = "Thale_Cress";
	public static final String THISTLE = "Thistle";
	public static final String TITAN_ARUM = "Titan_Arum";
	public static final String TRAVELLERS_PALM = "Travellers_Palm";
	public static final String TRILLIUM = "Trillium";
	public static final String TRUMPET_VINE = "Trumpet_Vine";
	public static final String VANILLA_ORCHID = "Vanilla_Orchid";
	public static final String VENUS_FLYTRAP = "Venus_Flytrap";
	public static final String VICTORIA_LILY_PAD = "Victoria_Lily_Pad";
	public static final String VIOLET = "Violet";
	public static final String VIRGINIA_CREEPER = "Virginia_Creeper";
	public static final String VOMITING_RUSSULA = "Vomiting_Russula";
	public static final String VOODOO_LILY = "Voodoo_Lily";
	public static final String WALKING_FERN = "Walking_Fern";
	public static final String WATER_HYACINTH = "Water_Hyacinth";
	public static final String WEEPING_MILK_CAP = "Weeping_Milk_Cap";
	public static final String WESTERN_SKUNK_CABBAGE = "Western_Skunk_Cabbage";
	public static final String WHITE_SKUNK_CABBAGE = "White_Skunk_Cabbage";
	public static final String WINE_CAP = "Wine_Cap";
	public static final String WISTERIA = "Wisteria";
	public static final String WOOD_BLEWIT = "Wood_Blewit";
	public static final String WOOD_EAR = "Wood_Ear";
	public static final String WOOD_FERN = "Wood_Fern";
	public static final String WOOD_LILY = "Wood_Lily";
	public static final String WOOD_POPPY = "Wood_Poppy";
	public static final String WOOLY_CHANTERELLE = "Wooly_Chanterelle";
	public static final String WOOLY_MILK_CAP = "Wooly_Milk_Cap";
	public static final String YARROW = "Yarrow";
	public static final String YELLOW_PARASOL_MUSHROOM = "Yellow_Parasol_Mushroom";
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
	public static final String ZZ_PLANT = "ZZ_Plant";
	
	public static final String ARPOPHYLLUM_GIGANTEUM = "Arpophyllum_Giganteum";
	public static final String ARTISTS_CONK = "Artists_Conk";
	public static final String BEEFSTEAK_FUNGUS = "Beefsteak_Fungus";
	public static final String BITTER_OYSTER = "Bitter_Oyster";
	public static final String BIRCH_POLYPORE = "Birch_Polypore";
	public static final String BLACK_SPLEENWORT = "Black_Spleenwort";
	public static final String BOSTON_FERN = "Boston_Fern";
	public static final String CHAGA = "Chaga";
	public static final String CHLOROPHOS_FOXFIRE = "Chlorophos_Foxfire";
	public static final String DRYADS_SADDLE = "Dryads_Saddle";
	public static final String HEDGEHOG_LIP_ORCHID = "Hedgehog_Lip_Orchid";
	public static final String LEOPARD_ORCHID_EPIPHYTE = "Leopard_Orchid_Epiphyte";
	public static final String LIONS_MANE = "Lions_Mane";
	public static final String MISTLETOE = "Mistletoe";
	public static final String PALE_UMBRELLA_ORCHID = "Pale_Umbrella_Orchid";
	public static final String PURPUREORACHIS = "Purpureorachis";
	public static final String REISHI = "Reishi";
	public static final String SHAGGY_BRACKET = "Shaggy_Bracket";
	public static final String SQUIRRELS_FOOT_FERN = "Squirrels_Foot_Fern";
	public static final String STAGHORN_FERN = "Staghorn_Fern";
	public static final String SULPHUR_SHELF = "Sulphur_Shelf";
	public static final String TILLANDSIA_BROMELIAD = "Tillandsia_Bromeliad";
	public static final String TURKEY_TAIL = "Turkey_Tail";
	
	public static final String AFRICAN_MILK_BARREL = "African_Milk_Barrel";
	public static final String AFRICAN_MILK_TREE = "African_Milk_Tree";
	public static final String ALBANIAN_SPURGE = "Albanian_Spurge";
	public static final String BLOOD_LILY = "Blood_Lily";
	public static final String BLUEBELL = "Bluebell";
	public static final String BONATEA = "Bonatea";
	public static final String CARALUMA = "Caralluma";
	public static final String CYCAD = "Cycad";
	public static final String DESERT_ROSE = "Desert_Rose";
	public static final String EGYPTIAN_AUTUMN_CROCUS = "Egyptian_Autumn_Crocus";
	public static final String EUROPEAN_BEDSTRAW= "European_Bedstraw";
	public static final String FREESIA = "Freesia";
	public static final String GARLIC_MUSTARD = "Garlic_Mustard";
	public static final String GIFBOOM = "Gifboom";
	public static final String LEOPARD_ORCHID = "Leopard_Orchid";
	public static final String LOOSE_FLOWERED_ORCHID = "Loose_Flowered_Orchid";
	public static final String LOTUS = "Lotus";
	public static final String MAIDENHAIR_FERN = "Maidenhair_Fern";
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
	public static final String SPANISH_MOSS = "Spanish_Moss";
	public static final String OLD_MANS_BEARD_LICHEN = "Old_Mans_Beard_Lichen";
	public static final String SAPPHIRE_TOWER = "Sapphire_Tower";
	public static final String QUEEN_OF_THE_ANDES = "Queen_of_the_Andes";

	
	public static final String[] SHALLOW_WATER_BIOMES = new String[]{"River","Beach","Gravel Beach","Swamp","Lake","Shore","Salt Swamp","Lakeshore","Riverbank","Estuary"};
	public static final String[] LAND_BIOMES = new String[] {"All","!Ocean","!Hell","!Deep Ocean"};
	public static final String[] ALL_BIOMES = AthsParser.getBiomes();
	
	public static final String[] ROCKTYPES = new String[] {"IgEx","IgIn","Sed","MM"};
	public static final String[][] ROCKTYPES_NAMES = new String[][] {Global.STONE_IGEX, Global.STONE_IGIN, Global.STONE_SED, Global.STONE_MM};
	public static final Block[] ROCKTYPES_BLOCKS = new Block[] {TFCBlocks.stoneIgEx, TFCBlocks.stoneIgIn, TFCBlocks.stoneSed, TFCBlocks.stoneMM};
	
	public static final String[] ALLOWED_REGIONS = new String[] {"Americas","Europe","Africa","Asia"};
	public static final String[] ALLOWED_BIOMES = AthsParser.add(AthsParser.append(ALL_BIOMES, AthsParser.prefix(ALL_BIOMES, "!")), "All");
	public static final String[] ALLOWED_ROCKS = AthsParser.add(AthsParser.append(Global.STONE_ALL, ROCKTYPES), "All");
	
	public static final ArrayList<BlockMetaPair> ALL_TREE_TRUNKS = new ArrayList<BlockMetaPair>(Arrays.asList(
			new BlockMetaPair(BlockSetup.logNatural, -1),
			new BlockMetaPair(BlockSetup.logNatural2, -1),
			new BlockMetaPair(BlockSetup.logNatural3, -1), 
			new BlockMetaPair(BlockSetup.branch__y_, -1), 
			new BlockMetaPair(BlockSetup.branch2__y_, -1), 
			new BlockMetaPair(BlockSetup.branch3__y_, -1)));
	
	// some large prime numbers
	public static final int PRIME_1 = 83;
	public static final int PRIME_2 = 139;
	
	public static final float TREE_SCALE = 4.0f;
	public static final int TREE_BASE_RARITY = 1000;
	
	public static final float HALF_PI = (float) Math.PI / 2;
	public static final float TWO_PI = (float) Math.PI * 2;
	
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
	
	public static final Point3D[] HORIZ_NEIGHBORS = new Point3D[] {
			new Point3D(0, 0, 1), 
			new Point3D(-1, 0, 0),
			new Point3D(0, 0, -1),
			new Point3D(1, 0, 0)};
}
