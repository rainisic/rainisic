/* @(#) Comment.java
 * 
 * Date: 2012-2-17
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smt.impl.entity;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Rainisic
 * 
 */
public class Comment implements Serializable {

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

	/** Comment ID. */
	private int id;

	/** Comment author. */
	private User author;

	/** Feed. */
	private Feed feed;

	/** Content. */
	private String content;

	/** Publish time. */
	private Calendar createTime;

	/** Comment status.(0:normal | 1:delete by user | 2:delete by administrator) */
	private int status = 0;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the author
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(User author) {
		this.author = author;
	}

	/**
	 * @return the feed
	 */
	public Feed getFeed() {
		return feed;
	}

	/**
	 * @param feed
	 *            the feed to set
	 */
	public void setFeed(Feed feed) {
		this.feed = feed;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the createTime
	 */
	public Calendar getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
}
