package com.facetorched.tfcaths.render.tileentities;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.facetorched.tfcaths.AthsMod;
import com.facetorched.tfcaths.blocks.BlockPlant3d;
import com.facetorched.tfcaths.util.AthsLogger;
import com.facetorched.tfcaths.util.AthsRandom;
import com.facetorched.tfcaths.util.ObjPart;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class TESRPlant3d extends TileEntitySpecialRenderer{

	@Override
	public void renderTileEntityAt(TileEntity te, double d0, double d1, double d2, float pticks) {
		int x = te.xCoord;
		int y = te.yCoord;
		int z = te.zCoord;
		if(!te.hasWorldObj()) {
			AthsLogger.error("tile entity missing world reference!");
			return;
		}
		World world = te.getWorldObj();
		if(!(world.getBlock(x, y, z) instanceof BlockPlant3d)) {
			AthsLogger.error("BlockPlant3d not found during render call");
			return;
		}
		BlockPlant3d block = ((BlockPlant3d)world.getBlock(x, y, z));
		int meta = world.getBlockMetadata(x, y, z);
		ArrayList<ObjPart> objParts = block.getModelParts(meta);
		if(!objParts.isEmpty()) {
			WavefrontObject model = block.getModelObj(meta);
			
			GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			
			Random random = AthsRandom.getRandom(x, z);
			GL11.glTranslated(d0 + 0.25 + random.nextDouble()*0.5, d1+random.nextDouble()*.001337, d2 + 0.25 + random.nextDouble()*0.5); //prevent some z fighting
			
			if(block.isAxisAligned)
				GL11.glRotatef(random.nextInt(4) * 90f, 0, 1, 0);
			else
				GL11.glRotatef(random.nextFloat() * 360f, 0, 1, 0); 
			
			float scale = block.getScale();
			scale *= 1 - .4 * random.nextFloat();
			
			GL11.glScalef(scale, scale, scale);
			
			Minecraft mc = Minecraft.getMinecraft();
			
			for(ObjPart part : objParts) {
				mc.renderEngine.bindTexture(part.getTexture());
				model.renderPart(part.getKey());
			}
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glPopMatrix();
		}
	}

}
