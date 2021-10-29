package com.facetorched.tfcaths.blocks;

import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.AthsMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockPlantLow extends BlockPlant{
	@SideOnly(Side.CLIENT)
	public IIcon[] sideIcons;
	public BlockPlantLow() {
		super();
		float h = 0.25F;
		this.setBlockBounds(0F, 0F, 0F, 1F, h, 1F);
	}
	@Override
	public int getRenderType() {
		return AthsBlockSetup.plantLowRenderID;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		super.registerBlockIcons(register);
		sideIcons = new IIcon[plantNames.length];
		for (int i = 0; i < plantNames.length; ++i){
			sideIcons[i] = register.registerIcon(AthsMod.MODID+":plants/"+plantNames[i]+"_Side");
		}
	}
}
