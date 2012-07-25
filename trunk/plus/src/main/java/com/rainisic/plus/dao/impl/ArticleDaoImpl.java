/* @(#) ArticleDaoImpl.java
 * Project:	plus
 * Package: com.rainisic.plus.dao.impl
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.plus.dao.impl;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rainisic.plus.dao.ArticleDao;
import com.rainisic.plus.entity.Article;
import com.rainisic.plus.entity.Category;
import com.rainisic.plus.vo.Page;

/**
 * @author rainisic
 *
 */
@Repository
public class ArticleDaoImpl implements ArticleDao {

	/* (non-Javadoc)
	 * @see com.rainisic.plus.dao.ArticleDao#get(int)
	 */
	@Override
	public Article get(int id) {
		Article article = new Article();
		article.setId(1);
		article.setTags("Java, Linux");
		article.setTitle("title");
		article.setContent("Content");
		article.setSummary("summary");
		article.setUrl("test");
		article.setPublishTime(Calendar.getInstance());
		Category category = new Category();
		category.setId(1);
		category.setName("Category");
		category.setUrl("cate");
		article.setCategory(category);
		return article;
	}

	/* (non-Javadoc)
	 * @see com.rainisic.plus.dao.ArticleDao#list(com.rainisic.plus.vo.Page)
	 */
	@Override
	public List<Article> list(Page page) {
		
		List<Article> articles = new LinkedList<Article>();
		for (int i = 0; i < 10; i ++) {
			Article article = new Article();
			article.setId(1);
			article.setTags("Java, Linux");
			article.setTitle("title");
			article.setContent("Content");
			article.setSummary("summary");
			article.setUrl("test");
			article.setPublishTime(Calendar.getInstance());
			Category category = new Category();
			category.setId(1);
			category.setName("Category");
			category.setUrl("cate");
			article.setCategory(category);
			articles.add(article);
		}
		
		return articles;
	}

}
