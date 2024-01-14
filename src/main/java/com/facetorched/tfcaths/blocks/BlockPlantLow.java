package com.facetorched.tfcaths.blocks;

import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.AthsMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockPlantLow extends BlockPlant{
	@SideOnly(Side.CLIENT)
	public IIcon[] sideIcons;
	public BlockPlantLow() {
		this(Material.vine);
	}
	public BlockPlantLow(Material m) {
		super(m);
		setHasNoDrops();
		setLayerBounds(.25f);
		this.renderId = AthsBlockSetup.plantLowRenderID;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		super.registerBlockIcons(register);
		sideIcons = new IIcon[plantNames.length];
		for (int i = 0; i < plantNames.length; ++i){
			if(iconVarys == null) // normal
				sideIcons[i] = register.registerIcon(AthsMod.MODID+":plants/"+plantNames[i]+"_Side");
			else { // icon diversity is related to specific varys
				if(isAnyVary(i, iconVarys)) { // is this meta included in the icon array?
					sideIcons[i] = register.registerIcon(AthsMod.MODID+":plants/"+plantNames[i]+"_Side");; // these icons should be treated normally
				}
				else if (getVary(i) != null){
					sideIcons[i] = register.registerIcon(AthsMod.MODID+":plants/"+plantKey + getVary(i).suffix + "_Side"); // for plants such as colored flowers, some textures are shared
				}
			}
		}
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
	public BlockPlant setScale(float scale) {
		throw new UnsupportedOperationException("Cannot set the scale of BlockPlantLow");
	}
}
