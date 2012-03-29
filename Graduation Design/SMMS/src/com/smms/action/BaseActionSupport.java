/* @(#) BaseActionSupport.java
 * 
 * Date: 2012-2-17
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smms.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Rainisic
 *
 */
public class BaseActionSupport extends ActionSupport implements SessionAware,
		ServletRequestAware, ServletResponseAware {

	/** Add default serial version UID. */
	private static final long serialVersionUID = 1L;
	
	/** Create logger by log4j. */
	private static Logger logger = Logger.getLogger(BaseActionSupport.class);

	/** Define the session. */
	protected Map<String, Object> session;

	/** Define the request. */
	protected HttpServletRequest request;

	/** Define the response. */
	protected HttpServletResponse response;
	
	/** Define the input stream for AJAX. */
	protected InputStream inputStream;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.ServletResponseAware#setServletResponse
	 * (javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * @param inputStream the inputStream to set
	 */
	public void setInputStreamValue(String text) {
		try {
			this.inputStream = new ByteArrayInputStream(new String(text).getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		}
	}
}
