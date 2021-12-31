package com.facetorched.tfcaths.render.blocks;

import java.util.Random;

import com.dunk.tfc.BlockSetup;
import com.facetorched.tfcaths.AthsBlockSetup;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class RenderPlantLilyPad extends AbstractRenderPlant{

	@Override
	public boolean renderPlantBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer, Tessellator tessellator, int rgb, int meta, float scale, IIcon icon, Random random) {
		renderer.setOverrideBlockTexture(icon);
		renderer.renderBlockLilyPad(block, x, y, z);
		renderer.clearOverrideBlockTexture();
		return true;
	}

	@Override
	public int getRenderId() {
		return AthsBlockSetup.plantLilyPadRenderID;
	}
}
