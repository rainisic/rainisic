/* @(#) CategoryDao.java
 * Project:	blog
 * Package:	com.rainisic.blog.dao
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.blog.dao;

import java.util.List;
import com.rainisic.blog.entity.Category;

/**
 * The DAO interface of article.
 * 
 * @author rainisic
 * @version 1.0.0
 */
public interface CategoryDao {

	/**
	 * Save a new category.
	 * 
	 * @param category
	 *            A transient category object.
	 * @return Identifier.
	 */
	public Integer save(Category category);

	/**
	 * Update a persistent category.
	 * 
	 * @param category
	 *            The persistent category for update.
	 */
	public void update(Category category);

	/**
	 * Load category by ID.
	 * 
	 * @param id
	 *            Category ID.
	 * @return Persistent category.
	 */
	public Category load(int id);

	/**
	 * Get the category list.
	 * 
	 * @return Category list.
	 */
	public List<Category> list();
}
