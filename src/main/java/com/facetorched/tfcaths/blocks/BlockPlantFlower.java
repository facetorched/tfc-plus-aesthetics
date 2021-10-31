package com.facetorched.tfcaths.blocks;

import java.util.Random;

import com.dunk.tfc.Core.TFC_Time;
import com.facetorched.tfcaths.AthsMod;
import com.facetorched.tfcaths.enums.EnumVary;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

@Deprecated
public class BlockPlantFlower extends BlockPlant{

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register){
		this.icons = new IIcon[plantNames.length];
		for (int i = 0; i < this.icons.length; ++i){
			if(isVary(i, EnumVary.FLOWER)) {
				this.icons[i] = register.registerIcon(AthsMod.MODID+":plants/"+plantNames[i]);
			}
			else {
				this.icons[i] = register.registerIcon(AthsMod.MODID+":plants/"+plantKey + getVary(i).suffix);
			}
		}
	}
}
