/* @(#) LogDao.java
 * 
 * Date: 2012-3-9
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smms.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.smms.entity.Log;

/**
 * @author Rainisic
 * 
 */
@Repository
public class LogDao extends BaseDaoSupport implements LogDaoInterface {
	
	/*
	 * (non-Javadoc)
	 * @see com.smms.dao.LogDaoInterface#list()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Log> list() {

		// Query and return logs by page.
		return (List<Log>) sessionFactory.getCurrentSession()
				.createCriteria(Log.class).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smms.dao.LogDaoInterface#save(com.smms.entity.Log)
	 */
	@Override
	public int save(Log log) {
		
		// Save and return the identifier.
		return (Integer) sessionFactory.getCurrentSession().save(log);
	}
}
