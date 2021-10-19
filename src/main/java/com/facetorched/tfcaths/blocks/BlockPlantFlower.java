package com.facetorched.tfcaths.blocks;

import java.util.Random;

import com.dunk.tfc.Core.TFC_Time;

import net.minecraft.world.World;

public class BlockPlantFlower extends BlockPlant{
	public int[] monthMetas;
	
	@Override
	public BlockPlant setNames(String name) {
		this.plantNames = new String[] {name + "_No_Flower", name};
		this.plantKey = name;
		this.setBlockName(name);
		return this;
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		if(this.monthMetas != null) {
			int month = TFC_Time.getSeasonAdjustedMonth(z);
			int meta = monthMetas[month];
			// negative values in monthMetas means ignore!
			if(meta >= 0 && world.getBlockMetadata(x, y, z) != meta) 
				world.setBlockMetadataWithNotify(x, y, z, monthMetas[month], 2);
		}
		this.checkAndDropBlock(world, x, y, z);
	}
	
	public int[] getMonthMetas() {
		return monthMetas;
	}
	public BlockPlant setMonthMetas(int[] monthMetas) {
		this.monthMetas = monthMetas;
		return this;
	}
}
