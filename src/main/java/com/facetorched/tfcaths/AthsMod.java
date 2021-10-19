package com.facetorched.tfcaths;

import com.facetorched.tfcaths.WorldGen.Generators.AthsWorldGenPlants;
import com.facetorched.tfcaths.proxy.IProxy;
import com.facetorched.tfcaths.util.Config;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = AthsMod.MODID, version = AthsMod.VERSION, name = AthsMod.NAME, dependencies = "required-after:terrafirmacraftplus;")
public class AthsMod
{
    public static final String MODID = "tfcaths";
    public static final String VERSION = "1.0.0";
    public static final String NAME = "TFC+ Aesthetics";
    
    @SidedProxy(
    	      clientSide="com.facetorched.tfcaths.proxy.ClientProxy", 
    	      serverSide="com.facetorched.tfcaths.proxy.ServerProxy"
    	    )
    public static IProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) // register blocks, items etc
    {
    	AthsGlobal.setConstants();
    	
    	AthsItemSetup.setup();
    	AthsBlockSetup.setup();
    	
    	Config.preInit(event.getModConfigurationDirectory());
    	Config.reload();
    	
    	GameRegistry.registerWorldGenerator(new AthsWorldGenPlants(), 100);
    	proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) //build data structures and register network handlers
    {
    	Config.reloadPlants();
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
