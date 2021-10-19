package com.facetorched.tfcaths.util;

import java.util.Random;

import com.facetorched.tfcaths.AthsGlobal;

public class AthsRandom {
	public static Random getRandom(int x, int z) {
		Random rand = new Random(x * AthsGlobal.PRIME_1 + z * AthsGlobal.PRIME_2);
		rand.nextInt(); // for some reason this is needed to avoid seed-value correlation
		return rand;
	}
}
