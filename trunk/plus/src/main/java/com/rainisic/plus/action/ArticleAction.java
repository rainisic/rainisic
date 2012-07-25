/* @(#) ArticleAction.java
 * Project:	plus
 * Package: com.rainisic.plus.action
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.plus.action;

import java.util.List;

import com.rainisic.plus.entity.Category;
import com.rainisic.plus.service.ArticleService;
import com.rainisic.plus.vo.ArticleVo;
import com.rainisic.plus.vo.Page;

/**
 * Action processor for article.
 * 
 * @author rainisic
 * @version 1.0.0
 */
public class ArticleAction extends BaseActionSupport {

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

	/** Define the article service. */
	private ArticleService articleServiceImpl;

	/** Define the article list. */
	private List<ArticleVo> articles;

	/** Define the article. */
	private ArticleVo article;

	/** Define the categories. */
	private List<Category> categories;

	private Page page;

	/**
	 * Publish a new article.
	 * 
	 * @return
	 */
	public String publish() {
		return SUCCESS;
	}

	/**
	 * List article by page.
	 * 
	 * @return
	 */
	public String list() {

		// Get article list.
		articles = articleServiceImpl.list(page == null ? new Page() : page);

		return "success";
	}

	/**
	 * Load article.
	 * 
	 * @return
	 */
	public String load() {

		// Check data validation.
		if (article != null && article.getUrl() != null
				&& article.getUrl().trim().length() > 0) {
			
			// Get article list.
			articles = articleServiceImpl.list(page == null ? new Page() : page);

			// Load the article.
			article = articleServiceImpl.load(article.getUrl());

			return "success";
		}

		return "error";
	}

	/**
	 * List article by page.
	 * 
	 * @return
	 */
	public String ajaxList() {

		// Get article list.
		articles = articleServiceImpl.list(page == null ? new Page() : page);

		return "success";
	}

	/**
	 * Load article.
	 * 
	 * @return
	 */
	public String ajaxLoad() {
		
		article = articleServiceImpl.load(null);

		return "success";
	}

	/**
	 * @return the articleServiceImpl
	 */
	public ArticleService getArticleServiceImpl() {
		return articleServiceImpl;
	}

	/**
	 * @param articleServiceImpl
	 *            the articleServiceImpl to set
	 */
	public void setArticleServiceImpl(ArticleService articleServiceImpl) {
		this.articleServiceImpl = articleServiceImpl;
	}

	/**
	 * @return the articles
	 */
	public List<ArticleVo> getArticles() {
		return articles;
	}

	/**
	 * @param articles
	 *            the articles to set
	 */
	public void setArticles(List<ArticleVo> articles) {
		this.articles = articles;
	}

	/**
	 * @return the article
	 */
	public ArticleVo getArticle() {
		return article;
	}

	/**
	 * @param article
	 *            the article to set
	 */
	public void setArticle(ArticleVo article) {
		this.article = article;
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
	 * @return the page
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}

}
