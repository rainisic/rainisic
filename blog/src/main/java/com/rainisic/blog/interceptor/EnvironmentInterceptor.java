/* @(#) EnvironmentInterceptor.java
 * Project:	blog
 * Package: com.rainisic.blog.interceptor
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.blog.interceptor;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.rainisic.blog.util.ApplicationConfiguration;

/**
 * Environment load interceptor.
 * @author rainisic
 * @version 1.0.0
 */
public class EnvironmentInterceptor extends AbstractInterceptor {

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com
	 * .opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		// Get HTTP request.
		HttpServletRequest request = (HttpServletRequest) invocation
				.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);

		// Put values below.
		// Put domain.
		request.setAttribute("domain",
				ApplicationConfiguration.getProperty("domain").trim());

		// Invoke the next interceptor.
		return invocation.invoke();
	}
}
