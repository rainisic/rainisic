/* @(#) ArticleServiceImpl.java
 * Project:	blog
 * Package:	com.rainisic.blog.service.impl
 * Author:	Rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.blog.service.impl;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rainisic.blog.dao.ArticleDao;
import com.rainisic.blog.entity.Article;
import com.rainisic.blog.service.ArticleService;
import com.rainisic.blog.vo.ArticleVo;
import com.rainisic.blog.vo.Page;

/**
 * @author Rainisic
 * 
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Resource
	private ArticleDao articleDaoImpl;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rainisic.blog.service.ArticleService#publish(com.rainisic.blog.vo
	 * .ArticleVo)
	 */
	@Override
	@Transactional
	public ArticleVo publish(ArticleVo article) {

		// Create a transient article obejct.
		Article transientArticle = new Article();
		transientArticle.setTitle(article.getTitle());
		transientArticle.setSummary(article.getSummary());
		transientArticle.setContent(article.getContent());
		transientArticle.setCategory(article.getCategory());
		transientArticle.setTags(article.getTags());
		transientArticle.setUrl(article.getUrl());
		transientArticle.setPublishTime(Calendar.getInstance());
		transientArticle.setUpdateTime(Calendar.getInstance());

		// Save the article.
		int id = articleDaoImpl.save(transientArticle);

		// Load the article.
		if (id > 0) {
			Article persistentArticle = articleDaoImpl.load(id);
			if (persistentArticle != null) {
				return new ArticleVo(persistentArticle);
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rainisic.blog.service.ArticleService#list(com.rainisic.blog.vo.Page)
	 */
	@Override
	@Transactional
	public List<ArticleVo> list(Page page) {
		
		// Load articles
		List<Article> articles = articleDaoImpl.list(page);
		
		// Define article VO list.
		List<ArticleVo> articleVos = new LinkedList<ArticleVo>();
		
		// Parse article to article VO.
		if (articles != null) {
			for (Article art : articles) {
				articleVos.add(new ArticleVo(art));
			}
		}
		return articleVos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rainisic.blog.service.ArticleService#display(java.lang.String)
	 */
	@Override
	@Transactional
	public ArticleVo display(String url) {
		
		// Load article.
		Article persistentArticle = articleDaoImpl.load(url);
		if (persistentArticle != null) {
			return new ArticleVo(persistentArticle);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.rainisic.blog.service.ArticleService#loadPage(com.rainisic.blog.vo.Page)
	 */
	@Override
	@Transactional
	public Page loadPage(Page page) {
		int totalPageCount = (articleDaoImpl.loadTotalCount() - 1) / page.getPageSize();
		if (page.getCurrentPage() > totalPageCount) {
			page.setCurrentPage(totalPageCount);
		}
		return page;
	}
}
