/* @(#) CommentDao.java
 *
 * Date: 2012-4-19
 * 
 * Author: Rainisic
 */
package com.smt.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smt.entity.Comment;
import com.smt.entity.Feed;

/**
 * @author Rainisic
 * 
 */
@Repository
public class CommentDao extends BaseDaoSupport implements CommentDaoInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smt.dao.CommentDaoInterface#list(com.smt.entity.Feed)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> list(Feed feed) {
		
		// List the feeds by authors and page.
		List<Comment> comments = this.sessionFactory.getCurrentSession()
				.createCriteria(Comment.class)
				.add(Restrictions.eq("feed", feed)).list();
		
		// Check results.
		return (comments != null && comments.size() > 0) ? comments : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smt.dao.CommentDaoInterface#save(com.smt.entity.Comment)
	 */
	@Override
	public int save(Comment comment) {
		return (Integer) this.sessionFactory.getCurrentSession().save(comment);
	}

}
