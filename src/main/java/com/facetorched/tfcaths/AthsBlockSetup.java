package com.facetorched.tfcaths;

import com.facetorched.tfcaths.blocks.BlockPlant;
import com.facetorched.tfcaths.blocks.BlockPlantAlgae;
import com.facetorched.tfcaths.blocks.BlockPlantCrop;
import com.facetorched.tfcaths.blocks.BlockPlantTree;
import com.facetorched.tfcaths.blocks.BlockPlantTreeTrimmable;
import com.facetorched.tfcaths.items.itemblocks.ItemPlant;
import com.facetorched.tfcaths.items.itemblocks.ItemPlantLilyPad;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class AthsBlockSetup {
	
	public static Block sagebrush;
	public static Block prarieGrass;
	public static Block leafyUndergrowth;
	public static Block mediumUndergrowth;
	
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
	
	public static Block algaeMatBrown;
	
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
	
	public static int plantCrossRenderID;
	public static int plantCropRenderID;
	public static int plantLilyPadRenderID;
	public static int plantTreeRenderID;
	public static int plantTreeTrimmableRenderID;
	
	public static void setup() {
		// plants with various sizes
		sagebrush = plantRegistryHelper(new BlockPlant().setNames(Global.SAGEBRUSH).setScale(2.0F));
		prarieGrass = plantRegistryHelper(new BlockPlant().setNames(Global.PRARIE_GRASS).setScale(2.0F)); // biome color??
		leafyUndergrowth = plantRegistryHelper(new BlockPlant().setNames(Global.LEAFY_UNDERGROWTH).setScale(2.0F));
		mediumUndergrowth = plantRegistryHelper(new BlockPlantCrop().setNames(Global.MEDIUM_UNDERGROWTH).setScale(2.0F));

		// plants
		boletus = plantRegistryHelper(new BlockPlant().setName(Global.BOLETUS));
		chanterelle = plantRegistryHelper(new BlockPlant().setName(Global.CHANTERELLE));
		devilsTongue = plantRegistryHelper(new BlockPlant().setName(Global.DEVILS_TOUNGE).setScale(2.0F));
		duneGrass = plantRegistryHelper(new BlockPlant().setName(Global.DUNE_GRASS));
		horsetail = plantRegistryHelper(new BlockPlant().setName(Global.HORSETAIL));
		indigoMilkCap = plantRegistryHelper(new BlockPlant().setName(Global.INDIGO_MILK_CAP));
		lobsterClaws = plantRegistryHelper(new BlockPlant().setName(Global.LOBSTER_CLAWS).setScale(2.0F));
		morel = plantRegistryHelper(new BlockPlant().setName(Global.MOREL));
		pondGrass = plantRegistryHelper(new BlockPlant().setName(Global.PONDGRASS).setScale(2.0F)); // biome color???
		thistle = plantRegistryHelper(new BlockPlant().setName(Global.THISTLE));
		titanArum = plantRegistryHelper(new BlockPlant().setName(Global.TITAN_ARUM).setScale(2.0F));
		voodooLily = plantRegistryHelper(new BlockPlant().setName(Global.VOODOO_LILY).setScale(2.0F));
		
		// lily pad like plants
		algaeMatBrown = plantLilyPadRegistryHelper(new BlockPlantAlgae().setName(Global.ALGAE_MAT_BROWN));
		
		// deciduous trees
		youngAsh = plantRegistryHelper(new BlockPlantTree().setNames(Global.YOUNG_ASH).setScale(6.0F).setMonthMetas(Global.DECIDUOUS_METAS));
		youngAspen = plantRegistryHelper(new BlockPlantTree().setNames(Global.YOUNG_ASPEN).setScale(6.0F).setMonthMetas(Global.DECIDUOUS_METAS));
		youngBirch = plantRegistryHelper(new BlockPlantTree().setNames(Global.YOUNG_BIRCH).setScale(6.0F).setMonthMetas(Global.DECIDUOUS_METAS));
		youngChestnut = plantRegistryHelper(new BlockPlantTree().setNames(Global.YOUNG_CHESTNUT).setScale(6.0F).setMonthMetas(Global.DECIDUOUS_METAS));
		youngGinko = plantRegistryHelper(new BlockPlantTree().setNames(Global.YOUNG_GINKO).setScale(6.0F).setMonthMetas(Global.DECIDUOUS_METAS));;
		youngHickory = plantRegistryHelper(new BlockPlantTree().setNames(Global.YOUNG_HICKORY).setScale(6.0F).setMonthMetas(Global.DECIDUOUS_METAS));;
		youngMaple = plantRegistryHelper(new BlockPlantTree().setNames(Global.YOUNG_MAPLE).setScale(6.0F).setMonthMetas(Global.DECIDUOUS_METAS));;
		youngOak = plantRegistryHelper(new BlockPlantTree().setNames(Global.YOUNG_OAK).setScale(6.0F).setMonthMetas(Global.DECIDUOUS_METAS));;
		youngSycamore = plantRegistryHelper(new BlockPlantTree().setNames(Global.YOUNG_SYCAMORE).setScale(6.0F).setMonthMetas(Global.DECIDUOUS_METAS));;
		youngWhiteElm = plantRegistryHelper(new BlockPlantTree().setNames(Global.YOUNG_WHITE_ELM).setScale(6.0F).setMonthMetas(Global.DECIDUOUS_METAS));;
		youngWillow = plantRegistryHelper(new BlockPlantTree().setNames(Global.YOUNG_WILLOW).setScale(6.0F).setMonthMetas(Global.DECIDUOUS_METAS));;
		
		// trees
		youngAcacia = plantRegistryHelper(new BlockPlantTree().setName(Global.YOUNG_ACACIA).setScale(6.0F));
		youngBaobab = plantRegistryHelper(new BlockPlantTree().setName(Global.YOUNG_BAOBAB).setScale(6.0F));
		youngDoglasFir = plantRegistryHelper(new BlockPlantTree().setName(Global.YOUNG_DOUGLAS_FIR).setScale(6.0F));
		youngEbony = plantRegistryHelper(new BlockPlantTree().setName(Global.YOUNG_EBONY).setScale(6.0F));
		youngFever = plantRegistryHelper(new BlockPlantTree().setName(Global.YOUNG_FEVER).setScale(6.0F));
		youngGhaf = plantRegistryHelper(new BlockPlantTree().setName(Global.YOUNG_GHAF).setScale(6.0F));
		youngKapok = plantRegistryHelper(new BlockPlantTree().setName(Global.YOUNG_KAPOK).setScale(6.0F));
		youngLaurel = plantRegistryHelper(new BlockPlantTree().setName(Global.YOUNG_LAUREL).setScale(6.0F));
		youngLimba = plantRegistryHelper(new BlockPlantTree().setName(Global.YOUNG_LIMBA).setScale(6.0F));
		youngMahoe = plantRegistryHelper(new BlockPlantTree().setName(Global.YOUNG_MAHOE).setScale(6.0F));
		youngMahogany = plantRegistryHelper(new BlockPlantTree().setName(Global.YOUNG_MAHOGANY).setScale(6.0F));
		youngPine = plantRegistryHelper(new BlockPlantTree().setName(Global.YOUNG_PINE).setScale(6.0F));
		youngSequoia = plantRegistryHelper(new BlockPlantTree().setName(Global.YOUNG_SEQUOIA).setScale(6.0F));
		youngSpruce = plantRegistryHelper(new BlockPlantTree().setName(Global.YOUNG_SPRUCE).setScale(6.0F));
		youngTeak = plantRegistryHelper(new BlockPlantTree().setName(Global.YOUNG_TEAK).setScale(6.0F));
		youngYew = plantRegistryHelper(new BlockPlantTree().setName(Global.YOUNG_YEW).setScale(6.0F));
		
		//trimmable
		youngWhiteCedar = plantRegistryHelper(new BlockPlantTreeTrimmable().setNames(Global.YOUNG_WHITE_CEDAR).setScale(6.0F));
		
	}
	
	public static Block plantRegistryHelper(BlockPlant block) {
		GameRegistry.registerBlock(block, ItemPlant.class, block.getPlantKey());
		return block;
	}
	
	public static Block plantLilyPadRegistryHelper(BlockPlant block) {
		GameRegistry.registerBlock(block, ItemPlantLilyPad.class, block.getPlantKey());
		return block;
	}
}
