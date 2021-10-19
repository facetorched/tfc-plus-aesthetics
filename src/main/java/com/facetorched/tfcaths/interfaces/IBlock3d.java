package com.facetorched.tfcaths.interfaces;

import net.minecraftforge.client.model.obj.WavefrontObject;

public interface IBlock3d {
	public WavefrontObject getObj(int meta);
	
	public String[] getParts(int meta);
}
