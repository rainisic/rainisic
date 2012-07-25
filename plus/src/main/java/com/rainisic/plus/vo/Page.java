/* @(#) Page.java
 * Project:	plus
 * Package: com.rainisic.plus.vo
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.plus.vo;

import com.rainisic.plus.util.ApplicationConfiguration;

/**
 * @author rainisic
 * 
 */
public class Page {

	private int pageSize;

	private int currentPage;

	private int pageCount;

	public Page() {
		pageSize = Integer.parseInt(ApplicationConfiguration
				.getProperty("defaultPageSize"));
	}

	public Page(int pageSize) {
		this.pageSize = pageSize;
	}

	public Page(int pageSize, int currentPage, int pageCount) {
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.pageCount = pageCount;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the pageCount
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * @param pageCount the pageCount to set
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
}
