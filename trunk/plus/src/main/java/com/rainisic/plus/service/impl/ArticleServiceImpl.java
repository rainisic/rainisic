/* @(#) ArticleServiceImpl.java
 * Project:	plus
 * Package: com.rainisic.plus.service.impl
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.plus.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rainisic.plus.dao.ArticleDao;
import com.rainisic.plus.entity.Article;
import com.rainisic.plus.service.ArticleService;
import com.rainisic.plus.vo.ArticleVo;
import com.rainisic.plus.vo.Page;

/**
 * @author rainisic
 *
 */
@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Resource
	private ArticleDao articleDaoImpl;
	
	/* (non-Javadoc)
	 * @see com.rainisic.plus.service.ArticleService#list(com.rainisic.plus.vo.Page)
	 */
	@Override
	public List<ArticleVo> list(Page page) {
		
		List<Article> articles = articleDaoImpl.list(page);
		List<ArticleVo> articleVos = new LinkedList<ArticleVo>();
 		for (Article ar : articles) {
 			ArticleVo vo = new ArticleVo(ar);
 			articleVos.add(vo);
 		}
		
		return articleVos;
	}

	/* (non-Javadoc)
	 * @see com.rainisic.plus.service.ArticleService#load(java.lang.String)
	 */
	@Override
	public ArticleVo load(String url) {
		
		return new ArticleVo(articleDaoImpl.get(0));
	}

}
