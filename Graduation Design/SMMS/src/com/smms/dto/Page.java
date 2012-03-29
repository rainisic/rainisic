/* @(#) Page.java
 * 
 * Date: 2012-2-24
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smms.dto;

/**
 * @author Rainisic
 * 
 */
public class Page {

	/** Default page size. */
	public static final int DEFAULT_PAGE_SIZE = 10;

	/** Object max count in one page. */
	private int pageSize;

	/** Current page index.(Start from 0.) */
	private int pageIndex;
	
	public Page() {
		this.pageSize = DEFAULT_PAGE_SIZE;
		this.pageIndex = 0;
	}
	
	/**
	 * Create a page by page index and default page size.
	 * @param pageIndex
	 */
	public Page(int pageIndex) {
		this.pageSize = DEFAULT_PAGE_SIZE;
		this.pageIndex = pageIndex;
	}

	/**
	 * Create a page by page size and current page index.
	 * 
	 * @param pageSize
	 * @param pageIndex
	 */
	public Page(int pageSize, int pageIndex) {
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @return the pageIndex
	 */
	public int getPageIndex() {
		return pageIndex;
	}
	
	/**
	 * Get the start index.
	 * @return
	 */
	public int getStartIndex() {
		return this.pageIndex * this.pageSize;
	}
}
