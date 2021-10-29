package com.facetorched.tfcaths.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockPlantLilyPad3d extends BlockPlant3d{
	// player can stand on this block!
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z){
        return AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
    }
	
	@Override
	public boolean shouldGenerateAt(World world, int x, int y, int z) {
		
		if(world.isSideSolid(x, y-2, z, ForgeDirection.UP) || world.isSideSolid(x, y-3, z, ForgeDirection.UP)) {
			for (int i = x-1; i <= x+1; i++) {
				for (int j = y-1; j <= y+1; j++) {
					if (world.getBlock(i, y, j) != Blocks.air)
						return false;
				}
			}
			return true;
		}
		return false;
	}
}
