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
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

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
		if (!b.hasMeta(meta) || meta <= b.plantNames.length)
			return;
		String sciName = "gui." + b.plantKey + "." + b.plantNames[meta] + ".sciname";
        list.add(TFC_Core.translate(sciName));
    }
	
	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player){
		BlockPlant b = (BlockPlant)this.field_150939_a;
		if (b.isWaterPlant) {
			this.tryToPlaceInWater(is, world, player);
		}
		return super.onItemRightClick(is, world, player);
		
	}
	
	public ItemStack tryToPlaceInWater(ItemStack is, World world, EntityPlayer player) {
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);
		if (movingobjectposition != null && movingobjectposition.typeOfHit == MovingObjectType.BLOCK){
			int i = movingobjectposition.blockX;
			int j = movingobjectposition.blockY;
			int k = movingobjectposition.blockZ;
			if (!world.canMineBlock(player, i, j, k) ||
				!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, is) ||
				!world.getBlock(i, j, k).getMaterial().isLiquid()) {
				return is;
			}
			if (this.field_150939_a.canBlockStay(world, i, j + 1, k) && world.isAirBlock(i, j + 1, k)){
				world.setBlock(i, j + 1, k, this.field_150939_a, this.getDamage(is), 2);
				world.spawnParticle("splash", i, j + 2, k, 0.0D, 0.0D, 0.0D);
				world.playSoundEffect(i + 0.5F, j + 0.5F, k + 0.5F, "random.splash", 0.5F, this.field_150939_a.stepSound.getPitch() * 0.8F);
				if (!player.capabilities.isCreativeMode)
					--is.stackSize;
			}
		}
		return is;
	}
}
