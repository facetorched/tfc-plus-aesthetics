package com.facetorched.tfcaths.blocks;

import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockPlantTree extends BlockPlant{

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
	@Override
	public BlockPlant setNames(String name) {
		this.plantNames = new String[] {name, name + "_Autumn", name + "_Winter"};
		this.plantKey = name;
		return this;
	}
}
