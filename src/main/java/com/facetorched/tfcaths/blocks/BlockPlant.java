package com.facetorched.tfcaths.blocks;

import java.util.List;
import java.util.Random;

import com.dunk.tfc.Blocks.BlockTerra;
import com.dunk.tfc.Core.TFCTabs;
import com.dunk.tfc.Core.TFC_Climate;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Core.TFC_Time;
import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.AthsGlobal;
import com.facetorched.tfcaths.AthsMod;
import com.facetorched.tfcaths.WorldGen.Generators.AthsWorldGenPlants;
import com.facetorched.tfcaths.WorldGen.Generators.PlantSpawnData;
import com.facetorched.tfcaths.enums.EnumVary;
import com.facetorched.tfcaths.util.AthsMath;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class BlockPlant extends BlockTerra{
	public String[] plantNames;
	public String plantKey;
	public float scale;
	public int numBaseMetas;
	public Integer[] varyStartIndexes;
	public boolean hasVarys;
	public EnumVary[] monthVarys;

	@SideOnly(Side.CLIENT)
	protected IIcon[] icons;
	
	public BlockPlant()
	{
		super(Material.plants);
		this.setTickRandomly(true);
		float var4 = 0.2F;
		this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);
		this.setCreativeTab(TFCTabs.TFC_DECORATION);
		this.scale = 1.0F; //default
		this.varyStartIndexes = new Integer[EnumVary.values().length]; // will default to false for all
		this.setHardness(0.0F);
		this.setStepSound(Block.soundTypeGrass);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ)  
	{
		/*
		if(TFCOptions.enableDebugMode && world.isRemote){
			Block b = world.getBlock(x, y-1, z);
			if(b!=null) {
				int[] ids = OreDictionary.getOreIDs(new ItemStack(b.getItem(world, x, y, z), 1, this.getDamageValue(world, x, y, z)));
				System.out.println(ids.length);
				for(int id : ids)
					System.out.println(OreDictionary.getOreName(id));
			}
		}
		if(world.isRemote) {
		}
		*/
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
	
	// when generated
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		//super.onBlockAdded(world, x, y, z);
		this.updateTick(world, x, y, z, null);
	}
	
	@Override
    public boolean canDropFromExplosion(Explosion e){
        return false;
    }

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		this.checkAndDropBlock(world, x, y, z);
	}

	protected void checkAndDropBlock(World world, int x, int y, int z){
		if (!this.canBlockStay(world, x, y, z)){
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlock(x, y, z, Blocks.air, 0, 2);
		}
	}
	
	/*
	public int getSafeItemMeta(int blockMeta) {
		int itemMeta = getItemMeta(blockMeta);
		return this.plantNames == null || itemMeta >= this.plantNames.length ? 0 : itemMeta;
	}
	
	public static int getItemMeta(int blockMeta) {
		return blockMeta & AthsGlobal.ITEM_META_BITMASK; // first 8 bits store item meta
	}
	public static int getBlockFlags(int blockMeta) {
		return blockMeta ^ getItemMeta(blockMeta);
	}
	public static boolean hasSnowLayer(int meta) {
		return AthsMath.isKthBit(meta, AthsGlobal.ITEM_META_BITS);
	}
	public static boolean hasSnowLayerAt(World world, int x, int y, int z) {
		return hasSnowLayer(world.getBlockMetadata(x, y, z));
	}
	public static int toggleSnowLayer(int meta) {
		return AthsMath.toggleKthBit(meta, AthsGlobal.ITEM_META_BITS);
	}
	public static void toggleSnowLayerAt(World world, int x, int y, int z) {
		System.out.println(world.setBlockMetadataWithNotify(x, y, z, toggleSnowLayer(world.getBlockMetadata(x, y, z)), 2));
	}
	*/
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand){
		updateVary(world, x, y, z);
		checkAndDropBlock(world, x, y, z);
	}
	
	/*
	public void updateSnowLayer(World world, int x, int y, int z) {
		float temp = TFC_Climate.getHeightAdjustedTemp(world, x, y, z);
		boolean inRain =  TFC_Core.isExposedToRain(world, x, y, z);
		boolean hasSnow = hasSnowLayerAt(world, x, y, z);
		//System.out.println(hasSnow);
		//System.out.println(inRain);
		if (temp <= 0 && inRain && !hasSnow) {
			toggleSnowLayerAt(world, x, y, z);
			//System.out.println(hasSnowLayerAt(world, x, y, z));
		}
		else if (temp > 0 && hasSnow) {
			toggleSnowLayerAt(world, x, y, z);
		}
	}
	*/
	
	public void updateVary(World world, int x, int y, int z) {
		if (this.hasVarys) {
			int blockMeta = world.getBlockMetadata(x, y, z);
			int month = TFC_Time.getSeasonAdjustedMonth(z);
			//float temp = TFC_Climate.getHeightAdjustedBioTemp(world, TFC_Time.getTotalDays(), x, y, z);
			float temp = TFC_Climate.getHeightAdjustedTemp(world, x, y, z);
			// WARNING the order of the following statements DOES matter. I wish it could be otherwise,
			// but some meta variations should be higher priority than others
			
			// snow
			if(hasVary(EnumVary.SNOW)) {
				boolean inRain =  TFC_Core.isExposedToRain(world, x, y, z);
				if(inRain && temp <= 0) {
					shiftToVary(world, x, y, z, blockMeta, EnumVary.SNOW);
				}
			}
			// flowers fruits etc based on month!
			if (monthVarys != null) {
				EnumVary monthVary =  monthVarys[month];
				if(monthVary != null && hasVary(monthVary)) {
					shiftToVary(world, x, y, z, blockMeta, monthVary);
					return; // success
				}
			}
			// default
			if (month < TFC_Time.OCTOBER && temp >= 0) {
				shiftToVary(world, x, y, z, blockMeta, EnumVary.DEFAULT);
				return; // success
			}
			
			// winter
			if(hasVary(EnumVary.WINTER)) {
				if ((month >= TFC_Time.OCTOBER && temp <= 0f) ||
					(month >= TFC_Time.NOVEMBER && temp <= 10f)) {
					shiftToVary(world, x, y, z, blockMeta, EnumVary.WINTER);
					return; // success
				}
			}
			// autumn
			if(hasVary(EnumVary.AUTUMN)) {
				if (month >= TFC_Time.OCTOBER && temp <= 10) {
					shiftToVary(world, x, y, z, blockMeta, EnumVary.AUTUMN);
					return; // success
				}
			}
		}
	}
	
	public void shiftToVary(World world, int x, int y, int z, int meta, EnumVary vary) {
		int newMeta = varyStartIndexes[vary.index] + (meta % numBaseMetas);
		if(meta != newMeta) {
			world.setBlockMetadataWithNotify(x, y, z, newMeta, 2);
		}
	}
	
	public boolean shiftMetaBy(World world, int x, int y, int z, int meta, int delta) {
		int testMeta = (meta % numBaseMetas) + delta;
		if (testMeta >= 0 && testMeta < numBaseMetas) {
			return world.setBlockMetadataWithNotify(x, y, z, meta + delta, 2);
		}
		return false;
	}
	
	public BlockPlant addVarys(EnumVary[] varys) {
		for(EnumVary vary : varys) {
			addVary(vary);
		}
		return this;
	}
	
	public BlockPlant addVary(EnumVary vary) {
		if(this.plantNames == null || numBaseMetas == 0) {
			throw new IllegalStateException("cannot add varieties before names!");
		}
		int numPlantNames = 0;
		for(int i = 0; i < varyStartIndexes.length; i ++) {
			if (varyStartIndexes[i] != null) {
				varyStartIndexes[i] = numPlantNames; // overwrite!
				numPlantNames += numBaseMetas;
			}
			else if(EnumVary.getEnum(i) == vary) {
				varyStartIndexes[i] = numPlantNames;
				numPlantNames += numBaseMetas;
			}
		}
		String[] tempPlantNames = new String[numPlantNames];
		
		for(int i = 0; i < varyStartIndexes.length; i ++) {
			if(varyStartIndexes[i] != null) {
				for(int j = 0; j < numBaseMetas; j++) {
					tempPlantNames[varyStartIndexes[i] + j] = plantNames[j] + EnumVary.getEnum(i).suffix;
				}
			}
		}
		this.plantNames = tempPlantNames;
		this.hasVarys = true;
		return this;
	}
	
	public boolean isVary(int meta, EnumVary vary) {
		if(hasVary(vary)) {
			int startIndex = varyStartIndexes[vary.index];
			return meta >= startIndex && meta < startIndex + numBaseMetas;
		}
		return false;
	}
	
	public boolean hasVary(EnumVary vary) {
		return varyStartIndexes[vary.index] != null;
	}
	
	public BlockPlant setNames(String name) {
		setNames(new String[] {name, name + "_Small", name + "_Large"});
		setKeyName(name);
		return this;
	}
	public BlockPlant setName(String name) {
		setNames(new String[] {name});
		setKeyName(name);
		return this;
	}
	public BlockPlant setKeyName(String name) {
		this.plantKey = name;
		this.setBlockName(name);
		return this;
	}
	public BlockPlant setNames(String[] names) {
		this.plantNames = names;
		this.varyStartIndexes[0] = 0;
		this.numBaseMetas = names.length;
		return this;
	}
	public void setPlantKey(String plantKey) {
		this.plantKey = plantKey;
	}
	public String getPlantKey() {
		return plantKey;
	}
	public float getScale() {
		return scale;
	}
	public BlockPlant setScale(float scale) {
		this.scale = scale;
		return this;
	}
	
	public BlockPlant setMonthVary(int month, EnumVary vary) {
		if (!hasVary(vary)) {
			throw new IllegalStateException("plant does not have variation: " + vary.toString());
		}
		if(monthVarys == null) {
			monthVarys = new EnumVary[TFC_Time.MONTHS.length];
		}
		monthVarys[month] = vary;
		return this;
	}
	public BlockPlant setMonthVarys(EnumVary[] varys) {
		for(int month = 0; month < varys.length; month++) {
			setMonthVary(month, varys[month]);
		}
		return this;
	}
	public BlockPlant setFlowerMonthRange(int startMonth, int endMonth) {
		for(int month = startMonth; month <= endMonth; month++) {
			setMonthVary(month, EnumVary.FLOWER);
		}
		return this;
	}
	public BlockPlant setFlowerMonth(int month) {
		setMonthVary(month, EnumVary.FLOWER);
		return this;
	}
	
	public boolean dropItemStacks(World world, int x, int y, int z, ItemStack is, int min, int max, Random random) {
		int numDrops = min + random.nextInt(max-min);
		is.stackSize = numDrops;
		dropBlockAsItem(world, x, y, z, is);
		return numDrops > 0;
	}
}
