package com.facetorched.tfcaths.tileentities;

import com.facetorched.tfcaths.blocks.BlockPlant;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TEPlant3d extends TileEntity{
	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;
        Block block = Minecraft.getMinecraft().theWorld.getBlock(xCoord, yCoord, zCoord);
        if(block instanceof BlockPlant) {
        	BlockPlant plant = (BlockPlant)block;
        	int r = (int)Math.ceil(plant.scale * 1.5);
        	bb = AxisAlignedBB.getBoundingBox(xCoord - r + 1, yCoord - r + 1, zCoord - r + 1, xCoord + r, yCoord + r, zCoord + r);
        }
        return bb;
    }
}
