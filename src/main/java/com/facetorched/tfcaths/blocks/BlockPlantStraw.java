package com.facetorched.tfcaths.blocks;

import java.util.ArrayList;
import java.util.Random;

import com.dunk.tfc.ItemSetup;
import com.facetorched.tfcaths.util.AthsParser;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockPlantStraw extends BlockPlant implements IShearable{
	public BlockPlantStraw() {
		super();
		setGrassBounds();
	}
	
	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
		if(AthsParser.isHolding(world, player, "itemShovel"))
			dropBlockAsItem(world, x, y, z, new ItemStack(this, 1, meta));
		else if(AthsParser.isHolding(world, player, "itemKnife")) {
			dropItemStacks(world, x, y, z, new ItemStack(ItemSetup.straw), 1, getBaseMeta(meta) + 2, new Random());
		}
	}
	@Override
	protected void checkAndDropBlock(World world, int x, int y, int z){
		if (!this.canBlockStay(world, x, y, z)){
			world.setBlock(x, y, z, Blocks.air, 0, 2);
		}
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
		return ret;
	}
}
