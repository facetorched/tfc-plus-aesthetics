package com.facetorched.tfcaths.WorldGen.Generators;

import java.util.HashMap;

import com.facetorched.tfcaths.AthsGlobal;
import com.facetorched.tfcaths.util.AthsParser;
import com.facetorched.tfcaths.util.BlockMetaPair;

import net.minecraft.block.Block;

public class CrystalSpawnData {
	public Block block;
	public Block block2; // crust
	public HashMap<Block, int[]> canGrowOn = new HashMap<Block, int[]>(); // block meta pairs
	public int size, dispersion, rarity;

	public CrystalSpawnData(String blockName, String blockName2, String[] canGrowOn, int size, int dispersion, int rarity){
		block = AthsParser.getBlockFromName(blockName); // error if not found
		block2 = Block.getBlockFromName(blockName2); // no error if not found (set to null)
		
		for(String rock : canGrowOn) {
			if(rock.equals("All")) {
				for(String allRock : AthsGlobal.ROCKTYPES) {
					addRockByType(allRock);
				}
			}
			else if(addRockByName(rock)) {}
			else addRockByType(rock);
		}
		
		this.size = size;
		this.dispersion = dispersion;
		this.rarity = rarity;
	}
	
	public BlockMetaPair getRockTypeByName(String name) {
		for(int i = 0; i < AthsGlobal.ROCKTYPES_NAMES.length; i++) {
			if(AthsParser.contains(AthsGlobal.ROCKTYPES_NAMES[i], name)) {
				return new BlockMetaPair(AthsGlobal.ROCKTYPES_BLOCKS[i], AthsParser.find(AthsGlobal.ROCKTYPES_NAMES[i], name));
			}
		}
		return null;
	}
	public boolean addRockByName(String name) {
		BlockMetaPair b = getRockTypeByName(name);
		if(b != null) {
			this.canGrowOn.put(b.block, AthsParser.add(this.canGrowOn.get(b.block), b.meta));
			return true;
		}
		return false;
	}
	
	public boolean addRockByType(String rockType) {
		Block rock = getRockByType(rockType);
		if(rock != null) {
			this.canGrowOn.put(rock, new int[] {-1});
			return true;
		}
		return false;
	}
	
	public Block getRockByType(String rockType) {
		for(int i = 0; i < AthsGlobal.ROCKTYPES.length; i++) {
			if(AthsGlobal.ROCKTYPES[i].equals(rockType)) {
				return AthsGlobal.ROCKTYPES_BLOCKS[i];
			}
		}
		return null;
	}
	
}
