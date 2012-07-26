/* @(#) CategoryDaoImpl.java
 * Project:	plus
 * Package: com.rainisic.plus.dao.impl
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.plus.dao.impl;

import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.rainisic.plus.dao.BaseDaoSupport;
import com.rainisic.plus.dao.CategoryDao;
import com.rainisic.plus.entity.Category;

/**
 * Category DAO implementation class.
 * 
 * @author rainisic
 * @version 1.0.0
 */
@Repository
public class CategoryDaoImpl extends BaseDaoSupport implements CategoryDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rainisic.plus.dao.CategoryDao#get(int)
	 */
	@Override
	public Category get(int id) {
		return (Category) sessionFactory.getCurrentSession().get(
				Category.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rainisic.plus.dao.CategoryDao#get(java.lang.String)
	 */
	@Override
	public Category get(String url) {
		return (Category) sessionFactory.getCurrentSession()
				.createCriteria(Category.class)
				.add(Restrictions.eq("url", url)).list().iterator().next();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rainisic.plus.dao.CategoryDao#list()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> list() {
		return sessionFactory.getCurrentSession()
				.createCriteria(Category.class).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rainisic.plus.dao.CategoryDao#save(com.rainisic.plus.entity.Category)
	 */
	@Override
	public int save(Category transientCategory) {
		return (Integer) sessionFactory.getCurrentSession().save(
				transientCategory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rainisic.plus.dao.CategoryDao#update(com.rainisic.plus.entity.Category
	 * )
	 */
	@Override
	public void update(Category persistentCategory) {
		sessionFactory.getCurrentSession().update(persistentCategory);
	}
}
