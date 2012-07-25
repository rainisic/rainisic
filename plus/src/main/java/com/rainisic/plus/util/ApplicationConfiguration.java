/* @(#) ApplicationConfiguration.java
 * Project:	plus
 * Package: com.rainisic.plus.util;
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.plus.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Load application configuration from properties file.
 * 
 * @author rainisic
 * @version 1.0.0
 */
public class ApplicationConfiguration {

	/** Define the configuration file name. */
	private static final String CONFIG_FILE = "config.properties";

	/** Define the static properties object. */
	private static Properties prop;

	/**
	 * Default constructor.
	 */
	public static void load() {

		// Instance a properties.
		prop = new Properties();

		InputStream inStream = null;

		try {

			inStream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(CONFIG_FILE);

			// Load properties from file.
			prop.load(inStream);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			// Close input stream.
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					inStream = null;
				}
			}
		}
	}

	/**
	 * Get property by key.
	 * 
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		return prop.getProperty(key);
	}

}
