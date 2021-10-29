package com.facetorched.tfcaths.render.blocks;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.blocks.BlockPlant3d;
import com.facetorched.tfcaths.tileentities.TEPlant3d;
import com.facetorched.tfcaths.util.AthsRandom;
import com.facetorched.tfcaths.util.ObjPart;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class RenderPlant3d implements ISimpleBlockRenderingHandler {
	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks renderer) {
		if(!(block instanceof BlockPlant3d)) {
			return;
		}
		BlockPlant3d block3d = (BlockPlant3d)block;
		ArrayList<ObjPart> objParts = block3d.getModelParts(meta);
		if(!objParts.isEmpty()) {
			WavefrontObject model = block3d.getModelObj();
			
			GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_CULL_FACE);
			
			GL11.glTranslatef(-0.5F, -1.0F, -0.5F);
			float invScale = 0.7f/block3d.scale;
			GL11.glScalef(invScale, invScale, invScale);
			
			Minecraft mc = Minecraft.getMinecraft();
			
			for(ObjPart part : objParts) {
				mc.renderEngine.bindTexture(part.getTexture());
				model.renderPart(part.getKey());
			}
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glPopMatrix();
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRenderId() {
		return AthsBlockSetup.plant3dRenderID;
	}
}
