package com.facetorched.tfcaths.render.blocks;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.blocks.BlockPlant3d;
import com.facetorched.tfcaths.tileentities.TEPlant3d;
import com.facetorched.tfcaths.util.AthsLogger;
import com.facetorched.tfcaths.util.AthsRandom;
import com.facetorched.tfcaths.util.ObjPart;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.obj.GroupObject;
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
			WavefrontObject model = block3d.getModelObj(meta);
			
			GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_CULL_FACE);
			
			GL11.glTranslatef(-0.5F, -1.0F, -0.5F);
			float invScale = 0.6f;
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
		/*
		BlockPlant3d block3d = ((BlockPlant3d)block);
		int meta = world.getBlockMetadata(x, y, z);
		ArrayList<ObjPart> objParts = block3d.getModelParts(meta);
		if(!objParts.isEmpty()) {
			WavefrontObject model = block3d.getModelObj(meta);
			
			GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			
			Random random = AthsRandom.getRandom(x, z);
			GL11.glTranslated(x + 0.25 + random.nextDouble()*0.5, y+random.nextDouble()*.001337, z + 0.25 + random.nextDouble()*0.5); //prevent some z fighting
			
			if(block3d.isAxisAligned)
				GL11.glRotatef(random.nextInt(4) * 90f, 0, 1, 0);
			else
				GL11.glRotatef(random.nextFloat() * 360f, 0, 1, 0); 
			
			float scale = block3d.getScale();
			scale *= 1 - .4 * random.nextFloat();
			
			GL11.glScalef(scale, scale, scale);
			
			Minecraft mc = Minecraft.getMinecraft();
			Tessellator tessellator = Tessellator.instance;
			
			//try {
			//tessellator.draw();
			//}
			//catch (IllegalStateException e) {
				
			//}
			
			for(ObjPart part : objParts) {
				System.out.println(part.key);
				//mc.renderEngine.bindTexture(part.getTexture());
				int rgb = block.colorMultiplier(world, x, y, z);
		        tessellator.setColorOpaque_I(rgb);

				for (GroupObject groupObject : model.groupObjects)
		        {
		            if (part.getKey().equalsIgnoreCase(groupObject.name))
		            {
		                groupObject.render(tessellator);
		            }
		        }
				//model.renderPart(part.getKey());
			}
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glPopMatrix();
			//tessellator.startDrawingQuads();
			//tessellator.disableColor();
			
		}
		return true;
		*/
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRenderId() {
		return AthsBlockSetup.plant3dRenderID;
	}
}
