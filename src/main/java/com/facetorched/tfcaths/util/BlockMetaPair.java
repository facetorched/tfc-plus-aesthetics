package com.facetorched.tfcaths.util;

import net.minecraft.block.Block;

public class BlockMetaPair {
	public Block block;
	public int meta;
	public BlockMetaPair(Block block, int meta) {
		this.block = block;
		this.meta = meta;
	}
	
	@Override
    public boolean equals(Object o) {
    	super.equals(o);
    	if(o.getClass() == BlockMetaPair.class) {
    		BlockMetaPair bm = (BlockMetaPair)o;
    		return 	bm.block.equals(this.block) &&
    				(bm.meta == this.meta || bm.meta == -1 || this.meta == -1);
    	}
    	return false;
    }
}
