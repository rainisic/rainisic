/* @(#) AdminDaoInterface.java
 * 
 * Date: 2012-3-9
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smms.dao;

import com.smms.entity.Admin;

/**
 * @author Rainisic
 *
 */
public interface AdminDaoInterface {

	/**
	 * Query administrator by username and password.
	 * @param admin
	 * @return
	 */
	public Admin query(Admin admin);

}
