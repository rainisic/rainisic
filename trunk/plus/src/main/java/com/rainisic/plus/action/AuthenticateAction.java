/* @(#) AuthenticateAction.java
 * Project:	plus
 * Package: com.rainisic.plus.action
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.plus.action;

import org.springframework.util.DigestUtils;
import com.rainisic.plus.util.ApplicationConfiguration;

/**
 * Shell authentication action.
 * 
 * @author rainisic
 * @version 1.0.0
 */
public class AuthenticateAction extends BaseActionSupport {

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

	/** Define the password. */
	private String password;

	/** Define the result. */
	private String result;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {

		// Check password.
		if (password != null
				&& DigestUtils.md5DigestAsHex(password.getBytes()).equals(
						ApplicationConfiguration.getProperty("password"))) {
			result = "right";
		} else {
			result = "wrong";
		}
		
		return "success";
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
}
