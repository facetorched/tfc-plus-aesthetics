package com.facetorched.tfcaths.items.itemblocks;

import com.dunk.tfc.Items.ItemBlocks.ItemTerraBlock;
import com.dunk.tfc.api.Enums.EnumWeight;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemCrystal extends ItemTerraBlock{

	public ItemCrystal(Block block) {
		super(block);
	}
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1)
	{
		return this.field_150939_a.getIcon(0, par1); //call the block's method
	}

	@Override
	public EnumWeight getWeight(ItemStack is)
	{
		return EnumWeight.LIGHT;
	}
}
