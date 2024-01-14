package com.facetorched.tfcaths.render.blocks;

import java.util.ArrayList;
import java.util.Random;

import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.AthsGlobal;
import com.facetorched.tfcaths.blocks.BlockPlant3d;
import com.facetorched.tfcaths.util.ObjPart;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.obj.Face;
import net.minecraftforge.client.model.obj.GroupObject;
import net.minecraftforge.client.model.obj.TextureCoordinate;
import net.minecraftforge.client.model.obj.Vertex;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class RenderPlant3d extends AbstractRenderPlant {
	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks renderer) {
		if (!(block instanceof BlockPlant3d)) {
			return;
		}
		BlockPlant3d block3d = (BlockPlant3d) block;
		WavefrontObject model = block3d.getModelObj(meta);
		Tessellator tessellator = Tessellator.instance;
		
		RenderHelper.disableStandardItemLighting();
		tessellator.startDrawingQuads();
		tessellator.setColorOpaque_F(1, 1, 1);
		float maxDist = 0f;
		for (Vertex v : model.vertices) {
			maxDist = Math.max(Math.max(Math.max(Math.abs(v.x), Math.abs(v.y)), Math.abs(v.z)), maxDist);
		}
		float dy = -0.1f;
		tessellator.addTranslation(0, dy, 0);
		renderWithOBJ(model, block3d.getModelParts(meta), tessellator, .8f/maxDist, 0.0f);
		tessellator.draw();
		tessellator.addTranslation(0, -dy, 0);
		RenderHelper.enableStandardItemLighting();
	}
	
	@Override
	public boolean renderPlantBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer, Tessellator tessellator, int rgb, int meta, float scale, IIcon icon, Random random) {
		BlockPlant3d block3d = ((BlockPlant3d) block);

		// rotate a random amount
		float rotation;
		if (block3d.isAxisAligned)
			rotation = random.nextInt(4) * AthsGlobal.HALF_PI;
		else
			rotation = random.nextFloat() * AthsGlobal.TWO_PI;
		
		// scale down by some amount
		if (!block3d.isConstantSize) {
			scale *= 1 - .4 * random.nextFloat();
		}
		
		// translate by some random amount and vertical shift to prevent z fighting with neighbors
		float dx = .25F + random.nextFloat() * 0.5F;
		float dy = random.nextFloat() * 0.001337F;
		float dz = 0.25F + random.nextFloat() * 0.5F;
		tessellator.addTranslation(x + dx, y + dy, z + dz);
		renderWithOBJ(block3d.getModelObj(meta), block3d.getModelParts(meta), tessellator, scale, rotation);
		tessellator.addTranslation(-x - dx, -y - dy, -z - dz);
		return true;
	}

	@Override
	public int getRenderId() {
		return AthsBlockSetup.plant3dRenderID;
	}

	public static void renderWithOBJ(WavefrontObject model, ArrayList<ObjPart> objParts, Tessellator tes, float scale,
			float rotation) {
		float cos = (float) Math.cos(rotation);
		float sin = (float) Math.sin(rotation);
		float x, z; // positions used for rotation
		
		for (GroupObject go : model.groupObjects) {
			ObjPart part = null;
			for (ObjPart p : objParts) {
				if (p.getKey().equalsIgnoreCase(go.name)) {
					part = p;
					break;
				}
			}
			if (part != null) {
				IIcon icon = part.getIcon();
				for (Face f : go.faces) {
					Vertex n = f.faceNormal;
					
					tes.setNormal(n.x, n.y, n.z);
					for (int i = 0; i < f.vertices.length; i++) {
						Vertex v = f.vertices[i];
						TextureCoordinate t = f.textureCoordinates[i];
						// rotate
						x = cos * v.x - sin * v.z;
						z = sin * v.x + cos * v.z;
						// scale
						x *= scale;
						z *= scale;
						tes.addVertexWithUV(x, v.y * scale, z, icon.getInterpolatedU(t.u * 16), icon.getInterpolatedV(t.v * 16));
					}
					// render other side of face
					tes.setNormal(-n.x, -n.y, -n.z); // negative normal
					for (int i = f.vertices.length - 1; i >= 0; i--) {
						Vertex v = f.vertices[i];
						TextureCoordinate t = f.textureCoordinates[i];
						// rotate
						x = cos * v.x - sin * v.z;
						z = sin * v.x + cos * v.z;
						// scale
						x *= scale;
						z *= scale;
						tes.addVertexWithUV(x, v.y * scale, z, icon.getInterpolatedU(t.u * 16), icon.getInterpolatedV(t.v * 16));
					}
				}
			}
		}
	}
}
