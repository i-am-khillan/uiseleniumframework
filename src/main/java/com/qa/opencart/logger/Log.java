package com.qa.opencart.logger;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Log {
	
	
	//add dependencies
	/**
	 * org.apache.logging.log4j
	 * log4j-core
	 */
	private static final Logger logger=LogManager.getLogger(Log.class);
	public static void info(String message) {
		logger.info(message);
	}
	public static void error(String message) {
		logger.error(message);
	}
	public static void error(String message,Exception e) {
		logger.info(message,e);
	}
	public static void debug(String message) {
		logger.debug(message);
	}
	public static void warn(String message) {
		logger.warn(message);
	}
	


}
