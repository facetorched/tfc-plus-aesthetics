package com.facetorched.tfcaths.util;

import com.dunk.tfc.WorldGen.DataLayer;
import com.dunk.tfc.WorldGen.TFCBiome;
import com.facetorched.tfcaths.AthsGlobal;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
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
	
	public static void damageItem(EntityPlayer player, ItemStack is) {
		is.damageItem(1, player);
		if (is.stackSize == 0)
			player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
	}
	
	public static String[] getBiomes()
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
	
	public static DataLayer getRockFromName(String name) {
		for(int i = 0; i < AthsGlobal.ROCKS.length; i++) {
			if(AthsGlobal.ROCKS[i].getName().equals(name)) {
				return AthsGlobal.ROCKS[i];
			}
		}
		return null;
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
	public static String[] append(String[][] srcs) {
		String[] dest = srcs[1];
		for(int i = 1; i < srcs.length; i++) {
			dest = append(dest, srcs[i]);
		}
		return dest;
	}
	
	public static int[] add(int[] src, int value) {
		if(src == null) {
			return new int[] {value};
		}
		int[] dest = new int[src.length + 1];
		for(int i = 0; i < src.length; i++) {
			dest[i] = src[i];
		}
		dest[src.length] = value;
		return dest;
	}
	
	public static String[] prefix(String[] src, String str) {
		String[] dest = new String[src.length];
		for(int i = 0; i < src.length; i++) {
			dest[i] = str + src[i];
		}
		return dest;
	}
	
	public static String[] suffix(String[] src, String str) {
		String[] dest = new String[src.length];
		for(int i = 0; i < src.length; i++) {
			dest[i] = src[i] + str;
		}
		return dest;
	}
	
	public static boolean isNegativeDirection(ForgeDirection d) {
		return d.equals(ForgeDirection.DOWN) || d.equals(ForgeDirection.NORTH) || d.equals(ForgeDirection.WEST);
	}
	
	public static boolean contains(String[] collection, String key) {
		for(String str : collection) {
			if(str.equals(key))
				return true;
		}
		return false;
	}
	
	/*
	 * -1 is wild card
	 */
	public static boolean contains(int[] collection, int key) {
		for(int i : collection) {
			if(i == key || i == -1)
				return true;
		}
		return false;
	}
	
	public static boolean contains(Block[] collection, Block key) {
		for(Block block : collection) {
			if(block.equals(key))
				return true;
		}
		return false;
	}
	
	public static int find(String[] collection, String key) {
		for(int i = 0; i < collection.length; i++) {
			if(collection[i].equals(key))
				return i;
		}
		return -1;
	}
}
