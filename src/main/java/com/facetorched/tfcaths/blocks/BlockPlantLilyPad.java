package com.facetorched.tfcaths.blocks;

import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.items.itemblocks.ItemPlantLilyPad;

import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockPlantLilyPad extends BlockPlant{
	public BlockPlantLilyPad() {
		super();
		float var4 = 0.1F;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, var4, 1.0F);
		setItemBlock(ItemPlantLilyPad.class);
	}
	
	@Override
	public BlockPlant setNames(String name) {
		this.plantNames = new String[] {name + "_Small", name + "_Small_Cluster", name + "_Tiny_Cluster"};
		this.plantKey = name;
		this.setBlockName(name);
		return this;
	}
	
	@Override
	public int getRenderType() {
		return AthsBlockSetup.plantLilyPadRenderID;
	}
	
	@Override
	public boolean shouldGenerateAt(World world, int x, int y, int z) {
		if(world.isSideSolid(x, y-2, z, ForgeDirection.UP))
			return true;
		return false;
	}
}
