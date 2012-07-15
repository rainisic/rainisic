/* @(#) Article.java
 * Project:	blog
 * Package:	com.rainisic.blog.entity
 * Author:	Rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.blog.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

/**
 * @author Rainisic
 * 
 */
@Entity
public class Article {

	/**
	 * The constants of visibility.<br />
	 * Always on top.
	 */
	public static final int TOP = 2;

	/**
	 * The constants of visibility.<br />
	 * The article visible.
	 */
	public static final int NORMAL = 1;

	/**
	 * The constants of visibility.<br />
	 * The article invisible.
	 */
	public static final int HIDDEN = 0;

	// ID, Primary key.
	@Id
	@GeneratedValue
	private int id;

	// Article title.
	private String title;

	// Content(Support rich text).
	@Column(columnDefinition = "text")
	private String content;

	// Article categories.
	@JoinColumn
	@ManyToMany
	private List<Category> categories;

	// The fake URL path(For URL rewrite).
	private String fakePath;

	// Article visibility.
	private int visibility;

	// Article tags(For SEO, etc.).
	private String tags;

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
	 * @return the categories
	 */
	public List<Category> getCategories() {
		return categories;
	}

	/**
	 * @param categories
	 *            the categories to set
	 */
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	/**
	 * @return the fakePath
	 */
	public String getFakePath() {
		return fakePath;
	}

	/**
	 * @param fakePath
	 *            the fakePath to set
	 */
	public void setFakePath(String fakePath) {
		this.fakePath = fakePath;
	}

	/**
	 * @return the visibility
	 */
	public int getVisibility() {
		return visibility;
	}

	/**
	 * @param visibility
	 *            the visibility to set
	 */
	public void setVisibility(int visibility) {
		this.visibility = visibility;
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
}
