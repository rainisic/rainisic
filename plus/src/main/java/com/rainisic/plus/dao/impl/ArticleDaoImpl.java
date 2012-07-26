/* @(#) ArticleDaoImpl.java
 * Project:	plus
 * Package: com.rainisic.plus.dao.impl
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.plus.dao.impl;

import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.rainisic.plus.dao.ArticleDao;
import com.rainisic.plus.dao.BaseDaoSupport;
import com.rainisic.plus.entity.Article;
import com.rainisic.plus.entity.Category;
import com.rainisic.plus.vo.Page;

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
	 * @see com.rainisic.plus.dao.ArticleDao#get(int)
	 */
	@Override
	public Article get(int id) {
		return (Article) sessionFactory.getCurrentSession().get(Article.class,
				id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rainisic.plus.dao.ArticleDao#get(java.lang.String)
	 */
	@Override
	public Article get(String url) {
		return (Article) sessionFactory.getCurrentSession()
				.createCriteria(Article.class).add(Restrictions.eq("url", url))
				.list().iterator().next();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rainisic.plus.dao.ArticleDao#list(com.rainisic.plus.entity.Category,
	 * com.rainisic.plus.vo.Page)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Article> list(Category category, Page page) {
		return sessionFactory.getCurrentSession().createCriteria(Article.class)
				.add(Restrictions.eq("category", category))
				.setMaxResults(page.getPageSize())
				.setFirstResult(page.getCurrentPage() * page.getPageSize())
				.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rainisic.plus.dao.ArticleDao#list(com.rainisic.plus.vo.Page)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Article> list(Page page) {
		return sessionFactory.getCurrentSession().createCriteria(Article.class)
				.setMaxResults(page.getPageSize())
				.setFirstResult(page.getCurrentPage() * page.getPageSize())
				.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rainisic.plus.dao.ArticleDao#save(com.rainisic.plus.entity.Article)
	 */
	@Override
	public int save(Article transientArticle) {
		return (Integer) sessionFactory.getCurrentSession().save(
				transientArticle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rainisic.plus.dao.ArticleDao#update(com.rainisic.plus.entity.Article)
	 */
	@Override
	public void update(Article persistentArticle) {
		sessionFactory.getCurrentSession().update(persistentArticle);
	}
}
