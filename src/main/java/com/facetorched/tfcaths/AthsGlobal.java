package com.facetorched.tfcaths;

import com.facetorched.tfcaths.util.AthsParser;

public class AthsGlobal {
	public static final String SAGEBRUSH = "Sagebrush";
	public static final String PRARIE_GRASS = "Prarie_Grass";
	public static final String LEAFY_UNDERGROWTH = "Leafy_Undergrowth";
	public static final String MEDIUM_UNDERGROWTH = "Medium_Undergrowth";
	
	public static final String BIRD_OF_PARADISE = "Bird_of_Paradise";
	public static final String BOLETUS = "Boletus";
	public static final String CHANTERELLE = "Chanterelle";
	public static final String DEVILS_TOUNGE = "Devils_Tongue";
	public static final String DUNE_GRASS = "Dune_Grass";
	public static final String HORSETAIL = "Horsetail";
	public static final String INDIGO_MILK_CAP = "Indigo_Milk_Cap";
	public static final String LOBSTER_CLAWS = "Lobster_Claws";
	public static final String MOREL = "Morel";
	public static final String POND_GRASS = "Pond_Grass";
	public static final String THISTLE = "Thistle";
	public static final String TITAN_ARUM = "Titan_Arum";
	public static final String VOODOO_LILY = "Voodoo_Lily";
	public static final String BRIDAL_VEIL_STINKHORN = "Bridal_Veil_Stinkhorn";
	public static final String ENTOLOMA = "Entoloma";
	public static final String SHITAKE = "Shitake";
	
	public static final String ALGAE_MAT = "Algae_Mat";
	public static final String ALGAE_MAT_BROWN = "Algae_Mat_Brown";
	public static final String ALGAE_MAT_CYANOBACTERIA = "Algae_Mat_Cyanobacteria";
	public static final String ALGAE_MAT_GREEN = "Algae_Mat_Green";
	public static final String ALGAE_MAT_RED = "Algae_Mat_Red";
	public static final String ALGAE_MAT_YELLOW = "Algae_Mat_Yellow";
	public static final String LILY_PAD = "Lily_Pad";
	
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
	
	public static final String VICTORIA_LILY_PAD = "Victoria_Lily_Pad";
	public static final String YUCCA = "Yucca";
	public static final String SUMAC_SHORT = "Sumac_Short";
	public static final String SUMAC_TALL = "Sumac_Tall";
	public static final String HOSTA_HALCYON = "Hosta_Halcyon";
	public static final String HOSTA_PARIOT = "Hosta_Patriot";
	public static final String HOSTA_VULCAN = "Hosta_Vulcan";
	public static final String BRACKEN_FERN = "Bracken_Fern";
	public static final String CYCAD = "Cycad";
	public static final String LADY_FERN = "Lady_Fern";
	public static final String OSTRICH_FERN = "Ostrich_Fern";
	public static final String PAINTED_FERN = "Painted_Fern";
	public static final String SWORD_FERN = "Sword_Fern";
	public static final String WOOD_FERN = "Wood_Fern";
	public static final String DEVILS_CLUB = "Devils_Club";
	public static final String BURRDOCK = "Burrdock";
	
	public static final String[] SHALLOW_WATER_BIOMES = new String[]{"River","Beach","Gravel Beach","Swamp","Lake","Shore","Salt Swamp","Lakeshore","Riverbank","Estuary"};
	public static final String[] LAND_BIOMES = new String[] {"All","!Ocean","!Hell","!Deep Ocean"};
	
	public static String[] ALL_BIOMES;
	
	// some large prime numbers
	public static final int PRIME_1 = 83;
	public static final int PRIME_2 = 139;
	
	// metas allocated for item metas
	public static final int ITEM_META_BITS = 3;
	public static final int ITEM_META_BITMASK = (1 << ITEM_META_BITS) - 1;
	
	public static final float TREE_SCALE = 5.0f;
	
	public static void setConstants() {
		ALL_BIOMES = AthsParser.getBiomeStringList();
	}
}
