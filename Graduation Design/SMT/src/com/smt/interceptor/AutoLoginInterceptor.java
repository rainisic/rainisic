/* @(#) AutoLoginInterceptor.java
 * 
 * Date: 2012-2-17
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smt.interceptor;

import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.smt.entity.User;
import com.smt.service.UserServiceInterface;
import com.smt.util.Constants;
import com.smt.util.CookieUtil;
import com.smt.util.DESEncryptor;

/**
 * @author Rainisic
 *
 */
public class AutoLoginInterceptor extends AbstractInterceptor {

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

	/** Define the user service. */
	@Resource
	private UserServiceInterface userService;
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		// Define the session.
		Map<String, Object> session = invocation.getInvocationContext()
				.getSession();

		// Get the user in session.
		if (session.get(Constants.LOGIN_USER) == null) {

			// Define the request.
			HttpServletRequest request = (HttpServletRequest) invocation
					.getInvocationContext().get(
							ServletActionContext.HTTP_REQUEST);

			// Get the auto login cookie.
			Cookie cookie = CookieUtil.getCookie(request,
					Constants.USER_AUTOLOGIN_COOKIE);

			// Check cookie.
			if (cookie != null) {

				// User login.
				String[] splitResult = cookie.getValue().split("\\|", 2);
				if (splitResult.length > 1) {
					User persistentUser = userService.login(new User(
							DESEncryptor.decrypt(splitResult[0]), DESEncryptor
									.decrypt(splitResult[1])));

					// Check query result.
					if (persistentUser != null) {

						// If login successfully, add user to session.
						session.put(Constants.LOGIN_USER, persistentUser);
					} else {

						// If cookie exists but login failed, delete the cookie.
						CookieUtil
								.deleteCookie(
										request,
										(HttpServletResponse) invocation
												.getInvocationContext()
												.get(ServletActionContext.HTTP_RESPONSE),
										Constants.USER_AUTOLOGIN_COOKIE);
					}
				}
			}
		}
		return invocation.invoke();
	}
}
