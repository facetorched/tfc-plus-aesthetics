package com.facetorched.tfcaths.render.blocks;

import java.util.Random;

import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.util.AthsRandom;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class RenderDirectionalCross implements ISimpleBlockRenderingHandler{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {
		Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        int rgb = block.colorMultiplier(world, x, y, z);
        tessellator.setColorOpaque_I(rgb);
        int meta = world.getBlockMetadata(x, y, z);
        ForgeDirection d = ForgeDirection.getOrientation(meta % 6);
		IIcon icon = block.getIcon(0, meta);
		Random random = AthsRandom.getRandom(x, z);
		AthsRenderBlocks.drawCrossedSquares(icon, x, y, z, 1.0f, random, d);
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRenderId() {
		return AthsBlockSetup.directionalCrossRenderID;
	}

}
