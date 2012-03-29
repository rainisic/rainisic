/* @(#) FeedDaoInterface.java
 * 
 * Date: 2012-2-19
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smt.dao;

import java.util.Collection;
import java.util.List;

import com.smt.dto.Page;
import com.smt.entity.Feed;
import com.smt.entity.User;

/**
 * @author Rainisic
 * 
 */
public interface FeedDaoInterface {

	/**
	 * Save a new feed.
	 * 
	 * @param transientFeed
	 * @return
	 */
	public int save(Feed transientFeed);

	/**
	 * Query a feed by id.
	 * 
	 * @param id
	 * @return
	 */
	public Feed query(int id);

	/**
	 * Get feeds list by author.
	 * 
	 * @param author
	 * @param page
	 * @return
	 */
	public List<Feed> list(User author, Page page);

	/**
	 * Get feeds list by authors.
	 * 
	 * @param authors
	 * @param page
	 * @return
	 */
	public List<Feed> list(Collection<User> authors, Page page);

	/**
	 * Update a persistent feed.
	 * 
	 * @param persistentFeed
	 * @return
	 */
	public void update(Feed persistentFeed);
}
