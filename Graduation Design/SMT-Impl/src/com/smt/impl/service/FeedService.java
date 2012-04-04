/* @(#) FeedService.java
 *
 * Date: 2012-4-4
 * 
 * Author: Rainisic
 */
package com.smt.impl.service;

import java.util.List;

import com.smt.impl.dao.FeedDao;
import com.smt.impl.entity.Feed;

/**
 * @author Rainisic
 * 
 */
public class FeedService {

	/** Define the singleton feed service. */
	private static FeedService feedService;

	/** Get the instance of feed DAO. */
	private FeedDao feedDao = FeedDao.getInstance();

	/**
	 * Private constructor.
	 */
	private FeedService() {
	}

	/**
	 * Get the instance of feed service.
	 * 
	 * @return
	 */
	public static FeedService getInstance() {
		if (feedService == null) {
			feedService = new FeedService();
		}
		return feedService;
	}

	/**
	 * List all feeds.
	 * 
	 * @return
	 */
	public List<Feed> list() {
		return feedDao.list();
	}
}
