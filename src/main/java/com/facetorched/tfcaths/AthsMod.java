package com.facetorched.tfcaths;

import com.facetorched.tfcaths.WorldGen.Generators.AthsWorldGenCrystals;
import com.facetorched.tfcaths.WorldGen.Generators.AthsWorldGenPlants;
import com.facetorched.tfcaths.handlers.AthsFoodCraftingHandler;
import com.facetorched.tfcaths.proxy.IProxy;
import com.facetorched.tfcaths.util.Config;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = AthsMod.MODID, version = AthsMod.VERSION, name = AthsMod.NAME, dependencies = "required-after:terrafirmacraftplus;after:teloaddon")
public class AthsMod
{
    public static final String MODID = "tfcaths";
    public static final String VERSION = "1.6.3"; // change in gradle.build as well
    public static final String NAME = "TFC+ Aesthetics";
    
    @SidedProxy(
    	      clientSide="com.facetorched.tfcaths.proxy.ClientProxy", 
    	      serverSide="com.facetorched.tfcaths.proxy.ServerProxy"
    	    )
    public static IProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) // register blocks, items etc
    {
    	proxy.preInit(event); // must happen before block setup
    	
    	AthsItemSetup.setup();
    	AthsBlockSetup.setup();
    	
    	Config.preInit(event.getModConfigurationDirectory());
    	Config.reload();
    	
    	GameRegistry.registerWorldGenerator(new AthsWorldGenPlants(), 200);
    	GameRegistry.registerWorldGenerator(new AthsWorldGenCrystals(), 201);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) //build data structures and register network handlers
    {
    	Config.reloadPlants();
    	Config.reloadCrystals();
    	
    	AthsRecipes.registerRecipes();
    	
    	AthsBlockSetup.addFlowerPotPlants();
    	
    	FMLCommonHandler.instance().bus().register(new AthsFoodCraftingHandler());
    	
    	proxy.init(event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	proxy.postInit(event);
    }
    
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs)
	{
		if (eventArgs.modID.equalsIgnoreCase(MODID));
			Config.reload();
	}
}
