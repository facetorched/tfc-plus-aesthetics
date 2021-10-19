package com.facetorched.tfcaths.items.itemblocks;

import com.facetorched.tfcaths.blocks.BlockPlantAlgae;
import com.facetorched.tfcaths.util.AthsLogger;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemPlantAlgae extends ItemPlantLilyPad{

	public ItemPlantAlgae(Block b) {
		super(b);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack is, int par2)
	{
		try {
			BlockPlantAlgae algae = ((BlockPlantAlgae)this.field_150939_a);
			int rgb = 128;
			return Math.min((int)(rgb * algae.redMult) + algae.redShift, 255) << 16 | 
					Math.min((int)(rgb * algae.greenMult) + algae.greenShift, 255) << 8 | 
					Math.min((int)(rgb * algae.blueMult) + algae.blueShift, 255);
		}
		catch (ClassCastException e) {
			AthsLogger.error("Unable to get algae color");
		}
		return 0;
	}

}
