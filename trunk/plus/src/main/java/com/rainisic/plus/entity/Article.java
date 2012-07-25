/* @(#) Article.java
 * Project:	plus
 * Package: com.rainisic.plus.entity
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.plus.entity;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The entity class of article.
 * @author rainisic
 * @version 1.0.0
 */
@Entity
public class Article {

	/** Category. */
	@JoinColumn
	@ManyToOne
	private Category category;
	
	/** Content */
	private String content;
	
	/** Article ID. */
	@Id
	@GeneratedValue
	private int id;
	
	/** Publish time. */
	private Calendar publishTime;
	
	/** Summary */
	private String summary;
	
	/** Tags, split by comma. */
	private String tags;
	
	/** Title. */
	private String title;
	
	/** Article URL. */
	private String url;

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the publishTime
	 */
	public Calendar getPublishTime() {
		return publishTime;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param publishTime the publishTime to set
	 */
	public void setPublishTime(Calendar publishTime) {
		this.publishTime = publishTime;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
