package com.facetorched.tfcaths.render.blocks;

import java.util.Random;

import com.dunk.tfc.BlockSetup;
import com.dunk.tfc.Blocks.Flora.BlockBranch;
import com.facetorched.tfcaths.AthsGlobal;
import com.facetorched.tfcaths.WorldGen.Generators.AthsWorldGenPlants;
import com.facetorched.tfcaths.WorldGen.Generators.PlantSpawnData;
import com.facetorched.tfcaths.blocks.BlockPlant3d;
import com.facetorched.tfcaths.util.Point3D;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.obj.Vertex;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class RenderPlantEpiphyte3d extends RenderPlant3d{
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
		PlantSpawnData data = AthsWorldGenPlants.plantList.get(block3d.plantKey);
		Point3D origin = new Point3D(x, y, z);
		float rotation = 0f;
		float inset = 0.5f;
		
		for(Point3D p : origin.add(AthsGlobal.HORIZ_NEIGHBORS)) {
			Block b = world.getBlock(p.x, p.y, p.z);
			int m = world.getBlockMetadata(p.x, p.y, p.z);
			if(data.canGrowOnBlock(b, m)) {
				if (b == BlockSetup.branch2__y_ && m == 1) {
					inset = 0.9F;
				}
				else if(b instanceof BlockBranch) {
					inset = 0.75F;
				}
				break;
			}
			rotation += AthsGlobal.HALF_PI;
		}
		// scale down by some amount
		scale *= 1 - .4 * random.nextFloat();
		
		// translate by some random amount and vertical shift to prevent z fighting with neighbors
		float cos = (float)Math.cos(rotation);
		float sin = (float)Math.sin(rotation);
		
		
		float dx = 0.5F - sin * inset;
		float dy = 0.5F;
		float dz = 0.5F - cos * - inset;
		
		tessellator.addTranslation(x + dx, y + dy, z + dz);
		renderWithOBJ(block3d.getModelObj(meta), block3d.getModelParts(meta), tessellator, scale, rotation);
		tessellator.addTranslation(-x - dx, -y - dy, -z - dz);
		return true;
	}
}
