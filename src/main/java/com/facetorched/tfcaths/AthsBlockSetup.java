package com.facetorched.tfcaths;

import com.dunk.tfc.ItemSetup;
import com.dunk.tfc.Blocks.Vanilla.BlockCustomFlowerPot;
import com.dunk.tfc.Core.TFC_Time;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.Constant.Global;
import com.dunk.tfc.api.Enums.EnumTree;
import com.facetorched.teloaddon.TeloItemSetup;
import com.facetorched.tfcaths.blocks.BlockCrystal;
import com.facetorched.tfcaths.blocks.BlockCrystalCluster;
import com.facetorched.tfcaths.blocks.BlockPlant;
import com.facetorched.tfcaths.blocks.BlockPlant3d;
import com.facetorched.tfcaths.blocks.BlockPlant3dFlower;
import com.facetorched.tfcaths.blocks.BlockPlantAlgae;
import com.facetorched.tfcaths.blocks.BlockPlantCactus;
import com.facetorched.tfcaths.blocks.BlockPlantEpiphyte3d;
import com.facetorched.tfcaths.blocks.BlockPlantEpiphyte3dFlower;
import com.facetorched.tfcaths.blocks.BlockPlantEpiphyte3dFungus;
import com.facetorched.tfcaths.blocks.BlockPlantFlower;
import com.facetorched.tfcaths.blocks.BlockPlantFungus;
import com.facetorched.tfcaths.blocks.BlockPlantLayerFlower;
import com.facetorched.tfcaths.blocks.BlockPlantLilyPad;
import com.facetorched.tfcaths.blocks.BlockPlantLilyPad3d;
import com.facetorched.tfcaths.blocks.BlockPlantLilyPad3dFlower;
import com.facetorched.tfcaths.blocks.BlockPlantLow;
import com.facetorched.tfcaths.blocks.BlockPlantLowFlower;
import com.facetorched.tfcaths.blocks.BlockPlantStraw;
import com.facetorched.tfcaths.blocks.BlockPlantTree;
import com.facetorched.tfcaths.blocks.BlockPlantTree3d;
import com.facetorched.tfcaths.blocks.BlockPlantTree3dFlower;
import com.facetorched.tfcaths.blocks.BlockPlantTreeFlower;
import com.facetorched.tfcaths.blocks.BlockPlantTreeTrimmable;
import com.facetorched.tfcaths.blocks.BlockPlantTreeTrimmableFlower;
import com.facetorched.tfcaths.enums.EnumVary;
import com.facetorched.tfcaths.items.itemblocks.ItemCrystal;
import com.facetorched.tfcaths.util.AthsParser;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class AthsBlockSetup {
	
	public static Block sagebrush;
	public static Block prairieGrass;
	public static Block leafyUndergrowth;
	public static Block mediumUndergrowth;
	
	public static Block aloeVera;
	public static Block angelsTrumpet;
	public static Block angelWingCactus;
	public static Block blueCereusCactus;
	public static Block camasFlower;
	public static Block chives;
	public static Block clover;
	public static Block commonReeds;
	public static Block creepingCharlie;
	public static Block cupPlant;
	public static Block daffodil;
	public static Block elephantGrass;
	public static Block fireweed;
	public static Block giantHogweed;
	public static Block heather;
	public static Block hibiscus;
	public static Block holly;
	public static Block fieldHorsetail;
	public static Block roughHorsetail;
	public static Block indianPipe;
	public static Block iris;
	public static Block jackInThePulpit;
	public static Block lavender;
	public static Block leafyLowUndergrowth;
	public static Block lupine;
	public static Block marigold;
	public static Block ocotillo;
	public static Block organPipeCactus;
	public static Block pokeweed;
	public static Block pricklyPear;
	public static Block rosebush;
	public static Block saguaro;
	public static Block scalyTreeFern;
	public static Block sensitiveFern;
	public static Block sorbus;
	public static Block sundew;
	public static Block sunflower;
	public static Block swordFern;
	public static Block venusFlytrap;
	public static Block waterHyacinth;
	public static Block yarrow;
	public static Block devilsFingers;
	public static Block duckweed;
	
	public static Block birdOfParadise;
	public static Block boletus;
	public static Block chanterelle;
	public static Block devilsTongue;
	public static Block duneGrass;
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
	
	public static Block africanMilkBarrel;
	public static Block africanMilkTree;
	public static Block albanianSpurge;
	public static Block bloodLily;
	public static Block bluebell;
	public static Block bonatea;
	public static Block caralluma;
	public static Block chlorophosFoxfire;
	public static Block cycad;
	public static Block desertRose;
	public static Block egyptianAutumnCrocus;
	public static Block europeanBedstraw;
	public static Block freesia;
	public static Block garlicMustard;
	public static Block gifboom;
	public static Block leopardOrchid;
	public static Block looseFloweredOrchid;
	public static Block lotus;
	public static Block maidenhairFern;
	public static Block mallow;
	public static Block narbonVetch;
	public static Block papyrus;
	public static Block periwinkle;
	public static Block quaqua;
	public static Block rafflesia;
	public static Block royalJasmine;
	public static Block saltwort;
	public static Block saxaul;
	public static Block sesame;
	public static Block snakeSanseveria;
	public static Block starfishPlant;
	public static Block swordSanseveria;
	public static Block waterPlantain;
	public static Block welwitschia;
	public static Block woodBitterVetch;
	
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
	public static Block sumac;
	public static Block hostaHalcyon;
	public static Block hostaPatriot;
	public static Block hostaVulcan;
	public static Block hostaSumAndSubstance;
	public static Block brackenFern;
	public static Block ladyFern;
	public static Block ostrichFern;
	public static Block paintedFern;
	public static Block woodFern;
	public static Block devilsClub;
	public static Block burdock;
	
	public static Block arpophyllumGiganteum;
	public static Block artistsConk;
	public static Block beefsteakFungus;
	public static Block bitterOyster;
	public static Block birchPolypore;
	public static Block blackSpleenwort;
	public static Block bostonFern;
	public static Block chaga;
	public static Block dryadsSaddle;
	public static Block hedgehogLipOrchid;
	public static Block leopardOrchidEpiphyte;
	public static Block lionsMane;
	public static Block mistletoe;
	public static Block paleUmbrellaOrchid;
	public static Block purpureorachis;
	public static Block reishi;
	public static Block shaggyBracket;
	public static Block squirrelsFootFern;
	public static Block staghornFern;
	public static Block sulphurShelf;
	public static Block tillandsiaBromeliad;
	public static Block turkeyTail;
	
	public static Block agate;
	public static Block amethyst;
	public static Block amethystCluster;
	public static Block beryl;
	public static Block berylCluster;
	public static Block diamond;
	public static Block diamondCluster;
	public static Block emerald;
	public static Block emeraldCluster;
	public static Block garnet;
	public static Block garnetCluster;
	public static Block jade;
	public static Block jasper;
	public static Block opal;
	public static Block ruby;
	public static Block rubyCluster;
	public static Block sapphire;
	public static Block sapphireCluster;
	public static Block topaz;
	public static Block topazCluster;
	public static Block tourmaline;
	public static Block tourmalineCluster;
	public static Block fluorite;
	public static Block fluoriteCluster;
	
	public static Block gypsum;
	public static Block gypsumCluster;
	public static Block rockCrystal;
	public static Block rockCrystalCluster;
	
	public static int plantCrossRenderID;
	public static int plantCropRenderID;
	public static int plantLilyPadRenderID;
	public static int plantTreeRenderID;
	public static int plantTreeTrimmableRenderID;
	public static int plant3dRenderID;
	public static int plantEpiphyte3dRenderID;
	public static int plantLowRenderID;
	public static int plantLayerRenderID;
	public static int directionalLayerRenderID;
	public static int directionalCrossRenderID;
	public static int plantWaterRenderID;
	
	public static void setup() {
		// embedded
		agate = crystalRegistryHelper(new BlockCrystal().setItemRare(ItemSetup.gemAgate).setIsTransparent(), AthsGlobal.AGATE);
		jade = crystalRegistryHelper(new BlockCrystal().setItemRare(ItemSetup.gemJade).setIsTransparent(), AthsGlobal.JADE);
		jasper = crystalRegistryHelper(new BlockCrystal().setItemRare(ItemSetup.gemJasper).setIsTransparent(), AthsGlobal.JASPER);
		opal = crystalRegistryHelper(new BlockCrystal().setItemRare(ItemSetup.gemOpal).setIsTransparent(), AthsGlobal.OPAL);
		
		// cluster/crusts
		amethyst = crystalRegistryHelper(new BlockCrystal().setItemCommon(ItemSetup.gemAmethyst), AthsGlobal.AMETHYST);
		amethystCluster = crystalClusterRegistryHelper(amethyst);
		beryl = crystalRegistryHelper(new BlockCrystal().setItemCommon(ItemSetup.gemBeryl), AthsGlobal.BERYL);
		berylCluster = crystalClusterRegistryHelper(beryl);
		diamond = crystalRegistryHelper(new BlockCrystal().setItemCommon(ItemSetup.gemDiamond), AthsGlobal.DIAMOND);
		diamondCluster = crystalClusterRegistryHelper(diamond);
		emerald = crystalRegistryHelper(new BlockCrystal().setItemCommon(ItemSetup.gemEmerald), AthsGlobal.EMERALD);
		emeraldCluster = crystalClusterRegistryHelper(emerald);
		garnet = crystalRegistryHelper(new BlockCrystal().setItemCommon(ItemSetup.gemGarnet), AthsGlobal.GARNET);
		garnetCluster = crystalClusterRegistryHelper(garnet);
		ruby = crystalRegistryHelper(new BlockCrystal().setItemCommon(ItemSetup.gemRuby), AthsGlobal.RUBY);
		rubyCluster = crystalClusterRegistryHelper(ruby);
		sapphire = crystalRegistryHelper(new BlockCrystal().setItemCommon(ItemSetup.gemSapphire), AthsGlobal.SAPPHIRE);
		sapphireCluster = crystalClusterRegistryHelper(sapphire);
		topaz = crystalRegistryHelper(new BlockCrystal().setItemCommon(ItemSetup.gemTopaz), AthsGlobal.TOPAZ);
		topazCluster = crystalClusterRegistryHelper(topaz);
		tourmaline = crystalRegistryHelper(new BlockCrystal().setItemCommon(ItemSetup.gemTourmaline), AthsGlobal.TOURMALINE);
		tourmalineCluster = crystalClusterRegistryHelper(tourmaline);
		
		if (Loader.isModLoaded("teloaddon") && com.facetorched.teloaddon.util.Config.addFluorite) {
			fluorite = crystalRegistryHelper(new BlockCrystal().setItemCommon(TeloItemSetup.fluorite), AthsGlobal.FLUORITE);
			fluoriteCluster = crystalClusterRegistryHelper(fluorite);
		}
		
		//non "Gems"
		gypsum = crystalRegistryHelper(new BlockCrystal().setItem(ItemSetup.powder, 11), AthsGlobal.GYPSUM);
		gypsumCluster = crystalRegistryHelper(new BlockCrystalCluster().setItem(ItemSetup.oreChunk, Global.oreSize + 1), AthsGlobal.GYPSUM);
		rockCrystal = crystalRegistryHelper(new BlockCrystal().setItem(ItemSetup.looseRock, 15), AthsGlobal.ROCK_CRYSTAL);
		rockCrystalCluster = crystalRegistryHelper(new BlockCrystalCluster().setItem(ItemSetup.looseRock, 15), AthsGlobal.ROCK_CRYSTAL);
		
		// plants with various sizes
		sagebrush = plantRegistryHelper(new BlockPlant(Material.vine).setExtraNames(AthsGlobal.SAGEBRUSH).addVary(EnumVary.SNOW).setHasNoDrops().setScale(2.0F));
		prairieGrass = plantRegistryHelper(new BlockPlantStraw().setExtraNames(AthsGlobal.PRAIRIE_GRASS).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.AUTUMN}).setIsFoliageColor().setRenderID(plantCropRenderID).setScale(2.0F));
		leafyUndergrowth = plantRegistryHelper(new BlockPlant(Material.vine).setExtraNames(AthsGlobal.LEAFY_UNDERGROWTH).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW}).setHasNoDrops().setScale(2.0F));
		mediumUndergrowth = plantRegistryHelper(new BlockPlantStraw().setExtraNames(AthsGlobal.MEDIUM_UNDERGROWTH).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW}).setRenderID(plantCropRenderID).setScale(2.0F));
		pokeweed = plantRegistryHelper(new BlockPlant().setExtraNames(AthsGlobal.POKEWEED,"Large").addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW, EnumVary.FRUIT}).setMonthVaryRange(TFC_Time.AUGUST, TFC_Time.SEPTEMBER, EnumVary.FRUIT).setScale(3f));
		saxaul = plantRegistryHelper(new BlockPlant().setExtraNames(AthsGlobal.SAXAUL, "Small", "Large", "Huge").setScale(4.0f));
		saltwort = plantRegistryHelper(new BlockPlant(Material.vine).setExtraNames(AthsGlobal.SALTWORT, "Small").addVary(EnumVary.SNOW));
		
		// straw dropping plants
		duneGrass = plantRegistryHelper(new BlockPlantStraw().setName(AthsGlobal.DUNE_GRASS).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW}));
		fieldHorsetail = plantRegistryHelper(new BlockPlantStraw().setName(AthsGlobal.FIELD_HORSETAIL).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.EARLY_SPRING}).setMonthVary(TFC_Time.MARCH, EnumVary.EARLY_SPRING));
		roughHorsetail = plantRegistryHelper(new BlockPlantStraw().setName(AthsGlobal.ROUGH_HORSETAIL).addVary(EnumVary.SNOW));
		pondGrass = plantRegistryHelper(new BlockPlantStraw().setName(AthsGlobal.POND_GRASS).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW}).setIsFoliageColor().setScale(2.0F));
		commonReeds = plantRegistryHelper(new BlockPlantStraw().setName(AthsGlobal.COMMON_REEDS).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.AUGUST, TFC_Time.OCTOBER).setIsWaterPlant().setScale(2.0F));
		elephantGrass = plantRegistryHelper(new BlockPlantStraw().setName(AthsGlobal.ELEPHANT_GRASS).setIsFoliageColor().setRenderID(plantCropRenderID).setScale(3.0F));
		papyrus = plantRegistryHelper(new BlockPlantStraw().setName(AthsGlobal.PAPYRUS).setIsWaterPlant().setScale(4.0F));
		
		//misc plants
		clover = plantRegistryHelper(new BlockPlantLayerFlower().setName(AthsGlobal.CLOVER).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.APRIL, TFC_Time.JULY));
		leafyLowUndergrowth = plantRegistryHelper(new BlockPlantLow().setName(AthsGlobal.LEAFY_LOW_UNDERGROWTH).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW}).setHasNoDrops());
		aloeVera = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.ALOE_VERA).addVary(EnumVary.FLOWER).setFlowerMonth(TFC_Time.APRIL));
		creepingCharlie = plantRegistryHelper(new BlockPlantLayerFlower().setName(AthsGlobal.CREEPING_CHARLIE).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.APRIL, TFC_Time.JULY));
		indianPipe = plantRegistryHelper(new BlockPlant().setName(AthsGlobal.INDIAN_PIPE).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW}));
		sundew = plantRegistryHelper(new BlockPlant().setName(AthsGlobal.SUNDEW));
		venusFlytrap = plantRegistryHelper(new BlockPlant().setName(AthsGlobal.VENUS_FLYTRAP));
		waterPlantain = plantRegistryHelper(new BlockPlant().setName(AthsGlobal.WATER_PLANTAIN).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW}).setIsWaterPlant().setScale(2.0F));
		
		// mushrooms
		boletus = plantRegistryHelper(new BlockPlantFungus().setName(AthsGlobal.BOLETUS).setBrownMushroom(32f));
		chanterelle = plantRegistryHelper(new BlockPlantFungus().setName(AthsGlobal.CHANTERELLE).setBrownMushroom(32f));
		indigoMilkCap = plantRegistryHelper(new BlockPlantFungus().setName(AthsGlobal.INDIGO_MILK_CAP).setBrownMushroom(16f));
		morel = plantRegistryHelper(new BlockPlantFungus().setName(AthsGlobal.MOREL).setBrownMushroom(4f));
		bridalVeilStinkhorn = plantRegistryHelper(new BlockPlantFungus().setName(AthsGlobal.BRIDAL_VEIL_STINKHORN).setRedMushroom(4f));
		entoloma = plantRegistryHelper(new BlockPlantFungus().setName(AthsGlobal.ENTOLOMA).setRedMushroom(6f));
		shitake = plantRegistryHelper(new BlockPlantFungus().setName(AthsGlobal.SHITAKE).setBrownMushroom(10f));
		devilsFingers = plantRegistryHelper(new BlockPlantFungus().setName(AthsGlobal.DEVILS_FINGERS).setRedMushroom(4f));
		chlorophosFoxfire = plantRegistryHelper(new BlockPlantFungus().setName(AthsGlobal.CHLOROPHOS_FOXFIRE).setBrownMushroom(2f).setLightLevel(0.27f));
		
		// flowers
		africanMilkBarrel = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.AFRICAN_MILK_BARREL).addVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.JUNE, TFC_Time.AUGUST).setIsDamaging().setPoisonDuration(2).setScale(2.0f));
		africanMilkTree = plantRegistryHelper(new BlockPlant().setName(AthsGlobal.AFRICAN_MILK_TREE).addVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.MAY, TFC_Time.JULY).setIsDamaging().setPoisonDuration(2).setScale(2.0f));
		albanianSpurge = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.ALBANIAN_SPURGE).addVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.FEBRUARY, TFC_Time.APRIL).setRenderID(plantCropRenderID).setScale(2.0f));
		bloodLily = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.BLOOD_LILY).addVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.MAY, TFC_Time.JUNE));
		bluebell = plantRegistryHelper(new BlockPlantLowFlower().setName(AthsGlobal.BLUEBELL).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.APRIL, TFC_Time.JUNE));
		bonatea = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.BONATEA).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.APRIL, TFC_Time.JULY));
		caralluma = plantRegistryHelper(new BlockPlant().setName(AthsGlobal.CARALLUMA).addVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.MARCH, TFC_Time.APRIL).setScale(2.0f));
		desertRose = plantRegistryHelper(new BlockPlantFlower().setExtraNames(AthsGlobal.DESERT_ROSE, "Tiny", "Small", "Large").addVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.APRIL, TFC_Time.AUGUST).setIsWoody().setScale(6.0f));
		egyptianAutumnCrocus = plantRegistryHelper(new BlockPlantLayerFlower().setName(AthsGlobal.EGYPTIAN_AUTUMN_CROCUS).addVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.DECEMBER, TFC_Time.FEBRUARY));
		europeanBedstraw = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.EUROPEAN_BEDSTRAW).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.JULY, TFC_Time.SEPTEMBER).setRenderID(plantCropRenderID).setScale(2.0f));
		freesia = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.FREESIA).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.MARCH, TFC_Time.MAY));
		garlicMustard = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.GARLIC_MUSTARD).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.APRIL, TFC_Time.JUNE).setRenderID(plantCropRenderID).setScale(2.0f));
		gifboom = plantRegistryHelper(new BlockPlant().setExtraNames(AthsGlobal.GIFBOOM).addVarys(new EnumVary[] {EnumVary.FLOWER, EnumVary.FRUIT}).setFlowerMonthRange(TFC_Time.APRIL, TFC_Time.JUNE).setMonthVaryRange(TFC_Time.AUGUST, TFC_Time.OCTOBER, EnumVary.FRUIT).setIsDamaging().setPoisonDuration(2).setScale(6.0f));
		leopardOrchid = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.LEOPARD_ORCHID).addVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.MAY, TFC_Time.JULY).setScale(2.0f));
		looseFloweredOrchid = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.LOOSE_FLOWERED_ORCHID).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.APRIL, TFC_Time.JUNE));
		mallow = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.MALLOW).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.APRIL, TFC_Time.OCTOBER).setRenderID(plantCropRenderID).setScale(2.0f));
		narbonVetch = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.NARBON_VETCH).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.MAY, TFC_Time.JULY));
		periwinkle = plantRegistryHelper(new BlockPlantLowFlower().setName(AthsGlobal.PERIWINKLE).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.JUNE, TFC_Time.NOVEMBER));
		quaqua = plantRegistryHelper(new BlockPlant().setName(AthsGlobal.QUAQUA).addVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.SEPTEMBER, TFC_Time.DECEMBER).setIsDamaging());
		royalJasmine = plantRegistryHelper(new BlockPlantFlower().setExtraNames(AthsGlobal.ROYAL_JASMINE, "Large").addVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.JULY, TFC_Time.OCTOBER).setScale(2.0f));
		sesame = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.SESAME).addVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.JUNE, TFC_Time.AUGUST).setScale(2.0f));
		woodBitterVetch = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.WOOD_BITTER_VETCH).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.MAY, TFC_Time.JULY));
		birdOfParadise = plantRegistryHelper(new BlockPlant().setName(AthsGlobal.BIRD_OF_PARADISE).addVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.APRIL, TFC_Time.NOVEMBER).setScale(2.0f));
		devilsTongue = plantRegistryHelper(new BlockPlant().setName(AthsGlobal.DEVILS_TOUNGE).addVary(EnumVary.FLOWER).setFlowerMonth(TFC_Time.MAY).setScale(2.0F));
		lobsterClaws = plantRegistryHelper(new BlockPlant().setName(AthsGlobal.LOBSTER_CLAWS).addVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.APRIL, TFC_Time.AUGUST).setScale(2.0F));
		titanArum = plantRegistryHelper(new BlockPlant().setName(AthsGlobal.TITAN_ARUM).addVary(EnumVary.FLOWER).setFlowerMonth(TFC_Time.AUGUST).setScale(2.0F));
		voodooLily = plantRegistryHelper(new BlockPlant().setName(AthsGlobal.VOODOO_LILY).addVary(EnumVary.FLOWER).setFlowerMonth(TFC_Time.JUNE).setScale(2.0F));
		angelsTrumpet = plantRegistryHelper(new BlockPlantTree().setNames(AthsGlobal.ANGELS_TRUMPET, new String[] {"Orange","Pink","White"}).addVary(EnumVary.FLOWER).addIconVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.JUNE,TFC_Time.OCTOBER));
		angelWingCactus = plantRegistryHelper(new BlockPlantCactus().setName(AthsGlobal.ANGEL_WING_CACTUS).addVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.MAY, TFC_Time.JUNE).setScale(2f));
		blueCereusCactus = plantRegistryHelper(new BlockPlantCactus().setExtraNames(AthsGlobal.BLUE_CEREUS_CACTUS, "Large").addVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.MAY, TFC_Time.JUNE).setScale(3f).setTreeBounds());
		organPipeCactus = plantRegistryHelper(new BlockPlantCactus().setName(AthsGlobal.ORGAN_PIPE_CACTUS).addVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.MAY, TFC_Time.JUNE).setScale(6f).setTreeBounds());
		camasFlower = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.CAMAS_FLOWER).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.APRIL, TFC_Time.MAY));
		chives = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.CHIVES).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.MAY, TFC_Time.JULY));
		cupPlant = plantRegistryHelper(new BlockPlantFlower().setExtraNames(AthsGlobal.CUP_PLANT, "Large").addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.JUNE, TFC_Time.JULY).setRenderID(plantCropRenderID).setScale(3f));
		daffodil = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.DAFFODIL).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.FEBRUARY, TFC_Time.MARCH));
		fireweed = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.FIREWEED).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.JUNE, TFC_Time.SEPTEMBER).setScale(2f));
		giantHogweed = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.GIANT_HOGWEED).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.JUNE, TFC_Time.JULY).setPoisonDuration(5).setScale(3f));
		heather = plantRegistryHelper(new BlockPlantFlower().setNames(AthsGlobal.HEATHER, new String[] {"Pink","White"}).addVary(EnumVary.FLOWER).addVary(EnumVary.SNOW).addIconVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.JULY, TFC_Time.AUGUST).setGrassBounds());
		hibiscus = plantRegistryHelper(new BlockPlantFlower().setNames(AthsGlobal.HIBISCUS, new String[] {"Orange","Pink","Red","White"}).addVary(EnumVary.FLOWER).addIconVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.JULY, TFC_Time.AUGUST).setScale(2f));
		iris = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.IRIS).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.MAY, TFC_Time.JULY).setScale(2f));
		jackInThePulpit = plantRegistryHelper(new BlockPlant().setName(AthsGlobal.JACK_IN_THE_PULPIT).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.APRIL, TFC_Time.JUNE));
		lavender = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.LAVENDER).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.JUNE, TFC_Time.AUGUST).setScale(2f));
		lupine = plantRegistryHelper(new BlockPlantFlower().setNames(AthsGlobal.LUPINE, new String[] {"Blue","Purple","Red","Yellow"}).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).addIconVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.APRIL, TFC_Time.JULY));
		marigold = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.MARIGOLD).addVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.JUNE, TFC_Time.OCTOBER).setScale(1.0F));
		ocotillo = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.OCOTILLO).addVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.MARCH, TFC_Time.JUNE).setIsDamaging().setScale(3.0F));
		rosebush = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.ROSEBUSH).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.MAY, TFC_Time.SEPTEMBER).setIsDamaging().setScale(2f));
		sorbus = plantRegistryHelper(new BlockPlantTreeFlower().setName(AthsGlobal.SORBUS).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER, EnumVary.FRUIT}).setFlowerMonthRange(TFC_Time.JUNE, TFC_Time.JULY).setMonthVary(TFC_Time.SEPTEMBER, EnumVary.FRUIT));
		sunflower = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.SUNFLOWER).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.JUNE, TFC_Time.SEPTEMBER).setScale(3f));
		thistle = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.THISTLE).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.MAY, TFC_Time.AUGUST).setIsDamaging().setScale(1f));
		yarrow = plantRegistryHelper(new BlockPlantFlower().setName(AthsGlobal.YARROW).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setFlowerMonthRange(TFC_Time.JUNE, TFC_Time.SEPTEMBER).setScale(1f));
		saguaro = plantRegistryHelper(new BlockPlantCactus().setNames(AthsGlobal.SAGUARO, new String[] {"Fork", "Side", "Short", "Staggered", "Stumpy"}).addVary(EnumVary.FLOWER).setFlowerMonthRange(TFC_Time.MAY, TFC_Time.JUNE).setScale(6f).setTreeBounds());
		pricklyPear = plantRegistryHelper(new BlockPlantCactus().setName(AthsGlobal.PRICKLY_PEAR).addVarys(new EnumVary[] {EnumVary.FLOWER, EnumVary.FRUIT}).setFlowerMonth(TFC_Time.MAY).setMonthVaryRange(TFC_Time.SEPTEMBER, TFC_Time.OCTOBER, EnumVary.FRUIT).setScale(2f));
		holly = plantRegistryHelper(new BlockPlantTreeTrimmableFlower().setExtraNames(AthsGlobal.HOLLY, "Trimmed", "Short").addVarys(new EnumVary[] {EnumVary.FLOWER, EnumVary.FRUIT, EnumVary.SNOW}).setFlowerMonthRange(TFC_Time.MAY,TFC_Time.JUNE).setMonthVaryRange(TFC_Time.OCTOBER, TFC_Time.DECEMBER, EnumVary.FRUIT).setIsDamaging());

		// lily pad like plants
		lilyPad = plantRegistryHelper(new BlockPlantLilyPad().setExtraNames(AthsGlobal.LILY_PAD).addVary(EnumVary.SNOW));
		duckweed = plantRegistryHelper(new BlockPlantLilyPad().setName(AthsGlobal.DUCKWEED).addVary(EnumVary.SNOW));
		algaeMatGreen = plantRegistryHelper(new BlockPlantAlgae().setColorRange(.6667f, 80, .5f, 128, 0f, 0).setExtraNames(AthsGlobal.ALGAE_MAT_GREEN).addVary(EnumVary.SNOW));
		algaeMatRed = plantRegistryHelper(new BlockPlantAlgae().setColorRange(.5f, 60, .3f, 0, .1f, 0).setExtraNames(AthsGlobal.ALGAE_MAT_RED).addVary(EnumVary.SNOW));
		algaeMatCyanobacteria = plantRegistryHelper(new BlockPlantAlgae().setColorRange(0f, 0, .5f, 70, .1f, 40).setExtraNames(AthsGlobal.ALGAE_MAT_CYANOBACTERIA).addVary(EnumVary.SNOW));
		
		
		// deciduous trees
		youngAsh = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.ASH).setName(AthsGlobal.YOUNG_ASH).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW}));
		youngAspen = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.ASPEN).setName(AthsGlobal.YOUNG_ASPEN).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW}));
		youngBirch = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.BIRCH).setName(AthsGlobal.YOUNG_BIRCH).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW}));
		youngChestnut = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.CHESTNUT).setName(AthsGlobal.YOUNG_CHESTNUT).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW}));
		youngGinko = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.GINGKO).setName(AthsGlobal.YOUNG_GINGKO).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW}));
		youngHickory = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.HICKORY).setName(AthsGlobal.YOUNG_HICKORY).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW}));
		youngMaple = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.MAPLE).setName(AthsGlobal.YOUNG_MAPLE).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW}));
		youngOak = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.OAK).setName(AthsGlobal.YOUNG_OAK).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW}));
		youngSycamore = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.SYCAMORE).setName(AthsGlobal.YOUNG_SYCAMORE).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW}));
		youngWhiteElm = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.WHITEELM).setName(AthsGlobal.YOUNG_WHITE_ELM).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW}));
		youngWillow = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.WILLOW).setName(AthsGlobal.YOUNG_WILLOW).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.SNOW}));
		
		// trees snowy
		youngDoglasFir = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.DOUGLASFIR).setName(AthsGlobal.YOUNG_DOUGLAS_FIR).addVary(EnumVary.SNOW));
		youngPine = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.PINE).setName(AthsGlobal.YOUNG_PINE).addVary(EnumVary.SNOW));
		youngSequoia = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.REDWOOD).setName(AthsGlobal.YOUNG_SEQUOIA).addVary(EnumVary.SNOW));
		youngSpruce = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.SPRUCE).setName(AthsGlobal.YOUNG_SPRUCE).addVary(EnumVary.SNOW));
		youngYew = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.YEW).setName(AthsGlobal.YOUNG_YEW).addVary(EnumVary.SNOW));
		
		// trees
		youngAcacia = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.UTACACIA).setName(AthsGlobal.YOUNG_ACACIA));
		youngBaobab = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.BAOBAB).setName(AthsGlobal.YOUNG_BAOBAB));
		youngEbony = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.EBONY).setName(AthsGlobal.YOUNG_EBONY));
		youngFever = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.FEVERTREE).setName(AthsGlobal.YOUNG_FEVER));
		youngGhaf = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.GHAF).setName(AthsGlobal.YOUNG_GHAF));
		youngKapok = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.KAPOK).setName(AthsGlobal.YOUNG_KAPOK));
		youngLaurel = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.LAUREL).setName(AthsGlobal.YOUNG_LAUREL));
		youngLimba = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.LIMBA).setName(AthsGlobal.YOUNG_LIMBA));
		youngMahoe = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.MAHOE).setName(AthsGlobal.YOUNG_MAHOE));
		youngMahogany = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.MAHOGANY).setName(AthsGlobal.YOUNG_MAHOGANY));
		youngTeak = plantRegistryHelper(new BlockPlantTree().setSapling(EnumTree.TEAK).setName(AthsGlobal.YOUNG_TEAK));
		
		//trimmable
		youngWhiteCedar = plantRegistryHelper(new BlockPlantTreeTrimmable().setSapling(EnumTree.WHITECEDAR).setExtraNames(AthsGlobal.YOUNG_WHITE_CEDAR).addVary(EnumVary.SNOW));
		
		//3d
		lotus = plantRegistryHelper(new BlockPlantLilyPad3d().setOvercrowdRadius(1).setName(AthsGlobal.LOTUS).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW, EnumVary.FLOWER}).setNamedVaryPart(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW}, "Leaf").setVaryPart(new EnumVary[] {EnumVary.DEFAULT, EnumVary.FLOWER}, "Leaf").setPart("Root").setVaryParts(EnumVary.FLOWER, new String[] {"Petal", "Stamen", "Stamen_Top"}).setVaryPart(new EnumVary[] {EnumVary.DEFAULT, EnumVary.FLOWER}, "Stem").setVaryParts(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW}, "Lotus_Winter", new String[] {"Stem"}).setFlowerMonthRange(TFC_Time.JUNE, TFC_Time.OCTOBER).setScale(2.0f));
		rafflesia = plantRegistryHelper(new BlockPlant3d().setName(AthsGlobal.RAFFLESIA).addVary(EnumVary.FLOWER).setVaryParts(EnumVary.FLOWER, new String[] {"Petal", "Center"}).setPart("Base").setFlowerMonthRange(TFC_Time.APRIL, TFC_Time.MAY).setScale(2.0f));
		snakeSanseveria = plantRegistryHelper(new BlockPlant3d().setExtraNames(AthsGlobal.SNAKE_SANSEVERIA, "Variegated").setNamedParts(new String[] {"Leaf_Curly", "Leaf_Straight", "Leaf_Wavy"}).setOverrideModelName().setScale(2.0f));
		starfishPlant = plantRegistryHelper(new BlockPlant3d().setName(AthsGlobal.STARFISH_PLANT).addVary(EnumVary.FLOWER).setVaryPart(EnumVary.FLOWER, "Flower").setPart("Stem").setFlowerMonthRange(TFC_Time.JULY, TFC_Time.SEPTEMBER).setScale(2.0f));
		swordSanseveria = plantRegistryHelper(new BlockPlant3d().setName(AthsGlobal.SWORD_SANSEVERIA).setParts(new String[] {"Leaf_Long", "Leaf_Medium", "Leaf_Short", "Stem"}).setScale(2.0f));
		welwitschia = plantRegistryHelper(new BlockPlant3d().setNames(AthsGlobal.WELWITSCHIA, new String[] {"Male", "Female"}).addVarys(new EnumVary[] {EnumVary.FLOWER, EnumVary.FRUIT}).setPart(EnumVary.FLOWER, 0, "Flower").setPart(EnumVary.FRUIT, 1, "Cones").setParts(new String[] {"Leaf_Curled", "Leaf_Straight", "Leaf_Wavy", "Stem", "Stem_Center"}).setOverrideModelName().setFlowerMonthRange(TFC_Time.JULY, TFC_Time.SEPTEMBER).setMonthVaryRange(TFC_Time.APRIL, TFC_Time.JUNE, EnumVary.FRUIT).setBlacklistMeta(EnumVary.FRUIT, 0).setBlacklistMeta(EnumVary.FLOWER, 1).setScale(2.0f));
		cycad = plantRegistryHelper(new BlockPlantTree3d().setNames(AthsGlobal.CYCAD, new String[] {"Male", "Female"}).addVary(EnumVary.FLOWER).setParts(new String[] {"Frond", "Trunk", "Shag"}).setNamedBaseMetaPart(EnumVary.FLOWER, "Cone").setIsAxisAligned().setOverrideModelName().setFlowerMonthRange(TFC_Time.APRIL, TFC_Time.AUGUST).setScale(2.5f));
		
		maidenhairFern = plantRegistryHelper(new BlockPlant3d().setName(AthsGlobal.MAIDENHAIR_FERN).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.SNOW}).setNamedPart("Frond").setScale(1f));
		victoriaLilyPad = plantRegistryHelper(new BlockPlantLilyPad3d().setOvercrowdRadius(1).setName(AthsGlobal.VICTORIA_LILY_PAD).setParts(new String[] {"Base", "Rim_Gap", "Rim_Middle", "Roots"}).setScale(2.0f));
		yucca = plantRegistryHelper(new BlockPlant3d().setName(AthsGlobal.YUCCA).addVary(EnumVary.FLOWER).setVaryPart(EnumVary.FLOWER, "Flower").setPart("Leaf").setFlowerMonthRange(TFC_Time.APRIL, TFC_Time.JUNE).setScale(1.5f));
		burdock = plantRegistryHelper(new BlockPlant3dFlower().setExtraNames(AthsGlobal.BURDOCK, "Large").addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.FLOWER, EnumVary.SNOW}).setNamedVaryPart(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.FLOWER, EnumVary.SNOW}, "Stem").setVaryPart(new EnumVary[] {EnumVary.DEFAULT, EnumVary.AUTUMN, EnumVary.FLOWER}, "Leaf").setFlowerMonthRange(TFC_Time.JUNE, TFC_Time.OCTOBER).setScale(2f));
		waterHyacinth = plantRegistryHelper(new BlockPlantLilyPad3dFlower().setName(AthsGlobal.WATER_HYACINTH).addVary(EnumVary.FLOWER).setNamedVaryPart(EnumVary.FLOWER, "Top").setNamedVaryPart(EnumVary.DEFAULT, "Top").setPart("Base").setPart("Roots").setFlowerMonthRange(TFC_Time.AUGUST, TFC_Time.SEPTEMBER));
		sumac = plantRegistryHelper(new BlockPlantTree3dFlower().setExtraNames(AthsGlobal.SUMAC, "Large").addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.FLOWER}).setVaryPart(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.FLOWER}, "Flower").setVaryPart(new EnumVary[] {EnumVary.DEFAULT, EnumVary.FLOWER}, "Leaves").setNamedVaryPart(EnumVary.AUTUMN, "Leaves").setParts(new String[] {"Stem_Top", "Stem_Bottom", "Stem_Middle"}).setFlowerMonthRange(TFC_Time.JULY, TFC_Time.NOVEMBER).setScale(3f));
		hostaHalcyon = plantRegistryHelper(new BlockPlant3dFlower().setName(AthsGlobal.HOSTA_HALCYON).setOverrideModelName("Hosta").addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.FLOWER, EnumVary.SNOW}).setVaryPart(new EnumVary[] {EnumVary.DEFAULT, EnumVary.FLOWER}, "Leaf").setVaryPart(EnumVary.AUTUMN, "Autumn_Leaf").setVaryPart(EnumVary.AUTUMN, "Hosta", "Autumn_Stem").setVaryPart(new EnumVary[] {EnumVary.DEFAULT, EnumVary.FLOWER}, "Hosta", "Stem").setVaryPart(EnumVary.DEFAULT, "Hosta", "Scape").setNamedVaryPart(EnumVary.FLOWER, "Scape").setVaryPart(EnumVary.WINTER, "Hosta_Winter", "Scape").setVaryPart(EnumVary.SNOW, "Hosta_Snow", "Scape").setFlowerMonthRange(TFC_Time.JULY, TFC_Time.AUGUST).setScale(2f));
		hostaPatriot = plantRegistryHelper(new BlockPlant3dFlower().setName(AthsGlobal.HOSTA_PATRIOT).setOverrideModelName("Hosta").addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.FLOWER, EnumVary.SNOW}).setVaryPart(new EnumVary[] {EnumVary.DEFAULT, EnumVary.FLOWER}, "Leaf").setVaryPart(EnumVary.AUTUMN, "Autumn_Leaf").setVaryPart(EnumVary.AUTUMN, "Hosta", "Autumn_Stem").setVaryPart(new EnumVary[] {EnumVary.DEFAULT, EnumVary.FLOWER}, "Hosta", "Stem").setVaryPart(EnumVary.DEFAULT, "Hosta", "Scape").setNamedVaryPart(EnumVary.FLOWER, "Scape").setVaryPart(EnumVary.WINTER, "Hosta_Winter", "Scape").setVaryPart(EnumVary.SNOW, "Hosta_Snow", "Scape").setFlowerMonthRange(TFC_Time.MAY, TFC_Time.JUNE).setScale(2f));
		hostaVulcan = plantRegistryHelper(new BlockPlant3dFlower().setName(AthsGlobal.HOSTA_VULCAN).setOverrideModelName("Hosta").addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.FLOWER, EnumVary.SNOW}).setVaryPart(new EnumVary[] {EnumVary.DEFAULT, EnumVary.FLOWER}, "Leaf").setVaryPart(EnumVary.AUTUMN, "Autumn_Leaf").setVaryPart(EnumVary.AUTUMN, "Hosta", "Autumn_Stem").setVaryPart(new EnumVary[] {EnumVary.DEFAULT, EnumVary.FLOWER}, "Hosta", "Stem").setVaryPart(EnumVary.DEFAULT, "Hosta", "Scape").setNamedVaryPart(EnumVary.FLOWER, "Scape").setVaryPart(EnumVary.WINTER, "Hosta_Winter", "Scape").setVaryPart(EnumVary.SNOW, "Hosta_Snow", "Scape").setFlowerMonthRange(TFC_Time.AUGUST, TFC_Time.SEPTEMBER).setScale(2f));
		hostaSumAndSubstance = plantRegistryHelper(new BlockPlant3dFlower().setName(AthsGlobal.HOSTA_SUM_AND_SUBSTANCE).addVarys(new EnumVary[] {EnumVary.AUTUMN, EnumVary.WINTER, EnumVary.FLOWER, EnumVary.SNOW}).setVaryPart(new EnumVary[] {EnumVary.DEFAULT, EnumVary.FLOWER}, "Leaf").setNamedVaryPart(EnumVary.AUTUMN, "Leaf").setVaryPart(EnumVary.AUTUMN, "Hosta_Autumn", "Stem").setVaryPart(new EnumVary[] {EnumVary.DEFAULT, EnumVary.FLOWER}, "Hosta", "Stem").setVaryPart(EnumVary.DEFAULT, "Hosta", "Scape").setNamedVaryPart(EnumVary.FLOWER, "Scape").setVaryPart(EnumVary.WINTER, "Hosta_Winter", "Scape").setVaryPart(EnumVary.SNOW, "Hosta_Snow", "Scape").setFlowerMonthRange(TFC_Time.JULY, TFC_Time.AUGUST).setScale(2f));
		brackenFern = plantRegistryHelper(new BlockPlant3d().setName(AthsGlobal.BRACKEN_FERN).addVary(EnumVary.WINTER).setNamedPart("Stem").setNamedPart("Frond").setScale(2f));
		ladyFern = plantRegistryHelper(new BlockPlant3d().setName(AthsGlobal.LADY_FERN).addVary(EnumVary.WINTER).setNamedPart("Frond").setScale(2f));
		ostrichFern = plantRegistryHelper(new BlockPlant3d().setName(AthsGlobal.OSTRICH_FERN).addVary(EnumVary.WINTER).setVaryPart(EnumVary.DEFAULT,"Frond").setVaryPart(EnumVary.WINTER,"Winter_Frond").setScale(2f));
		paintedFern = plantRegistryHelper(new BlockPlant3d().setName(AthsGlobal.PAINTED_FERN).addVary(EnumVary.WINTER).setNamedPart("Frond").setScale(2f));
		scalyTreeFern = plantRegistryHelper(new BlockPlantTree3d().setName(AthsGlobal.SCALY_TREE_FERN).setPart("Frond").setPart("Trunk").setIsAxisAligned().setScale(2.5f));
		sensitiveFern = plantRegistryHelper(new BlockPlant3d().setName(AthsGlobal.SENSITIVE_FERN).addVary(EnumVary.WINTER).setVaryPart(EnumVary.DEFAULT,"Frond").setVaryPart(EnumVary.WINTER,"Winter_Frond").setScale(2f));
		swordFern = plantRegistryHelper(new BlockPlant3d().setName(AthsGlobal.SWORD_FERN).setPart("Frond").setScale(2.5f));
		woodFern = plantRegistryHelper(new BlockPlant3d().setName(AthsGlobal.WOOD_FERN).addVary(EnumVary.WINTER).setNamedPart("Frond").setScale(3f));
		devilsClub = plantRegistryHelper(new BlockPlant3d().setName(AthsGlobal.DEVILS_CLUB).addVarys(new EnumVary[] {EnumVary.WINTER, EnumVary.FRUIT}).setVaryPart(EnumVary.FRUIT,"Fruit").setVaryParts(new EnumVary[] {EnumVary.DEFAULT, EnumVary.FRUIT}, new String[] {"Leaf", "Stem"}).setNamedVaryPart(EnumVary.WINTER, "Stem").setMonthVaryRange(TFC_Time.JULY, TFC_Time.AUGUST, EnumVary.FRUIT).setIsDamaging());
		
		//epiphyte 3d
		arpophyllumGiganteum = plantRegistryHelper(new BlockPlantEpiphyte3dFlower().setName(AthsGlobal.ARPOPHYLLUM_GIGANTEUM).addVary(EnumVary.FLOWER).setPart("Leaf").setVaryPart(EnumVary.FLOWER, "Flower").setFlowerMonthRange(TFC_Time.FEBRUARY, TFC_Time.MAY).setScale(2f));
		blackSpleenwort = plantRegistryHelper(new BlockPlantEpiphyte3d().setName(AthsGlobal.BLACK_SPLEENWORT).setPart("Frond").setScale(2f));
		bostonFern = plantRegistryHelper(new BlockPlantEpiphyte3d().setName(AthsGlobal.BOSTON_FERN).setPart("Frond").setScale(2f));
		hedgehogLipOrchid  = plantRegistryHelper(new BlockPlantEpiphyte3dFlower().setName(AthsGlobal.HEDGEHOG_LIP_ORCHID).addVary(EnumVary.FLOWER).setPart("Leaf").setPart("Stem").setVaryPart(EnumVary.FLOWER, "Flower").setFlowerMonthRange(TFC_Time.MARCH, TFC_Time.JUNE));
		leopardOrchidEpiphyte = plantRegistryHelper(new BlockPlantEpiphyte3dFlower().setName(AthsGlobal.LEOPARD_ORCHID_EPIPHYTE).addVary(EnumVary.FLOWER).setPart(null).setFlowerMonthRange(TFC_Time.MAY, TFC_Time.JULY));
		mistletoe = plantRegistryHelper(new BlockPlantEpiphyte3dFlower().setNames(AthsGlobal.MISTLETOE, new String[] {"American", "Desert", "Dwarf", "European"}).addVary(EnumVary.FRUIT).setNamedPart(null).setOverrideModelName().setBlacklistMeta(6).setMonthVaryRange(TFC_Time.OCTOBER, TFC_Time.DECEMBER, EnumVary.FRUIT).setScale(2f));
		paleUmbrellaOrchid = plantRegistryHelper(new BlockPlantEpiphyte3dFlower().setName(AthsGlobal.PALE_UMBRELLA_ORCHID).addVary(EnumVary.FLOWER).setPart("Leaf").setPart("Stem").setVaryParts(EnumVary.FLOWER, new String[] {"Flower", "Crown"}).setFlowerMonthRange(TFC_Time.AUGUST, TFC_Time.OCTOBER).setScale(1f));
		purpureorachis = plantRegistryHelper(new BlockPlantEpiphyte3dFlower().setName(AthsGlobal.PURPUREORACHIS).addVary(EnumVary.FLOWER).setPart("Leaf").setPart("Stem").setVaryPart(EnumVary.FLOWER, "Flower").setFlowerMonthRange(TFC_Time.FEBRUARY, TFC_Time.APRIL).setScale(2f));
		squirrelsFootFern = plantRegistryHelper(new BlockPlantEpiphyte3d().setName(AthsGlobal.SQUIRRELS_FOOT_FERN).setPart("Frond").setScale(2f));
		staghornFern = plantRegistryHelper(new BlockPlantEpiphyte3d().setName(AthsGlobal.STAGHORN_FERN).setPart("Frond_Upper").setPart("Frond_Lower").setScale(2f));
		tillandsiaBromeliad = plantRegistryHelper(new BlockPlantEpiphyte3d().setName(AthsGlobal.TILLANDSIA_BROMELIAD).addVary(EnumVary.FLOWER).setPart("Leaf").setVaryPart(EnumVary.FLOWER, "Flower").setFlowerMonthRange(TFC_Time.JUNE, TFC_Time.AUGUST));
		
		//cringe polypore moment
		artistsConk = plantRegistryHelper(new BlockPlantEpiphyte3dFungus().setName(AthsGlobal.ARTISTS_CONK).setPart(null));
		beefsteakFungus = plantRegistryHelper(new BlockPlantEpiphyte3dFungus().setName(AthsGlobal.BEEFSTEAK_FUNGUS).setPart(null).setBrownMushroom(16f));
		bitterOyster = plantRegistryHelper(new BlockPlantEpiphyte3dFungus().setName(AthsGlobal.BITTER_OYSTER).setPart(null).setLightLevel(0.27f));
		birchPolypore = plantRegistryHelper(new BlockPlantEpiphyte3dFungus().setName(AthsGlobal.BIRCH_POLYPORE).setPart(null).setBrownMushroom(8f));
		chaga = plantRegistryHelper(new BlockPlantEpiphyte3dFungus().setName(AthsGlobal.CHAGA).setPart(null));
		dryadsSaddle = plantRegistryHelper(new BlockPlantEpiphyte3dFungus().setName(AthsGlobal.DRYADS_SADDLE).setPart(null).setBrownMushroom(120f));
		lionsMane = plantRegistryHelper(new BlockPlantEpiphyte3dFungus().setName(AthsGlobal.LIONS_MANE).setPart(null).setBrownMushroom(16f));
		reishi = plantRegistryHelper(new BlockPlantEpiphyte3dFungus().setName(AthsGlobal.REISHI).setPart(null));
		shaggyBracket = plantRegistryHelper(new BlockPlantEpiphyte3dFungus().setName(AthsGlobal.SHAGGY_BRACKET).setPart(null));
		sulphurShelf = plantRegistryHelper(new BlockPlantEpiphyte3dFungus().setName(AthsGlobal.SULPHUR_SHELF).setPart(null).setBrownMushroom(160f));
		turkeyTail = plantRegistryHelper(new BlockPlantEpiphyte3dFungus().setName(AthsGlobal.TURKEY_TAIL).setPart(null));

	}
	
	public static BlockPlant plantRegistryHelper(BlockPlant block) {
		GameRegistry.registerBlock(block, block.getItemBlock(), block.getPlantKey());
		if(block.isFlammable) {
			Blocks.fire.setFireInfo(block, 5, 5); // must happen after registry
		}
		return block;
	}
	public static BlockCrystal crystalRegistryHelper(BlockCrystal block, String name) {
		block.setName(name);
		GameRegistry.registerBlock(block, ItemCrystal.class, block.crystalName);
		return block;
	}
	
	public static BlockCrystal crystalClusterRegistryHelper(Block crust) {
		return crystalRegistryHelper(new BlockCrystalCluster().setItemRare(((BlockCrystal)crust).crystalItem), ((BlockCrystal)crust).crystalName);
	}
	
	public static void addFlowerPotPlants() {
		for(BlockPlant b : AthsParser.getAthsPlants()) {
			if(b.getRenderType() == plantCrossRenderID || b.getRenderType() == plantCropRenderID) {
				((BlockCustomFlowerPot)TFCBlocks.flowerPot).addValidBlock(b);
			}
		}
	}
}
