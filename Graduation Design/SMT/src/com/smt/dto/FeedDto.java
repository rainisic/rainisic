/* @(#) FeedDto.java
 * 
 * Date: 2012-2-27
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smt.dto;

import java.io.Serializable;

import com.smt.entity.User;

/**
 * @author Rainisic
 *
 */
public class FeedDto implements Serializable {
	
	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;
	
	/** ID. */
	private int id;

	/** Author. */
	private User author;
	
	/** Feed content. */
	private String content;
	
	/** The reference feed. */
	private int reference = 0;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
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
	 * @param author the author to set
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
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the reference
	 */
	public int getReference() {
		return reference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(int reference) {
		this.reference = reference;
	}
}
