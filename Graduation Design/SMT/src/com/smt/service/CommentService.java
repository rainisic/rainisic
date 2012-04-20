/* @(#) CommentService.java
 *
 * Date: 2012-4-19
 * 
 * Author: Rainisic
 */
package com.smt.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.smt.dao.CommentDaoInterface;
import com.smt.dao.FeedDaoInterface;
import com.smt.entity.Comment;

/**
 * @author Rainisic
 *
 */
@Service
public class CommentService implements CommentServiceInterface {
	
	@Resource
	private CommentDaoInterface commentDao;
	
	@Resource
	private FeedDaoInterface feedDao;

	/*
	 * (non-Javadoc)
	 * @see com.smt.service.CommentServiceInterface#list(int)
	 */
	@Override
	@Transactional
	public List<Comment> list(int id) {
		return commentDao.list(feedDao.query(id));
	}

	/* (non-Javadoc)
	 * @see com.smt.service.CommentServiceInterface#publish(com.smt.entity.Comment)
	 */
	@Override
	@Transactional
	public int publish(Comment comment) {
		comment.setFeed(feedDao.query(comment.getFeed().getId()));
		return commentDao.save(comment);
	}

}
