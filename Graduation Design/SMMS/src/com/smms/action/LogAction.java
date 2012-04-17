/* @(#) LogAction.java
 *
 * Date: 2012-4-17
 * 
 * Author: Rainisic
 */
package com.smms.action;

import java.util.List;

import com.smms.entity.Log;
import com.smms.service.LogServiceInterface;

/**
 * @author Rainisic
 *
 */
public class LogAction extends BaseActionSupport {

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

	/** Define the log service. */
	private LogServiceInterface logService;
	
	/** Define the log list. */
	private List<Log> logs;
	
	/**
	 * List all logs.
	 * @return
	 */
	public String list() {
		
		logs = logService.list();
		
		return SUCCESS;
	}

	/**
	 * @return the logService
	 */
	public LogServiceInterface getLogService() {
		return logService;
	}

	/**
	 * @param logService the logService to set
	 */
	public void setLogService(LogServiceInterface logService) {
		this.logService = logService;
	}

	/**
	 * @return the logs
	 */
	public List<Log> getLogs() {
		return logs;
	}

	/**
	 * @param logs the logs to set
	 */
	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}
}
