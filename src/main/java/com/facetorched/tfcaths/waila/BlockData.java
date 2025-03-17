package com.facetorched.tfcaths.waila;

import java.util.List;

import com.facetorched.tfcaths.AthsMod;
import com.facetorched.tfcaths.blocks.BlockCrystal;
import com.facetorched.tfcaths.blocks.BlockPlant;
import com.facetorched.tfcaths.enums.EnumVary;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockData implements IWailaDataProvider
{
	protected static BlockData theBlockData = new BlockData();
	
	public static void callbackRegister(IWailaRegistrar reg){
		reg.registerStackProvider(theBlockData, BlockCrystal.class);
		reg.registerStackProvider(theBlockData, BlockPlant.class);
		//reg.registerHeadProvider(theBlockData, BlockPlant.class);
		reg.registerTailProvider(theBlockData, BlockPlant.class);
	}
	
	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		Block block = accessor.getBlock();
		if (block instanceof BlockCrystal) {
			BlockCrystal b = (BlockCrystal)(block);
			int meta = (int)((accessor.getWorld().getTotalWorldTime() / 20) % b.crystalMetas.length);
			return new ItemStack(b.crystalItem, 1, b.crystalMetas[meta]);
		}
		if (block instanceof BlockPlant) {
			BlockPlant p = (BlockPlant)block;
			if (p.isVary(accessor.getMetadata(), EnumVary.MYCELIUM))
				return new ItemStack(Blocks.mycelium);
		}
		return null;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP arg0, TileEntity arg1, NBTTagCompound arg2, World arg3, int arg4,
			int arg5, int arg6) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getWailaBody(ItemStack arg0, List<String> arg1, IWailaDataAccessor arg2,
			IWailaConfigHandler arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getWailaHead(ItemStack itemstack, List<String> tooltip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		// currently unused
		Block block = accessor.getBlock();
		if (block instanceof BlockPlant) {
			BlockPlant p = (BlockPlant)block;
			if (p.isVary(accessor.getMetadata(), EnumVary.MYCELIUM)) {
				tooltip.set(0, "Geg?");
			}
		}
		return tooltip;
	}

	@Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> tooltip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        // Modify the tooltip tail (default shows mod name)
        tooltip.set(1,"§9§o" + AthsMod.NAME);
        return tooltip;
    }
}