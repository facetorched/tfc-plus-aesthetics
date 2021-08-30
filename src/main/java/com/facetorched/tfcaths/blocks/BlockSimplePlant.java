package com.facetorched.tfcaths.blocks;

import java.util.List;
import java.util.Random;

import com.dunk.tfc.Reference;
import com.dunk.tfc.Blocks.BlockTerra;
import com.dunk.tfc.Core.TFCTabs;
import com.dunk.tfc.Core.TFC_Climate;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Render.Blocks.RenderFlora;
import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.AthsMod;
import com.facetorched.tfcaths.WorldGen.Generators.AthsWorldGenPlants;
import com.facetorched.tfcaths.WorldGen.Generators.PlantSpawnData;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSimplePlant extends BlockTerra{
	public String[] plantNames;
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons;
	public String plantKey;
	
	public BlockSimplePlant(String plantName)
	{
		super(Material.plants);
		this.setTickRandomly(true);
		float var4 = 0.2F;
		this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);
		this.setCreativeTab(TFCTabs.TFC_DECORATION);
		plantNames = new String[]{plantName};
		plantKey = plantName;
	}
	public BlockSimplePlant(String[] plantNames, String plantKey)
	{
		super(Material.plants);
		this.setTickRandomly(true);
		float var4 = 0.2F;
		this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);
		this.setCreativeTab(TFCTabs.TFC_DECORATION);
		this.plantNames = plantNames;
		this.plantKey = plantKey;
	}

	public boolean canGrowConditions(World world, int x, int y, int z, int plantMeta)
	{
		PlantSpawnData data = AthsWorldGenPlants.plantList.get(this.plantKey);
		if( data == null) {
			return false;
		}
		float evt = TFC_Climate.getCacheManager(world).getEVTLayerAt(x, z).floatdata1;
		float rain = TFC_Climate.getRainfall(world, x, 144, z);
		float bioTemp =TFC_Climate.getBioTemperatureHeight(world, x, y, z);
		
		return bioTemp >= data.minTemp && bioTemp <= data.maxTemp && 
				rain >= data.minRainfall && rain <= data.maxRainfall && 
				evt >= data.minEVT && evt <= data.maxEVT;
	}

	@SideOnly(Side.CLIENT)
	@Override
	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	 */
	public void getSubBlocks(Item item, CreativeTabs tabs, List list)
	{
		// Change to false if this block should not be added to the creative tab
		Boolean addToCreative = true;

		if(addToCreative)
		{
			for(int i = 0; i < plantNames.length; i++)
				list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (meta >= this.icons.length)
			meta = 0;
		return this.icons[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		this.icons = new IIcon[plantNames.length];

		for (int i = 0; i < this.icons.length; ++i)
		{
			this.icons[i] = register.registerIcon(AthsMod.MODID+":plants/"+plantNames[i]);
		}
	}

	@Override
	public int damageDropped(int dmg)
	{
		return dmg;
	}

	/**
	 * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
	 */
	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return /*(world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) && */this.canThisPlantGrowOnThisBlock(world.getBlock(x, y - 1, z));
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);
		return (world.isAirBlock(x, y, z) || block.getMaterial().isReplaceable()) && this.canThisPlantGrowOnThisBlock(world.getBlock(x, y - 1, z));
	}

	protected boolean canThisPlantGrowOnThisBlock(Block block)
	{
		//TODO change this to use defined blocks
		PlantSpawnData data = AthsWorldGenPlants.plantList.get(this.plantKey);
		if( data == null) {
			return false;
		}
		for(int i = 0; i < data.canGrowOn.length; i++) {
			if(data.canGrowOn[i] == block) {
				return true;
			}
		}
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public int getRenderType()
	{
		return AthsBlockSetup.simplePlantRenderID;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		this.checkAndDropBlock(world, x, y, z);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		this.checkAndDropBlock(world, x, y, z);
	}

	protected void checkAndDropBlock(World world, int x, int y, int z)
	{
		if (!this.canBlockStay(world, x, y, z))
		{
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlock(x, y, z, getBlockById(0), 0, 2);
		}
	}
}
