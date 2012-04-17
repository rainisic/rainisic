/* @(#) LogServiceInterface.java
 *
 * Date: 2012-4-17
 * 
 * Author: Rainisic
 */
package com.smms.service;

import java.util.List;

import com.smms.entity.Log;

/**
 * @author Rainisic
 *
 */
public interface LogServiceInterface {

	/**
	 * List all logs.
	 * @return
	 */
	public List<Log> list();
}
