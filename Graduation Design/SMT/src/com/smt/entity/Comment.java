/* @(#) Comment.java
 * 
 * Date: 2012-2-17
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smt.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Rainisic
 * 
 */
@Entity
public class Comment implements Serializable {

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

	/** Comment ID. */
	@Id
	@GeneratedValue
	private int id;

	/** Comment author. */
	@ManyToOne
	@JoinColumn(nullable = false)
	private User author;

	/** Feed. */
	@ManyToOne
	@JoinColumn(nullable = false)
	private Feed feed;

	/** Content. */
	@Column(nullable = false)
	private String content;

	/** Publish time. */
	@Column(nullable = false)
	private Calendar createTime;

	/** Comment status.(0:normal | 1:delete by user | 2:delete by administrator) */
	@Column(columnDefinition = "int default 0")
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
