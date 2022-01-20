package com.facetorched.tfcaths.blocks;

import java.util.ArrayList;
import java.util.Random;

import com.dunk.tfc.ItemSetup;
import com.dunk.tfc.api.TFCItems;
import com.facetorched.tfcaths.util.AthsParser;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.oredict.OreDictionary;

public class BlockPlantStraw extends BlockPlant implements IShearable{
	public BlockPlantStraw() {
		super();
		setGrassBounds();
	}
	
	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
		//super.harvestBlock(world, player, x, y, z, meta);

		ItemStack is = player.inventory.getCurrentItem();
		if (is != null && is.getItem() == TFCItems.stoneFlake){
			dropItemStacks(world, x, y, z, new ItemStack(ItemSetup.straw), 1, getBaseMeta(meta) + 2, new Random());
			if (world.rand.nextInt(4) == 0){
				is.stackSize--;
			}
			return;
		}
		int[] equipIDs = OreDictionary.getOreIDs(is);
		for (int id : equipIDs)
		{
			String name = OreDictionary.getOreName(id);
			if (name.startsWith("itemShovel")) {
				dropBlockAsItem(world, x, y, z, new ItemStack(this, 1, meta));
				AthsParser.damageItem(player, is);
				break;
			}
			else if (name.startsWith("itemKnife")){
				dropItemStacks(world, x, y, z, new ItemStack(ItemSetup.straw), 1, getBaseMeta(meta) + 2, new Random());
				AthsParser.damageItem(player, is);
				break;
			}
			else if (name.startsWith("itemScythe"))
			{
				//Spawn the straw for the block that we've already destroyed
				dropItemStacks(world, x, y, z, new ItemStack(ItemSetup.straw), 1, getBaseMeta(meta) + 2, new Random());
				//Now check each block around the destroyed block for AOE directions
				for (int r = -1; r < 2; r++)
				{
					for (int c = -1; c < 2; c++)
					{
						if (world.getBlock(r + x, y, c + z) == this)
						{
							dropItemStacks(world, r + x, y, c + z, new ItemStack(ItemSetup.straw), 1, getBaseMeta(meta) + 2, new Random());
							AthsParser.damageItem(player, is);
							world.setBlockToAir(r + x, y, c + z);
						}
					}
				}
				break;
			}
		}
	}
	@Override
	protected void checkAndDropBlock(World world, int x, int y, int z){
		if (!this.canBlockStay(world, x, y, z)){
			world.setBlock(x, y, z, Blocks.air, 0, 2);
		}
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
		return ret;
	}
}
