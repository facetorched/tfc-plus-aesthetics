package com.facetorched.tfcaths.render.blocks;

import java.util.Random;

import com.facetorched.tfcaths.blocks.BlockPlant;
import com.facetorched.tfcaths.util.AthsLogger;

import net.minecraft.block.Block;

public class RenderPlantTreeTrimmable extends RenderPlantCross{
	@Override
	public float getScale(Block block, Random random, int meta){
		float scale = 6.0F;
		try {
			scale = ((BlockPlant)block).getScale();
		}
		catch(ClassCastException e) {
			AthsLogger.error(e);
		}
		if(meta == 0) // add some variation to larger things like trees (less obvious pixel size difference at this scale)
			scale += random.nextFloat()*2.0F;
		return scale;
	}
}
