package com.facetorched.tfcaths.blocks;

import com.facetorched.tfcaths.AthsBlockSetup;

public class BlockPlantLayer extends BlockPlant{
	public BlockPlantLayer() {
		super();
		setLayerBounds(.01f);
	}
	@Override
	public int getRenderType() {
		return AthsBlockSetup.plantLilyPadRenderID;
	}
}
