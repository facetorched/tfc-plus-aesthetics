package com.facetorched.tfcaths.WorldGen.Generators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.dunk.tfc.Blocks.Terrain.BlockStone;
import com.dunk.tfc.Core.TFC_Climate;
import com.dunk.tfc.WorldGen.DataLayer;
import com.facetorched.tfcaths.util.AthsLogger;
import com.facetorched.tfcaths.util.AthsMath;
import com.facetorched.tfcaths.util.Point3D;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class AthsWorldGenCrystals implements IWorldGenerator{
	
	public static Map<String, CrystalSpawnData> crystalList = new HashMap<String, CrystalSpawnData>();
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider) {
		
		// convert chunk coord too block coord at about the center of generation region
		int cornerX = chunkX * 16;
		int cornerZ = chunkZ * 16;
		
		int numProbes = 5;
		
		ArrayList<Point3D> caveSurfaces = new ArrayList<Point3D>(); // stone block where a crystal could grow on
		
		for(int i = 0; i < numProbes; i++) {
			int x = cornerX + random.nextInt(16) + 8;
			int z = cornerZ + random.nextInt(16) + 8;
			for(int y = world.getTopSolidOrLiquidBlock(x, z); y > 1; y--) { // bottom of the world is just bedrock?
				if(world.isAirBlock(x, y, z) && world.getBlock(x, y + 1, z) instanceof BlockStone) {
					
					int roofY = y;
					while(world.isAirBlock(x, y, z) && y > 1) {
						y--;
					}
					
					for(int j = 0; j < 3; j++) { // send out 3 rays
						int innerY = y - random.nextInt(roofY - y); // can't think of any edge cases where this errors
						
						// degrees of freedom
						Point3D dof = new Point3D(
								random.nextInt(2) * 2 - 1,
								random.nextInt(2) * 2 - 1,
								random.nextInt(2) * 2 - 1);
						
						// point meanders towards a surface
						Point3D snake = new Point3D(x, innerY, z);
						
						for(int k = 0; k < 16; k++) { // give up after 15
							int d = random.nextInt(3);
							snake.step(dof, d);
							
							if (world.getBlock(snake.x, snake.y, snake.z) instanceof BlockStone) {
								caveSurfaces.add(snake);
								break;
							}
							if (!world.isAirBlock(snake.x, snake.y, snake.z)) { // some non stone obstruction
								break;
							}
						}
					}
				}
			}
		}
		
		Collections.shuffle(caveSurfaces); // shuffle the surfaces
		
		// shuffle the crystal keys
		// loop over crystals
		// how many crystals ideally do we want = n
		// loop over cave surfaces and check rock type suitable for this crystal and remove from the surface list and generate a crystal there decrement n
		// keep checking to make sure surface list isn't empty
		// 

		
		/*
		
		DataLayer rockLayer1 = TFC_Climate.getCacheManager(world).getRockLayerAt(chunkX, chunkZ, 0);
		DataLayer rockLayer2 = TFC_Climate.getCacheManager(world).getRockLayerAt(chunkX, chunkZ, 1);
		DataLayer rockLayer3 = TFC_Climate.getCacheManager(world).getRockLayerAt(chunkX, chunkZ, 2);
		if (rockLayer1.block == b && (rockLayer1.data2 == metadata || metadata == -1) ||
			rockLayer2.block == b && (rockLayer2.data2 == metadata || metadata == -1) ||
			rockLayer3.block == b && (rockLayer3.data2 == metadata || metadata == -1))
			
		*/
	}
	
	

}
