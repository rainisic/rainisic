/* @(#) CategoryService.java
 * Project:	blog
 * Package:	com.rainisic.blog.service
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.blog.service;

import java.util.List;

import com.rainisic.blog.entity.Category;

/**
 * Category service interface.
 * 
 * @author rainisic
 * @version 1.0.0
 */
public interface CategoryService {

	/**
	 * Add new category.
	 * 
	 * @param category
	 *            The category to add.
	 * @return New category.
	 */
	public Category add(Category category);

	/**
	 * Get all category list.
	 * 
	 * @return Category list.
	 */
	public List<Category> list();

}
