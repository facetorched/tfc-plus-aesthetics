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
		int rotation = 0;
		// render each layer
		for(int i = 0; i < 2; i++) {
			float h = 0.251f - (0.125f * (float)i);
			
	        // randomly rotate (and make sure layers never line up)
	        rotation = (rotation + random.nextInt(4 - i)) % 4;
	        
	        AthsRenderBlocks.drawGroundLayer(icon, x, y, z, h, rotation, rgb);
	        
	        float shade = 0.75f;
	        rgb = (int)((rgb & 0xff0000)*shade) & 0xff0000 | 
	        	  (int)((rgb & 0x00ff00)*shade) & 0x00ff00|
	        	  (int)((rgb & 0x0000ff)*shade) & 0x0000ff;
	        rotation ++;
		}
		return false;
	}

	@Override
	public int getRenderId() {
		return AthsBlockSetup.plantLowRenderID;
	}
}
