package com.facetorched.tfcaths.items.itemblocks;

import com.dunk.tfc.Core.ColorizerFoliageTFC;
import com.facetorched.tfcaths.blocks.BlockPlant;
import com.facetorched.tfcaths.enums.EnumVary;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

@Deprecated
public class ItemPlantFoliage extends ItemPlant{
	public ItemPlantFoliage(Block b) {
		super(b);
		// TODO Auto-generated constructor stub
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack is, int renderPass)
	{
		BlockPlant block = (BlockPlant)this.field_150939_a;
		int meta = is.getItemDamage();
		if(block.isVary(meta, EnumVary.SNOW) || block.isVary(meta, EnumVary.WINTER)){
			return super.getColorFromItemStack(is, meta);
		}
		return ColorizerFoliageTFC.getFoliageColorBasic();
	}
}