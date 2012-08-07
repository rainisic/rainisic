/* @(#) BaseDaoSupport.java
 * Project:	blog
 * Package: com.rainisic.blog.dao
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.blog.dao;

import javax.annotation.Resource;
import org.hibernate.SessionFactory;

/**
 * The base DAO class support.
 * 
 * @author rainisic
 * @version 1.0.0
 */
public class BaseDaoSupport {

	/** Define the session factory. */
	@Resource
	protected SessionFactory sessionFactory;
}
