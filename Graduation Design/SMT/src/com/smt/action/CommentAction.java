/* @(#) CommentAction.java
 *
 * Date: 2012-4-19
 * 
 * Author: Rainisic
 */
package com.smt.action;

import java.util.Calendar;
import java.util.List;

import com.smt.entity.Comment;
import com.smt.entity.Feed;
import com.smt.entity.User;
import com.smt.service.CommentServiceInterface;
import com.smt.util.Constants;

/**
 * @author Rainisic
 * 
 */
public class CommentAction extends BaseActionSupport {

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;
	
	private CommentServiceInterface commentService;
	
	private Comment comment;
	
	private List<Comment> comments;
	
	private Feed feed;

	public String publish() {
		
		User user = (User) session.get(Constants.LOGIN_USER);
		if (user != null) {
			comment.setAuthor(user);
			comment.setCreateTime(Calendar.getInstance());
			comment.setFeed(feed);
			int result = commentService.publish(comment);
			if (result > 0) {
				comment.setId(result);
			}
			return "success";
		}
		return null;
	}

	public String list() {
		
		if (feed.getId() > 0) {
			comments = commentService.list(feed.getId());
			return "success";
		}
		return null;
	}

	/**
	 * @return the comment
	 */
	public Comment getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(Comment comment) {
		this.comment = comment;
	}

	/**
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * @return the commentService
	 */
	public CommentServiceInterface getCommentService() {
		return commentService;
	}

	/**
	 * @param commentService the commentService to set
	 */
	public void setCommentService(CommentServiceInterface commentService) {
		this.commentService = commentService;
	}

	/**
	 * @return the feed
	 */
	public Feed getFeed() {
		return feed;
	}

	/**
	 * @param feed the feed to set
	 */
	public void setFeed(Feed feed) {
		this.feed = feed;
	}
}
