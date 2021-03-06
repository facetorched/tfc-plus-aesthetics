package com.facetorched.tfcaths.proxy;

import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.render.blocks.RenderDirectionalCross;
import com.facetorched.tfcaths.render.blocks.RenderDirectionalLayer;
import com.facetorched.tfcaths.render.blocks.RenderPlant3d;
import com.facetorched.tfcaths.render.blocks.RenderPlantCrop;
import com.facetorched.tfcaths.render.blocks.RenderPlantCross;
import com.facetorched.tfcaths.render.blocks.RenderPlantEpiphyte3d;
import com.facetorched.tfcaths.render.blocks.RenderPlantLayer;
import com.facetorched.tfcaths.render.blocks.RenderPlantLilyPad;
import com.facetorched.tfcaths.render.blocks.RenderPlantLow;
import com.facetorched.tfcaths.render.blocks.RenderPlantTree;
import com.facetorched.tfcaths.render.blocks.RenderPlantTreeTrimmable;
import com.facetorched.tfcaths.render.blocks.RenderPlantWater;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
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
    	RenderingRegistry.registerBlockHandler(AthsBlockSetup.plant3dRenderID = RenderingRegistry.getNextAvailableRenderId(), new RenderPlant3d());
    	RenderingRegistry.registerBlockHandler(AthsBlockSetup.plantEpiphyte3dRenderID = RenderingRegistry.getNextAvailableRenderId(), new RenderPlantEpiphyte3d());
    	RenderingRegistry.registerBlockHandler(AthsBlockSetup.plantLowRenderID = RenderingRegistry.getNextAvailableRenderId(), new RenderPlantLow());
    	RenderingRegistry.registerBlockHandler(AthsBlockSetup.plantLayerRenderID = RenderingRegistry.getNextAvailableRenderId(), new RenderPlantLayer());
    	RenderingRegistry.registerBlockHandler(AthsBlockSetup.directionalLayerRenderID = RenderingRegistry.getNextAvailableRenderId(), new RenderDirectionalLayer());
    	RenderingRegistry.registerBlockHandler(AthsBlockSetup.directionalCrossRenderID = RenderingRegistry.getNextAvailableRenderId(), new RenderDirectionalCross());
    	RenderingRegistry.registerBlockHandler(AthsBlockSetup.plantWaterRenderID = RenderingRegistry.getNextAvailableRenderId(), new RenderPlantWater());
    	//ClientRegistry.bindTileEntitySpecialRenderer(TEPlant3d.class, new TESRPlant3d());
    	
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        // DEBUG
        //System.out.println("on Client side");
        // register key bindings
    	registerWailaClasses();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
    	// needs to register after items have been registered

        // DEBUG
        //System.out.println("on Client side");
    }
    
    public void registerWailaClasses(){
		FMLInterModComms.sendMessage("Waila", "register", "com.facetorched.tfcaths.waila.BlockData.callbackRegister"); // Blocks
	}
}
