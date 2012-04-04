/* @(#) Feed.java
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
public class Feed implements Serializable {

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

	/** Normal. */
	public static final int NORMAL = 0;

	/** Delete by user. */
	public static final int DELETE_BY_USER = 1;

	/** Delete by administrator. */
	public static final int DELETE_BY_ADMIN = 2;

	/** ID. */
	private int id;

	/** Author. */
	private User author;

	/** Content. */
	private String content;

	/** Reference feed. */
	private Feed reference;

	/** Publish time. */
	private Calendar createTime;

	/** Feed status. */
	private int status;

	/**
	 * Default constructor.
	 */
	public Feed() {
	}

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
	 * @return the reference
	 */
	public Feed getReference() {
		return reference;
	}

	/**
	 * @param reference
	 *            the reference to set
	 */
	public void setReference(Feed reference) {
		this.reference = reference;
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
