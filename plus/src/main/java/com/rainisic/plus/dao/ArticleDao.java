/* @(#) ArticleDao.java
 * Project:	plus
 * Package: com.rainisic.plus.dao
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.plus.dao;

import java.util.List;

import com.rainisic.plus.entity.Article;
import com.rainisic.plus.vo.Page;

/**
 * @author rainisic
 *
 */
public interface ArticleDao {
	
	public Article get(int id);
	
	public List<Article> list(Page page);
}
