/* @(#) FeedServiceInterface.java
 * 
 * Date: 2012-2-24
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smt.service;

import java.util.List;

import com.smt.dto.FeedDto;
import com.smt.dto.Page;
import com.smt.entity.Feed;

/**
 * @author Rainisic
 * 
 */
public interface FeedServiceInterface {

	/**
	 * Publish a new feed.
	 * 
	 * @param feedDto
	 * @return
	 */
	public Feed publish(FeedDto feedDto);

	/**
	 * Get feeds list by authors / author and page.
	 * 
	 * @param authors
	 * @param page
	 * @return
	 */
	public List<Feed> list(Object authors, Page page);

	/**
	 * Delete a feed by user or administrator.
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	public boolean delete(int id, int status);
}
