package com.facetorched.tfcaths.util;

public class BitMap {
	public boolean[] map;
	int x;
	int z;
	int width;
	int height;
	
	public BitMap(int x, int z, int width, int height) {
		this.width = width; // number of columns
		this.height = height; // number of rows
		this.x = x; // upper left corner
		this.z = z; // upper left corner
		this.map = new boolean[width * height]; //initializes as false
	}
	
	public boolean get(int x, int z) {
		try {
			return this.map[x - this.x + (z - this.z) * this.width];
		}
		catch (IndexOutOfBoundsException e) {
			return true;
		}
	}
	
	public void set(int x, int z) {
		try {
			this.map[x - this.x + (z - this.z) * this.width] = true;
		}
		catch (IndexOutOfBoundsException e) {
		}
	}
	
	public void zero() {
		for(int i = 0; i<map.length;i ++) {
			map[i] = false;
		}
	}
}
