/* @(#) Page.java
 * Project:	blog
 * Package: com.rainisic.blog.vo
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.blog.vo;

/**
 * For paging.
 * 
 * @author rainisic
 * @version 1.0.0
 */
public class Page {

	/** Current page index(Start with 0). */
	private int currentPage;

	/** Total page count. */
	private int pageCount;

	/** Page size. */
	private int pageSize;

	/**
	 * Default constructor.
	 */
	public Page() {
		this.pageSize = 10;
		this.currentPage = 0;
		this.pageCount = 0;
	}

	/**
	 * Construct a specific pager.
	 * 
	 * @param pageSize
	 *            Page size.
	 * @param currentPage
	 *            Current page index(Start with 0).
	 * @param pageCount
	 *            Total page count.
	 */
	public Page(int pageSize, int currentPage, int pageCount) {
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.pageCount = pageCount;
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @return the pageCount
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @param pageCount
	 *            the pageCount to set
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
