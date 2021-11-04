package com.facetorched.tfcaths.items.itemblocks;

import com.dunk.tfc.Core.ColorizerFoliageTFC;
import com.dunk.tfc.Items.ItemBlocks.ItemTerraBlock;
import com.dunk.tfc.api.Enums.EnumWeight;
import com.facetorched.tfcaths.blocks.BlockPlant;
import com.facetorched.tfcaths.enums.EnumVary;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemPlant extends ItemTerraBlock{
	public ItemPlant(Block b)
	{
		super(b);
		metaNames = ((BlockPlant)b).plantNames;
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
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack is, int renderPass)
	{
		BlockPlant block = (BlockPlant)this.field_150939_a;
		if(block.isFoliageColor) {
			int meta = is.getItemDamage();
			if(block.isVary(meta, EnumVary.SNOW) || block.isVary(meta, EnumVary.WINTER)){
				return super.getColorFromItemStack(is, meta);
			}
			return ColorizerFoliageTFC.getFoliageColorBasic();
		}
		return super.getColorFromItemStack(is, renderPass);
	}
}
