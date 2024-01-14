package com.facetorched.tfcaths.blocks;

import com.facetorched.tfcaths.interfaces.ILilyPad;
import com.facetorched.tfcaths.items.itemblocks.ItemPlantLilyPad;

import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockPlantLilyPad3d extends BlockPlant3d implements ILilyPad{
	
	public int overcrowdRadius = 0;
	
	public BlockPlantLilyPad3d() {
		super();
		setLayerBounds(.1f);
		setItemBlock(ItemPlantLilyPad.class);
	}
	
	@Override
	public boolean shouldGenerateAt(World world, int x, int y, int z) {
		
		if(world.isSideSolid(x, y-2, z, ForgeDirection.UP) || world.isSideSolid(x, y-3, z, ForgeDirection.UP)) {
			for (int i = x-overcrowdRadius; i <= x+overcrowdRadius; i++) {
				for (int j = z-overcrowdRadius; j <= z+overcrowdRadius; j++) {
					if (world.getBlock(i, y, j) != Blocks.air)
						return false;
				}
			}
			return true;
		}
		return false;
	}
	public BlockPlantLilyPad3d setOvercrowdRadius(int radius) {
		this.overcrowdRadius = radius;
		return this;
	}
}
