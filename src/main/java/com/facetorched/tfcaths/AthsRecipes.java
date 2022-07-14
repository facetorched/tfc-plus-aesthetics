package com.facetorched.tfcaths;

import com.dunk.tfc.Food.ItemFoodTFC;
import com.dunk.tfc.api.TFCFluids;
import com.dunk.tfc.api.Crafting.BarrelManager;
import com.dunk.tfc.api.Crafting.BarrelRecipe;
import com.facetorched.tfcaths.blocks.BlockPlant;
import com.facetorched.tfcaths.interfaces.IFungus;
import com.facetorched.tfcaths.util.AthsLogger;
import com.facetorched.tfcaths.util.AthsParser;
import com.facetorched.tfcaths.util.Config;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class AthsRecipes {
	public static void registerRecipes() {
		if(Config.mushroomRecipes) {
			for(BlockPlant b : AthsParser.getAthsPlants(IFungus.class)) {
				addFoodRefineRecipe(b, b.getFoodItemStack());
			}
		}
		if(Config.miscRecipes) {
			GameRegistry.addRecipe(new ItemStack(Items.paper, 9), "###", '#', AthsBlockSetup.papyrus);
			BarrelManager.getInstance().addRecipe(new BarrelRecipe(new ItemStack(AthsBlockSetup.pokeweed, 1, 8), new FluidStack(TFCFluids.TANNIN, 1000), 
					new ItemStack(AthsBlockSetup.pokeweed, 1, 0), new FluidStack(TFCFluids.REDDYE, 1000)).setMinTechLevel(0));
			BarrelManager.getInstance().addRecipe(new BarrelRecipe(new ItemStack(AthsBlockSetup.pokeweed, 1, 9), new FluidStack(TFCFluids.TANNIN, 3000), 
					new ItemStack(AthsBlockSetup.pokeweed, 1, 1), new FluidStack(TFCFluids.REDDYE, 3000)).setMinTechLevel(0));
		}
		if(Config.propagationRecipes) {
			for(BlockPlant plant : AthsParser.getAthsPlants()) {
				for(int meta : plant.getMetas()) {
					int time = 240; // 10 days
					
					// balanced for food giving items
					if(plant.foodItemStack != null) {
						ItemStack stack = plant.foodItemStack;
						if(stack.hasTagCompound() && stack.getTagCompound().hasKey("foodWeight")) {
							float foodWeight = stack.getTagCompound().getFloat("foodWeight");
							time += (int)(foodWeight/10) * 24;
						}
					}
					
					int baseMeta = plant.getBaseMeta(meta);
					
					BarrelManager.getInstance().addRecipe(new BarrelRecipe(new ItemStack(plant, 1, meta), new FluidStack(TFCFluids.FRESHWATER, 1000), 
							new ItemStack(plant, 2, baseMeta), new FluidStack(TFCFluids.FRESHWATER, 1000), time).setSealedRecipe(false).setMinTechLevel(0));
				}
			}
		}
		
	}
	
	public static void addFoodRefineRecipe(Block foodInput, Item foodOutput, float outputWeight){
		addFoodRefineRecipe(foodInput, ItemFoodTFC.createTag(new ItemStack(foodOutput, 1, 0)));
	}
	
	public static void addFoodRefineRecipe(Block foodInput, ItemStack foodOutput){
		if(foodInput != null && foodOutput != null) {
			GameRegistry.addRecipe(new ShapelessOreRecipe(foodOutput, new ItemStack(foodInput, 1, 0), "itemKnife"));
		}
		else {
			AthsLogger.error("Failed to add food refine recipe for " + 
				(foodInput == null ? "null" : foodInput.getUnlocalizedName()) + " to " +
				(foodOutput == null ? "null" : foodOutput.getUnlocalizedName())); 
		}
	}
}
