/* @(#) ConfigurationDao.java
 *
 * Date: 2012-4-17
 * 
 * Author: Rainisic
 */
package com.smt.dao;

import org.springframework.stereotype.Repository;
import com.smt.entity.Configuration;

/**
 * @author Rainisic
 * 
 */
@Repository
public class ConfigurationDao extends BaseDaoSupport implements
		ConfigurationDaoInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smt.dao.ConfigurationDaoInterface#load()
	 */
	@Override
	public Configuration load() {
		return (Configuration) this.sessionFactory.getCurrentSession()
				.createCriteria(Configuration.class).list().iterator().next();
	}
}
