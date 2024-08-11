package com.facetorched.tfcaths.blocks;

import java.util.ArrayList;
import java.util.Random;

import com.dunk.tfc.ItemSetup;
import com.dunk.tfc.Blocks.Vanilla.BlockCustomTallGrass;
import com.dunk.tfc.api.TFCItems;
import com.facetorched.tfcaths.util.AthsParser;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.oredict.OreDictionary;

public class BlockPlantStraw extends BlockPlant implements IShearable{
	public BlockPlantStraw() {
		super(Material.vine);
		setGrassBounds();
		setHasNoDrops();
	}
	
	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
		if(world.isRemote) {
			super.harvestBlock(world, player, x, y, z, meta);
			return;
		}
		ItemStack is = player.inventory.getCurrentItem();
		if (is != null && is.getItem() == TFCItems.stoneFlake){
			dropItemStacks(world, x, y, z, new ItemStack(ItemSetup.straw), 1, getMaxStraw(meta), new Random());
			if (world.rand.nextInt(4) == 0){
				is.stackSize--;
			}
			return;
		}
		int[] equipIDs = OreDictionary.getOreIDs(is);
		for (int id : equipIDs){
			String name = OreDictionary.getOreName(id);
			if (name.startsWith("itemKnife")){
				dropItemStacks(world, x, y, z, new ItemStack(ItemSetup.straw), 1, getMaxStraw(meta), new Random());
				AthsParser.damageItem(player, is);
				return;
			}
			else if (name.startsWith("itemScythe"))
			{
				//Spawn the straw for the block that we've already destroyed
				dropItemStacks(world, x, y, z, new ItemStack(ItemSetup.straw), 1, getMaxStraw(meta), new Random());
				//Now check each block around the destroyed block for AOE directions
				for (int r = -1; r < 2; r++){
					for (int c = -1; c < 2; c++){
						if (world.getBlock(r + x, y, c + z) instanceof BlockPlantStraw){
							BlockPlantStraw b = (BlockPlantStraw)world.getBlock(r + x, y, c + z);
							int bMeta = world.getBlockMetadata(r + x, y, c + z);
							dropItemStacks(world, r + x, y, c + z, new ItemStack(ItemSetup.straw), 1, b.getMaxStraw(bMeta), new Random());
							AthsParser.damageItem(player, is);
							world.setBlockToAir(r + x, y, c + z);
						}
						else if(world.getBlock(r + x, y, c + z) instanceof BlockCustomTallGrass) {
							dropItemStacks(world, r + x, y, c + z, new ItemStack(ItemSetup.straw), 1, 1, null);
							AthsParser.damageItem(player, is);
							world.setBlockToAir(r + x, y, c + z);
						}
					}
				}
				return;
			}
		}
		super.harvestBlock(world, player, x, y, z, meta);
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z){
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune){
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
		return ret;
	}
	
	public int getMaxStraw(int meta) {
		int maxStraw; // exclusive upper bound
		if(numBaseMetas == 3) {
			int m = getBaseMeta(meta);
			if(m == 0) {
				maxStraw = 3;
			}
			else if (m == 1) {
				maxStraw = 2;
			}
			else {
				maxStraw = 4;
			}
		}
		else {
			maxStraw = (int)this.scale + 1;
		}
		return maxStraw;
	}
}
