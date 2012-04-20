/* @(#) CommentServiceInterface.java
 *
 * Date: 2012-4-19
 * 
 * Author: Rainisic
 */
package com.smt.service;

import java.util.List;
import com.smt.entity.Comment;

/**
 * @author Rainisic
 *
 */
public interface CommentServiceInterface {

	public List<Comment> list(int id);
	
	public int publish(Comment comment); 
}
