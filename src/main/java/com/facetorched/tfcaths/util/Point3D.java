package com.facetorched.tfcaths.util;

public class Point3D {

	public int x;
	public int y;
	public int z;

    public Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public int getDistSq(Point3D point) {
    	return  (this.x - point.x) * (this.x - point.x) + 
    			(this.y - point.y) * (this.y - point.y) + 
    			(this.z - point.z) * (this.z - point.z);
    }

}
