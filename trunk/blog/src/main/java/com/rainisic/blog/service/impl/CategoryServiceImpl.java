/* @(#) CategoryServiceImpl.java
 * Project:	blog
 * Package:	com.rainisic.blog.service.impl
 * Author:	Rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.blog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rainisic.blog.dao.CategoryDao;
import com.rainisic.blog.entity.Category;
import com.rainisic.blog.service.CategoryService;

/**
 * Category service implementation.
 * 
 * @author Rainisic
 * @version 1.0.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Resource
	private CategoryDao categoryDaoImpl;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rainisic.blog.service.CategoryService#add(com.rainisic.blog.entity
	 * .Category)
	 */
	@Override
	@Transactional
	public Category add(Category category) {
		int id = categoryDaoImpl.save(category);
		if (id > 0) {
			return categoryDaoImpl.load(id);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rainisic.blog.service.CategoryService#list()
	 */
	@Override
	@Transactional
	public List<Category> list() {
		return categoryDaoImpl.list();
	}

}
