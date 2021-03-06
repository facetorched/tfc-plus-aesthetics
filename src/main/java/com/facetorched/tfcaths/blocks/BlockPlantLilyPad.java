package com.facetorched.tfcaths.blocks;

import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.interfaces.ILilyPad;
import com.facetorched.tfcaths.items.itemblocks.ItemPlantLilyPad;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockPlantLilyPad extends BlockPlant implements ILilyPad{
	public BlockPlantLilyPad() {
		this(Material.plants);
	}
	public BlockPlantLilyPad(Material m) {
		super(m);
		setLayerBounds(.1f);
		setItemBlock(ItemPlantLilyPad.class);
		this.renderId = AthsBlockSetup.plantLilyPadRenderID;
	}
	
	@Override
	public BlockPlant setExtraNames(String name) {
		setNames(new String[] {name + "_Small", name + "_Small_Cluster", name + "_Tiny_Cluster"});
		setKeyName(name);
		return this;
	}
	
	@Override
	public boolean shouldGenerateAt(World world, int x, int y, int z) {
		if(world.isSideSolid(x, y-2, z, ForgeDirection.UP))
			return true;
		return false;
	}
	
	@Override
	public BlockPlant setScale(float scale) {
		throw new UnsupportedOperationException("Cannot set the scale of BlockPlantLilyPad");
	}
}
