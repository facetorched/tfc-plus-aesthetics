package com.facetorched.tfcaths.render.blocks;

import java.util.Random;

import com.facetorched.tfcaths.AthsBlockSetup;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class RenderPlantCross extends AbstractRenderPlant {
	@Override
	public boolean renderPlantBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer, Tessellator tessellator, int rgb, int meta, float scale, IIcon icon, Random random) {
		AthsRenderBlocks.drawCrossedSquares(icon, x + random.nextDouble()/4.0, y, z + random.nextDouble()/4.0, scale, random);
		return true;
	}
	@Override
	public int getRenderId() {
		return AthsBlockSetup.plantCropRenderID;
	}
}
