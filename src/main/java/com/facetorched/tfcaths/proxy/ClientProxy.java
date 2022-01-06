package com.facetorched.tfcaths.proxy;

import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.AthsMod;
import com.facetorched.tfcaths.render.blocks.RenderDirectionalCross;
import com.facetorched.tfcaths.render.blocks.RenderDirectionalLayer;
import com.facetorched.tfcaths.render.blocks.RenderPlant3d;
import com.facetorched.tfcaths.render.blocks.RenderPlantCrop;
import com.facetorched.tfcaths.render.blocks.RenderPlantCross;
import com.facetorched.tfcaths.render.blocks.RenderPlantLayer;
import com.facetorched.tfcaths.render.blocks.RenderPlantLilyPad;
import com.facetorched.tfcaths.render.blocks.RenderPlantLow;
import com.facetorched.tfcaths.render.blocks.RenderPlantTree;
import com.facetorched.tfcaths.render.blocks.RenderPlantTreeTrimmable;
import com.facetorched.tfcaths.render.blocks.RenderPlantWater;
import com.facetorched.tfcaths.render.tileentities.TESRPlant3d;
import com.facetorched.tfcaths.tileentities.TEPlant3d;

import cpw.mods.fml.client.registry.ClientRegistry;
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
    	RenderingRegistry.registerBlockHandler(AthsBlockSetup.plant3dRenderID = RenderingRegistry.getNextAvailableRenderId(), new RenderPlant3d());
    	RenderingRegistry.registerBlockHandler(AthsBlockSetup.plantLowRenderID = RenderingRegistry.getNextAvailableRenderId(), new RenderPlantLow());
    	RenderingRegistry.registerBlockHandler(AthsBlockSetup.plantLayerRenderID = RenderingRegistry.getNextAvailableRenderId(), new RenderPlantLayer());
    	RenderingRegistry.registerBlockHandler(AthsBlockSetup.directionalLayerRenderID = RenderingRegistry.getNextAvailableRenderId(), new RenderDirectionalLayer());
    	RenderingRegistry.registerBlockHandler(AthsBlockSetup.directionalCrossRenderID = RenderingRegistry.getNextAvailableRenderId(), new RenderDirectionalCross());
    	RenderingRegistry.registerBlockHandler(AthsBlockSetup.plantWaterRenderID = RenderingRegistry.getNextAvailableRenderId(), new RenderPlantWater());
    	ClientRegistry.registerTileEntity(TEPlant3d.class, AthsMod.MODID + ":TEPlant3d", new TESRPlant3d());
    	//ClientRegistry.bindTileEntitySpecialRenderer(TEPlant3d.class, new TESRPlant3d());
    	
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
