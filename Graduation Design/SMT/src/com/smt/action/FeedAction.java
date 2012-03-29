/* @(#) FeedAction.java
 * 
 * Date: 2012-2-24
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smt.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.smt.dto.FeedDto;
import com.smt.dto.Page;
import com.smt.entity.Feed;
import com.smt.entity.User;
import com.smt.service.FeedServiceInterface;
import com.smt.util.Constants;

/**
 * @author Rainisic
 * 
 */
public class FeedAction extends BaseActionSupport {

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

	/** Feed service. */
	private FeedServiceInterface feedService;

	/** Feed list. */
	private List<Feed> feeds;
	
	/** Feed DTO. */
	private FeedDto feedDto;
	
	/** Feed. */
	private Feed feed;

	/** User. */
	private User user;

	/** Page. */
	private Page page;

	/**
	 * Publish new feed.
	 * 
	 * @return
	 */
	public String publish() {
		
		// Get user from session.
		User user = (User) session.get(Constants.LOGIN_USER);
		
		// Check user and feed.
		if (user != null && feedDto != null) {
			
			// Publish.
			feedDto.setAuthor(user);
			feed = feedService.publish(feedDto);
			
			// Check result.
			if (feed.getId() > 0) {
				return "success";
			}
		}
		return null;
	}

	/**
	 * Get the subscribe feed list.
	 * 
	 * @return
	 */
	public String subscribe() {

		// Get login user.
		user = (User) session.get(Constants.LOGIN_USER);

		if (user != null && page != null) {

			// Create author set.
			Set<User> authors = new HashSet<User>();

			// Add user self.
			authors.add(user);

			// Add user's friends.
			if (user.getFriends() != null && user.getFriends().size() > 0) {
				authors.addAll(user.getFriends());
			}

			// Get the feeds list.
			feeds = feedService.list(authors, page);
			return "success";
		}
		return null;
	}

	/**
	 * Get the user feed list.
	 * 
	 * @return
	 */
	public String userFeeds() {

		// Check page.
		if (page != null) {

			// Get the feeds list.
			feeds = feedService.list(user, page);
			return "success";
		}
		return null;
	}

	/**
	 * @param feedService
	 *            the feedService to set
	 */
	public void setFeedService(FeedServiceInterface feedService) {
		this.feedService = feedService;
	}

	/**
	 * @return the feed
	 */
	public Feed getFeed() {
		return feed;
	}

	/**
	 * @param feed the feed to set
	 */
	public void setFeed(Feed feed) {
		this.feed = feed;
	}

	/**
	 * @return the feedDto
	 */
	public FeedDto getFeedDto() {
		return feedDto;
	}

	/**
	 * @param feedDto the feedDto to set
	 */
	public void setFeedDto(FeedDto feedDto) {
		this.feedDto = feedDto;
	}

	/**
	 * @return the feeds
	 */
	public List<Feed> getFeeds() {
		return feeds;
	}

	/**
	 * @param feeds
	 *            the feeds to set
	 */
	public void setFeeds(List<Feed> feeds) {
		this.feeds = feeds;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the page
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}
}
