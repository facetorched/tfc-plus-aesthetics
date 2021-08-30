package com.facetorched.tfcaths;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class AthsItemSetup {
	
	public static void setup() {
	}
	
	public static Item registryHelper(Item item, String unlocName) {
		item.setUnlocalizedName(unlocName);
		GameRegistry.registerItem(item, unlocName);
		item.setTextureName(unlocName);
		return item;
	}
}
