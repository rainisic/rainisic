/* @(#) FeedDao.java
 * 
 * Date: 2012-2-24
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smt.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smt.dto.Page;
import com.smt.entity.Feed;
import com.smt.entity.User;

/**
 * @author Rainisic
 * 
 */
@Repository
public class FeedDao extends BaseDaoSupport implements FeedDaoInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smms.dao.FeedDaoInterface#save(com.smms.entity.Feed)
	 */
	@Override
	public int save(Feed transientFeed) {
		return (Integer) this.sessionFactory.getCurrentSession().save(
				transientFeed);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smms.dao.FeedDaoInterface#query(int)
	 */
	@Override
	public Feed query(int id) {
		return (Feed) this.sessionFactory.getCurrentSession().get(Feed.class,
				id);
	}

	/* (non-Javadoc)
	 * @see com.smms.dao.FeedDaoInterface#list(com.smms.entity.User, com.smms.dto.Page)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Feed> list(User author, Page page) {
		
		// List the feeds by authors and page.
		List<Feed> feeds = this.sessionFactory.getCurrentSession()
				.createCriteria(Feed.class)
				.add(Restrictions.eq("author", author))
				.setFirstResult(page.getStartIndex())
				.setMaxResults(page.getPageSize()).list();
		
		// Check results.
		return (feeds != null && feeds.size() > 0) ? feeds : null;
	}

	/* (non-Javadoc)
	 * @see com.smms.dao.FeedDaoInterface#list(java.util.Collection, com.smms.util.Page)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Feed> list(Collection<User> authors, Page page) {
		
		// List the feeds by authors and page.
		List<Feed> feeds = this.sessionFactory.getCurrentSession()
				.createCriteria(Feed.class)
				.add(Restrictions.in("author", authors))
				.setFirstResult(page.getStartIndex())
				.setMaxResults(page.getPageSize()).list();
		
		// Check results.
		return (feeds != null && feeds.size() > 0) ? feeds : null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smms.dao.FeedDaoInterface#update(com.smms.entity.Feed)
	 */
	@Override
	public void update(Feed persistentFeed) {
		this.sessionFactory.getCurrentSession().update(persistentFeed);
	}
}
