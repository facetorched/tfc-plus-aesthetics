package com.facetorched.tfcaths.render.blocks;

import java.util.Random;

import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.AthsGlobal;
import com.facetorched.tfcaths.blocks.BlockPlant;
import com.facetorched.tfcaths.util.AthsLogger;
import com.facetorched.tfcaths.util.AthsRandom;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class RenderPlantCross extends AbstractRenderPlant {

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		renderSnow(world, x, y, z, block, modelId, renderer);
		renderLeaves(world, x, y, z, block, modelId, renderer);
		Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        int l = block.colorMultiplier(world, x, y, z);
        float f = (float)(l >> 16 & 255) / 255.0F;
        float f1 = (float)(l >> 8 & 255) / 255.0F;
        float f2 = (float)(l & 255) / 255.0F;

        tessellator.setColorOpaque_F(f, f1, f2);

        int meta = world.getBlockMetadata(x, y, z);
        
		IIcon icon = block.getIcon(0, meta);
		
		//add a bit of x z variation based on coords
		Random random = AthsRandom.getRandom(x, z);
		
		renderer.drawCrossedSquares(icon, x + random.nextDouble()/4.0, y, z + random.nextDouble()/4.0, getScale(block, random, meta));
		return true;
	}

	@Override
	public int getRenderId() {
		return AthsBlockSetup.plantCropRenderID;
	}
	
}
