package com.facetorched.tfcaths.proxy;

import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.render.blocks.RenderPlantCrop;
import com.facetorched.tfcaths.render.blocks.RenderPlantCross;
import com.facetorched.tfcaths.render.blocks.RenderPlantLilyPad;
import com.facetorched.tfcaths.render.blocks.RenderPlantTree;
import com.facetorched.tfcaths.render.blocks.RenderPlantTreeTrimmable;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy implements IProxy {

	@Override
    public void preInit(FMLPreInitializationEvent event)
    {
        // DEBUG
        //System.out.println("on Client side");
		
		// this must happen before the blocks get initialized since they store their render IDs
		RenderingRegistry.registerBlockHandler(AthsBlockSetup.plantCrossRenderID = RenderingRegistry.getNextAvailableRenderId(), new RenderPlantCross());
    	RenderingRegistry.registerBlockHandler(AthsBlockSetup.plantCropRenderID = RenderingRegistry.getNextAvailableRenderId(), new RenderPlantCrop());
    	RenderingRegistry.registerBlockHandler(AthsBlockSetup.plantLilyPadRenderID = RenderingRegistry.getNextAvailableRenderId(), new RenderPlantLilyPad());
    	RenderingRegistry.registerBlockHandler(AthsBlockSetup.plantTreeRenderID = RenderingRegistry.getNextAvailableRenderId(), new RenderPlantTree());
    	RenderingRegistry.registerBlockHandler(AthsBlockSetup.plantTreeTrimmableRenderID = RenderingRegistry.getNextAvailableRenderId(), new RenderPlantTreeTrimmable());
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        // DEBUG
        //System.out.println("on Client side");
        // register key bindings
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
    	// needs to register after items have been registered

        // DEBUG
        //System.out.println("on Client side");
    }
}
