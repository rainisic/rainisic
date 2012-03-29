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

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.smms.dto.Page;
import com.smms.entity.Log;

/**
 * @author Rainisic
 * 
 */
@Repository
public class LogDao extends BaseDaoSupport implements LogDaoInterface {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smms.dao.LogDaoInterface#query(com.smms.dto.Page)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Log> query(Page page) {

		// Query and return logs by page.
		return (List<Log>) sessionFactory.getCurrentSession()
				.createCriteria(Log.class).setFirstResult(page.getStartIndex())
				.setMaxResults(page.getPageSize()).list();
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
