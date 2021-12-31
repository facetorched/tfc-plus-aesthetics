package com.facetorched.tfcaths.util;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.util.ForgeDirection;

public class Point3DD {
	public double x;
	public double y;
	public double z;

    public Point3DD(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public double getDistSq(Point3DD point) {
    	return  (this.x - point.x) * (this.x - point.x) + 
    			(this.y - point.y) * (this.y - point.y) + 
    			(this.z - point.z) * (this.z - point.z);
    }
    
    public Point3DD rotateToFace(ForgeDirection d) {
    	double i = 0, j = 0, k = 0;
    	if(AthsParser.isNegativeDirection(d)) {
    		i = j = k = 1;
    	}
    	int dx = d.offsetX;
		int dy = d.offsetY;
		int dz = d.offsetZ;
		i += dy * x + dx * y + dz * z;
		j += dy * y + dx * z + dz * x;
		k += dy * z + dx * x + dz * y;
		x = i;
		y = j;
		z = k;
		return this;
    }
    
    public Point3DD rotate90Clockwise() {
    	double tempx = x;
    	x = -y;
    	y = tempx;
    	return this;
    }
    
    public Point3DD rotate90Clockwise(int iterations) {
    	for(int i = 0; i < iterations; i++) {
    		rotate90Clockwise();
    	}
    	return this;
    }
    
    public Point3DD mirrorHorizontal(double offset) {
    	x = offset - x;
    	return this;
    }
    
    public Point3DD translate(double dx, double dy, double dz) {
    	x += dx;
    	y += dy;
    	z += dz;
    	return this;
    }

}
