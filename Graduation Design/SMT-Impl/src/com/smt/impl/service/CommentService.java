/* @(#) CommentService.java
 *
 * Date: 2012-4-4
 * 
 * Author: Rainisic
 */
package com.smt.impl.service;

import java.util.List;
import com.smt.impl.dao.CommentDao;
import com.smt.impl.entity.Comment;

/**
 * @author Rainisic
 * 
 */
public class CommentService {

	/** Define the singleton comment service. */
	private static CommentService commentService;

	/** Get the instance of comment DAO. */
	private CommentDao commentDao = CommentDao.getInstance();

	/**
	 * Private constructor.
	 */
	private CommentService() {
	}

	/**
	 * Get the instance of comment service.
	 * 
	 * @return
	 */
	public static CommentService getInstance() {
		if (commentService == null) {
			commentService = new CommentService();
		}
		return commentService;
	}

	/**
	 * List all comments.
	 * 
	 * @return
	 */
	public List<Comment> list() {
		return commentDao.list();
	}
}
