/* @(#) CategoryAction.java
 * Project:	blog
 * Package:	com.rainisic.blog.action
 * Author:	Rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.blog.action;

import java.util.List;

import com.rainisic.blog.entity.Category;
import com.rainisic.blog.service.CategoryService;

/**
 * Category action class.
 * 
 * @author Rainisic
 * @version 1.0.0
 */
public class CategoryAction extends BaseActionSupport {

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

	/** Category. */
	private Category category;

	/** Category list. */
	private List<Category> categories;

	/** Define the category service. */
	private CategoryService categoryServiceImpl;

	/**
	 * Add new category.
	 * 
	 * @return
	 */
	public String add() {

		// Check data.
		if (category != null && category.getName() != null
				&& category.getName().trim().length() > 0) {
			
			// Add new category.
			category = categoryServiceImpl.add(category);
			if (category != null) {
				return SUCCESS;
			}
		}
		return ERROR;
	}

	/**
	 * Get category list.
	 * 
	 * @return
	 */
	public String list() {
		categories = categoryServiceImpl.list();
		return SUCCESS;
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
	 * @param categoryServiceImpl the categoryServiceImpl to set
	 */
	public void setCategoryServiceImpl(CategoryService categoryServiceImpl) {
		this.categoryServiceImpl = categoryServiceImpl;
	}
}
