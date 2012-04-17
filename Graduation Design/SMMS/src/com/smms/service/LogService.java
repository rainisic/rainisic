/* @(#) LogService.java
 *
 * Date: 2012-4-17
 * 
 * Author: Rainisic
 */
package com.smms.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smms.dao.LogDaoInterface;
import com.smms.entity.Log;

/**
 * @author Rainisic
 *
 */
@Service
public class LogService implements LogServiceInterface {
	
	@Resource
	private LogDaoInterface logDao;

	/* (non-Javadoc)
	 * @see com.smms.service.LogServiceInterface#list()
	 */
	@Override
	@Transactional
	public List<Log> list() {
		return logDao.list();
	}

}
