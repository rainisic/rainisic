/* @(#) ArticleDaoImpl.java
 * Project:	blog
 * Package:	com.rainisic.blog.dao.impl
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.blog.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.rainisic.blog.dao.ArticleDao;
import com.rainisic.blog.dao.BaseDaoSupport;
import com.rainisic.blog.entity.Article;
import com.rainisic.blog.entity.Category;
import com.rainisic.blog.vo.Page;

/**
 * Article DAO implementation class.
 * 
 * @author rainisic
 * @version 1.0.0
 */
@Repository
public class ArticleDaoImpl extends BaseDaoSupport implements ArticleDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rainisic.blog.dao.ArticleDao#save(com.rainisic.blog.entity.Article)
	 */
	@Override
	public Integer save(Article article) {
		return (Integer) sessionFactory.getCurrentSession().save(article);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rainisic.blog.dao.ArticleDao#update(com.rainisic.blog.entity.Article)
	 */
	@Override
	public void update(Article article) {
		sessionFactory.getCurrentSession().update(article);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rainisic.blog.dao.ArticleDao#load(int)
	 */
	@Override
	public Article load(int id) {
		return (Article) sessionFactory.getCurrentSession().get(Article.class,
				id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rainisic.blog.dao.ArticleDao#load(java.lang.String)
	 */
	@Override
	public Article load(String url) {
		return (Article) sessionFactory.getCurrentSession()
				.createCriteria(Article.class).add(Restrictions.eq("url", url))
				.list().iterator().next();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rainisic.blog.dao.ArticleDao#list(com.rainisic.blog.vo.Page)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Article> list(Page page) {
		return sessionFactory.getCurrentSession().createCriteria(Article.class)
				.setFirstResult(page.getCurrentPage() * page.getPageSize())
				.setMaxResults(page.getPageSize()).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rainisic.blog.dao.ArticleDao#list(com.rainisic.blog.entity.Category,
	 * com.rainisic.blog.vo.Page)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Article> list(Category category, Page page) {
		return sessionFactory.getCurrentSession().createCriteria(Article.class)
				.add(Restrictions.eq("category", category))
				.setFirstResult(page.getCurrentPage() * page.getPageSize())
				.setMaxResults(page.getPageSize()).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rainisic.blog.dao.ArticleDao#loadTotalCount()
	 */
	@Override
	public Integer loadTotalCount() {
		return ((Long) sessionFactory.getCurrentSession()
				.createQuery("select count(*) from Article").iterate()
				.next()).intValue();
	}
}
