package com.facetorched.tfcaths.blocks;

import java.util.List;
import java.util.Random;

import com.dunk.tfc.TerraFirmaCraft;
import com.dunk.tfc.Blocks.BlockTerra;
import com.dunk.tfc.Core.TFCTabs;
import com.dunk.tfc.Core.TFC_Climate;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Core.TFC_Time;
import com.dunk.tfc.Entities.Mobs.EntityWolfTFC;
import com.dunk.tfc.api.TFCItems;
import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.AthsMod;
import com.facetorched.tfcaths.WorldGen.Generators.AthsWorldGenPlants;
import com.facetorched.tfcaths.WorldGen.Generators.PlantSpawnData;
import com.facetorched.tfcaths.enums.EnumVary;
import com.facetorched.tfcaths.items.itemblocks.ItemPlant;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class BlockPlant extends BlockTerra{
	public String[] plantNames;
	public String plantKey;
	public float scale;
	public int numBaseMetas;
	public Integer[] varyStartIndexes;
	public boolean hasVarys;
	public EnumVary[] monthVarys; // for flowers and fruits
	public EnumVary[] iconVarys; // varys that have unique icons
	public Class<? extends ItemBlock> itemBlock;
	public boolean isFoliageColor;

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
		this.setItemBlock(ItemPlant.class);
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
	public void registerBlockIcons(IIconRegister register){
		this.icons = new IIcon[plantNames.length];
		for (int i = 0; i < this.icons.length; ++i){
			if(iconVarys == null) // normal
				this.icons[i] = register.registerIcon(AthsMod.MODID+":plants/"+plantNames[i]);
			else { // icon diversity is related to specific varys
				if(isAnyVary(i, iconVarys)) {
					this.icons[i] = register.registerIcon(AthsMod.MODID+":plants/"+plantNames[i]);
				}
				else {
					this.icons[i] = register.registerIcon(AthsMod.MODID+":plants/"+plantKey + getVary(i).suffix);
				}
			}
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
		return data.canGrowOnBlock(block);
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
	
	// when placed or generated
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		//this.updateTick(world, x, y, z, null);
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
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand){
		updateVary(world, x, y, z, rand);
		checkAndDropBlock(world, x, y, z);
	}
	
	public void updateVary(World world, int x, int y, int z, Random random) {
		if (this.hasVarys) {
			int meta = world.getBlockMetadata(x, y, z);
			int month = TFC_Time.getSeasonAdjustedMonth(z);
			//float temp = TFC_Climate.getHeightAdjustedBioTemp(world, TFC_Time.getTotalDays(), x, y, z);
			float temp = TFC_Climate.getHeightAdjustedTemp(world, x, y, z);
			// WARNING the order of the following statements DOES matter. I wish it could be otherwise,
			// but some meta variations should be higher priority than others
			
			// snow
			if(hasVary(EnumVary.SNOW)) {
				boolean inRain =  TFC_Core.isExposedToRain(world, x, y, z);
				if(inRain && temp <= 0) {
					shiftToVary(world, x, y, z, meta, EnumVary.SNOW);
					return;
				}
				else if(isVary(meta, EnumVary.SNOW) && temp<0) { // snow persists
					return;
				}
			}
			
			// winter
			if(hasVary(EnumVary.WINTER)) {
				if ((month >= TFC_Time.OCTOBER && temp <= 0f) ||
					(month >= TFC_Time.NOVEMBER && temp <= 10f)) {
					shiftToVary(world, x, y, z, meta, EnumVary.WINTER);
					return; // success
				}
			}
			// autumn
			if(hasVary(EnumVary.AUTUMN)) {
				if (month >= TFC_Time.OCTOBER && temp <= 10 ) {//&& random.nextInt(10) == 0) {
					shiftToVary(world, x, y, z, meta, EnumVary.AUTUMN);
					return; // success
				}
			}
			
			// flowers fruits etc based on month!
			if (monthVarys != null) {
				EnumVary monthVary =  monthVarys[month];
				if(monthVary != null && hasVary(monthVary)) {
					shiftToVary(world, x, y, z, meta, monthVary);
					return; // success
				}
			}
			
			// default
			if (month < TFC_Time.OCTOBER && temp > 0) {
				shiftToVary(world, x, y, z, meta, EnumVary.DEFAULT);
				return; // success
			}
			shiftToVary(world, x, y, z, meta, EnumVary.DEFAULT);
		}
	}
	
	public void shiftToVary(World world, int x, int y, int z, int meta, EnumVary vary) {
		int newMeta = varyStartIndexes[vary.index] + getBaseMeta(meta);
		if(meta != newMeta) {
			world.setBlockMetadataWithNotify(x, y, z, newMeta, 2);
		}
	}
	
	// useful for growing or trimming plants
	public boolean shiftMeta(World world, int x, int y, int z, int meta, int delta) {
		int testMeta = getBaseMeta(meta) + delta;
		if (testMeta >= 0 && testMeta < numBaseMetas) {
			return world.setBlockMetadataWithNotify(x, y, z, meta + delta, 2);
		}
		return false;
	}
	public EnumVary[] getVarys() {
		EnumVary[] ret = new EnumVary[getNumVarys()];
		int c = 0;
		for(int i = 0; i < EnumVary.values().length; i++) {
			if(hasVary(EnumVary.values()[i])) {
				ret[c] = EnumVary.values()[i];
				c++;
			}
		}
		return ret;
	}
	public EnumVary getVary(int meta) {
		return getVarys()[meta / numBaseMetas];
	}
	public int getNumVarys() {
		return plantNames.length/numBaseMetas;
	}
	public int getBaseMeta(int meta) {
		return meta % numBaseMetas;
	}
	public BlockPlant addVarys(EnumVary[] varys) {
		for(EnumVary vary : varys) {
			addVary(vary);
		}
		return this;
	}
	
	public BlockPlant setIsFoliageColor() {
		this.isFoliageColor = true;
		return this;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		if(isFoliageColor) {
			int meta = world.getBlockMetadata(x, y, z);
			if(this.isVary(meta, EnumVary.SNOW) || this.isVary(meta, EnumVary.WINTER)){
				return super.colorMultiplier(world, x, y, z);
			}
			return TerraFirmaCraft.proxy.grassColorMultiplier(world, x, y, z);
		}
		return super.colorMultiplier(world, x, y, z);
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
	public boolean isAnyVary(int meta, EnumVary[] varys) {
		for(EnumVary vary : varys) {
			if(isVary(meta, vary)) {
				return true;
			}
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
	public BlockPlant setNames(String name, String suffix) {
		setNames(new String[] {name, name + "_" + suffix});
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
	public BlockPlant setNames(String name, String[] suffixes) {
		String[] names = new String[suffixes.length];
		for(int i = 0; i < suffixes.length; i++) {
			names[i] = name + "_" + suffixes[i];
		}
		setNames(names);
		setKeyName(name);
		return this;
	}
	public BlockPlant setIconVarys(EnumVary[] varys) {
		iconVarys = varys;
		return this;
	}
	public BlockPlant addIconVary(EnumVary vary) {
		if(iconVarys == null) {
			setIconVarys(new EnumVary[] {vary});
			return this;
		}
		EnumVary[] tempIconVarys = new EnumVary[iconVarys.length + 1];
		for(int i = 0; i < iconVarys.length; i++) {
			tempIconVarys[i] = iconVarys[i];
		}
		tempIconVarys[iconVarys.length] = vary;
		setIconVarys(tempIconVarys);
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
	public BlockPlant setMonthVaryRange(int startMonth, int endMonth, EnumVary vary) {
		for(int month = startMonth; month != endMonth; month = (month + 1) % 12) {
			setMonthVary(month, vary);
		}
		setMonthVary(endMonth, vary); // I guess this should be inclusive :/
		return this;
	}
	public BlockPlant setFlowerMonthRange(int startMonth, int endMonth) {
		return setMonthVaryRange(startMonth, endMonth, EnumVary.FLOWER);
	}
	public BlockPlant setMonthVarys(EnumVary[] varys) {
		for(int month = 0; month < varys.length; month++) {
			setMonthVary(month, varys[month]);
		}
		return this;
	}
	public BlockPlant setFlowerMonth(int month) {
		setMonthVary(month, EnumVary.FLOWER);
		return this;
	}
	public BlockPlant setItemBlock(Class<? extends ItemBlock> itemBlock) {
		this.itemBlock = itemBlock;
		return this;
	}
	public Class<? extends ItemBlock> getItemBlock(){
		return this.itemBlock;
	}
	
	public boolean dropItemStacks(World world, int x, int y, int z, ItemStack is, int min, int max, Random random) {
		int numDrops = min + random.nextInt(max-min);
		is.stackSize = numDrops;
		dropBlockAsItem(world, x, y, z, is);
		return numDrops > 0;
	}
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (isVary(world.getBlockMetadata(x, y, z), EnumVary.SNOW)) {
			if (entity instanceof EntityPlayer){
				ItemStack bootsI = ((EntityPlayer) entity).getCurrentArmor(0);
				if (bootsI != null && (bootsI.getItem() == TFCItems.wolfFurBoots || bootsI.getItem() == TFCItems.bearFurBoots || bootsI.getItem() == TFCItems.furBoots)){
					return;
				}
			}
			double speed = 0.98;
			speed = Math.max(speed, 0);
			if (!(entity instanceof EntityWolfTFC)){
				entity.motionX *= speed;
				entity.motionZ *= speed;
			}
		}
	}
}
