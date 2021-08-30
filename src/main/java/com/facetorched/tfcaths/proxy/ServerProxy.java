package com.facetorched.tfcaths.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
public class ServerProxy implements IProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {

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
