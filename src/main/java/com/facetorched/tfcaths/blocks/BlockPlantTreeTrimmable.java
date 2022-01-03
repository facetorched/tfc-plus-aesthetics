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
	public BlockPlantTreeTrimmable() {
		super();
		this.renderId = AthsBlockSetup.plantTreeTrimmableRenderID;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)  
	{
		if (player.getHeldItem() != null && 
			(player.getHeldItem().getItem() instanceof ItemCustomSaw || player.getHeldItem().getItem() instanceof ItemShears)) {
			int meta = world.getBlockMetadata(x, y, z);
			if(player.isSneaking()) {
				shiftMeta(world, x, y, z, meta, 2); // add 2 to the meta (narrow)
				System.out.println("hi");
			}
			else {
				shiftMeta(world, x, y, z, meta, 1); // add 1 to the meta (trimmed)
			}
		}
		return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
	}
	@Override
	public BlockPlant setNames(String name) {
		setNames(new String[] {name, name + "_Trimmed", name + "_Narrow"});
		setKeyName(name);
		return this;
	}
	
	@Override
	public BlockPlant setName(String name) {
		return setNames(name);
	}
}
