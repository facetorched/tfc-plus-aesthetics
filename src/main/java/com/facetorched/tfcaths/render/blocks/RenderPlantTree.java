package com.facetorched.tfcaths.render.blocks;

import java.util.Random;

import com.facetorched.tfcaths.AthsGlobal;
import com.facetorched.tfcaths.blocks.BlockPlant;
import com.facetorched.tfcaths.util.AthsLogger;

import net.minecraft.block.Block;

public class RenderPlantTree extends RenderPlantCross{
	@Override
	public float getRenderScale(Block block, Random random, int meta){
		
		float scale = AthsGlobal.TREE_SCALE;
		
		try {
			scale = ((BlockPlant)block).getScale();
		}
		catch(ClassCastException e) {
			AthsLogger.error(e);
		}
		float f = 1.0f;
		scale *= random.nextFloat() * f + 1f;
		return scale;
	}
}
