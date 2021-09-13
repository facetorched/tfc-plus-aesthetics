package com.facetorched.tfcaths.blocks;

import com.facetorched.tfcaths.AthsBlockSetup;

public class BlockPlantCrop extends BlockPlant{

	public BlockPlantCrop() {
		super();
		float var4 = 0.4F;
		this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 2.0F, 0.5F + var4);
	}
	@Override
	public int getRenderType() {
		return AthsBlockSetup.plantCropRenderID;
	}
}
