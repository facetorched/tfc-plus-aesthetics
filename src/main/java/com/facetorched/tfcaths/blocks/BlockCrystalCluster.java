package com.facetorched.tfcaths.blocks;

import java.util.Random;

import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.AthsMod;
import com.facetorched.tfcaths.util.AthsParser;
import com.facetorched.tfcaths.util.Point3DD;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockCrystalCluster extends BlockCrystal{
	
	@Override
	public int getRenderType(){
		return AthsBlockSetup.directionalCrossRenderID;
	}
	
	public BlockCrystalCluster setName(String name) {
		crystalName = name + "_Cluster";
		setBlockName(crystalName);
		setBlockTextureName(AthsMod.MODID + ":crystals/" + crystalName);
		return this;
	}
	
	/**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderBlockPass()
    {
        return 0;
    }
    
    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z){
    	int meta = world.getBlockMetadata(x, y, z);
    	ForgeDirection d = getDirection(meta);
    	double d1 = 0.4d;
		Point3DD pt1 = new Point3DD(0.5 - d1, 0.0, 0.5 - d1).rotateToFace(d);
		Point3DD pt2 = new Point3DD(0.5 + d1, d1*2, 0.5 + d1).rotateToFace(d);
		if(AthsParser.isNegativeDirection(d)) { // max and mins get flipped
			this.setBlockBounds((float)pt2.x, (float)pt2.y, (float)pt2.z, (float)pt1.x, (float)pt1.y, (float)pt1.z);
		}
		else {
			this.setBlockBounds((float)pt1.x, (float)pt1.y, (float)pt1.z, (float)pt2.x, (float)pt2.y, (float)pt2.z);
		}
    }
}
