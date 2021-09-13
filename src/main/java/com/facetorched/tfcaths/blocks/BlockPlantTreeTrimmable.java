package com.facetorched.tfcaths.blocks;

import com.dunk.tfc.Items.Tools.ItemCustomSaw;
import com.dunk.tfc.Items.Tools.ItemShears;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockPlantTreeTrimmable extends BlockPlantTree{
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ)  
	{
		if (entityplayer.getHeldItem() != null && 
			(entityplayer.getHeldItem().getItem() instanceof ItemCustomSaw || entityplayer.getHeldItem().getItem() instanceof ItemShears))
			world.setBlockMetadataWithNotify(x, y, z, 1, 2); // meta 1 is sheared
		return super.onBlockActivated(world, x, y, z, entityplayer, side, hitX, hitY, hitZ);
	}
	@Override
	public BlockPlant setNames(String name) {
		this.plantNames = new String[] {name, name + "_Trimmed"};
		this.plantKey = name;
		return this;
	}
}
