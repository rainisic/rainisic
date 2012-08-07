/* @(#) ArticleDao.java
 * Project:	blog
 * Package: com.rainisic.blog.dao
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.blog.dao;

import java.util.List;
import com.rainisic.blog.entity.Article;
import com.rainisic.blog.entity.Category;
import com.rainisic.blog.vo.Page;

/**
 * The DAO interface of article.
 * 
 * @author rainisic
 * @version 1.0.0
 */
public interface ArticleDao {

	/**
	 * Save a new article.
	 * 
	 * @param article
	 *            A transient article object.
	 * @return Identifier.
	 */
	public Integer save(Article article);

	/**
	 * Update a persistent article.
	 * 
	 * @param article
	 *            The persistent article for update.
	 */
	public void update(Article article);

	/**
	 * Load article by ID.
	 * 
	 * @param id
	 *            Article ID.
	 * @return Persistent article.
	 */
	public Article load(int id);

	/**
	 * Load article by URL.
	 * 
	 * @param url
	 *            The URL of article.
	 * @return Persistent article.
	 */
	public Article load(String url);

	/**
	 * Get the paged article list.
	 * 
	 * @param page
	 *            The article page.
	 * @return Article list.
	 */
	public List<Article> list(Page page);

	/**
	 * Get the article list. Query with category and page with page.
	 * 
	 * @param category
	 *            Persistent article category.
	 * @param page
	 *            The article page.
	 * @return Article list.
	 */
	public List<Article> list(Category category, Page page);

	/**
	 * Load article total page count.
	 * 
	 * @return
	 */
	public Integer loadTotalCount();

}
