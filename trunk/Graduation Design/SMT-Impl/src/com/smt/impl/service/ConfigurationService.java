/* @(#) ConfigurationService.java
 *
 * Date: 2012-4-17
 * 
 * Author: Rainisic
 */
package com.smt.impl.service;

import com.smt.impl.dao.ConfigurationDao;

/**
 * @author Rainisic
 *
 */
public class ConfigurationService {

	private static ConfigurationService configurationService;
	
	private ConfigurationService() {
	}

	public static ConfigurationService getInstance() {
		if (configurationService == null) {
			configurationService = new ConfigurationService();
		}
		return configurationService;
	}
	
	private ConfigurationDao configurationDao;
	
	public void changeTheme(String theme) {
		configurationDao.changeTheme(theme);
	}
}
