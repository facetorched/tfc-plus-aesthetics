package com.facetorched.tfcaths;

import com.dunk.tfc.Food.ItemFoodTFC;
import com.dunk.tfc.api.TFCItems;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class AthsRecipes {
	public static void RegisterRecipes() {
		addBrownMushroomRecipe(AthsBlockSetup.beefsteakFungus, 16f);
		addBrownMushroomRecipe(AthsBlockSetup.birchPolypore, 8f);
		addBrownMushroomRecipe(AthsBlockSetup.boletus, 32f);
		addRedMushroomRecipe(AthsBlockSetup.bridalVeilStinkhorn, 4f);
		addBrownMushroomRecipe(AthsBlockSetup.chanterelle, 32f);
		addBrownMushroomRecipe(AthsBlockSetup.sulphurShelf, 160f);
		addBrownMushroomRecipe(AthsBlockSetup.birchPolypore, 8f);
		addBrownMushroomRecipe(AthsBlockSetup.chlorophosFoxfire, 2f);
		addRedMushroomRecipe(AthsBlockSetup.devilsFingers, 4f);
		addBrownMushroomRecipe(AthsBlockSetup.dryadsSaddle, 120f);
		addRedMushroomRecipe(AthsBlockSetup.entoloma, 6f);
		addBrownMushroomRecipe(AthsBlockSetup.indigoMilkCap, 16f);
		addBrownMushroomRecipe(AthsBlockSetup.lionsMane, 16f);
		addBrownMushroomRecipe(AthsBlockSetup.morel, 4f);
		addBrownMushroomRecipe(AthsBlockSetup.birchPolypore, 8f);
		addBrownMushroomRecipe(AthsBlockSetup.shitake, 10f);
	}
	
	public static void addFoodRefineRecipe(Block foodInput, Item foodOutput, float outputWeight){
		GameRegistry.addRecipe(
		new ShapelessOreRecipe(ItemFoodTFC.createTag(new ItemStack(foodOutput, 1, 0), outputWeight), new ItemStack(foodInput, 1, 0), "itemKnife"));
	}
	
	public static void addBrownMushroomRecipe(Block foodInput, float outputWeight) {
		addFoodRefineRecipe(foodInput, TFCItems.mushroomFoodB, outputWeight);
	}
	public static void addRedMushroomRecipe(Block foodInput, float outputWeight) {
		addFoodRefineRecipe(foodInput, TFCItems.mushroomFoodR, outputWeight);
	}
}
