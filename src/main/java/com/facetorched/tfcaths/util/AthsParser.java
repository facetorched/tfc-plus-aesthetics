package com.facetorched.tfcaths.util;

import net.minecraft.block.Block;

public class AthsParser {
	public static Block getBlockFromName(String blockName) {
		Block ret = Block.getBlockFromName(blockName);
		
		if (ret == null){
			AthsLogger.error("config file contains invalid block name " + blockName);
			throw new java.lang.NullPointerException("invalid block name " + blockName);
		}
		return ret;
	}
}
