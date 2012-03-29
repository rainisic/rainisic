/* @(#) Feed.java
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

import com.smt.dto.FeedDto;

/**
 * @author Rainisic
 * 
 */
@Entity
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
	@Id
	@GeneratedValue
	private int id;

	/** Author. */
	@ManyToOne
	@JoinColumn(nullable = false)
	private User author;

	/** Content. */
	@Column(nullable = false)
	private String content;

	/** Reference feed. */
	@ManyToOne
	private Feed reference;

	/** Publish time. */
	@Column(nullable = false)
	private Calendar createTime;

	/** Feed status. */
	@Column(nullable = false, columnDefinition = "int default 0")
	private int status;

	/**
	 * Default constructor.
	 */
	public Feed() {
	}

	/**
	 * Constructor with DTO.
	 * 
	 * @param feedDto
	 */
	public Feed(FeedDto feedDto) {
		this.id = feedDto.getId();
		this.author = feedDto.getAuthor();
		this.content = feedDto.getContent();
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
