/* @(#) ArticleVo.java
 * Project:	blog
 * Package:	com.rainisic.blog.vo
 * Author:	Rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.blog.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.rainisic.blog.entity.Article;
import com.rainisic.blog.entity.Category;

/**
 * @author Rainisic
 * 
 */
public class ArticleVo {

	/** Article ID. */
	private int id;

	/** Title. */
	private String title;

	/** Summary */
	private String summary;

	/** Content */
	private String content;

	/** Category. */
	private Category category;

	/** Tags, split by comma. */
	private String tags;

	/** Article URL. */
	private String url;

	/** Publish time. */
	private String publishTime;

	/** The last modify time. */
	private String updateTime;

	public ArticleVo() {
	}

	public ArticleVo(Article article) {
		this.id = article.getId();
		this.title = article.getTitle();
		this.summary = article.getSummary();
		this.content = article.getContent();
		this.category = article.getCategory();
		this.tags = article.getTags();
		this.url = article.getUrl();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		this.publishTime = df.format(article.getPublishTime().getTime());
		this.updateTime = df.format(article.getUpdateTime().getTime());
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
	public String getPublishTime() {
		return publishTime;
	}

	/**
	 * @param publishTime
	 *            the publishTime to set
	 */
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	/**
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
