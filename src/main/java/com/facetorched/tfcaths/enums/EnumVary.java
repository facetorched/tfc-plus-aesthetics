package com.facetorched.tfcaths.enums;

public enum EnumVary {
	
	DEFAULT (0, ""),
	AUTUMN (1, "_Autumn"),
	WINTER (2, "_Winter"),
	SNOW (3, "_Snow"),
	FLOWER (4, "_Flower"),
	EARLY_SPRING (5, "_Early_Spring"),
	FRUIT (6, "_Fruit");
	
	
	public final String suffix;
	public final int index;
	private EnumVary(int index, String suffix) {
		this.suffix = suffix;
		this.index = index;
	}
	
	public static EnumVary getEnum(int index) {
		for(EnumVary e: EnumVary.values()) {
		    if(e.index == index) {
		      return e;
		    }
		  }
		  return null;// not found
	}
}
