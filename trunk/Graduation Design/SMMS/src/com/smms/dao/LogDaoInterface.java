/* @(#) LogDaoInterface.java
 * 
 * Date: 2012-3-9
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smms.dao;

import java.util.List;
import com.smms.dto.Page;
import com.smms.entity.Log;

/**
 * @author Rainisic
 *
 */
public interface LogDaoInterface {

	/**
	 * Save a log.
	 * @param log
	 * @return
	 */
	public int save(Log log);
	
	/**
	 * Query logs by page.
	 * @param page
	 * @return
	 */
	public List<Log> query(Page page);
}
