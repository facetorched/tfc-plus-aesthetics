package com.facetorched.tfcaths.util;

import net.minecraft.util.ResourceLocation;

public class ObjPart {
	public ResourceLocation texture;
	public String key;
	
	public ObjPart(ResourceLocation texture, String key) {
		this.texture = texture;
		this.key = key;
	}
	
	public ResourceLocation getTexture() {
		return texture;
	}

	public void setTexture(ResourceLocation texture) {
		this.texture = texture;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
