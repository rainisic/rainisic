/* @(#) CategoryDaoImpl.java
 * Project:	blog
 * Package:	com.rainisic.blog.dao.impl
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.blog.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rainisic.blog.dao.BaseDaoSupport;
import com.rainisic.blog.dao.CategoryDao;
import com.rainisic.blog.entity.Category;

/**
 * Category DAO implementation.
 * 
 * @author rainisic
 * @version 1.0.0
 */
@Repository
public class CategoryDaoImpl extends BaseDaoSupport implements CategoryDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rainisic.blog.dao.CategoryDao#save(com.rainisic.blog.entity.Category)
	 */
	@Override
	public Integer save(Category category) {
		return (Integer) sessionFactory.getCurrentSession().save(category);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rainisic.blog.dao.CategoryDao#update(com.rainisic.blog.entity.Category
	 * )
	 */
	@Override
	public void update(Category category) {
		sessionFactory.getCurrentSession().update(category);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rainisic.blog.dao.CategoryDao#load(int)
	 */
	@Override
	public Category load(int id) {
		return (Category) sessionFactory.getCurrentSession().get(
				Category.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rainisic.blog.dao.CategoryDao#list()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> list() {
		return sessionFactory.getCurrentSession()
				.createCriteria(Category.class).list();
	}
}
