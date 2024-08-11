package com.facetorched.tfcaths.blocks;

import java.util.Random;

import com.dunk.tfc.BlockSetup;
import com.dunk.tfc.ItemSetup;
import com.dunk.tfc.api.Enums.EnumTree;
import com.facetorched.tfcaths.interfaces.ITree;
import com.facetorched.tfcaths.util.AthsParser;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockPlantTree3d extends BlockPlant3d implements ITree{
	public ItemStack sapling;
	public BlockPlantTree3d() {
		super();
		setTreeBounds();
		this.setHardness(1.0F);
		this.setStepSound(Block.soundTypeWood);
		this.setHarvestLevel("axe", 0);
		setHasCollision();
	}
	
	@Override
	public ItemStack getSapling() {
		return this.sapling;
	}
	
	public BlockPlantTree3d setSapling(ItemStack sapling) {
		this.sapling = sapling;
		return this;
	}
	
	public BlockPlantTree3d setSapling(EnumTree tree) {
		// This is some serious hard coding >:(
		int meta = tree.woodMeta;
		if (meta < 16) {
			setSapling(new ItemStack(BlockSetup.sapling, 0, meta));
		}
		else if(meta < 32) {
			setSapling(new ItemStack(BlockSetup.sapling2, 0, meta % 16));
		}
		else
			setSapling(new ItemStack(BlockSetup.sapling3, 0, meta % 32));
		return this;
	}
	
	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
		if(AthsParser.isHolding(world, player, "itemShovel"))
			super.harvestBlock(world, player, x, y, z, meta);
		else if (!world.isRemote) {
			Random random = new Random();
			if (this.sapling != null)
				dropItemStacks(world, x, y, z, this.sapling, 0, 3, random);
			if(dropItemStacks(world, x, y, z, new ItemStack(ItemSetup.pole), 0, 2, random))
				dropItemStacks(world, x, y, z, new ItemStack(ItemSetup.stick), 0, 3, random);
			else
				dropItemStacks(world, x, y, z, new ItemStack(ItemSetup.stick), 2, 5, random);
		}
	}
	
	@Override
	public boolean shouldGenerateAt(World world, int x, int y, int z) {
		for(int i = 1; i < (int)scale; i++) {
			if(!world.isAirBlock(x, y + i, z)) {
				return false;
			}
		}
		return true;
	}
}
