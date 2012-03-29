/* @(#) AdminDao.java
 * 
 * Date: 2012-3-9
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smms.dao;

import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.smms.entity.Admin;

/**
 * @author Rainisic
 * 
 */
@Repository
public class AdminDao extends BaseDaoSupport implements AdminDaoInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smms.dao.AdminDaoInterface#query(com.smms.entity.Admin)
	 */
	@Override
	public Admin query(Admin admin) {

		// Get current session and query by username.
		List<?> admins = sessionFactory.getCurrentSession()
				.createCriteria(Admin.class)
				.add(Restrictions.eq("username", admin.getUsername()))
				.add(Restrictions.eq("password", admin.getPassword())).list();

		// Check and return query result.
		if (admins != null && admins.size() > 0) {
			return (Admin) admins.get(0);
		}
		return null;
	}

}
