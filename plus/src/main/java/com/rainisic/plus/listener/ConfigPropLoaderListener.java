/* @(#) ConfigPropLoaderListener.java
 * Project:	plus
 * Package: com.rainisic.plus.listener
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.plus.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.rainisic.plus.util.ApplicationConfiguration;

/**
 * @author rainisic
 * 
 */
public class ConfigPropLoaderListener implements ServletContextListener {

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
		
		// Load application configuration.
		ApplicationConfiguration.load();
	}
}
