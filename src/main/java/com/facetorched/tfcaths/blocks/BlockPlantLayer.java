package com.facetorched.tfcaths.blocks;

import com.facetorched.tfcaths.AthsBlockSetup;

public class BlockPlantLayer extends BlockPlant{
	public BlockPlantLayer() {
		super();
		float h = 0.01F;
		this.setBlockBounds(0F, 0F, 0F, 1F, h, 1F);
	}
	@Override
	public int getRenderType() {
		return AthsBlockSetup.plantLilyPadRenderID;
	}
}
