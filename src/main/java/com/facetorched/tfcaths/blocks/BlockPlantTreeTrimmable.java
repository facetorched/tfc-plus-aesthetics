package com.facetorched.tfcaths.blocks;

import com.dunk.tfc.Items.Tools.ItemShears;
import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.util.AthsParser;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockPlantTreeTrimmable extends BlockPlantTree{
	public BlockPlantTreeTrimmable() {
		super();
		this.renderId = AthsBlockSetup.plantTreeTrimmableRenderID;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)  {
		if (!world.isRemote && player.getHeldItem() != null && 
			(AthsParser.isHolding(world, player, "itemSaw") || player.getHeldItem().getItem() instanceof ItemShears)) {
			int meta = world.getBlockMetadata(x, y, z);
			//if(player.isSneaking()) {
			//	shiftMeta(world, x, y, z, meta, 2); // add 2 to the meta (narrow)
			//}
			shiftMeta(world, x, y, z, meta, 1); // add 1 to the meta (trimmed)
		}
		return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
	}
	@Override
	public BlockPlant setExtraNames(String name) {
		return setExtraNames(name, "Trimmed", "Narrow");
	}
	
	@Override
	public BlockPlant setName(String name) {
		return setExtraNames(name);
	}
}
