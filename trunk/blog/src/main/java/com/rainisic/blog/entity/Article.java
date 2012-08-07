/* @(#) Article.java
 * Project:	blog
 * Package: com.rainisic.blog.entity
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.blog.entity;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.rainisic.blog.entity.Category;

/**
 * The entity class of article.
 * 
 * @author rainisic
 * @version 1.0.0
 */
@Entity
public class Article {

	/** Article ID. */
	@Id
	@GeneratedValue
	private int id;

	/** Title. */
	private String title;

	/** Summary */
	private String summary;

	/** Content */
	private String content;

	/** Category. */
	@JoinColumn
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;

	/** Tags, split by comma. */
	private String tags;

	/** Article URL. */
	private String url;

	/** Publish time. */
	private Calendar publishTime;

	/** The last modify time. */
	private Calendar updateTime;

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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
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
	 * @param summary
	 *            the summary to set
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
	 * @param content
	 *            the content to set
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
	 * @param category
	 *            the category to set
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
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the publishTime
	 */
	public Calendar getPublishTime() {
		return publishTime;
	}

	/**
	 * @param publishTime
	 *            the publishTime to set
	 */
	public void setPublishTime(Calendar publishTime) {
		this.publishTime = publishTime;
	}

	/**
	 * @return the updateTime
	 */
	public Calendar getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Calendar updateTime) {
		this.updateTime = updateTime;
	}
}
