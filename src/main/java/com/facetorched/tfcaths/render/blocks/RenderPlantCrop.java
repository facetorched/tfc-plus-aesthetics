package com.facetorched.tfcaths.render.blocks;

import java.util.Random;

import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.AthsGlobal;
import com.facetorched.tfcaths.blocks.BlockPlant;
import com.facetorched.tfcaths.util.AthsRandom;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class RenderPlantCrop extends AbstractRenderPlant{

	public boolean renderPlantBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer, Tessellator tesselator, int rgb, int meta, float scale, IIcon icon, Random random) {
		AthsRenderBlocks.renderBlockCrops(icon, x + random.nextDouble()/4.0, y, z + random.nextDouble()/4.0, scale, random);
		return false;
	}

	@Override
	public int getRenderId() {
		return AthsBlockSetup.plantCrossRenderID;
	}
}
