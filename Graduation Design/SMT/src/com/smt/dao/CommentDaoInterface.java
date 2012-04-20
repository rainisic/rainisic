/* @(#) CommentDaoInterface.java
 *
 * Date: 2012-4-19
 * 
 * Author: Rainisic
 */
package com.smt.dao;

import java.util.List;

import com.smt.entity.Comment;
import com.smt.entity.Feed;

/**
 * @author Rainisic
 *
 */
public interface CommentDaoInterface {

	public List<Comment> list(Feed feed);
	
	public int save(Comment comment); 
}
