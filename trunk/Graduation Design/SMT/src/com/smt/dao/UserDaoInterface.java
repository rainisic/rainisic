/* @(#) UserDaoInterface.java
 * 
 * Date: 2012-2-17
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smt.dao;

import com.smt.entity.User;

/**
 * @author Rainisic
 * 
 */
public interface UserDaoInterface {

	/**
	 * Query user by username.
	 * 
	 * @param value
	 * @return
	 */
	public User query(String value);

	/**
	 * Save the transient user.
	 * 
	 * @param transientUser
	 * @return
	 */
	public int save(User transientUser);

	/**
	 * Update the persistent user.
	 * 
	 * @param persistentUser
	 */
	public void update(User persistentUser);

}
