package com.facetorched.tfcaths.blocks;

import com.facetorched.tfcaths.AthsBlockSetup;

public class BlockPlantCrop extends BlockPlantStraw{

	public BlockPlantCrop() {
		super();
		setGrassBounds();
	}
	@Override
	public int getRenderType() {
		return AthsBlockSetup.plantCropRenderID;
	}
}
