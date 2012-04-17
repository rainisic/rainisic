/* @(#) ThemeInterceptor.java
 *
 * Date: 2012-4-5
 * 
 * Author: Rainisic
 */
package com.smt.interceptor;

import java.util.Map;
import javax.annotation.Resource;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.smt.service.ConfigurationServiceInterface;

/**
 * @author Rainisic
 * 
 */
public class ThemeInterceptor extends AbstractInterceptor {

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private ConfigurationServiceInterface configurationService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com
	 * .opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		// Get the session.
		Map<String, Object> session = invocation.getInvocationContext()
				.getSession();

		// Check the theme
		if (!session.containsKey("smms_theme")
				|| session.get("smms_theme") == null
				|| session.get("smms_theme").equals("")) {

			// Get the theme.
			session.put("smms_theme", configurationService.load().getTheme());
		}

		return invocation.invoke();
	}
}
