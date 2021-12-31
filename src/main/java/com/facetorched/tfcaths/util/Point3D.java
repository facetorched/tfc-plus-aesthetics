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
    
    public Point3D invert() {
    	this.x *= -1;
    	this.y *= -1;
    	this.z *= -1;
    	return this;
    }
    
    public Point3D step(Point3D dof, int direction){
		switch(direction){
		case 1: 
			this.x += dof.x;
			break;
		case 2: 
			this.y += dof.y;
			break;
		case 3: 
			this.z += dof.z;
			break;
		}
		return this;
	}

}
