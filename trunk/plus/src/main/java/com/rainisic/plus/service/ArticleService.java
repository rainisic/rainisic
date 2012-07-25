/* @(#) ArticleService.java
 * Project:	plus
 * Package: com.rainisic.plus.service
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.plus.service;

import java.util.List;
import com.rainisic.plus.vo.ArticleVo;
import com.rainisic.plus.vo.Page;

/**
 * @author rainisic
 *
 */
public interface ArticleService {

	public List<ArticleVo> list(Page page);
	
	public ArticleVo load(String url);
}
