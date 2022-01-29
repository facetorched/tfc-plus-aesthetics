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
    
    public Point3D add(Point3D p1, Point3D p2) {
    	return new Point3D(p1.x + p2.x, p1.y + p2.y, p1.z + p2.z); // make new point
    }
    
    public Point3D[] add(Point3D[] points) {
    	Point3D[] ret = new Point3D[points.length];
    	for(int i = 0; i < points.length; i++) {
    		ret[i] = add(points[i], this);
    	}
    	return ret;
    }
    
    @Override
    public String toString() {
    	return "("+ this.x + "," + this.y + "," + this.z + ")";
    }
    
    @Override
    public boolean equals(Object o) {
    	super.equals(o);
    	if(o.getClass() == Point3D.class) {
    		Point3D p = (Point3D)o;
    		return 	p.x == this.x &&
    				p.y == this.y &&
    				p.z == this.z;
    	}
    	return false;
    }
}
