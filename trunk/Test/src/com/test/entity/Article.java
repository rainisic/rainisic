/* @(#) Article.java
 * Project:	Test
 * Package:	com.test.entity
 * Author:	Rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.test.entity;

import java.util.Calendar;

/**
 * @author Rainisic
 *
 */
public class Article {
	
	private int id;
	
	private String title;
	
	private String summary;
	
	private String content;
	
	private Category category;

	private String tags;
	
	private Calendar publishTime;

	private String fakepath;

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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
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
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * @return the publishTime
	 */
	public Calendar getPublishTime() {
		return publishTime;
	}

	/**
	 * @param publishTime the publishTime to set
	 */
	public void setPublishTime(Calendar publishTime) {
		this.publishTime = publishTime;
	}

	/**
	 * @return the fakepath
	 */
	public String getFakepath() {
		return fakepath;
	}

	/**
	 * @param fakepath the fakepath to set
	 */
	public void setFakepath(String fakepath) {
		this.fakepath = fakepath;
	}
}
