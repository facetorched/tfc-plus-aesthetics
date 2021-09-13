package com.facetorched.tfcaths.blocks;

import com.dunk.tfc.TerraFirmaCraft;

import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockPlantAlgae extends BlockPlantLilyPad{
	@Override
	public boolean shouldGenerateAt(World world, int x, int y, int z) {
		if(world.isSideSolid(x, y-3, z, ForgeDirection.UP)) // deeper water
			return true;
		return false;
	}
	@Override
	public int colorMultiplier(IBlockAccess bAccess, int x, int y, int z)
	{
		return TerraFirmaCraft.proxy.foliageColorMultiplier(bAccess, x, y, z);
	}
}
