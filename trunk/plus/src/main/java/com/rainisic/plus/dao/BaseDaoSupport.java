/* @(#) BaseDaoSupport.java
 * Project:	plus
 * Package: com.rainisic.plus.dao
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.plus.dao;

import org.hibernate.SessionFactory;
import javax.annotation.Resource;

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
