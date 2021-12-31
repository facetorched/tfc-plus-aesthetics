package com.facetorched.tfcaths.render.blocks;

import java.util.Random;

import com.facetorched.tfcaths.util.Point3DD;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

public class AthsRenderBlocks {
	/**
     * Custom Implementation of Render block crops implementation (modified cpw's code)
     */
    public static void renderBlockCrops(IIcon icon, double x, double y, double z, float scale, Random random){
        Tessellator tessellator = Tessellator.instance;
        
        double d3;
        double d5;
        // randomly mirror the texture!
        if(random.nextBoolean()) {
        	d3 = (double)icon.getMinU();
            d5 = (double)icon.getMaxU();
        }
        else {
        	d3 = (double)icon.getMaxU();
            d5 = (double)icon.getMinU();
        }
        double d4 = (double)icon.getMinV();
        double d6 = (double)icon.getMaxV();
        double d7 = x + 0.5D - 0.25D;
        double d8 = x + 0.5D + 0.25D;
        double d9 = z + 0.5D - 0.5D * (double)scale;
        double d10 = z + 0.5D + 0.5D * (double)scale;
        tessellator.addVertexWithUV(d7, y + (double)scale, d9, d3, d4);
        tessellator.addVertexWithUV(d7, y + 0.0D, d9, d3, d6);
        tessellator.addVertexWithUV(d7, y + 0.0D, d10, d5, d6);
        tessellator.addVertexWithUV(d7, y + (double)scale, d10, d5, d4);
        tessellator.addVertexWithUV(d7, y + (double)scale, d10, d3, d4);
        tessellator.addVertexWithUV(d7, y + 0.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d7, y + 0.0D, d9, d5, d6);
        tessellator.addVertexWithUV(d7, y + (double)scale, d9, d5, d4);
        tessellator.addVertexWithUV(d8, y + (double)scale, d10, d3, d4);
        tessellator.addVertexWithUV(d8, y + 0.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d8, y + 0.0D, d9, d5, d6);
        tessellator.addVertexWithUV(d8, y + (double)scale, d9, d5, d4);
        tessellator.addVertexWithUV(d8, y + (double)scale, d9, d3, d4);
        tessellator.addVertexWithUV(d8, y + 0.0D, d9, d3, d6);
        tessellator.addVertexWithUV(d8, y + 0.0D, d10, d5, d6);
        tessellator.addVertexWithUV(d8, y + (double)scale, d10, d5, d4);
        d7 = x + 0.5D - 0.5D * (double)scale;
        d8 = x + 0.5D + 0.5D * (double)scale;
        d9 = z + 0.5D - 0.25D;
        d10 = z + 0.5D + 0.25D;
        tessellator.addVertexWithUV(d7, y + (double)scale, d9, d3, d4);
        tessellator.addVertexWithUV(d7, y + 0.0D, d9, d3, d6);
        tessellator.addVertexWithUV(d8, y + 0.0D, d9, d5, d6);
        tessellator.addVertexWithUV(d8, y + (double)scale, d9, d5, d4);
        tessellator.addVertexWithUV(d8, y + (double)scale, d9, d3, d4);
        tessellator.addVertexWithUV(d8, y + 0.0D, d9, d3, d6);
        tessellator.addVertexWithUV(d7, y + 0.0D, d9, d5, d6);
        tessellator.addVertexWithUV(d7, y + (double)scale, d9, d5, d4);
        tessellator.addVertexWithUV(d8, y + (double)scale, d10, d3, d4);
        tessellator.addVertexWithUV(d8, y + 0.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d7, y + 0.0D, d10, d5, d6);
        tessellator.addVertexWithUV(d7, y + (double)scale, d10, d5, d4);
        tessellator.addVertexWithUV(d7, y + (double)scale, d10, d3, d4);
        tessellator.addVertexWithUV(d7, y + 0.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d8, y + 0.0D, d10, d5, d6);
        tessellator.addVertexWithUV(d8, y + (double)scale, d10, d5, d4);
    }
    
    /**
     * Utility function to layer on the ground
     */
    public static void drawGroundLayer(IIcon icon, double x, double y, double z, float h, int rotation, int rgb) {
    	Tessellator tessellator = Tessellator.instance;
    	
    	double d0 = (double)icon.getMinU();
        double d1 = (double)icon.getMinV();
        double d2 = (double)icon.getMaxU();
        double d3 = (double)icon.getMaxV();
        float f1 = (float)x + 0.5F;
        float f2 = (float)z + 0.5F;
        float f3 = (float)(rotation & 1) * 0.5F * (float)(1 - rotation / 2 % 2 * 2);
        float f4 = (float)(rotation + 1 & 1) * 0.5F * (float)(1 - (rotation + 1) / 2 % 2 * 2);
		tessellator.setColorOpaque_I(rgb);
        tessellator.addVertexWithUV((double)(f1 + f3 - f4), (double)((float)y + h), (double)(f2 + f3 + f4), d0, d1);
        tessellator.addVertexWithUV((double)(f1 + f3 + f4), (double)((float)y + h), (double)(f2 - f3 + f4), d2, d1);
        tessellator.addVertexWithUV((double)(f1 - f3 + f4), (double)((float)y + h), (double)(f2 - f3 - f4), d2, d3);
        tessellator.addVertexWithUV((double)(f1 - f3 - f4), (double)((float)y + h), (double)(f2 + f3 - f4), d0, d3);
        tessellator.setColorOpaque_I((rgb & 16711422) >> 1);
        tessellator.addVertexWithUV((double)(f1 - f3 - f4), (double)((float)y + h), (double)(f2 + f3 - f4), d0, d3);
        tessellator.addVertexWithUV((double)(f1 - f3 + f4), (double)((float)y + h), (double)(f2 - f3 - f4), d2, d3);
        tessellator.addVertexWithUV((double)(f1 + f3 + f4), (double)((float)y + h), (double)(f2 - f3 + f4), d2, d1);
        tessellator.addVertexWithUV((double)(f1 + f3 - f4), (double)((float)y + h), (double)(f2 + f3 + f4), d0, d1);
    }
    
    /**
     * Utility function to layer on the ground
     */
    public static void drawLayer(IIcon icon, double x, double y, double z, float h, int rotation, int rgb) {
    	Tessellator tessellator = Tessellator.instance;
    	
    	double d0 = (double)icon.getMinU();
        double d1 = (double)icon.getMinV();
        double d2 = (double)icon.getMaxU();
        double d3 = (double)icon.getMaxV();
        float f1 = (float)x + 0.5F;
        float f2 = (float)z + 0.5F;
        float f3 = (float)(rotation & 1) * 0.5F * (float)(1 - rotation / 2 % 2 * 2);
        float f4 = (float)(rotation + 1 & 1) * 0.5F * (float)(1 - (rotation + 1) / 2 % 2 * 2);
		tessellator.setColorOpaque_I(rgb);
        tessellator.addVertexWithUV((double)(f1 + f3 - f4), (double)((float)y + h), (double)(f2 + f3 + f4), d0, d1);
        tessellator.addVertexWithUV((double)(f1 + f3 + f4), (double)((float)y + h), (double)(f2 - f3 + f4), d2, d1);
        tessellator.addVertexWithUV((double)(f1 - f3 + f4), (double)((float)y + h), (double)(f2 - f3 - f4), d2, d3);
        tessellator.addVertexWithUV((double)(f1 - f3 - f4), (double)((float)y + h), (double)(f2 + f3 - f4), d0, d3);
        tessellator.setColorOpaque_I((rgb & 16711422) >> 1);
        tessellator.addVertexWithUV((double)(f1 - f3 - f4), (double)((float)y + h), (double)(f2 + f3 - f4), d0, d3);
        tessellator.addVertexWithUV((double)(f1 - f3 + f4), (double)((float)y + h), (double)(f2 - f3 - f4), d2, d3);
        tessellator.addVertexWithUV((double)(f1 + f3 + f4), (double)((float)y + h), (double)(f2 - f3 + f4), d2, d1);
        tessellator.addVertexWithUV((double)(f1 + f3 - f4), (double)((float)y + h), (double)(f2 + f3 + f4), d0, d1);
    }
    
    /**
     * Utility function to draw crossed squares (Aths edition)
     */
    public static void drawCrossedSquares(IIcon icon, double x, double y, double z, float scale, Random random){
        Tessellator tessellator = Tessellator.instance;
        double d3;
        double d5;
        // randomly mirror the texture!
        if(random.nextBoolean()) {
        	d3 = (double)icon.getMinU();
            d5 = (double)icon.getMaxU();
        }
        else {
        	d3 = (double)icon.getMaxU();
            d5 = (double)icon.getMinU();
        }
        
        double d4 = (double)icon.getMinV();
        double d6 = (double)icon.getMaxV();
        
        double d7 = 0.45D * (double)scale;
        double d8 = x + 0.5D - d7;
        double d9 = x + 0.5D + d7;
        double d10 = z + 0.5D - d7;
        double d11 = z + 0.5D + d7;
        tessellator.addVertexWithUV(d8, y + (double)scale, d10, d3, d4);
        tessellator.addVertexWithUV(d8, y + 0.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d9, y + 0.0D, d11, d5, d6);
        tessellator.addVertexWithUV(d9, y + (double)scale, d11, d5, d4);
        tessellator.addVertexWithUV(d9, y + (double)scale, d11, d3, d4);
        tessellator.addVertexWithUV(d9, y + 0.0D, d11, d3, d6);
        tessellator.addVertexWithUV(d8, y + 0.0D, d10, d5, d6);
        tessellator.addVertexWithUV(d8, y + (double)scale, d10, d5, d4);
        tessellator.addVertexWithUV(d8, y + (double)scale, d11, d3, d4);
        tessellator.addVertexWithUV(d8, y + 0.0D, d11, d3, d6);
        tessellator.addVertexWithUV(d9, y + 0.0D, d10, d5, d6);
        tessellator.addVertexWithUV(d9, y + (double)scale, d10, d5, d4);
        tessellator.addVertexWithUV(d9, y + (double)scale, d10, d3, d4);
        tessellator.addVertexWithUV(d9, y + 0.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d8, y + 0.0D, d11, d5, d6);
        tessellator.addVertexWithUV(d8, y + (double)scale, d11, d5, d4);
    }
    
    /**
     * Utility function to draw crossed squares in a certain direction
     */
    public static void drawCrossedSquares(IIcon icon, double x, double y, double z, float scale, Random random, ForgeDirection d){
        Tessellator tessellator = Tessellator.instance;
        double uMin;
        double uMax;
        // randomly mirror the texture!
        if(random.nextBoolean()) {
        	uMin = (double)icon.getMinU();
            uMax = (double)icon.getMaxU();
        }
        else {
        	uMin = (double)icon.getMaxU();
            uMax = (double)icon.getMinU();
        }
        
        double vMin = (double)icon.getMinV();
        double vMax = (double)icon.getMaxV();
        
        double r = 0.45D * (double)scale;
        double min = 0.5D - r;
        double max = 0.5D + r;
        Point3DD xYz = new Point3DD(min, scale, min).rotateToFace(d).translate(x, y, z);
        Point3DD xyz = new Point3DD(min, 0, min).rotateToFace(d).translate(x, y, z);
        Point3DD XyZ = new Point3DD(max, 0, max).rotateToFace(d).translate(x, y, z);
        Point3DD XYZ = new Point3DD(max, scale, max).rotateToFace(d).translate(x, y, z);
        
        Point3DD xYZ = new Point3DD(min, scale, max).rotateToFace(d).translate(x, y, z);
        Point3DD xyZ = new Point3DD(min, 0, max).rotateToFace(d).translate(x, y, z);
        Point3DD Xyz = new Point3DD(max, 0, min).rotateToFace(d).translate(x, y, z);
        Point3DD XYz = new Point3DD(max, scale, min).rotateToFace(d).translate(x, y, z);
        
        drawQuad(tessellator, d, xYz, xyz, XyZ, XYZ, uMin, uMax, vMin, vMax);
        drawQuad(tessellator, d, XYZ, XyZ, xyz, xYz, uMin, uMax, vMin, vMax);
        drawQuad(tessellator, d, xYZ, xyZ, Xyz, XYz, uMin, uMax, vMin, vMax);
        drawQuad(tessellator, d, XYz, Xyz, xyZ, xYZ, uMin, uMax, vMin, vMax);
    }
    
    private static void drawQuad(Tessellator tessellator, ForgeDirection d, Point3DD p1, Point3DD p2, Point3DD p3, Point3DD p4, double uMin, double uMax, double vMin, double vMax) {
    	tessellator.addVertexWithUV(p1.x, p1.y, p1.z, uMin, vMin);
    	tessellator.addVertexWithUV(p2.x, p2.y, p2.z, uMin, vMax);
    	tessellator.addVertexWithUV(p3.x, p3.y, p3.z, uMax, vMax);
    	tessellator.addVertexWithUV(p4.x, p4.y, p4.z, uMax, vMin);
    }
}
