package com.facetorched.tfcaths.proxy;

import com.facetorched.tfcaths.AthsMod;
import com.facetorched.tfcaths.tileentities.TEPlant3d;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
public class ServerProxy implements IProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		GameRegistry.registerTileEntity(TEPlant3d.class, AthsMod.MODID + ":TEPlant3d");
	}

	@Override
	public void init(FMLInitializationEvent event) {
		//This probably will stay empty

	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		//register server commands?
	}
}
