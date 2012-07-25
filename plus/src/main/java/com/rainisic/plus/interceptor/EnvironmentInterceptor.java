/* @(#) EnvironmentInterceptor.java
 * Project:	plus
 * Package:	com.rainisic.plus.interceptor
 * Author:	rainisic
 * Date:	Jul 24, 2012
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.plus.interceptor;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.rainisic.plus.util.ApplicationConfiguration;

/**
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
		
		// Put the subject.
		String subject = null;
		String[] uris = request.getRequestURI().split("/");
		if (uris.length < 2 || uris[1] == "") {
			subject = "home";
		} else {
			subject = uris[1];
		}
		request.setAttribute("subject", subject);
		
		// Invoke the next interceptor.
		return invocation.invoke();
	}
}
