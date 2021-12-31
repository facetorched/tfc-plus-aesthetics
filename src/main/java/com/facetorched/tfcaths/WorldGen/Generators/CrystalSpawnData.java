package com.facetorched.tfcaths.WorldGen.Generators;

import java.util.ArrayList;
import java.util.HashMap;

import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.Constant.Global;
import com.facetorched.tfcaths.util.AthsParser;

import net.minecraft.block.Block;

public class CrystalSpawnData {
	public Block block;
	public Block block2; // crust
	public HashMap<Block, int[]> canGrowOn = new HashMap<Block, int[]>(); // block meta pairs
	public ArrayList<String> canGrowOnOreDict = new ArrayList<String>();
	public int size, dispersion, rarity;

	public CrystalSpawnData(String blockName, String blockName2, String[] canGrowOn, int size, int dispersion, int rarity){
		block = AthsParser.getBlockFromName(blockName); // error if not found
		block2 = Block.getBlockFromName(blockName); // no error if not found (set to null)
		
		for(int i = 0; i < canGrowOn.length; i++) {
			Block b = Block.getBlockFromName(canGrowOn[i]);
			if(b != null) {
				this.canGrowOn.put(b, null);
			}
			else if(!(
					addCanGrowOn(canGrowOn[i], TFCBlocks.stoneIgEx, Global.STONE_IGEX) ||
					addCanGrowOn(canGrowOn[i], TFCBlocks.stoneIgIn, Global.STONE_IGIN) ||
					addCanGrowOn(canGrowOn[i], TFCBlocks.stoneMM, Global.STONE_MM) ||
					addCanGrowOn(canGrowOn[i], TFCBlocks.stoneSed, Global.STONE_SED)
					)) {
				this.canGrowOnOreDict.add(canGrowOn[i]);
			}
		}
		this.size = size;
		this.dispersion = dispersion;
		this.rarity = rarity;
	}
	public boolean addCanGrowOn(String rockName, Block rocktype, String[] rockNames) {
		if(AthsParser.contains(rockNames, rockName)) {
			int index = AthsParser.find(rockNames, rockName);
			this.canGrowOn.put(rocktype, AthsParser.add(this.canGrowOn.get(rocktype), index));
			return true;
		}
		return false;
	}
	
}
