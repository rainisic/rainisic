/* @(#) ArticleDao.java
 * Project:	plus
 * Package: com.rainisic.plus.dao
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.plus.dao;

import java.util.List;

import com.rainisic.plus.entity.Article;
import com.rainisic.plus.entity.Category;
import com.rainisic.plus.vo.Page;

/**
 * Article DAO interface.
 * 
 * @author rainisic
 * @version 1.0.0
 */
public interface ArticleDao {

	/**
	 * Get the article by id.
	 * 
	 * @param id
	 * @return
	 */
	public Article get(int id);

	/**
	 * Get the article by URL.
	 * 
	 * @param url
	 * @return
	 */
	public Article get(String url);

	/**
	 * Get the article list by category and page.
	 * 
	 * @param category
	 * @param page
	 * @return
	 */
	public List<Article> list(Category category, Page page);

	/**
	 * Get the article list by page.
	 * 
	 * @param page
	 * @return
	 */
	public List<Article> list(Page page);

	/**
	 * Save the transient article.
	 * 
	 * @param article
	 * @return
	 */
	public int save(Article transientArticle);

	/**
	 * Update the persistent article.
	 * 
	 * @param persistentArticle
	 */
	public void update(Article persistentArticle);
}
