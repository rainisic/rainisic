/* @(#) FeedService.java
 * 
 * Date: 2012-2-24
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smt.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smt.dao.FeedDaoInterface;
import com.smt.dao.UserDaoInterface;
import com.smt.dto.FeedDto;
import com.smt.dto.Page;
import com.smt.entity.Feed;
import com.smt.entity.User;

/**
 * @author Rainisic
 *
 */
@Service
public class FeedService implements FeedServiceInterface {
	
	/** Feed DAO. */
	@Resource
	private FeedDaoInterface feedDao;
	
	/** User DAO. */
	@Resource
	private UserDaoInterface userDao;

	/* (non-Javadoc)
	 * @see com.smms.service.FeedServiceInterface#publish(com.smms.entity.Feed)
	 */
	@Override
	@Transactional
	public Feed publish(FeedDto feedDto) {
		
		// Create feed by DTO.
		Feed feed = new Feed(feedDto);
		
		// Set create time and reference.
		feed.setCreateTime(Calendar.getInstance());
		if (feedDto.getReference() > 0) {
			feed.setReference(feedDao.query(feedDto.getReference()));
		}
		
		// Save the feed and get the identifier.
		feed.setId(feedDao.save(feed));
		return feed;
	}

	/* (non-Javadoc)
	 * @see com.smms.service.FeedServiceInterface#list(java.lang.Object, com.smms.dto.Page)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Feed> list(Object authors, Page page) {
		if (authors instanceof Collection) {
			return feedDao.list((Collection<User>)authors, page);
		} else if (authors instanceof User) {
			return feedDao.list(userDao.query(((User) authors).getUsername()), page);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.smms.service.FeedServiceInterface#delete(int, int)
	 */
	@Override
	public boolean delete(int id, int status) {
		
		// Get the feed to be delete.
		Feed persistentFeed = feedDao.query(id);
		
		// Check null and set new content.
		if (persistentFeed != null) {
			
			// Change feed status.
			persistentFeed.setStatus(status);
			
			// Update to database.
			feedDao.update(persistentFeed);
			return true;
		}
		return false;
	}
}
