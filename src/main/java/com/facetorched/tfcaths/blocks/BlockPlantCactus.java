package com.facetorched.tfcaths.blocks;

public class BlockPlantCactus extends BlockPlantFlower{
	public BlockPlantCactus () {
		super();
		this.setIsWoody(); // has collision!
		this.setIsDamaging();
	}
}
