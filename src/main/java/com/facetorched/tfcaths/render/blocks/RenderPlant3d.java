package com.facetorched.tfcaths.render.blocks;

import java.util.ArrayList;
import java.util.Random;

import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.blocks.BlockPlant3d;
import com.facetorched.tfcaths.util.AthsRandom;
import com.facetorched.tfcaths.util.ObjPart;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.obj.Face;
import net.minecraftforge.client.model.obj.GroupObject;
import net.minecraftforge.client.model.obj.TextureCoordinate;
import net.minecraftforge.client.model.obj.Vertex;
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
			Tessellator tes = Tessellator.instance;
			RenderHelper.disableStandardItemLighting();
	        tes.startDrawingQuads();
	        tes.setColorOpaque_F(1, 1, 1);
	        float scale = block3d.getScale();
	        renderWithOBJ(model, block3d.getModelParts(meta), tes, null, scale / 3f);
	        tes.draw();
	        
	        RenderHelper.enableStandardItemLighting();
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {

		BlockPlant3d block3d = ((BlockPlant3d)world.getBlock(x, y, z));
		int meta = world.getBlockMetadata(x, y, z);
		ArrayList<ObjPart> objParts = block3d.getModelParts(meta);
		if(!objParts.isEmpty()) {
			WavefrontObject model = block3d.getModelObj(meta);
			
			//GL11.glPushMatrix();
			
			//GL11.glDisable(GL11.GL_CULL_FACE);
			
			Random random = AthsRandom.getRandom(x, z);
			//GL11.glTranslated(0 + 0.25 + random.nextDouble()*0.5, 0+random.nextDouble()*.001337, 0 + 0.25 + random.nextDouble()*0.5); //prevent some z fighting
			
			//if(block3d.isAxisAligned)
			//	GL11.glRotatef(random.nextInt(4) * 90f, 0, 1, 0);
			//else
			//	GL11.glRotatef(random.nextFloat() * 360f, 0, 1, 0); 
			
			float scale = block3d.getScale();
			scale *= 1 - .4 * random.nextFloat();
			Tessellator tes = Tessellator.instance;
			tes.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
	        tes.setColorOpaque_F(1, 1, 1);
	        float dx = .25F + random.nextFloat() * 0.5F;
	        float dy = random.nextFloat() * 0.001337F;
	        float dz = 0.25F + random.nextFloat() * 0.5F;
	        tes.addTranslation(x + dx, y + dy , z + dz); //prevent some z fighting
	        renderWithOBJ(model, block3d.getModelParts(meta), tes, random, scale);
	        tes.addTranslation(-x - dx, -y - dy, -z - dz);
			return true;
		}
		return false;
	}

	@Override
	public int getRenderId() {
		return AthsBlockSetup.plant3dRenderID;
	}
	
	public static void renderWithOBJ(WavefrontObject model, ArrayList<ObjPart> objParts, Tessellator tes, Random random, float scale)
    {
		int rotation = random == null ? 0 : random.nextInt(4); // number of 90 degrees to rotate
		float x, z, _x; // positions used for rotation
		
        for(GroupObject go : model.groupObjects){
        	ObjPart part = null;
        	for(ObjPart p : objParts) {
        		if(p.getKey().equalsIgnoreCase(go.name)) {
        			part = p;
        		}
        	}
        	if(part != null) {
        		
	            for(Face f : go.faces) {
	                Vertex n = f.faceNormal;
	                tes.setNormal(n.x, n.y, n.z);
	                for(int i = 0; i < f.vertices.length; i++) 
	                {
	                    Vertex v = f.vertices[i];
	                    TextureCoordinate t = f.textureCoordinates[i];
	                    x = v.x * scale;
                    	z = v.z * scale;
	                    for(int r = 0; r < rotation; r++) {
	                    	_x = x;
	                    	x = -z;
	                    	z = _x;
	                    }
	                    tes.addVertexWithUV(x, v.y * scale, z,
	                    	part.getIcon().getInterpolatedU(t.u * 16),
	                    	part.getIcon().getInterpolatedV(t.v * 16));
	                }
	            }
	            
	            for(Face f : go.faces) {
	                Vertex n = f.faceNormal;
	                tes.setNormal(-n.x, -n.y, -n.z); // negative normal?
	                for(int i = f.vertices.length-1; i >= 0; i--) 
	                {
	                    Vertex v = f.vertices[i];
	                    TextureCoordinate t = f.textureCoordinates[i];
	                    x = v.x * scale;
                    	z = v.z * scale;
	                    for(int r = 0; r < rotation; r++) {
	                    	_x = x;
	                    	x = -z;
	                    	z = _x;
	                    }
	                    tes.addVertexWithUV(x, v.y * scale, z,
	                    	part.getIcon().getInterpolatedU(t.u * 16),
	                    	part.getIcon().getInterpolatedV(t.v * 16));
	                }
	            }
        	}
        }
    }
}
