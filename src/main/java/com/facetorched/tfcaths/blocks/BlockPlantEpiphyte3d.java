package com.facetorched.tfcaths.blocks;

import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.AthsGlobal;
import com.facetorched.tfcaths.WorldGen.Generators.AthsWorldGenPlants;
import com.facetorched.tfcaths.WorldGen.Generators.PlantSpawnData;
import com.facetorched.tfcaths.util.Point3D;

import net.minecraft.world.World;

public class BlockPlantEpiphyte3d extends BlockPlant3d{
	public BlockPlantEpiphyte3d() {
		super();
		this.renderId = AthsBlockSetup.plantEpiphyte3dRenderID;
	}
	
	/**
	 * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
	 */
	@Override
	public boolean canBlockStay(World world, int x, int y, int z){
		PlantSpawnData data = AthsWorldGenPlants.plantList.get(this.plantKey);
		if( data == null) {
			return false;
		}
		Point3D origin = new Point3D(x, y, z);
		for(Point3D p : origin.add(AthsGlobal.HORIZ_NEIGHBORS)) {
			
			// temporary fix ######################
			if(p.x >> 4 != x >> 4 || p.z >> 4 != z >> 4) {
				continue;
			}
			// ####################################
			
			else if(data.canGrowOnBlock(world.getBlock(p.x, p.y, p.z), world.getBlockMetadata(p.x, p.y, p.z))) {
				return true;
			}
		}
		return false;
	}
	
}
