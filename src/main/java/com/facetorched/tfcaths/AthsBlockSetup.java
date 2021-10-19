package com.facetorched.tfcaths;

import com.dunk.tfc.Core.TFC_Time;
import com.dunk.tfc.api.Enums.EnumTree;
import com.facetorched.tfcaths.blocks.BlockPlant;
import com.facetorched.tfcaths.blocks.BlockPlant3d;
import com.facetorched.tfcaths.blocks.BlockPlantAlgae;
import com.facetorched.tfcaths.blocks.BlockPlantFoliage;
import com.facetorched.tfcaths.blocks.BlockPlantLilyPad;
import com.facetorched.tfcaths.blocks.BlockPlantLilyPad3d;
import com.facetorched.tfcaths.blocks.BlockPlantStraw;
import com.facetorched.tfcaths.blocks.BlockPlantCrop;
import com.facetorched.tfcaths.blocks.BlockPlantFlower;
import com.facetorched.tfcaths.blocks.BlockPlantTree;
import com.facetorched.tfcaths.blocks.BlockPlantTreeDeciduous;
import com.facetorched.tfcaths.blocks.BlockPlantTreeSnowable;
import com.facetorched.tfcaths.blocks.BlockPlantTreeTrimmable;
import com.facetorched.tfcaths.enums.EnumVary;
import com.facetorched.tfcaths.items.itemblocks.ItemPlant;
import com.facetorched.tfcaths.items.itemblocks.ItemPlantAlgae;
import com.facetorched.tfcaths.items.itemblocks.ItemPlantLilyPad;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class AthsBlockSetup {
	
	public static Block sagebrush;
	public static Block prarieGrass;
	public static Block leafyUndergrowth;
	public static Block mediumUndergrowth;
	
	public static Block birdOfParadise;
	public static Block boletus;
	public static Block chanterelle;
	public static Block devilsTongue;
	public static Block duneGrass;
	public static Block horsetail;
	public static Block indigoMilkCap;
	public static Block lobsterClaws;
	public static Block morel;
	public static Block pondGrass;
	public static Block thistle;
	public static Block titanArum;
	public static Block voodooLily;
	public static Block bridalVeilStinkhorn;
	public static Block entoloma;
	public static Block shitake;
	
	public static Block lilyPad;
	public static Block algaeMatGreen;
	public static Block algaeMatRed;
	public static Block algaeMatCyanobacteria;
	
	public static Block youngAsh;
	public static Block youngAspen;
	public static Block youngBirch;
	public static Block youngChestnut;
	public static Block youngGinko;
	public static Block youngHickory;
	public static Block youngMaple;
	public static Block youngOak;
	public static Block youngSycamore;
	public static Block youngWhiteElm;
	public static Block youngWillow;
	
	public static Block youngAcacia;
	public static Block youngBaobab;
	public static Block youngDoglasFir;
	public static Block youngEbony;
	public static Block youngFever;
	public static Block youngGhaf;
	public static Block youngKapok;
	public static Block youngLaurel;
	public static Block youngLimba;
	public static Block youngMahoe;
	public static Block youngMahogany;
	public static Block youngPine;
	public static Block youngSequoia;
	public static Block youngSpruce;
	public static Block youngTeak;
	public static Block youngYew;
	
	public static Block youngWhiteCedar;
	
	public static Block victoriaLilyPad;
	public static Block yucca;
	
	public static int plantCrossRenderID;
	public static int plantCropRenderID;
	public static int plantLilyPadRenderID;
	public static int plantTreeRenderID;
	public static int plantTreeTrimmableRenderID;
	public static int plant3dRenderID;
	
	public static void setup() {
		// plants with various sizes
		sagebrush = plantRegistryHelper(new BlockPlant().setNames(AthsGlobal.SAGEBRUSH).setScale(2.0F));
		prarieGrass = plantRegistryHelper(new BlockPlantFoliage().setNames(AthsGlobal.PRARIE_GRASS).setScale(2.0F));
		leafyUndergrowth = plantRegistryHelper(new BlockPlant().setNames(AthsGlobal.LEAFY_UNDERGROWTH).setScale(2.0F));
		mediumUndergrowth = plantRegistryHelper(new BlockPlantCrop().setNames(AthsGlobal.MEDIUM_UNDERGROWTH).setScale(2.0F));

		// straw dropping plants
		duneGrass = plantRegistryHelper(new BlockPlantStraw().setName(AthsGlobal.DUNE_GRASS));
		horsetail = plantRegistryHelper(new BlockPlantStraw().setName(AthsGlobal.HORSETAIL));
		pondGrass = plantRegistryHelper(new BlockPlantFoliage().setName(AthsGlobal.POND_GRASS).setScale(2.0F));
		
		// mushrooms
		boletus = plantRegistryHelper(new BlockPlant().setName(AthsGlobal.BOLETUS));
		chanterelle = plantRegistryHelper(new BlockPlant().setName(AthsGlobal.CHANTERELLE));
		indigoMilkCap = plantRegistryHelper(new BlockPlant().setName(AthsGlobal.INDIGO_MILK_CAP));
		morel = plantRegistryHelper(new BlockPlant().setName(AthsGlobal.MOREL));
		bridalVeilStinkhorn = plantRegistryHelper(new BlockPlant().setName(AthsGlobal.BRIDAL_VEIL_STINKHORN));
		entoloma = plantRegistryHelper(new BlockPlant().setName(AthsGlobal.ENTOLOMA));
		shitake = plantRegistryHelper(new BlockPlant().setName(AthsGlobal.SHITAKE));
		
		// changing plants
		birdOfParadise = plantRegistryHelper(new BlockPlant().setNames(AthsGlobal.BIRD_OF_PARADISE).addVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.APRIL, TFC_Time.NOVEMBER).setScale(2.0f));
		devilsTongue = plantRegistryHelper(new BlockPlant().setNames(AthsGlobal.DEVILS_TOUNGE).addVary(EnumVary.FLOWER).setFlowerMonth(TFC_Time.MAY).setScale(2.0F));
		lobsterClaws = plantRegistryHelper(new BlockPlant().setNames(AthsGlobal.LOBSTER_CLAWS).addVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.APRIL, TFC_Time.AUGUST).setScale(2.0F));
		thistle = plantRegistryHelper(new BlockPlant().setNames(AthsGlobal.THISTLE).addVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.MAY, TFC_Time.OCTOBER));
		titanArum = plantRegistryHelper(new BlockPlant().setNames(AthsGlobal.TITAN_ARUM).addVary(EnumVary.FLOWER).setFlowerMonth(TFC_Time.AUGUST).setScale(2.0F));
		voodooLily = plantRegistryHelper(new BlockPlant().setNames(AthsGlobal.VOODOO_LILY).addVary(EnumVary.FLOWER).setFlowerMonth(TFC_Time.JUNE).setScale(2.0F));
		
		// lily pad like plants
		lilyPad = plantLilyPadRegistryHelper(new BlockPlantLilyPad().setNames(AthsGlobal.LILY_PAD));
		algaeMatGreen = plantAlgaeRegistryHelper(new BlockPlantAlgae().setColorRange(.6667f, 80, .5f, 128, 0f, 0).setNames(AthsGlobal.ALGAE_MAT_GREEN));
		algaeMatRed = plantAlgaeRegistryHelper(new BlockPlantAlgae().setColorRange(.5f, 60, .3f, 0, .1f, 0).setNames(AthsGlobal.ALGAE_MAT_RED));
		algaeMatCyanobacteria = plantAlgaeRegistryHelper(new BlockPlantAlgae().setColorRange(0f, 0, .5f, 70, .1f, 40).setNames(AthsGlobal.ALGAE_MAT_CYANOBACTERIA));
		
		// deciduous trees
		youngAsh = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.ASH).setNames(AthsGlobal.YOUNG_ASH).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW}));
		youngAspen = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.ASPEN).setNames(AthsGlobal.YOUNG_ASPEN).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW}));
		youngBirch = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.BIRCH).setNames(AthsGlobal.YOUNG_BIRCH).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW}));
		youngChestnut = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.CHESTNUT).setNames(AthsGlobal.YOUNG_CHESTNUT).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW}));
		youngGinko = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.GINGKO).setNames(AthsGlobal.YOUNG_GINGKO).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW}));
		youngHickory = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.HICKORY).setNames(AthsGlobal.YOUNG_HICKORY).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW}));
		youngMaple = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.MAPLE).setNames(AthsGlobal.YOUNG_MAPLE).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW}));
		youngOak = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.OAK).setNames(AthsGlobal.YOUNG_OAK).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW}));
		youngSycamore = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.SYCAMORE).setNames(AthsGlobal.YOUNG_SYCAMORE).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW}));
		youngWhiteElm = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.WHITEELM).setNames(AthsGlobal.YOUNG_WHITE_ELM).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW}));
		youngWillow = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.WILLOW).setNames(AthsGlobal.YOUNG_WILLOW).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW}));
		
		// trees snowy
		youngDoglasFir = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.DOUGLASFIR).setNames(AthsGlobal.YOUNG_DOUGLAS_FIR).addVary(EnumVary.SNOW));
		youngPine = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.PINE).setNames(AthsGlobal.YOUNG_PINE).addVary(EnumVary.SNOW));
		youngSequoia = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.REDWOOD).setNames(AthsGlobal.YOUNG_SEQUOIA).addVary(EnumVary.SNOW));
		youngSpruce = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.SPRUCE).setNames(AthsGlobal.YOUNG_SPRUCE).addVary(EnumVary.SNOW));
		youngYew = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.YEW).setNames(AthsGlobal.YOUNG_YEW).addVary(EnumVary.SNOW));
		
		// trees
		youngAcacia = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.UTACACIA).setName(AthsGlobal.YOUNG_ACACIA));
		youngBaobab = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.BAOBAB).setName(AthsGlobal.YOUNG_BAOBAB));
		youngEbony = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.EBONY).setName(AthsGlobal.YOUNG_EBONY));
		youngFever = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.FEVERTREE).setName(AthsGlobal.YOUNG_FEVER));
		youngGhaf = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.GHAF).setName(AthsGlobal.YOUNG_GHAF));
		youngKapok = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.KAPOK).setName(AthsGlobal.YOUNG_KAPOK));
		youngLaurel = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.LAUREL).setName(AthsGlobal.YOUNG_LAUREL));
		youngLimba = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.LIMBA).setName(AthsGlobal.YOUNG_LIMBA));
		youngMahoe = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.MAHOE).setName(AthsGlobal.YOUNG_MAHOE));
		youngMahogany = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.MAHOGANY).setName(AthsGlobal.YOUNG_MAHOGANY));
		youngTeak = plantFlammableRegistryHelper(new BlockPlantTree().setSapling(EnumTree.TEAK).setName(AthsGlobal.YOUNG_TEAK));
		
		//trimmable
		youngWhiteCedar = plantFlammableRegistryHelper(new BlockPlantTreeTrimmable().setSapling(EnumTree.WHITECEDAR).setNames(AthsGlobal.YOUNG_WHITE_CEDAR).addVary(EnumVary.SNOW));
		
		//3d
		victoriaLilyPad = plantLilyPadRegistryHelper(new BlockPlantLilyPad3d().setName(AthsGlobal.VICTORIA_LILY_PAD).setPart("Base", 0).setPart("Rim_Gap", 0).setPart("Rim_Middle", 0).setPart("Roots", 0));
		yucca = plantRegistryHelper(new BlockPlant3d().setName(AthsGlobal.YUCCA).setPart("Flower", 0).setPart("Leaf", 0).setPart("Stem", 0));
	}
	
	public static BlockPlant plantRegistryHelper(BlockPlant block) {
		GameRegistry.registerBlock(block, ItemPlant.class, block.getPlantKey());
		return block;
	}
	
	public static BlockPlant plantFlammableRegistryHelper(BlockPlant block) {
		GameRegistry.registerBlock(block, ItemPlant.class, block.getPlantKey());
		Blocks.fire.setFireInfo(block, 5, 5);
		return block;
	}
	
	public static BlockPlant plantLilyPadRegistryHelper(BlockPlant block) {
		GameRegistry.registerBlock(block, ItemPlantLilyPad.class, block.getPlantKey());
		return block;
	}
	
	public static BlockPlant plantAlgaeRegistryHelper(BlockPlant block) {
		GameRegistry.registerBlock(block, ItemPlantAlgae.class, block.getPlantKey());
		return block;
	}
}
