package com.facetorched.tfcaths.util;

public class AthsMath {
	public static int toggleKthBit(int n, int k)
	{
	    return (n ^ (1 << k));
	}
	public static boolean isKthBit(int n, int k) {
		return ((n >> k) & 1) == 1;
	}
	public static int ceilDiv(int n, int d) {
		return (n + d - 1) / d;
	}
}
