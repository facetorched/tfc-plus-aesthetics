package com.facetorched.tfcaths.util;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class AthsParser {
	public static Block getBlockFromName(String blockName) {
		Block ret = Block.getBlockFromName(blockName);
		
		if (ret == null){
			AthsLogger.error("config file contains invalid block name " + blockName);
			throw new java.lang.NullPointerException("invalid block name " + blockName);
		}
		return ret;
	}
	public static boolean isHolding(World world, EntityPlayer player, String oreDict) {
		if (!world.isRemote){
			ItemStack equip = player.getCurrentEquippedItem();
			if (equip != null){
				int[] equipIDs = OreDictionary.getOreIDs(equip);
				for (int id : equipIDs){
					String name = OreDictionary.getOreName(id);
					if (name.startsWith(oreDict)){
						return true;
					}
				}
			}
		}
		return false;
	}
}
