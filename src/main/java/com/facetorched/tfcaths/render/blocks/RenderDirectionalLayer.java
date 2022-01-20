package com.facetorched.tfcaths.render.blocks;

import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.util.Point3DD;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class RenderDirectionalLayer implements ISimpleBlockRenderingHandler{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {
		//if(!(block instanceof BlockCrystal)) {
		//	return false;
		//}
		//BlockCrystal crystal = (BlockCrystal)block;
		Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        int rgb = block.colorMultiplier(world, x, y, z);
        tessellator.setColorOpaque_I(rgb);
		int meta = world.getBlockMetadata(x, y, z);
		ForgeDirection d = ForgeDirection.getOrientation(meta % 6);
		Point3DD pt1 = new Point3DD(0,0.01,0).rotateToFace(d);
		Point3DD pt2 = new Point3DD(1,0.01,1).rotateToFace(d);
		renderer.setRenderBounds(pt1.x, pt1.y, pt1.z, pt2.x, pt2.y, pt2.z);
		renderer.renderStandardBlock(block, x, y, z);
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}

	@Override
	public int getRenderId() {
		return AthsBlockSetup.directionalLayerRenderID;
	}

}
