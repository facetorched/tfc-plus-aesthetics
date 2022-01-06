package com.facetorched.tfcaths.blocks;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import com.dunk.tfc.BlockSetup;
import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.AthsMod;
import com.facetorched.tfcaths.WorldGen.Generators.PlantSpawnData;
import com.facetorched.tfcaths.enums.EnumVary;
import com.facetorched.tfcaths.tileentities.TEPlant3d;
import com.facetorched.tfcaths.util.AthsLogger;
import com.facetorched.tfcaths.util.ObjPart;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.ModelFormatException;
import net.minecraftforge.client.model.obj.WavefrontObject;
import scala.actors.threadpool.Arrays;

public class BlockPlant3d extends BlockPlant implements ITileEntityProvider{
	public WavefrontObject modelObjs[];
	public HashMap<Integer, ArrayList<ObjPart>> modelParts = new HashMap<Integer, ArrayList<ObjPart>>();
	public String overrideModelName;
	public boolean isAxisAligned;
	
	public BlockPlant3d() {
		super();
		setGrassBounds();
		this.renderId = AthsBlockSetup.plant3dRenderID;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register){
		//super.registerBlockIcons(register);
		modelObjs = new WavefrontObject[numBaseMetas];
		for(int i = 0; i < numBaseMetas; i++) {
			if(overrideModelName == null)
				try {
					modelObjs[i] = (WavefrontObject)AdvancedModelLoader.loadModel(new ResourceLocation(AthsMod.MODID + ":models/blocks/plants/" + plantNames[i] + ".obj"));
				}
				catch (ModelFormatException e) {
					AthsLogger.error("unable to parse " + AthsMod.MODID + ":models/blocks/plants/" + plantNames[i] + ".obj");
				}
			else {
				modelObjs[i] = (WavefrontObject)AdvancedModelLoader.loadModel(new ResourceLocation(AthsMod.MODID + ":models/blocks/plants/" + overrideModelName + ".obj"));
			}
		}
	}
	
	public BlockPlant3d setOverrideModelName(String name) {
		this.overrideModelName = name;
		return this;
	}
	public BlockPlant3d setOverrideModelName() {
		this.overrideModelName = plantKey;
		return this;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TEPlant3d();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return null;
	}
	
	public BlockPlant3d setIsAxisAligned() {
		this.isAxisAligned = true;
		return this;
	}
	
	public WavefrontObject getModelObj(int meta) {
		return modelObjs[getBaseMeta(meta)];
	}
	
	public ArrayList<ObjPart> getModelParts(int meta) {
		return this.modelParts.get(meta);
	}
	
	/**
	 * set parts for all metas of a given vary
	 */
	public BlockPlant3d setVaryParts(EnumVary vary, String plantName, String[] partNames) {
		for(String partName : partNames) {
			setVaryPart(vary, plantName, partName);
		}
		return this;
	}
	/**
	 * set parts for all metas of a given vary. use plantkey as name
	 */
	public BlockPlant3d setVaryParts(EnumVary vary, String[] partNames) {
		for(String partName : partNames) {
			setVaryPart(vary, partName);
		}
		return this;
	}
	/**
	 * set parts for all metas of a given vary. use the varyStartIndex's name
	 */
	public BlockPlant3d setNamedVaryParts(EnumVary vary, String[] partNames) {
		for(String partName : partNames) {
			setNamedVaryPart(vary, partName);
		}
		return this;
	}
	/**
	 * set parts for all metas of the given varys
	 */
	public BlockPlant3d setVaryParts(EnumVary[] varys, String plantName, String[] partNames) {
		for(String partName : partNames) {
			setVaryPart(varys, plantName, partName);
		}
		return this;
	}
	/**
	 * set parts for all metas of the given varys. use plantkey as name
	 */
	public BlockPlant3d setVaryParts(EnumVary[] varys, String[] partNames) {
		for(String partName : partNames) {
			setVaryPart(varys, partName);
		}
		return this;
	}
	/**
	 * set part for all metas of the given varys. use each of the varyStartIndex's names
	 */
	public BlockPlant3d setNamedVaryParts(EnumVary[] varys, String[] partNames) {
		for(String partName : partNames) {
			setNamedVaryPart(varys, partName);
		}
		return this;
	}
	/**
	 * set part for all metas of a given vary
	 */
	public BlockPlant3d setVaryPart(EnumVary vary, String plantName, String partName) {
		for(int i = 0; i < numBaseMetas; i++) {
			int meta = varyStartIndexes[vary.index] + i;
			setPart(meta, plantName, partName);
		}
		return this;
	}
	/**
	 * set part for all metas of a given vary. use plantkey as name
	 */
	public BlockPlant3d setVaryPart(EnumVary vary, String partName) {
		return setVaryPart(vary, plantKey, partName);
	}
	/**
	 * set part for all metas of a given vary. use the varyStartIndex's name
	 */
	public BlockPlant3d setNamedVaryPart(EnumVary vary, String partName) {
		return setVaryPart(vary, plantNames[varyStartIndexes[vary.index]], partName);
	}
	/**
	 * set part for all metas of the given varys
	 */
	public BlockPlant3d setVaryPart(EnumVary[] varys, String plantName, String partName) {
		for(EnumVary vary : varys) {
			setVaryPart(vary, plantName, partName);
		}
		return this;
	}
	/**
	 * set part for all metas of the given varys. use plantkey as name
	 */
	public BlockPlant3d setVaryPart(EnumVary[] varys, String partName) {
		return setVaryPart(varys, plantKey, partName); //default is plantKey
	}
	/**
	 * set part for all metas of the given varys. use each of the varyStartIndex's names
	 */
	public BlockPlant3d setNamedVaryPart(EnumVary[] varys, String partName) {
		for(EnumVary vary : varys) {
			setNamedVaryPart(vary, partName);
		}
		return this;
	}
	
	public BlockPlant3d setBaseMetaPart(int baseMeta, String plantName, String partName) {
		for(int meta = baseMeta; meta < plantNames.length; meta += numBaseMetas) {
			setPart(meta, plantName, partName);
		}
		return this;
	}
	public BlockPlant3d setBaseMetaPart(int baseMeta, String partName) {
		return setBaseMetaPart(baseMeta, plantKey, partName);
	}
	public BlockPlant3d setNamedBaseMetaPart(int baseMeta, String partName) {
		return setBaseMetaPart(baseMeta, plantNames[baseMeta], partName);
	}
	public BlockPlant3d setBaseMetaPart(int[] baseMetas, String plantName, String partName) {
		for(int baseMeta : baseMetas) {
			setBaseMetaPart(baseMeta, plantName, partName);
		}
		return this;
	}
	public BlockPlant3d setBaseMetaPart(int[] baseMetas, String partName) {
		return setBaseMetaPart(baseMetas, plantKey, partName); //default is plantKey
	}
	public BlockPlant3d setNamedBaseMetaPart(int[] baseMetas, String partName) {
		for(int baseMeta : baseMetas) {
			setNamedBaseMetaPart(baseMeta, partName);
		}
		return this;
	}
	
	/**
	 * set part for only one specific meta given a vary and baseMeta. use that meta's name
	 */
	public BlockPlant3d setNamedPart(EnumVary vary, int baseMeta, String partName) {
		int meta = varyStartIndexes[vary.index] + baseMeta;
		return setNamedPart(meta, partName);
	}
	
	/**
	 * set part for only one specific meta given a vary and baseMeta. use plantKey as name
	 */
	public BlockPlant3d setPart(EnumVary vary, int baseMeta, String partName) {
		int meta = varyStartIndexes[vary.index] + baseMeta;
		return setPart(meta, partName);
	}
	
	/**
	 *  the most basic way to set a part. only use this externally if brute force is needed
	 */
	public BlockPlant3d setPart(int meta, String plantName, String partName) {
		ObjPart part =  new ObjPart(new ResourceLocation(AthsMod.MODID, 
				"textures/blocks/plants/" + plantName + "_" + partName + ".png"), partName);
		if (this.modelParts.containsKey(meta)) {
			this.modelParts.get(meta).add(part);
		}
		else {
			ArrayList<ObjPart> obj = new ArrayList<ObjPart>();
			obj.add(part);
			this.modelParts.put(meta, obj);
		}
		return this;
	}
	
	/**
	 * set part for only one specific meta using that meta's name
	 */
	public BlockPlant3d setNamedPart(int meta, String partName) {
		return setPart(meta, plantNames[meta], partName);
	}
	/**
	 *  set part for all possible metas using that meta's name
	 */
	public BlockPlant3d setNamedPart(String partName) {
		for(int meta = 0; meta < plantNames.length; meta++) {
			setNamedPart(meta, partName);
		}
		return this;
	}
	/**
	 * set part for only one specific meta using plantkey as name
	 */
	public BlockPlant3d setPart(int meta, String partName) {
		return setPart(meta, plantKey, partName);
	}
	/**
	 *  set part for all provided metas using plantkey as name
	 */
	public BlockPlant3d setPart(int[] metas, String partName) {
		for(int meta : metas) {
			setPart(meta, partName);
		}
		return this;
	}
	/**
	 *  set part for all possible metas using plantkey as name
	 */
	public BlockPlant3d setPart(String partName) {
		for(int meta = 0; meta < plantNames.length; meta++) {
			setPart(meta, partName);
		}
		return this;
	}
	
	/**
	 *  set parts for all possible metas using plantkey as name
	 */
	public BlockPlant3d setParts(String[] partNames) {
		for(String partName : partNames) {
			setPart(partName);
		}
		return this;
	}
	
	// it's nice to have these return BlockPlant3d. I wish there was a way that wasn't so cringe???
	@Override
	public BlockPlant3d addVarys(EnumVary[] varys) {
		super.addVarys(varys);
		return this;
	}
	@Override
	public BlockPlant3d addVary(EnumVary vary) {
		super.addVary(vary);
		return this;
	}
	@Override
	public BlockPlant3d setName(String name) {
		super.setName(name);
		return this; 
	}
	@Override
	public BlockPlant3d setExtraNames(String name, String suffix) {
		super.setExtraNames(name, suffix);
		return this;
	}
	@Override
	public BlockPlant3d setExtraNames(String name) {
		super.setExtraNames(name);
		return this;
	}
	@Override
	public BlockPlant3d setExtraNames(String name, String suffix1, String suffix2) {
		super.setExtraNames(name, suffix1, suffix2);
		return this;
	}
	@Override
	public BlockPlant3d setExtraNames(String name, String suffix1, String suffix2, String suffix3) {
		super.setExtraNames(name, suffix1, suffix2, suffix3);
		return this;
	}
	@Override
	public BlockPlant3d setKeyName(String name) {
		super.setKeyName(name);
		return this;
	}
	@Override
	public BlockPlant3d setNames(String[] names) {
		super.setNames(names);
		return this;
	}
	public BlockPlant3d setNames(String name, String[] suffixes) {
		super.setNames(name, suffixes);
		return this;
	}
}
