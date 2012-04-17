/* @(#) ConfigurationService.java
 *
 * Date: 2012-4-17
 * 
 * Author: Rainisic
 */
package com.smt.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smt.dao.ConfigurationDaoInterface;
import com.smt.entity.Configuration;

/**
 * @author Rainisic
 *
 */
@Service
public class ConfigurationService implements ConfigurationServiceInterface {
	
	@Resource
	private ConfigurationDaoInterface configurationDao;

	/* (non-Javadoc)
	 * @see com.smt.service.ConfigurationServiceInterface#load()
	 */
	@Override
	public Configuration load() {
		return configurationDao.load();
	}
}
