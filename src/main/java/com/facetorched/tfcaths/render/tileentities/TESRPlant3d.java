package com.facetorched.tfcaths.render.tileentities;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.facetorched.tfcaths.AthsMod;
import com.facetorched.tfcaths.blocks.BlockPlant3d;
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
		// TODO Auto-generated method stub
		int x = te.xCoord;
		int y = te.yCoord;
		int z = te.zCoord;
		BlockPlant3d block = ((BlockPlant3d)te.getWorldObj().getBlock(x, y, z));
		int meta = te.getWorldObj().getBlockMetadata(x, y, z);
		WavefrontObject model = block.getModelObj();
		ArrayList<ObjPart> objParts = block.getModelParts(meta);
		if(!objParts.isEmpty()) {
			
			GL11.glPushMatrix();
			GL11.glTranslated(d0 + 0.5, d1+.00001337, d2 + 0.5); //prevent some z fighting
			GL11.glRotatef(AthsRandom.getRandom(x, z).nextFloat() * 360f, 0, 1, 0); 
			GL11.glScalef(2.0f, 2.0f, 2.0f);
			GL11.glDisable(GL11.GL_CULL_FACE);
			//Tessellator tessellator = Tessellator.instance;
			//tessellator.setNormal(0, 1, 0);
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
