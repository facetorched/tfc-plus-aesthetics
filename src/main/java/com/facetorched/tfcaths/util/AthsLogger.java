package com.facetorched.tfcaths.util;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;

//basic class for logging information. I copied this from IE
public class AthsLogger {
	public static boolean debug = false;
	  
	  public static void log(Level logLevel, Object object) {
	    FMLLog.log("TFC+ Aesthetics", logLevel, String.valueOf(object), new Object[0]);
	  }
	  
	  public static void error(Object object) {
	    log(Level.ERROR, object);
	  }
	  
	  public static void info(Object object) {
	    log(Level.INFO, object);
	  }
	  
	  public static void warn(Object object) {
	    log(Level.WARN, object);
	  }
	  
	  public static void debug(Object object) {}
}
