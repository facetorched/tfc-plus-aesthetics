package com.facetorched.tfcaths.items.itemblocks;

import java.util.ArrayList;

import com.dunk.tfc.BlockSetup;
import com.dunk.tfc.api.Interfaces.ITreeBlock;
import com.facetorched.tfcaths.WorldGen.Generators.AthsWorldGenPlants;
import com.facetorched.tfcaths.util.Point3D;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class ItemPlantLilyPad extends ItemPlant{

	public ItemPlantLilyPad(Block b) {
		super(b);
		// TODO Auto-generated constructor stub
	}
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer par3EntityPlayer)
	{
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, par3EntityPlayer, true);

		if (movingobjectposition == null)
		{
			return par1ItemStack;
		}
		else
		{
			if (movingobjectposition.typeOfHit == MovingObjectType.BLOCK)
			{
				int i = movingobjectposition.blockX;
				int j = movingobjectposition.blockY;
				int k = movingobjectposition.blockZ;
				Block b = world.getBlock(i, j, k);
				//System.out.println(world.getBlock(i, j, k) == BlockSetup.branch__y_);
				//System.out.println(AthsWorldGenPlants.isCubeOrLiquid(par2World.getBlock(i, j, k), par2World, i, j, k));
				//System.out.println(par2World.getBlock(i, j, k) instanceof ITreeBlock);
				//System.out.println(AthsWorldGenPlants.getTopSolidOrLiquidBlock(world, i, k));
				int[] ids = OreDictionary.getOreIDs(new ItemStack(b.getItem(world, i, j, k), 1, b.getDamageValue(world, i, j, k)));
				System.out.println(ids.length);
				for(int id : ids)
					System.out.println(OreDictionary.getOreName(id));
				
				ArrayList<Point3D> vertPoints = new ArrayList<Point3D>();
				ArrayList<Block> vertBlocks = new ArrayList<Block>();
				
				int x = i;
				int z = k;
				
				int y = AthsWorldGenPlants.getTopSolidOrLiquidBlock(world, x, z);
				
				if(world.getBlock(x, y - 1, z) == BlockSetup.shrub) {
					world.setBlock(x, y - 1, z, Blocks.air);
				}
				
				y--; // first solid cube
				Block block = world.getBlock(x, y, z);
				ArrayList<Point3D> horizAirNeighbors = AthsWorldGenPlants.getHorizAirNeighbors(world, x, y, z);
				while(y >= 0) {
					if(horizAirNeighbors == null || !AthsWorldGenPlants.isSolidOrLiquid(block, world, x, y, z)) { // is surrounded by cubes or isn't solid anymore
						break;
					}
					if(!horizAirNeighbors.isEmpty()) {
						for(Point3D p : horizAirNeighbors) {
							vertPoints.add(p);
							vertBlocks.add(block);
						}
					}
					y--;
					block = world.getBlock(x, y, z);
					horizAirNeighbors = AthsWorldGenPlants.getHorizAirNeighbors(world, x, y, z);
					
				}
				
				for(int g = 0; g < vertBlocks.size(); g++) {
					if(vertBlocks.get(g) instanceof ITreeBlock) {
						Point3D p = vertPoints.get(g);
						world.setBlock(p.x, p.y, p.z, Blocks.glass);
					}
				}
				
				if (!world.canMineBlock(par3EntityPlayer, i, j, k))
					return par1ItemStack;

				if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack))
					return par1ItemStack;

				if (this.field_150939_a.canBlockStay(world, i, j + 1, k)&& world.isAirBlock(i, j + 1, k))
				{
					world.setBlock(i, j + 1, k, this.field_150939_a, this.getDamage(par1ItemStack), 2);
					world.spawnParticle("splash", i, j + 2, k, 0.0D, 0.0D, 0.0D);
					world.playSoundEffect(i + 0.5F, j + 0.5F, k + 0.5F, "random.splash", 0.5F, this.field_150939_a.stepSound.getPitch() * 0.8F);
					if (!par3EntityPlayer.capabilities.isCreativeMode)
						--par1ItemStack.stackSize;
				}
			}
			return par1ItemStack;
		}
	}
	
}
