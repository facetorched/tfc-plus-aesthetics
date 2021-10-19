package com.facetorched.tfcaths.util;

public class BitMap {
	public boolean[] map;
	int x;
	int z;
	int width;
	int height;
	
	public BitMap(int x, int z, int width, int height) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.z = z;
		this.map = new boolean[width * height];
		for(boolean b : this.map) {
			if(b) {
				throw new IllegalStateException();
			}
		}
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
		this.map = new boolean[this.width * this.height];
	}
}
