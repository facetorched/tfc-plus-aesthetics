package com.facetorched.tfcaths.blocks;

import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.mutable.MutableInt;

import com.dunk.tfc.Blocks.BlockTerra;
import com.dunk.tfc.Core.TFCTabs;
import com.dunk.tfc.Core.TFC_Climate;
import com.dunk.tfc.Core.TFC_Time;
import com.dunk.tfc.api.TFCOptions;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class BlockPlant extends BlockTerra{
	public String[] plantNames;
	public String plantKey;
	public float scale;
	public int[] monthMetas;

	@SideOnly(Side.CLIENT)
	protected IIcon[] icons;
	
	public BlockPlant()
	{
		super(Material.plants);
		this.setTickRandomly(true);
		float var4 = 0.2F;
		this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);
		this.setCreativeTab(TFCTabs.TFC_DECORATION);
		this.setBlockName(plantKey);
		this.scale = 1.0F; //default
		this.monthMetas = null;
		this.setHardness(0.0F);
		this.setStepSound(Block.soundTypeGrass);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ)  
	{
		if(TFCOptions.enableDebugMode && world.isRemote){
			Block b = world.getBlock(x, y-1, z);
			if(b!=null) {
				int[] ids = OreDictionary.getOreIDs(new ItemStack(b.getItem(world, x, y, z), 1, this.getDamageValue(world, x, y, z)));
				System.out.println(ids.length);
				for(int id : ids)
					System.out.println(OreDictionary.getOreName(id));
			}
		}
		
		System.out.println(this.shouldGenerateAt(world, x, y, z));
		
		return super.onBlockActivated(world, x, y, z, entityplayer, side, hitX, hitY, hitZ);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		return this.canThisPlantGrowOnThisBlock(world.getBlock(x, y - 1, z));
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);
		return (world.isAirBlock(x, y, z) || block.getMaterial().isReplaceable()) && this.canThisPlantGrowOnThisBlock(world.getBlock(x, y - 1, z));
	}

	protected boolean canThisPlantGrowOnThisBlock(Block block)
	{
		PlantSpawnData data = AthsWorldGenPlants.plantList.get(this.plantKey);
		if( data == null) {
			return false;
		}
		if(data.canGrowOn.contains(block)) {
			return true;
		}
		int[] ids = OreDictionary.getOreIDs(new ItemStack(Item.getItemFromBlock(block), 1, 0)); //forced to do damage 0
		for(int id : ids)
			if(data.canGrowOnOreDict.contains(OreDictionary.getOreName(id)))
				return true;
		return false;
	}
	
	// Besides habitat constraints, are there any other constraints to the generation of this block?
	public boolean shouldGenerateAt(World world, int x, int y, int z) {
		return true;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public int getRenderType()
	{
		return AthsBlockSetup.plantCrossRenderID;
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
		if(this.monthMetas != null) {
			int month = TFC_Time.getSeasonAdjustedMonth(z);
			world.setBlockMetadataWithNotify(x, y, z, monthMetas[month], 2);
		}
		this.checkAndDropBlock(world, x, y, z);
	}
	
	// when placed
	@Override
	public void onPostBlockPlaced(World world, int x, int y, int z, int meta) {
		//super.onPostBlockPlaced(world, x, y, z, meta);
		if(this.monthMetas != null) {
			int month = TFC_Time.getSeasonAdjustedMonth(z);
			world.setBlockMetadataWithNotify(x, y, z, monthMetas[month], 2);
		}
	}
	
	// when generated
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		//super.onBlockAdded(world, x, y, z);
		if(this.monthMetas != null) {
			int month = TFC_Time.getSeasonAdjustedMonth(z);
			world.setBlockMetadataWithNotify(x, y, z, monthMetas[month], 2);
		}
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
	public float getScale() {
		return scale;
	}
	public BlockPlant setScale(float scale) {
		this.scale = scale;
		return this;
	}
	public String getPlantKey() {
		return plantKey;
	}
	public BlockPlant setPlantKey(String plantKey) {
		this.plantKey = plantKey;
		return this;
	}
	public int[] getMonthMetas() {
		return monthMetas;
	}
	public BlockPlant setMonthMetas(int[] monthMetas) {
		this.monthMetas = monthMetas;
		return this;
	}
	public BlockPlant setNames(String name) {
		this.plantNames = new String[] {name, name + "_Small", name + "_Large"};
		this.plantKey = name;
		return this;
	}
	public BlockPlant setNames(String[] names) {
		this.plantNames = names;
		return this;
	}
	public BlockPlant setName(String name) {
		this.plantNames = new String[] {name};
		this.plantKey = name;
		return this;
	}
	public BlockPlant setKey(String key) {
		this.plantKey = key;
		return this;
	}
	@Override
	public BlockPlant setLightOpacity(int level) {
		super.setLightOpacity(level);
		return this;
	}
}
