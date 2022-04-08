package com.facetorched.tfcaths.handlers;

import java.util.List;

import com.dunk.tfc.Food.ItemFoodTFC;
import com.dunk.tfc.Handlers.FoodCraftingHandler;
import com.facetorched.tfcaths.items.itemblocks.ItemPlant;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class AthsFoodCraftingHandler {
	@SubscribeEvent
	public void onFoodCrafting(ItemCraftedEvent e){

		ItemStack craftResult = e.crafting;
		IInventory craftingInv = e.craftMatrix;

		if (craftingInv != null){
			if (craftResult.getItem() instanceof ItemFoodTFC && gridHasPlant(craftingInv)){
				List<ItemStack> knives = OreDictionary.getOres("itemKnife", false);
				FoodCraftingHandler.handleItem(e.player, craftingInv, knives);
			}
		}
	}
	
	public static boolean gridHasPlant(IInventory iinventory){
		for (int i = 0; i < iinventory.getSizeInventory(); i++){
			if (iinventory.getStackInSlot(i) == null)
				continue;
			if (iinventory.getStackInSlot(i).getItem() instanceof ItemPlant)
				return true;
		}
		return false;
	}
}
