/* @(#) BaseDaoSupport.java
 * 
 * Date: 2012-2-17
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smms.dao;

import javax.annotation.Resource;
import org.hibernate.SessionFactory;

/**
 * @author Rainisic
 *
 */
public class BaseDaoSupport {
	
	/** Define the session factory. */
	@Resource
	protected SessionFactory sessionFactory;

}
