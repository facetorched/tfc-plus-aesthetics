package com.facetorched.tfcaths.blocks;

import com.dunk.tfc.BlockSetup;
import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.enums.EnumVary;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;

public class BlockPlantLayer extends BlockPlant{
	public BlockPlantLayer() {
		super(Material.vine);
		setHasNoDrops();
		setLayerBounds(.0625f);
		this.renderId = AthsBlockSetup.plantLayerRenderID;
	}
	
	@Override
	public BlockPlant setScale(float scale) {
		throw new UnsupportedOperationException("Cannot set the scale of BlockPlantLayer");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if(this.isVary(meta, EnumVary.SNOW)) {
			return BlockSetup.snow.getIcon(1, 0);
		}
		return super.getIcon(side, meta);
	}
}
