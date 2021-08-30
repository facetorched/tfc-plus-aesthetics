package com.facetorched.tfcaths.proxy;

import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.render.blocks.RenderSimplePlant;

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
        
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        // DEBUG
        //System.out.println("on Client side");
    	RenderingRegistry.registerBlockHandler(AthsBlockSetup.simplePlantRenderID = RenderingRegistry.getNextAvailableRenderId(), new RenderSimplePlant());
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
