package com.facetorched.tfcaths.blocks;

import java.util.Random;

import com.facetorched.tfcaths.AthsGlobal;
import com.facetorched.tfcaths.items.itemblocks.ItemPlantAlgae;
import com.facetorched.tfcaths.util.AthsParser;
import com.facetorched.tfcaths.util.AthsRandom;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPlantAlgae extends BlockPlantLilyPad{
	public float redMult;
	public int redShift;
	public float greenMult;
	public int greenShift;
	public float blueMult;
	public int blueShift;
	
	public BlockPlantAlgae() {
		this(Material.vine);
	}
	public BlockPlantAlgae(Material m) {
		super(m);
		setItemBlock(ItemPlantAlgae.class);
	}
	
	@Override
	public int colorMultiplier(IBlockAccess bAccess, int x, int y, int z)
	{
		Random random = AthsRandom.getRandom(x, z);
		int rgb = 2 * random.nextInt(256);
		
		// compute average of deterministic colors of surrounding algae color (poor man's convolution)
		random = AthsRandom.getRandom(x+1, z);
		rgb += random.nextInt(256);
		
		random = AthsRandom.getRandom(x-1, z);
		rgb += random.nextInt(256);
		
		random = AthsRandom.getRandom(x, z+1);
		rgb += random.nextInt(256);
		
		random = AthsRandom.getRandom(x, z-1);
		rgb += random.nextInt(256);
		rgb /= 6;
		
		// green algae has red mult of .6667 and shift of 80, and green mult of .5 and shift of 128 setColorRange(.6667f, 80, .5f, 128, 0f, 0)
		// red algae has red mult of .5 and shift of 60, and green mult of .3 and blue mult of .1 setColorRange(.5f, 60, .3f, 0, .1f, 0)
		// cyanobacteria has green mult of .5 green shift of 70, blue mult of .1, blueshift of 40 setColorRange(0f, 0, .5f, 70, .1f, 40)
		
		return  Math.min((int)(rgb * redMult) + redShift, 255) << 16 | 
				Math.min((int)(rgb * greenMult) + greenShift, 255) << 8 | 
				Math.min((int)(rgb * blueMult) + blueShift, 255);
	}
	
	@Override
	public BlockPlant setExtraNames(String name) {
		setNames(new String[] {AthsGlobal.ALGAE_MAT, AthsGlobal.ALGAE_MAT + "_Thick", AthsGlobal.ALGAE_MAT + "_Sparse"});
		setKeyName(name);
		return this;
	}
	
	public BlockPlantAlgae setColorRange(float redMult, int redShift, float greenMult, int greenShift, float blueMult, int blueShift) {
		this.redMult = redMult;
		this.redShift = redShift;
		this.greenMult = greenMult;
		this.greenShift = greenShift;
		this.blueMult = blueMult;
		this.blueShift = blueShift;
		return this; 
	}
	
	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
		if(AthsParser.isHolding(world, player, "itemShovel"))
			dropBlockAsItem(world, x, y, z, new ItemStack(this, 1, meta));
	}
	@Override
	protected void checkAndDropBlock(World world, int x, int y, int z){
		if (!this.canBlockStay(world, x, y, z)){
			world.setBlock(x, y, z, Blocks.air, 0, 2);
		}
	}
}