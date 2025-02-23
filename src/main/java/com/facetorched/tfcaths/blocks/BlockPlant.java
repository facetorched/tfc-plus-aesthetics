package com.facetorched.tfcaths.blocks;

import java.util.List;
import java.util.Random;

import com.dunk.tfc.ItemSetup;
import com.dunk.tfc.TerraFirmaCraft;
import com.dunk.tfc.Blocks.BlockTerra;
import com.dunk.tfc.Core.TFCTabs;
import com.dunk.tfc.Core.TFC_Climate;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Core.TFC_Time;
import com.dunk.tfc.Entities.Mobs.EntityWolfTFC;
import com.dunk.tfc.Food.ItemFoodTFC;
import com.dunk.tfc.api.Entities.IAnimal;
import com.dunk.tfc.api.TFCItems;
import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.AthsMod;
import com.facetorched.tfcaths.WorldGen.Generators.AthsWorldGenPlants;
import com.facetorched.tfcaths.WorldGen.Generators.PlantSpawnData;
import com.facetorched.tfcaths.enums.EnumVary;
import com.facetorched.tfcaths.items.itemblocks.ItemPlant;
import com.facetorched.tfcaths.util.AthsParser;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockPlant extends BlockTerra{
	public String[] plantNames;
	public String plantKey;
	public float scale;
	public int numBaseMetas;
	public Integer[] varyStartIndexes;
	public boolean hasVarys;
	public EnumVary[] monthVarys; // for flowers and fruits
	public EnumVary[] iconVarys; // varys that have unique icons (e.g. different colored flowers)
	public boolean[] blacklistMetas; // metas that shouldn't exist
	public Class<? extends ItemBlock> itemBlock;
	public boolean isFoliageColor;
	public boolean hasCollision;
	public boolean hasNoDrops;
	public int renderId;
	public boolean isWaterPlant;
	public boolean isDamaging;
	public int poisonDuration;
	public boolean isFlammable;
	public ItemStack foodItemStack;

	@SideOnly(Side.CLIENT)
	protected IIcon[] icons;
	
	public BlockPlant() {
		this(Material.plants);
	}
	public BlockPlant(Material m){
		super(m);
		this.setTickRandomly(true);
		this.setFlowerBounds();
		this.setCreativeTab(TFCTabs.TFC_DECORATION);
		this.scale = 1.0F; //default
		this.varyStartIndexes = new Integer[EnumVary.values().length]; // will default to false for all
		this.setHardness(0.0F);
		this.setStepSound(Block.soundTypeGrass);
		this.setItemBlock(ItemPlant.class);
		this.renderId = AthsBlockSetup.plantCrossRenderID;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ)  {
		return super.onBlockActivated(world, x, y, z, entityplayer, side, hitX, hitY, hitZ);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	@Override
	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	 */
	public void getSubBlocks(Item item, CreativeTabs tabs, List list){
		// Change to false if this block should not be added to the creative tab
		Boolean addToCreative = true;

		if(addToCreative){
			for(int i = 0; i < plantNames.length; i++) {
				if (blacklistMetas == null || !blacklistMetas[i]) {
					list.add(new ItemStack(item, 1, i));
				}
			}
		}
	}
	
	public int getNumBlacklist() {
		if(blacklistMetas == null) {
			return 0;
		}
		int result = 0;
		for(boolean b : blacklistMetas) {
			result += b ? 1 : 0;
		}
		return result;
	}
	
	public int[] getMetas() {
		
		int[] result = new int[plantNames.length - getNumBlacklist()];
		int index = 0;
		for(int meta = 0; meta < plantNames.length; meta++) {
			if (blacklistMetas == null || !blacklistMetas[meta]) {
				result[index] = meta;
				index++;
			}
		}
		return result;
	}
	
	public int[] getBaseMetas() {
		int[] result = new int[numBaseMetas];
		for (int meta = 0; meta < numBaseMetas; meta++) {
			result[meta] = meta;
		}
		return result;
	}
	
	public BlockPlant setFlowerBounds() {
		float var4 = 0.2F;
		this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);
		return this;
	}
	
	public BlockPlant setGrassBounds() {
		float var4 = 0.4F;
		this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 2.0F, 0.5F + var4);
		return this;
	}
	public BlockPlant setTreeBounds() {
		setThinBounds(1.5f);
		return this;
	}
	public BlockPlant setLayerBounds(float h) {
		this.setBlockBounds(0F, 0F, 0F, 1F, h, 1F);
		return this;
	}
	public BlockPlant setThinBounds(float h) {
		float var4 = 0.25F;
		this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, h, 0.5F + var4);
		return this;
	}
	public BlockPlant setHasNoDrops() {
		hasNoDrops = true;
		return this;
	}
	public BlockPlant setIsFlammable() {
		isFlammable = true;
		return this;
	}
	public BlockPlant setIsWoody() {
		this.setHardness(1.0F);
		this.setStepSound(Block.soundTypeWood);
		this.setHarvestLevel("axe", 0);
		this.setIsFlammable();
		return this.setHasCollision();
	}
	public BlockPlant setPoisonDuration(int d) {
		this.poisonDuration = d;
		return this;
	}
	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
		if(!world.isRemote && poisonDuration != 0 && player.getHeldItem() == null &&
				! ( hasVary(EnumVary.WINTER, meta) && (isVary(meta, EnumVary.SNOW) || isVary(meta, EnumVary.WINTER)))) {
			player.addPotionEffect(new PotionEffect(19, poisonDuration * 20));
		}
		if(hasNoDrops) {
			if(AthsParser.isHolding(world, player, "itemShovel")) {
				super.harvestBlock(world, player, x, y, z, meta);
			}
			return;
		}
		super.harvestBlock(world, player, x, y, z, meta);
	}
	public BlockPlant setRenderID(int id) {
		if(id == AthsBlockSetup.plantCropRenderID) {
			setGrassBounds();
		}
		this.renderId = id;
		return this;
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
				if(isAnyVary(i, iconVarys)) { // is this meta included in the icon array?
					this.icons[i] = register.registerIcon(AthsMod.MODID+":plants/"+plantNames[i]); // these icons should be treated normally
				}
				else if (getVary(i) != null){
					this.icons[i] = register.registerIcon(AthsMod.MODID+":plants/"+plantKey + getVary(i).suffix); // for plants such as colored flowers, some textures are shared
				}
			}
		}
	}

	@Override
	public int damageDropped(int dmg){
		return dmg;
	}

	/**
	 * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
	 */
	@Override
	public boolean canBlockStay(World world, int x, int y, int z){
		PlantSpawnData data = AthsWorldGenPlants.plantList.get(this.plantKey);
		if (isWaterPlant && !(world.isSideSolid(x, y-1, z, ForgeDirection.UP) || world.isSideSolid(x, y-2, z, ForgeDirection.UP))) {
			return false;
		}
		return data.canGrowOnBlock(world.getBlock(x, y - 1, z), 0); // use meta 0 for now
	}
	

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z){
		Block block = world.getBlock(x, y, z);
		return (world.isAirBlock(x, y, z) || block.getMaterial().isReplaceable()) && this.canBlockStay(world, x, y, z);
	}
	
	// Besides habitat constraints, are there any other constraints to the generation of this block?
	public boolean shouldGenerateAt(World world, int x, int y, int z) {
		return true;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z){
		if(hasCollision) {
			return AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
		}
		return null;
	}

	@Override
	public int getRenderType(){
		return this.renderId;
	}

	@Override
	public boolean isOpaqueCube(){
		return false;
	}
	
	@Override
	public BlockPlant setLightLevel(float f) {
		super.setLightLevel(f);
		return this;
	}

	@Override
	public boolean renderAsNormalBlock(){
		return false;
	}
	
	@Override
    public boolean canDropFromExplosion(Explosion e){
        return false;
    }

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block){
		this.checkAndDropBlock(world, x, y, z);
	}

	protected void checkAndDropBlock(World world, int x, int y, int z){
		if (!world.isRemote && !this.canBlockStay(world, x, y, z)){
			if (!this.hasNoDrops)
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
		if (world.isRemote) return;
		if (this.hasVarys) {
			int meta = world.getBlockMetadata(x, y, z);
			int month = TFC_Time.getSeasonAdjustedMonth(z);
			//float temp = TFC_Climate.getHeightAdjustedBioTemp(world, TFC_Time.getTotalDays(), x, y, z);
			float temp = TFC_Climate.getHeightAdjustedTemp(world, x, y, z);
			// WARNING the order of the following statements DOES matter. I wish it could be otherwise,
			// but some meta variations should be higher priority than others
			
			// snow
			if(hasVary(EnumVary.SNOW, meta)) {
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
			if(hasVary(EnumVary.WINTER, meta)) {
				if ((month >= TFC_Time.OCTOBER && temp <= 0f) ||
					(month >= TFC_Time.NOVEMBER && temp <= 10f)) {
					shiftToVary(world, x, y, z, meta, EnumVary.WINTER);
					return; // success
				}
			}
			// autumn
			if(hasVary(EnumVary.AUTUMN, meta)) {
				if (month >= TFC_Time.OCTOBER && temp <= 10 ) {//&& random.nextInt(10) == 0) {
					shiftToVary(world, x, y, z, meta, EnumVary.AUTUMN);
					return; // success
				}
			}
			
			// flowers fruits etc based on month!
			if (monthVarys != null) {
				EnumVary monthVary =  monthVarys[month];
				if(monthVary != null && hasVary(monthVary, meta)) {
					shiftToVary(world, x, y, z, meta, monthVary);
					return; // success
				}
			}
			
			// default
			if (month < TFC_Time.OCTOBER && temp > 0 || // it has gotten warm enough in the spring
				(!isVary(meta, EnumVary.WINTER) && !isVary(meta, EnumVary.AUTUMN) && !isVary(meta, EnumVary.SNOW)) // revert back from flower or fruit etc.
				){
				shiftToVary(world, x, y, z, meta, EnumVary.DEFAULT);
				return; // success
			}
			// if have reached here, this plant may be in a cold enough location that it will not winter state in the spring
			
			//shiftToVary(world, x, y, z, meta, EnumVary.DEFAULT);
		}
		int meta = world.getBlockMetadata(x, y, z);
		if (meta >= plantNames.length) { // something horrible has happened
			shiftToVary(world, x, y, z, meta, EnumVary.DEFAULT);
		}
	}
	
	public void shiftToVary(World world, int x, int y, int z, int meta, EnumVary vary) {
		if(world.isRemote) return;
		int newMeta = varyStartIndexes[vary.index] + getBaseMeta(meta);
		if(meta != newMeta) {
			if (!hasVary(vary, meta)) {
				throw new IllegalStateException("Unable to shift vary. " + this.plantKey + " does not have variation: " + vary.toString());
			}
			world.setBlockMetadataWithNotify(x, y, z, newMeta, 2);
		}
	}
	
	// useful for growing or trimming plants
	public void shiftMeta(World world, int x, int y, int z, int meta, int delta) {
		if(world.isRemote) return;
		int testMeta = getBaseMeta(meta) + delta;
		if (testMeta >= 0 && testMeta < numBaseMetas) {
			world.setBlockMetadataWithNotify(x, y, z, meta + delta, 2);
		}
	}
	public EnumVary[] getVarys() {
		EnumVary[] ret = new EnumVary[plantNames.length];
		for(int i = 0; i < plantNames.length; i++) {
			ret[i] = getVary(i);
		}
		return ret;
	}
	public EnumVary getVary(int meta) {
		for(EnumVary vary : EnumVary.values()) {
			if(isVary(meta, vary)) {
				return vary;
			}
		}
		return null;
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
	public BlockPlant setHasCollision() {
		this.hasCollision = true;
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
		if(hasVary(vary, meta)) {
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
	
	//public boolean hasVary(EnumVary vary) {
	//	return varyStartIndexes[vary.index] != null;
	//}
	/**
	 * given the current meta, does the plant have this vary?
	 */
	public boolean hasVary(EnumVary vary, int meta) {
		Integer start = varyStartIndexes[vary.index];
		if(this.blacklistMetas != null) {
			return start != null && !blacklistMetas[start + getBaseMeta(meta)];
		}
		return start != null;
	}
	
	public BlockPlant setExtraNames(String name) {
		return setExtraNames(name, "Small", "Large");
	}
	public BlockPlant setExtraNames(String name, String suffix) {
		setNames(new String[] {name, name + "_" + suffix});
		setKeyName(name);
		return this;
	}
	public BlockPlant setExtraNames(String name, String suffix1, String suffix2) {
		setNames(new String[] {name, name + "_" + suffix1, name + "_" + suffix2});
		setKeyName(name);
		return this;
	}
	public BlockPlant setExtraNames(String name, String suffix1, String suffix2, String suffix3) {
		setNames(new String[] {name, name + "_" + suffix1, name + "_" + suffix2, name + "_" + suffix3});
		setKeyName(name);
		return this;
	}
	public BlockPlant setExtraNames(String name, String [] suffixes) {
		String [] names = new String [suffixes.length + 1];
		names[0] = name;
		for (int i = 1; i < names.length; i++) {
			names[i] = name + "_" + suffixes[i - 1];
		}
		setNames(names);
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
		if(scale > 1f) {
			setGrassBounds();
		}
		return this;
	}
	public BlockPlant setMonthVary(int month, EnumVary vary) {
		if (varyStartIndexes[vary.index] == null) {
			throw new IllegalStateException("Unable to set month vary." + this.plantKey + " does not have variation: " + vary.toString());
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
	public BlockPlant setBlacklistMeta(int meta) {
		if(blacklistMetas == null) {
			blacklistMetas = new boolean[plantNames.length];
		}
		blacklistMetas[meta] = true;
		return this;
	}
	public BlockPlant setBlacklistMeta(EnumVary vary, int baseMeta) {
		int meta = varyStartIndexes[vary.index] + baseMeta;
		return setBlacklistMeta(meta);
	}
	public BlockPlant setItemBlock(Class<? extends ItemBlock> itemBlock) {
		this.itemBlock = itemBlock;
		return this;
	}
	public Class<? extends ItemBlock> getItemBlock(){
		return this.itemBlock;
	}
	
	public boolean dropItemStacks(World world, int x, int y, int z, ItemStack is, int min, int max, Random random) {
		if(world.isRemote || max < min) {
			return false;
		}
		int numDrops = min;
		if (max > min) {
			numDrops = min + random.nextInt(max - min);
		}
		is.stackSize = numDrops;
		dropBlockAsItem(world, x, y, z, is);
		return true;
	}
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity){
		if(world.isRemote) return;
		int meta = world.getBlockMetadata(x, y, z);
		if(this.isDamaging && entity instanceof EntityLivingBase && !(entity instanceof IAnimal) &&
				! ( hasVary(EnumVary.WINTER, meta) && (isVary(meta, EnumVary.SNOW) || isVary(meta, EnumVary.WINTER))) && // if it gets withered, it shouldn't hurt you
				! (entity instanceof EntityPlayer && ((EntityPlayer)entity).getDisplayName().equals("FaceTorched"))) { // trole
			entity.attackEntityFrom(DamageSource.cactus, 5);
			
		}
		if (isVary(meta, EnumVary.SNOW)) {
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
	public BlockPlant setIsWaterPlant() {
		this.isWaterPlant = true;
		return setRenderID(AthsBlockSetup.plantWaterRenderID);
	}
	public BlockPlant setIsDamaging() {
		this.isDamaging = true;
		return this;
	}
	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}
	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
		char c = this.plantKey.toLowerCase().charAt(0);
		return (c - 'a') * 15 / 25 + 1;
	}
	
	public ItemStack getFoodItemStack() {
		return this.foodItemStack;
	}
	
	public BlockPlant setFoodItemStack(Item food, float weight){
		this.foodItemStack = ItemFoodTFC.createTag(new ItemStack(food, 1, 0), weight);
		return this;
	}	
	
	public BlockPlant setBrownMushroom(float weight) {
		return setFoodItemStack(ItemSetup.mushroomFoodB, weight);
	}

	public BlockPlant setRedMushroom(float weight) {
		return setFoodItemStack(ItemSetup.mushroomFoodR, weight);
	}
}
