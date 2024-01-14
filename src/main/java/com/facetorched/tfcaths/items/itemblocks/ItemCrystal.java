package com.facetorched.tfcaths.items.itemblocks;

import java.util.List;

import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Items.ItemBlocks.ItemTerraBlock;
import com.dunk.tfc.api.Enums.EnumWeight;
import com.facetorched.tfcaths.blocks.BlockCrystal;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {
		super.addInformation(stack, player, list, advanced);
		BlockCrystal b = (BlockCrystal)this.field_150939_a;
		String sciName = "gui." + b.crystalName + ".sciname";
        list.add(TFC_Core.translate(sciName));
    }
}
