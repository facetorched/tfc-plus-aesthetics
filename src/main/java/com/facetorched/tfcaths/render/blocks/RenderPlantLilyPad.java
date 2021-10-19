package com.facetorched.tfcaths.render.blocks;

import java.util.Random;

import com.facetorched.tfcaths.AthsBlockSetup;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class RenderPlantLilyPad extends AbstractRenderPlant{

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {

		Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        int rgb = block.colorMultiplier(world, x, y, z);

        tessellator.setColorOpaque_I(rgb);
		//well this is stupid. might want to make this special eventually
		//renderer.renderBlockLilyPad(block, x, y, z);
        
        int meta = world.getBlockMetadata(x, y, z);
        
		IIcon iicon = block.getIcon(0, meta);

        float f = 0.015625F;
        double d0 = (double)iicon.getMinU();
        double d1 = (double)iicon.getMinV();
        double d2 = (double)iicon.getMaxU();
        double d3 = (double)iicon.getMaxV();
        long l = (long)(x * 3129871) ^ (long)z * 116129781L ^ (long)y;
        l = l * l * 42317861L + l * 11L;
        int i1 = (int)(l >> 16 & 3L);
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        float f1 = (float)x + 0.5F;
        float f2 = (float)z + 0.5F;
        float f3 = (float)(i1 & 1) * 0.5F * (float)(1 - i1 / 2 % 2 * 2);
        float f4 = (float)(i1 + 1 & 1) * 0.5F * (float)(1 - (i1 + 1) / 2 % 2 * 2);
        tessellator.setColorOpaque_I(rgb);
        //tessellator.setColorOpaque_I(block.getBlockColor());
        tessellator.addVertexWithUV((double)(f1 + f3 - f4), (double)((float)y + f), (double)(f2 + f3 + f4), d0, d1);
        tessellator.addVertexWithUV((double)(f1 + f3 + f4), (double)((float)y + f), (double)(f2 - f3 + f4), d2, d1);
        tessellator.addVertexWithUV((double)(f1 - f3 + f4), (double)((float)y + f), (double)(f2 - f3 - f4), d2, d3);
        tessellator.addVertexWithUV((double)(f1 - f3 - f4), (double)((float)y + f), (double)(f2 + f3 - f4), d0, d3);
        tessellator.setColorOpaque_I((rgb & 16711422) >> 1);
        //tessellator.setColorOpaque_I((block.getBlockColor() & 16711422) >> 1);
        tessellator.addVertexWithUV((double)(f1 - f3 - f4), (double)((float)y + f), (double)(f2 + f3 - f4), d0, d3);
        tessellator.addVertexWithUV((double)(f1 - f3 + f4), (double)((float)y + f), (double)(f2 - f3 - f4), d2, d3);
        tessellator.addVertexWithUV((double)(f1 + f3 + f4), (double)((float)y + f), (double)(f2 - f3 + f4), d2, d1);
        tessellator.addVertexWithUV((double)(f1 + f3 - f4), (double)((float)y + f), (double)(f2 + f3 + f4), d0, d1);
        return true;
	}

	@Override
	public int getRenderId() {
		return AthsBlockSetup.plantLilyPadRenderID;
	}
	
}
