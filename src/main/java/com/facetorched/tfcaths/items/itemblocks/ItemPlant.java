package com.facetorched.tfcaths.items.itemblocks;

import java.util.List;

import com.dunk.tfc.Core.ColorizerFoliageTFC;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Items.ItemBlocks.ItemTerraBlock;
import com.dunk.tfc.api.Enums.EnumWeight;
import com.facetorched.tfcaths.blocks.BlockPlant;
import com.facetorched.tfcaths.enums.EnumVary;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {
		super.addInformation(stack, player, list, advanced);
		int meta = stack.getItemDamage();
		BlockPlant b = (BlockPlant)this.field_150939_a;
		String sciName = "gui." + b.plantKey + "." + b.plantNames[meta] + ".sciname";
        list.add(TFC_Core.translate(sciName));
    }
}
