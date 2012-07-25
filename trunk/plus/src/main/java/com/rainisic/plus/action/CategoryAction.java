/* @(#) CategoryAction.java
 * Project:	plus
 * Package: com.rainisic.plus.action
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.plus.action;

import java.util.List;

import com.rainisic.plus.entity.Category;

/**
 * @author rainisic
 *
 */
public class CategoryAction extends BaseActionSupport {

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;
	
	private List<Category> categories;
	
	public String list() {
		return SUCCESS;
	}
	
	public String ajaxList() {
		return SUCCESS;
	}
}
