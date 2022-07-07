package com.facetorched.tfcaths.waila;

import java.util.List;

import com.facetorched.tfcaths.blocks.BlockCrystal;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockData implements IWailaDataProvider
{
	protected static BlockData theBlockData = new BlockData();
	
	public static void callbackRegister(IWailaRegistrar reg){
		reg.registerStackProvider(theBlockData, BlockCrystal.class);
	}
	
	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		Block block = accessor.getBlock();

		if (block instanceof BlockCrystal) {
			BlockCrystal b = (BlockCrystal)(block);
			return new ItemStack(b.crystalItem, 1, b.crystalMetas[0]);
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
	public List<String> getWailaHead(ItemStack arg0, List<String> arg1, IWailaDataAccessor arg2,
			IWailaConfigHandler arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getWailaTail(ItemStack arg0, List<String> arg1, IWailaDataAccessor arg2,
			IWailaConfigHandler arg3) {
		// TODO Auto-generated method stub
		return null;
	}
}