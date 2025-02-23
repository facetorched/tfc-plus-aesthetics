package com.facetorched.tfcaths.items.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPlantLilyPad extends ItemPlant{

	public ItemPlantLilyPad(Block b) {
		super(b);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player){
		this.tryToPlaceInWater(is, world, player);
		return super.onItemRightClick(is, world, player);
	}
	
}
