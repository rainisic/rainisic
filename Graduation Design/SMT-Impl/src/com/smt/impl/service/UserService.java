/* @(#) UserService.java
 *
 * Date: 2012-4-3
 * 
 * Author: Rainisic
 */
package com.smt.impl.service;

import java.util.List;
import com.smt.impl.dao.UserDao;
import com.smt.impl.entity.User;

/**
 * 
 * @author Rainisic
 *
 */
public class UserService {
	
	/** Define the singleton user service. */
	private static UserService userService;
	
	/** Get the instance of user DAO. */
	private UserDao userDao = UserDao.getInstance();
	
	/**
	 * Private constructor.
	 */
	private UserService() {
	}

	/**
	 * Get the instance of user service.
	 * @return
	 */
	public static UserService getInstance() {
		if (userService == null) {
			userService = new UserService();
		}
		return userService;
	}

	/**
	 * List all users.
	 * @return
	 */
	public List<User> list() {
		return userDao.list();
	}
}
