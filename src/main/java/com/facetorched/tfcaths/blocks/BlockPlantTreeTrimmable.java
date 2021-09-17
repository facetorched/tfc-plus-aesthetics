package com.facetorched.tfcaths.blocks;

import java.util.Random;

import com.dunk.tfc.Core.TFC_Climate;
import com.dunk.tfc.Core.TFC_Time;
import com.dunk.tfc.Items.Tools.ItemCustomSaw;
import com.dunk.tfc.Items.Tools.ItemShears;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockPlantTreeTrimmable extends BlockPlantTreeSnowable{
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)  
	{
		if (player.getHeldItem() != null && 
			(player.getHeldItem().getItem() instanceof ItemCustomSaw || player.getHeldItem().getItem() instanceof ItemShears))
			world.setBlockMetadataWithNotify(x, y, z, 1, 2); // meta 1 is sheared
		return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
	}
	@Override
	public BlockPlant setNames(String name) {
		this.plantNames = new String[] {name, name + "_Snow", name + "_Trimmed", name + "_Trimmed_Snow"};
		this.plantKey = name;
		this.setBlockName(name);
		return this;
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand){
		int meta = world.getBlockMetadata(x, y, z);
		int month = TFC_Time.getSeasonAdjustedMonth(z);
		float temp = TFC_Climate.getHeightAdjustedBioTemp(world, TFC_Time.getTotalDays(), x, y, z);
		if(!this.checkSnow(world, x, y, z, meta, month, temp, 0, 1))
			this.checkSnow(world, x, y, z, meta, month, temp, 2, 3);
	}
}
