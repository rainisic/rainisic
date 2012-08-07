/* @(#) ArticleAction.java
 * Project:	blog
 * Package:	com.rainisic.blog.action
 * Author:	Rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.blog.action;

import java.util.List;
import com.rainisic.blog.service.ArticleService;
import com.rainisic.blog.vo.ArticleVo;
import com.rainisic.blog.vo.Page;

/**
 * @author Rainisic
 * 
 */
public class ArticleAction extends BaseActionSupport {

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

	/** Define the article VO. */
	private ArticleVo article;

	/** Define the article VO list. */
	private List<ArticleVo> articles;

	/** Define the page. */
	private Page page;

	/** Define the article service. */
	private ArticleService articleServiceImpl;

	/**
	 * Publish a new article.
	 * 
	 * @return
	 */
	public String publish() {

		if (article != null && article.getTitle() != null
				&& article.getTitle().trim().length() > 0
				&& article.getContent() != null
				&& article.getContent().trim().length() > 0
				&& article.getSummary() != null
				&& article.getSummary().trim().length() > 0) {

			// Publish new article.
			article = articleServiceImpl.publish(article);

			// Check publish result.
			if (article != null) {
				return SUCCESS;
			}
		}
		return ERROR;
	}

	/**
	 * Get article list.
	 * 
	 * @return
	 */
	public String list() {

		// Check page.
		if (page == null) {
			page = new Page();
		}
		
		// Load page.
		page = articleServiceImpl.loadPage(page);

		// Load article list.
		articles = articleServiceImpl.list(page);

		return SUCCESS;
	}

	/**
	 * Display the article.
	 * 
	 * @return
	 */
	public String display() {

		// Check article URL.
		if (article != null && article.getUrl() != null
				&& article.getUrl().trim().length() > 0) {
			
			// Load the article by URL.
			article = articleServiceImpl.display(article.getUrl());

			// Check result.
			if (article != null) {
				return SUCCESS;
			}
		}
		return ERROR;
	}

	/**
	 * @return the article
	 */
	public ArticleVo getArticle() {
		return article;
	}

	/**
	 * @param article the article to set
	 */
	public void setArticle(ArticleVo article) {
		this.article = article;
	}

	/**
	 * @return the articles
	 */
	public List<ArticleVo> getArticles() {
		return articles;
	}

	/**
	 * @param articles the articles to set
	 */
	public void setArticles(List<ArticleVo> articles) {
		this.articles = articles;
	}

	/**
	 * @return the page
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}

	/**
	 * @return the articleServiceImpl
	 */
	public ArticleService getArticleServiceImpl() {
		return articleServiceImpl;
	}

	/**
	 * @param articleServiceImpl the articleServiceImpl to set
	 */
	public void setArticleServiceImpl(ArticleService articleServiceImpl) {
		this.articleServiceImpl = articleServiceImpl;
	}
}
