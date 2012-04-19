/* @(#) LogicManagerAction.java
 *
 * Date: 2012-4-19
 * 
 * Author: Rainisic
 */
package com.smt.impl.action;

import java.util.LinkedList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.smt.impl.service.ConfigurationService;

/**
 * @author Rainisic
 * 
 */
public class LogicManagerAction extends ActionSupport {

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

	private ConfigurationService configurationService = ConfigurationService
			.getInstance();

	/** The data contents. */
	private List<String[]> contents;

	private boolean allow;

	private String result;

	public String loadControlList() {
		contents = new LinkedList<String[]>();
		contents.add(new String[] { "changeRegister", "打开/关闭注册功能" });
		return SUCCESS;
	}

	public String changeRegister() {

		if (configurationService.changeRegister(allow)) {
			result = "success";
		} else {
			result = "failed";
		}

		return SUCCESS;
	}

	/**
	 * @return the contents
	 */
	public List<String[]> getContents() {
		return contents;
	}

	/**
	 * @param contents the contents to set
	 */
	public void setContents(List<String[]> contents) {
		this.contents = contents;
	}

	/**
	 * @return the allow
	 */
	public boolean isAllow() {
		return allow;
	}

	/**
	 * @param allow the allow to set
	 */
	public void setAllow(boolean allow) {
		this.allow = allow;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
}
