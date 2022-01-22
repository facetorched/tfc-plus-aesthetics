package com.facetorched.tfcaths.blocks;

import java.util.ArrayList;
import java.util.HashMap;

import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.AthsMod;
import com.facetorched.tfcaths.enums.EnumVary;
import com.facetorched.tfcaths.util.AthsLogger;
import com.facetorched.tfcaths.util.ObjPart;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.ModelFormatException;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class BlockPlant3d extends BlockPlant{
	public WavefrontObject modelObjs[];
	public HashMap<Integer, ArrayList<ObjPart>> modelParts = new HashMap<Integer, ArrayList<ObjPart>>();
	public String overrideModelName; // despite being different plants, use the same model (this is the name of the obj file)
	public boolean isAxisAligned = false;
	
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
			if(overrideModelName == null) {
				modelObjs[i] = (WavefrontObject)AdvancedModelLoader.loadModel(new ResourceLocation(AthsMod.MODID + ":models/blocks/plants/" + plantNames[i] + ".obj"));
			}
			else {
				modelObjs[i] = (WavefrontObject)AdvancedModelLoader.loadModel(new ResourceLocation(AthsMod.MODID + ":models/blocks/plants/" + overrideModelName + ".obj"));
			}
		}
		
		for(int i = 0; i < plantNames.length; i++) {
			for(ObjPart objPart : modelParts.get(i)) {
				objPart.setIcon(register.registerIcon(objPart.getTexture()));
			}
		}
	}
	
	public BlockPlant3d setIsAxisAligned() {
		this.isAxisAligned = true;
		return this;
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
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta){
		if(this.modelParts != null && this.modelParts.get(meta) != null)
			return this.modelParts.get(meta).get(0).getIcon();
		return null;
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
	 * set part for a given meta using base meta names (example male and female versions of cones)
	 */
	public BlockPlant3d setNamedBaseMetaPart(EnumVary vary, String partName) {
		for(int i = 0; i < numBaseMetas; i++) {
			int meta = varyStartIndexes[vary.index] + i;
			setPart(meta, plantNames[i], partName);
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
		// should we only run this on the client? not sure how
		ObjPart part =  new ObjPart(AthsMod.MODID + ":plants/" + plantName + "_" + partName, partName);
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
