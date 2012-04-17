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
	
	private ConfigurationDao configurationDao = ConfigurationDao.getInstance();
	
	private ConfigurationService() {
	}

	public static ConfigurationService getInstance() {
		if (configurationService == null) {
			configurationService = new ConfigurationService();
		}
		return configurationService;
	}

	public void changeTheme(String theme) {
		configurationDao.changeTheme(theme);
	}
}
