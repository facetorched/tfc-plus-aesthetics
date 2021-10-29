package com.facetorched.tfcaths.blocks;

import com.dunk.tfc.TerraFirmaCraft;
import com.facetorched.tfcaths.enums.EnumVary;
import com.facetorched.tfcaths.items.itemblocks.ItemPlantFoliage;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.IBlockAccess;

public class BlockPlantFoliage extends BlockPlantStraw{
	public BlockPlantFoliage() {
		super();
		setItemBlock(ItemPlantFoliage.class);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if(this.isVary(meta, EnumVary.SNOW) || this.isVary(meta, EnumVary.WINTER)){
			return super.colorMultiplier(world, x, y, z);
		}
		return TerraFirmaCraft.proxy.grassColorMultiplier(world, x, y, z);
	}
}
