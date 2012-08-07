/* @(#) ConfigPropLoaderListener.java
 * Project:	blog
 * Package: com.rainisic.blog.listener
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.blog.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.rainisic.blog.util.ApplicationConfiguration;

/**
 * Configuration properties file loader listener.
 * 
 * @author rainisic
 * @version 1.0.0
 */
public class ConfigPropLoaderListener implements ServletContextListener {

	/** Configuration file path. */
	private static final String CONFIG_FILE_PATH = "config.properties";

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * 
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
	 * .ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {

		// Load from classpath.
		ApplicationConfiguration.load(Thread.currentThread()
				.getContextClassLoader().getResourceAsStream(CONFIG_FILE_PATH));
	}
}
