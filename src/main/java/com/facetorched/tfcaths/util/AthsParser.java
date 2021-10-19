package com.facetorched.tfcaths.util;

import com.dunk.tfc.WorldGen.TFCBiome;

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
	
	public static String[] getBiomeStringList()
	{
		TFCBiome[] biomeList = TFCBiome.getBiomeGenArray();
		if(biomeList.length == 0)
			throw new IllegalStateException();
		int biome_count = 0;
		for (TFCBiome biome : biomeList){
			if (biome != null){
				biome_count ++;
			}
		}
		String[] biomeNames = new String[biome_count];
		int index = 0;
		for (TFCBiome biome : biomeList){
			if (biome != null){
				biomeNames[index] = biome.biomeName;
				index++;
			}
		}
		return biomeNames;
	}
	
	public static String[] add(String[] src, String str) {
		String[] dest = new String[src.length + 1];
		for(int i = 0; i < src.length; i++) {
			dest[i] = src[i];
		}
		dest[src.length] = str;
		return dest;
	}
	
	public static String[] append(String[] src1, String[] src2) {
		String[] dest = new String[src1.length + src2.length];
		for(int i = 0; i < src1.length; i++) {
			dest[i] = src1[i];
		}
		for(int i = 0; i < src2.length; i++) {
			dest[i + src1.length] = src2[i];
		}
		return dest;
	}
	
	public static String[] prefix(String[] src, String str) {
		String[] dest = new String[src.length];
		for(int i = 0; i < src.length; i++) {
			dest[i] = str + src[i];
		}
		return dest;
	}
}
