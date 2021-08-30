package com.facetorched.tfcaths;

import com.facetorched.tfcaths.blocks.BlockSimplePlant;
import com.facetorched.tfcaths.items.itemblocks.ItemSimplePlant;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class AthsBlockSetup {
	
	public static Block sageBrush;
	public static int simplePlantRenderID;
	
	public static void loadBlocks() {
		sageBrush = new BlockSimplePlant(new String[] {"Sagebrush", "Sagebrush_Short", "Sagebrush_Large"}, "sageBrush").setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("sageBrush");
	}
	public static void registerBlocks() {
		GameRegistry.registerBlock(sageBrush, ItemSimplePlant.class, "sageBrush");
	}
}
