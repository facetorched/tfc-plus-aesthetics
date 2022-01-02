package com.facetorched.tfcaths.WorldGen.Generators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.dunk.tfc.Blocks.Terrain.BlockStone;
import com.dunk.tfc.Core.TFC_Climate;
import com.dunk.tfc.WorldGen.DataLayer;
import com.facetorched.tfcaths.AthsGlobal;
import com.facetorched.tfcaths.blocks.BlockCrystal;
import com.facetorched.tfcaths.blocks.BlockPlant;
import com.facetorched.tfcaths.enums.EnumVary;
import com.facetorched.tfcaths.util.AthsLogger;
import com.facetorched.tfcaths.util.AthsMath;
import com.facetorched.tfcaths.util.AthsParser;
import com.facetorched.tfcaths.util.BlockMetaPair;
import com.facetorched.tfcaths.util.Point3D;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.util.ForgeDirection;

public class AthsWorldGenCrystals implements IWorldGenerator{
	
	private static final int NUM_PROBES = 5;
	private static final int NUM_RAYS = 3;
	// <name of crystal, crystal generation parameters>
	public static Map<String, CrystalSpawnData> crystalList = new HashMap<String, CrystalSpawnData>();
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider) {
		
		// convert chunk coord too block coord at about the center of generation region
		int cornerX = chunkX * 16;
		int cornerZ = chunkZ * 16;
		
		ArrayList<Point3D> surfaces = new ArrayList<Point3D>(); // air block where a crystal could be
		
		for(int i = 0; i < NUM_PROBES; i++) {
			int x = cornerX + random.nextInt(16) + 8;
			int z = cornerZ + random.nextInt(16) + 8;
			for(int y = world.getTopSolidOrLiquidBlock(x, z); y > 1; y--) { // bottom of the world is just bedrock?
				if(world.isAirBlock(x, y, z) && world.getBlock(x, y + 1, z) instanceof BlockStone) {
					
					int roofY = y;
					while(world.isAirBlock(x, y, z) && y > 1) {
						y--;
					}
					
					for(int j = 0; j < NUM_RAYS; j++) { // send out some rays
						int innerY = y - random.nextInt(roofY - y); // can't think of any edge cases where this errors
						
						// randomly select degrees of freedom (this is to prevent backtracking of ray)
						Point3D dof = new Point3D(
								random.nextInt(2) * 2 - 1,
								random.nextInt(2) * 2 - 1,
								random.nextInt(2) * 2 - 1);
						
						// point meanders towards a surface
						Point3D ray = new Point3D(x, innerY, z);
						
						for(int k = 0; k < 16; k++) { // give up after 15
							int d = random.nextInt(3);
							ray.step(dof, d);
							Block b = world.getBlock(ray.x, ray.y, ray.z);
							if (b instanceof BlockStone) {
								ray.step(dof.invert(), d); // backtrack one block (should be air)
								surfaces.add(ray);
								break;
							}
							if (!world.isAirBlock(ray.x, ray.y, ray.z)) { // some non stone obstruction
								break;
							}
						}
					}
				}
			}
		}
		Collections.shuffle(surfaces, random); // shuffle so that the same type of crystal will not tend to group
		ArrayList<String> keys = new ArrayList<String>(crystalList.keySet());
		Collections.shuffle(keys, random); // shuffle so that on average no given crystal has a higher priority
		// loop over crystals
		for(String key : keys) {
			CrystalSpawnData data = crystalList.get(key);
			ArrayList<Integer> surfaceIndexes = new ArrayList<Integer>();
			for(int i = surfaces.size() - 1; i >= 0; i--) { // reverse for loop to preserve index as elements are removed later on
				if(hasValidSurface(getAdjacents(surfaces.get(i), world),data, world)){
					surfaceIndexes.add(i);
				}
			}

			int numClusters = AthsMath.binoRNG(random, surfaceIndexes.size(), data.rarity); // ideal number of clusters to generate if possible
			for(int i : surfaceIndexes) {
				if(numClusters <= 0) {
					break;
				}
				placeCrystal(surfaces.get(i), data, world, random);
				
				// TODO expand cluster using size and dispersion parameters
				
				surfaces.remove(i);
				numClusters--;
			}
			
		}
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
	
	public static boolean placeCrystal(Point3D p, CrystalSpawnData data, World world, Random random) {
		ArrayList<Integer> directions = getValidDirections(p, data, world);
		if(!directions.isEmpty()) {
			
			int d = directions.get(random.nextInt(directions.size()));
			Block b = data.block;
			if(data.block2 != null && random.nextBoolean()) {
				b = data.block2;
			}
			world.setBlock(p.x, p.y, p.z, b, d, 2);
			return true;
		}
		return false;
	}
	
	public static ArrayList<Integer> getValidDirections(Point3D p, CrystalSpawnData data, World world){
		ArrayList<Integer> valid = new ArrayList<Integer>();
		for(int i = 0; i < ForgeDirection.VALID_DIRECTIONS.length; i++) {
			ForgeDirection d = ForgeDirection.VALID_DIRECTIONS[i];
			Block block = world.getBlock(p.x + d.offsetX, p.y + d.offsetY, p.z + d.offsetZ);
			int meta = world.getBlockMetadata(p.x + d.offsetX, p.y + d.offsetY, p.z + d.offsetZ);
			if(data.canGrowOn.containsKey(block)){ // check block
				if(AthsParser.contains(data.canGrowOn.get(block), meta)){ // check meta
					valid.add(ForgeDirection.OPPOSITES[i]);
				}
			}
		}
		return valid;
	}
	
	public ArrayList<Point3D> getValidSurfaces(Point3D[] candidates, CrystalSpawnData data, World world){
		ArrayList<Point3D> valid = new ArrayList<Point3D>();
		for(Point3D p : candidates) {
			Block block = world.getBlock(p.x, p.y, p.z);
			int meta = world.getBlockMetadata(p.x, p.y, p.z);
			if(data.canGrowOn.containsKey(block)){ // check block
				if(AthsParser.contains(data.canGrowOn.get(block), meta)){ // check meta
					valid.add(p);
				}
			}
		}
		return valid;
	}
	
	public static boolean hasValidSurface(Point3D[] candidates, CrystalSpawnData data, World world){
		for(Point3D p : candidates) {
			Block block = world.getBlock(p.x, p.y, p.z);
			int meta = world.getBlockMetadata(p.x, p.y, p.z);
			if(data.canGrowOn.containsKey(block)){ // check block
				if(AthsParser.contains(data.canGrowOn.get(block), meta)){ // check meta
					return true;
				}
			}
		}
		return false;
	}
	
	public static ArrayList<Point3D> getValidOpenings(Point3D[] candidates, CrystalSpawnData data, World world){
		ArrayList<Point3D> valid = new ArrayList<Point3D>();
		for(Point3D p : candidates) {
			if(world.isAirBlock(p.x, p.y, p.z)){ // check block
				if(hasValidSurface(getAdjacents(p, world), data, world)) {
					valid.add(p);
			
				}
			}
		}
		return valid;
	}
	
	private static Point3D[] getAdjacents(Point3D p, World world) {
    	Point3D[] ret = new Point3D[6];
    	for(int i = 0; i < 6; i++) {
    		ForgeDirection d = ForgeDirection.VALID_DIRECTIONS[i];
    		ret[i] = new Point3D(p.x + d.offsetX, p.y + d.offsetY, p.z + d.offsetZ);
    	}
    	return ret;
    }
	
	private static Point3D[] getNeighbors(Point3D p, World world) {
		return p.add(AthsGlobal.NEIGHBORS);
	}
}
