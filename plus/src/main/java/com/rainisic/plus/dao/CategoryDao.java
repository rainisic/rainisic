/* @(#) CategoryDao.java
 * Project:	plus
 * Package: com.rainisic.plus.dao
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.plus.dao;

import java.util.List;
import com.rainisic.plus.entity.Category;

/**
 * Category DAO interface.
 * 
 * @author rainisic
 * @version 1.0.0
 */
public interface CategoryDao {

	/**
	 * Get category by ID.
	 * 
	 * @param id
	 * @return
	 */
	public Category get(int id);

	/**
	 * Get category by URL.
	 * 
	 * @param url
	 * @return
	 */
	public Category get(String url);

	/**
	 * List all categories.
	 * 
	 * @return
	 */
	public List<Category> list();

	/**
	 * Save the transient category.
	 * 
	 * @param transientCategory
	 * @return
	 */
	public int save(Category transientCategory);

	/**
	 * Update the persistent category.
	 * 
	 * @param persistentCategory
	 */
	public void update(Category persistentCategory);

}
