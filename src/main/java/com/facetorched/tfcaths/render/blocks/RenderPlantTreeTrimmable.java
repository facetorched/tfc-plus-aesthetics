package com.facetorched.tfcaths.render.blocks;

import java.util.Random;

import com.facetorched.tfcaths.AthsGlobal;
import com.facetorched.tfcaths.blocks.BlockPlant;
import com.facetorched.tfcaths.blocks.BlockPlantTreeTrimmable;
import com.facetorched.tfcaths.util.AthsLogger;

import net.minecraft.block.Block;

public class RenderPlantTreeTrimmable extends RenderPlantCross{
	@Override
	public float getRenderScale(Block block, Random random, int meta){
		float scale = AthsGlobal.TREE_SCALE;
		try {
			scale = ((BlockPlantTreeTrimmable)block).getScale();
			if(((BlockPlantTreeTrimmable)block).getBaseMeta(meta) != 1) //trimmed
				scale += random.nextFloat()*2.0F;
		}
		catch(ClassCastException e) {
			AthsLogger.error(e);
		}
		return scale;
	}
}
