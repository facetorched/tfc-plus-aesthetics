package com.facetorched.tfcaths.blocks;

import java.util.Random;

import com.facetorched.tfcaths.util.AthsParser;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class BlockPlantTree extends BlockPlant{

	public ItemStack[] drops = new ItemStack[0];
	public BlockPlantTree() {
		super();
		float var4 = 0.125F;
		this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, 1.5F, 0.5F + var4);
		// TODO Auto-generated constructor stub
	}
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
    }
	
	public BlockPlantTree setDroppedItemStack(ItemStack[] drops) {
		this.drops = drops;
		return this;
	}
	
	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
		if(AthsParser.isHolding(world, player, "itemShovel"))
			dropBlockAsItem(world, x, y, z, new ItemStack(this, 1, meta));
		else {
			Random random = new Random();
			for(ItemStack drop : this.drops) {
				dropBlockAsItem(world, x, y, z, drop);
				if(random.nextBoolean()) {
					dropBlockAsItem(world, x, y, z, drop);
				}
			}
		}
	}
}
