package com.facetorched.tfcaths.blocks;

import com.dunk.tfc.TerraFirmaCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.IBlockAccess;

public class BlockPlantFoliage extends BlockPlant{
	@SideOnly(Side.CLIENT)
	@Override
	public int colorMultiplier(IBlockAccess bAccess, int x, int y, int z) {
		return TerraFirmaCraft.proxy.grassColorMultiplier(bAccess, x, y, z);
	}
}
