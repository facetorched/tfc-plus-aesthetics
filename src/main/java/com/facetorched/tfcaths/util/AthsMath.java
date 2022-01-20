package com.facetorched.tfcaths.util;

import java.util.Random;

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
	
	/**
	 *  simulate binomial distribution using reciprocal of p
	 */
	public static int binoRNG(Random random, int n, int recip_p) {
		int x = 0;
		for(int i = 0; i < n; i++) {
			if(random.nextInt(recip_p) == 0)
				x++;
		}
		return x;
	}
}
