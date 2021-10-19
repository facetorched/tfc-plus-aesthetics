package com.facetorched.tfcaths.blocks;

import java.util.Random;

import com.dunk.tfc.Core.TFC_Climate;
import com.dunk.tfc.Core.TFC_Time;
import com.facetorched.tfcaths.AthsGlobal;

import net.minecraft.world.World;

public class BlockPlantTreeDeciduous extends BlockPlantTreeSnowable{
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand){
		int meta = world.getBlockMetadata(x, y, z);
		int month = TFC_Time.getSeasonAdjustedMonth(z);
		float temp = TFC_Climate.getHeightAdjustedBioTemp(world, TFC_Time.getTotalDays(), x, y, z);
		
		if (meta != 2 && meta != 3 && // not green or fall colors
				month < TFC_Time.OCTOBER &&
				temp >= 0)
			
			world.setBlockMetadataWithNotify(x, y, z, 3, 2); // meta 3 is green
		else if (meta  == 3 && // is green
			month >= TFC_Time.OCTOBER && 
			temp <= 10){ // 47 degrees F
			
			world.setBlockMetadataWithNotify(x, y, z, 2, 2); // meta 2 is fall colors
		}
		else if (meta != 0 && meta != 1 && // not no leaves or snow covered
				(
					(month >= TFC_Time.OCTOBER && temp <= 0f) ||
					(month >= TFC_Time.NOVEMBER && temp <= 10f)
				)){
				
			world.setBlockMetadataWithNotify(x, y, z, 0, 2); // meta 0 is no leaves
		}
		else
			this.checkSnow(world, x, y, z, meta, month, temp, 0, 1); // snow add or melt checks
	}
	
	@Override
	public BlockPlant setNames(String name) {
		this.plantNames = new String[] {name + "_Winter", name + "_Winter_Snow", name + "_Autumn", name};
		this.plantKey = name;
		this.setBlockName(name);
		return this;
	}
}
