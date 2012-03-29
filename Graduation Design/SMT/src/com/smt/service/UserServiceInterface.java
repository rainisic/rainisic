/* @(#) UserServiceInterface.java
 * 
 * Date: 2012-2-17
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smt.service;

import com.smt.entity.User;

/**
 * @author Rainisic
 * 
 */
public interface UserServiceInterface {

	/**
	 * Check username/e-mail/nickname availability.
	 * 
	 * @param value
	 * @return
	 */
	public boolean checkAvailability(String value);

	/**
	 * User register.
	 * 
	 * @param user
	 * @return
	 */
	public User register(User user);

	/**
	 * User login.
	 * 
	 * @param user
	 * @return
	 */
	public User login(User user);

	/**
	 * Modify user information.
	 * 
	 * @param user
	 * @return
	 */
	public User modify(User user);

	/**
	 * Get user information.
	 * 
	 * @param username
	 * @return
	 */
	public User getUserInformation(String username);

	/**
	 * Add a friend.
	 * 
	 * @param user
	 * @param friend
	 * @return
	 */
	public User addFriend(String user, String friend);

	/**
	 * Remove a friend.
	 * 
	 * @param user
	 * @param friend
	 * @return
	 */
	public User deleteFriend(String user, String friend);

	/**
	 * Change user status.
	 * 
	 * @param userId
	 * @param status
	 * @return
	 */
	public boolean changeStatus(User user, int status);
}