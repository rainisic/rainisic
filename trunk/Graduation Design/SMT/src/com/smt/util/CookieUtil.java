/* @(#) CookieUtil.java
 * 
 * Date: 2012-2-17
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smt.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Rainisic
 *
 */
public class CookieUtil {

	/**
	 * Add new Cookie.
	 * @param response
	 * @param name
	 * @param path
	 * @param value
	 * @param maxAge
	 */
	public static void addCookie(HttpServletResponse response, String name,
			String value, int maxAge) {
		
		// Create new cookie and set arguments.
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge > 0) {
			cookie.setMaxAge(maxAge);
		}
		
		// Add the cookie.
		response.addCookie(cookie);
	}
	
	/**
	 * Delete a cookie.
	 * @param request
	 * @param response
	 * @param name
	 */
	public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		
		// Delete cookie.
		Cookie cookie = new Cookie(name, null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		cookie.setValue("");
		response.addCookie(cookie);
	}
	
	/**
	 * Get cookie by name.
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		
		// Get all cookies.
		Cookie[] cookies = request.getCookies();
		
		// Check null.
		if (cookies != null) {
			
			// Get cookie by name.
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie;
				}
			}
		}
		return null;
	}
}
