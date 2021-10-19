package com.facetorched.tfcaths.blocks;

import java.util.Random;

import com.dunk.tfc.Core.TFC_Climate;
import com.dunk.tfc.Core.TFC_Time;
import com.dunk.tfc.Items.Tools.ItemCustomSaw;
import com.dunk.tfc.Items.Tools.ItemShears;
import com.facetorched.tfcaths.AthsBlockSetup;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockPlantTreeTrimmable extends BlockPlantTree{
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)  
	{
		if (player.getHeldItem() != null && 
			(player.getHeldItem().getItem() instanceof ItemCustomSaw || player.getHeldItem().getItem() instanceof ItemShears)) {
			int meta = world.getBlockMetadata(x, y, z);
			shiftMetaBy(world, x, y, z, meta, 1); // add 1 to the meta
		}
		return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
	}
	@Override
	public BlockPlant setNames(String name) {
		setNames(new String[] {name, name + "_Trimmed"});
		setKeyName(name);
		return this;
	}
	
	@Override
	public int getRenderType() {
		return AthsBlockSetup.plantTreeTrimmableRenderID;
	}
}
