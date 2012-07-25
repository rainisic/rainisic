/* @(#) ArticleVo.java
 * Project:	plus
 * Package: com.rainisic.plus.vo
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.plus.vo;

import java.text.SimpleDateFormat;

import com.rainisic.plus.entity.Article;
import com.rainisic.plus.entity.Category;

/**
 * @author rainisic
 * 
 */
public class ArticleVo {

	/** Category. */
	private Category category;

	/** Content */
	private String content;

	/** Article ID. */
	private int id;

	/** Publish time. */
	private String publishTime;

	/** Summary */
	private String summary;

	/** Tags, split by comma. */
	private String tags;

	/** Title. */
	private String title;

	/** Article URL. */
	private String url;

	/**
	 * Default constructor without parameter.
	 */
	public ArticleVo() {
	}

	/**
	 * Construct a new article VO by article entity.
	 * 
	 * @param article
	 */
	public ArticleVo(Article article) {

		// Put all values.
		this.category = article.getCategory();
		this.content = article.getContent();
		this.id = article.getId();
		this.publishTime = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss")
				.format(article.getPublishTime().getTime());
		this.summary = article.getSummary();
		this.tags = article.getTags();
		this.title = article.getTitle();
		this.url = article.getUrl();
	}

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
	public String getPublishTime() {
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
	 * @param category
	 *            the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param publishTime
	 *            the publishTime to set
	 */
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	/**
	 * @param summary
	 *            the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
