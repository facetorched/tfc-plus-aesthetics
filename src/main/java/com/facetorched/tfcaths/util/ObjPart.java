package com.facetorched.tfcaths.util;

import net.minecraft.util.IIcon;

public class ObjPart {
	public String texturePath;
	public String key;
	public IIcon icon;
	
	public IIcon getIcon() {
		return icon;
	}

	public void setIcon(IIcon icon) {
		this.icon = icon;
	}

	public ObjPart(String texturePath, String key) {
		this.texturePath = texturePath;
		this.key = key;
	}
	
	public String getTexture() {
		return this.texturePath;
	}

	public void setTexture(String texturePath) {
		this.texturePath = texturePath;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
