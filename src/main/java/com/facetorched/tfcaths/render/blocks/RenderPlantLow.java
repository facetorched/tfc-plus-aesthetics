package com.facetorched.tfcaths.render.blocks;

import java.util.Random;

import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.blocks.BlockPlantLow;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class RenderPlantLow extends AbstractRenderPlant{

	@Override
	public boolean renderPlantBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer, Tessellator tessellator, int rgb, int meta, float scale, IIcon icon, Random random) {
		if(!(block instanceof BlockPlantLow)) {
			return false;
		}
		BlockPlantLow plantLow = (BlockPlantLow)block;
		renderer.drawCrossedSquares(plantLow.sideIcons[meta], x, y, z, scale);
		int i1 = 0;
		// render each layer
		for(int i = 0; i < 2; i++) {
			float f = 0.251f - (0.125f * (float)i);
			
	        double d0 = (double)icon.getMinU();
	        double d1 = (double)icon.getMinV();
	        double d2 = (double)icon.getMaxU();
	        double d3 = (double)icon.getMaxV();
	        // randomly rotate (and make sure layers never line up)
	        i1 = (i1 + random.nextInt(4 - i)) % 4;
	        float f1 = (float)x + 0.5F;
	        float f2 = (float)z + 0.5F;
	        float f3 = (float)(i1 & 1) * 0.5F * (float)(1 - i1 / 2 % 2 * 2);
	        float f4 = (float)(i1 + 1 & 1) * 0.5F * (float)(1 - (i1 + 1) / 2 % 2 * 2);
			tessellator.setColorOpaque_I(rgb);
	        tessellator.addVertexWithUV((double)(f1 + f3 - f4), (double)((float)y + f), (double)(f2 + f3 + f4), d0, d1);
	        tessellator.addVertexWithUV((double)(f1 + f3 + f4), (double)((float)y + f), (double)(f2 - f3 + f4), d2, d1);
	        tessellator.addVertexWithUV((double)(f1 - f3 + f4), (double)((float)y + f), (double)(f2 - f3 - f4), d2, d3);
	        tessellator.addVertexWithUV((double)(f1 - f3 - f4), (double)((float)y + f), (double)(f2 + f3 - f4), d0, d3);
	        tessellator.setColorOpaque_I((rgb & 16711422) >> 1);
	        tessellator.addVertexWithUV((double)(f1 - f3 - f4), (double)((float)y + f), (double)(f2 + f3 - f4), d0, d3);
	        tessellator.addVertexWithUV((double)(f1 - f3 + f4), (double)((float)y + f), (double)(f2 - f3 - f4), d2, d3);
	        tessellator.addVertexWithUV((double)(f1 + f3 + f4), (double)((float)y + f), (double)(f2 - f3 + f4), d2, d1);
	        tessellator.addVertexWithUV((double)(f1 + f3 - f4), (double)((float)y + f), (double)(f2 + f3 + f4), d0, d1);
	        float shade = 0.75f;
	        rgb = (int)((rgb & 0xff0000)*shade) & 0xff0000 | 
	        	  (int)((rgb & 0x00ff00)*shade) & 0x00ff00|
	        	  (int)((rgb & 0x0000ff)*shade) & 0x0000ff;
	        i1 ++;
		}
		return false;
	}

	@Override
	public int getRenderId() {
		return AthsBlockSetup.plantLowRenderID;
	}
}
