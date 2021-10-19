package com.facetorched.tfcaths.blocks;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import com.facetorched.tfcaths.AthsMod;
import com.facetorched.tfcaths.WorldGen.Generators.PlantSpawnData;
import com.facetorched.tfcaths.tileentities.TEPlant3d;
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
import net.minecraftforge.client.model.obj.WavefrontObject;
import scala.actors.threadpool.Arrays;

public class BlockPlant3d extends BlockPlant implements ITileEntityProvider{
	public WavefrontObject modelObj;
	public HashMap<Integer, ArrayList<ObjPart>> modelParts = new HashMap<Integer, ArrayList<ObjPart>>();
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		//super.registerBlockIcons(register);
		modelObj = (WavefrontObject)AdvancedModelLoader.loadModel(new ResourceLocation(AthsMod.MODID + ":models/blocks/plants/" + this.plantKey + ".obj"));
	}
	
	@Override
	public int getRenderType() {
		return -1;
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
	
	public WavefrontObject getModelObj() {
		return modelObj;
	}
	
	public ArrayList<ObjPart> getModelParts(int meta) {
		return this.modelParts.get(meta);
	}
	
	public BlockPlant3d setPart(String partName, int[] metas) {
		if(this.plantKey == null) {
			throw new IllegalStateException("cannot name plant parts before plantKey");
		}
		for(int meta : metas) {
			setPart(meta, this.plantKey, partName);
		}
		return this;
	}
	
	public BlockPlant3d setPart(String partName, int meta) {
		if(this.plantKey == null) {
			throw new IllegalStateException("cannot name plant parts before plantNames");
		}
		setPart(meta, this.plantNames[meta], partName);
		return this;
	}
	
	public void setPart(int meta, String plantName, String partName) {
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
	}
	
	@Override
	public BlockPlant3d setName(String name) {
		this.plantNames = new String[] {name};
		this.plantKey = name;
		this.setBlockName(name);
		return this;
	}
}
