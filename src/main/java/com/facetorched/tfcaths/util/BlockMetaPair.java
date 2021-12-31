package com.facetorched.tfcaths.util;

import net.minecraft.block.Block;

public class BlockMetaPair {
	public Block block;
	public int meta;
	public BlockMetaPair(Block block, int meta) {
		this.block = block;
		this.meta = meta;
	}
}
