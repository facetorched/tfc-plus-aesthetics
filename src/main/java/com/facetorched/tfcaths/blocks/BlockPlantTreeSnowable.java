package com.facetorched.tfcaths.blocks;

import java.util.Random;

import com.dunk.tfc.Core.TFC_Climate;
import com.dunk.tfc.Core.TFC_Time;

import net.minecraft.world.World;

public class BlockPlantTreeSnowable extends BlockPlantTree{
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand){
		int meta = world.getBlockMetadata(x, y, z);
		int month = TFC_Time.getSeasonAdjustedMonth(z);
		float temp = TFC_Climate.getHeightAdjustedBioTemp(world, TFC_Time.getTotalDays(), x, y, z);
		this.checkSnow(world, x, y, z, meta, month, temp, 0, 1);
	}
	
	@Override
	public BlockPlant setNames(String name) {
		this.plantNames = new String[] {name, name + "_Snow"};
		this.plantKey = name;
		this.setBlockName(name);
		return this;
	}
	
	public boolean checkSnow(World world, int x, int y, int z, int meta, int month, float temp, int bareMeta, int snowMeta) {
		if(meta == bareMeta && world.isRaining() && TFC_Climate.getHeightAdjustedBioTemp(world, TFC_Time.getTotalDays(), x, y, z) < 0f) {
			world.setBlockMetadataWithNotify(x, y, z, snowMeta, 2);
		}
		else if(meta == snowMeta && TFC_Climate.getHeightAdjustedBioTemp(world, TFC_Time.getTotalDays(), x, y, z) > 0) {
			world.setBlockMetadataWithNotify(x, y, z, bareMeta, 2);
		}
		else
			return false;
		return true;
	}
}
