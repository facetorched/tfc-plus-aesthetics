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
		try {
			if(((BlockPlantTreeTrimmable)block).getBaseMeta(meta) >= 1) //trimmed
				return ((BlockPlantTreeTrimmable)block).getScale();
		}
		catch(ClassCastException e) {
			AthsLogger.error(e);
		}
		return super.getRenderScale(block, random, meta);
	}
}
