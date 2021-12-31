package com.facetorched.tfcaths.blocks;

import com.facetorched.tfcaths.AthsBlockSetup;

public class BlockPlantLayer extends BlockPlant{
	public BlockPlantLayer() {
		super();
		setLayerBounds(.0625f);
		this.renderId = AthsBlockSetup.plantLayerRenderID;
	}
}
