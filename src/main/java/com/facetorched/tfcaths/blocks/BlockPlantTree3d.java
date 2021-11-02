package com.facetorched.tfcaths.blocks;

import java.util.Random;

import com.dunk.tfc.ItemSetup;
import com.facetorched.tfcaths.util.AthsParser;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockPlantTree3d extends BlockPlant3d{
	public BlockPlantTree3d() {
		super();
		float var4 = 0.125F;
		this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, 1.5F, 0.5F + var4);
		this.setHardness(1.0F);
		this.setStepSound(Block.soundTypeWood);
		this.setHarvestLevel("axe", 0);
	}
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
    }
	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
		if(AthsParser.isHolding(world, player, "itemShovel"))
			dropBlockAsItem(world, x, y, z, new ItemStack(this, 1, meta));
		else {
			Random random = new Random();
			if(dropItemStacks(world, x, y, z, new ItemStack(ItemSetup.pole), 0, 2, random))
				dropItemStacks(world, x, y, z, new ItemStack(ItemSetup.stick), 0, 3, random);
			else
				dropItemStacks(world, x, y, z, new ItemStack(ItemSetup.stick), 2, 5, random);
		}
	}
	@Override
	protected void checkAndDropBlock(World world, int x, int y, int z){
		if (!this.canBlockStay(world, x, y, z)){
			world.setBlock(x, y, z, getBlockById(0), 0, 2);
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
