/* @(#) BaseActionSupport.java
 * Project:	plus
 * Package: com.rainisic.plus.action
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.plus.action;

import java.io.InputStream;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Inherit HTTP objects by extends this class.
 * 
 * @author rainisic
 * @version 1.0.0
 */
public class BaseActionSupport extends ActionSupport implements SessionAware,
		ServletRequestAware, ServletResponseAware {

	// Default serial version UID.
	private static final long serialVersionUID = 1L;

	/** Input stream for AJAX. */
	protected InputStream inputStream;

	/** Http request instance. */
	protected HttpServletRequest request;

	/** Http response instance. */
	protected HttpServletResponse response;

	/** Http session instance. */
	protected Map<String, Object> session;

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
	 * org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
