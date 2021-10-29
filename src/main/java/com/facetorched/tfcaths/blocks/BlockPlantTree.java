package com.facetorched.tfcaths.blocks;

import java.util.Random;

import com.dunk.tfc.BlockSetup;
import com.dunk.tfc.ItemSetup;
import com.dunk.tfc.api.Enums.EnumTree;
import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.AthsGlobal;
import com.facetorched.tfcaths.util.AthsParser;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockPlantTree extends BlockPlant{

	public ItemStack sapling;
	public BlockPlantTree() {
		super();
		float var4 = 0.125F;
		this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, 1.5F, 0.5F + var4);
		this.setHardness(1.0F);
		this.setStepSound(Block.soundTypeWood);
		this.setHarvestLevel("axe", 0);
		this.scale = AthsGlobal.TREE_SCALE;
		// TODO Auto-generated constructor stub
	}
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
    }
	
	public BlockPlantTree setSapling(ItemStack sapling) {
		this.sapling = sapling;
		return this;
	}
	
	public BlockPlantTree setSapling(EnumTree tree) {
		// This is some serious hard coding >:(
		int meta = tree.woodMeta;
		if (meta < 16) {
			this.sapling = new ItemStack(BlockSetup.sapling, 0, meta);
		}
		else if(meta < 32) {
			this.sapling = new ItemStack(BlockSetup.sapling2, 0, meta % 16);
		}
		else
			this.sapling = new ItemStack(BlockSetup.sapling3, 0, meta % 32);
		return this;
	}
	
	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
		if(AthsParser.isHolding(world, player, "itemShovel"))
			dropBlockAsItem(world, x, y, z, new ItemStack(this, 1, meta));
		else {
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
	
	@Override
	public int getRenderType() {
		return AthsBlockSetup.plantTreeRenderID;
	}
}
