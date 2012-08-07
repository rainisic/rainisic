/* @(#) ArticleService.java
 * Project:	blog
 * Package:	com.rainisic.blog.service
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.blog.service;

import java.util.List;

import com.rainisic.blog.vo.ArticleVo;
import com.rainisic.blog.vo.Page;

/**
 * @author rainisic
 *
 */
public interface ArticleService {

	public ArticleVo publish(ArticleVo article);
	
	public List<ArticleVo> list(Page page);
	
	public ArticleVo display(String url);
	
	public Page loadPage(Page page);
}
