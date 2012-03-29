/* @(#) UserDao.java
 * 
 * Date: 2012-2-17
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smt.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smt.entity.User;

/**
 * @author Rainisic
 * 
 */
@Repository
public class UserDao extends BaseDaoSupport implements UserDaoInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smms.dao.UserDaoInterface#query(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User query(String value) {

		// Get current session.
		Session session = this.sessionFactory.getCurrentSession();

		// Get the user by value.
		List<User> users = session
				.createCriteria(User.class)
				.add(Restrictions.or(Restrictions.eq("username", value),
						Restrictions.or(Restrictions.eq("email", value),
								Restrictions.eq("nickname", value)))).list();

		// Check and return result.
		return (users != null && users.size() > 0) ? users.get(0) : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smms.dao.UserDaoInterface#save(com.smms.entity.User)
	 */
	@Override
	public int save(User transientUser) {
		return (Integer) this.sessionFactory.getCurrentSession().save(
				transientUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smms.dao.UserDaoInterface#update(com.smms.entity.User)
	 */
	@Override
	public void update(User persistentUser) {

		// Get session and begin transaction.
		Session session = this.sessionFactory.getCurrentSession();

		// Update the persistent user.
		session.update(persistentUser);
	}
}
