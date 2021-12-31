package com.facetorched.tfcaths.items.itemblocks;

import com.dunk.tfc.Items.ItemBlocks.ItemTerraBlock;
import com.dunk.tfc.api.Enums.EnumWeight;
import com.facetorched.tfcaths.blocks.BlockCrystal;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

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
	/*
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer){
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);
		if (movingobjectposition == null){
			return par1ItemStack;
		}
		else{
			if (movingobjectposition.typeOfHit == MovingObjectType.BLOCK){
				int i = movingobjectposition.blockX;
				int j = movingobjectposition.blockY;
				int k = movingobjectposition.blockZ;

				if (!par2World.canMineBlock(par3EntityPlayer, i, j, k))
					return par1ItemStack;

				if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack))
					return par1ItemStack;

				if (this.field_150939_a.canBlockStay(par2World, i, j + 1, k)&& par2World.isAirBlock(i, j + 1, k))
				{
					par2World.setBlock(i, j + 1, k, this.field_150939_a, this.getDamage(par1ItemStack), 2);
					par2World.spawnParticle("splash", i, j + 2, k, 0.0D, 0.0D, 0.0D);
					par2World.playSoundEffect(i + 0.5F, j + 0.5F, k + 0.5F, "random.splash", 0.5F, this.field_150939_a.stepSound.getPitch() * 0.8F);
					if (!par3EntityPlayer.capabilities.isCreativeMode)
						--par1ItemStack.stackSize;
				}
			}
			return par1ItemStack;
		}
	}*/
}
