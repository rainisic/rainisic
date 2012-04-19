/* @(#) UserAction.java
 * 
 * Date: 2012-2-18
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smt.action;

import com.smt.entity.User;
import com.smt.service.ConfigurationServiceInterface;
import com.smt.service.UserServiceInterface;
import com.smt.util.Constants;
import com.smt.util.CookieUtil;
import com.smt.util.DESEncryptor;
import com.smt.util.MD5Encryptor;

/**
 * @author Rainisic
 * 
 */
public class UserAction extends BaseActionSupport {

	/** Add default serial version UID. */
	private static final long serialVersionUID = 1L;

	/** User service. */
	private UserServiceInterface userService;

	private ConfigurationServiceInterface configurationService;

	/** User. */
	private User user;

	/** Auto login. */
	private boolean autologin;

	/**
	 * Check e-mail availability.
	 * 
	 * @return
	 */
	public String checkEmail() {

		// Check e-mail length.
		if (user != null && user.getEmail() != null
				&& user.getEmail().length() > 0) {

			// Check e-mail availability.
			setInputStreamValue(userService.checkAvailability(user.getEmail()) ? "available"
					: "notAvailable");
			return "result";
		}
		return null;
	}

	/**
	 * New user sign up.
	 * 
	 * @return
	 */
	public String register() {
		
		if (!configurationService.load().isAllowRegister()) {
			setInputStreamValue("pause");
			return "result";
		}

		// Check null
		if (user != null
				&& user.getEmail() != null && user.getEmail().length() > 0
				&& user.getPassword() != null
				&& user.getPassword().length() > 0) {

			// Encrypt the password.
			user.setPassword(MD5Encryptor.encrypt(user.getPassword()));

			// User register.
			User persistentUser = userService.register(user);

			// Check the result.
			if (persistentUser != null) {

				// Add user to session.
				session.put(Constants.LOGIN_USER, persistentUser);
				setInputStreamValue(persistentUser.getUsername());
			} else {
				setInputStreamValue("registerFailed");
			}

			return "result";
		}
		return null;
	}

	/**
	 * User sign in.
	 * 
	 * @return
	 */
	public String login() {

		// Check null
		if (user != null && user.getUsername() != null
				&& user.getUsername().length() > 0
				&& user.getPassword() != null
				&& user.getPassword().length() > 0) {

			// Encrypt the password.
			user.setPassword(MD5Encryptor.encrypt(user.getPassword()));

			// User login.
			User persistentUser = userService.login(user);

			// Check result.
			if (persistentUser != null) {

				// Add auto login cookie.
				if (autologin) {
					CookieUtil.addCookie(
							response,
							Constants.USER_AUTOLOGIN_COOKIE,
							DESEncryptor.encrypt(persistentUser.getUsername())
									+ "|"
									+ DESEncryptor.encrypt(persistentUser
											.getPassword()), 60 * 60 * 24 * 7);
				}

				// Add user to session.
				session.put(Constants.LOGIN_USER, persistentUser);

				// Set the input stream.
				setInputStreamValue(persistentUser.getUsername());
			} else {

				// Set the input stream.
				setInputStreamValue("loginFailed");
			}
			return "result";
		}
		return null;
	}

	/**
	 * User logout.
	 * 
	 * @return
	 */
	public String logout() {

		// Remove the user cookie and session.
		if (session.get(Constants.LOGIN_USER) != null) {
			CookieUtil.deleteCookie(request, response,
					Constants.USER_AUTOLOGIN_COOKIE);
			session.remove(Constants.LOGIN_USER);
		}

		return "logout_success";
	}

	/**
	 * Redirect to user home page.
	 * 
	 * @return
	 */
	public String homepage() {

		// Check user.
		if (user != null && user.getUsername() != null
				&& user.getUsername().length() > 0) {

			User persistentUser = userService.getUserInformation(user
					.getUsername());

			if (persistentUser != null) {
				user = persistentUser;
				return "homepage";
			}
		}
		return "error";
	}

	/**
	 * @param userService
	 *            the userService to set
	 */
	public void setUserService(UserServiceInterface userService) {
		this.userService = userService;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the auto login
	 */
	public boolean isAutologin() {
		return autologin;
	}

	/**
	 * @param autologin
	 *            the auto login to set
	 */
	public void setAutologin(boolean autologin) {
		this.autologin = autologin;
	}

	/**
	 * @return the configurationService
	 */
	public ConfigurationServiceInterface getConfigurationService() {
		return configurationService;
	}

	/**
	 * @param configurationService
	 *            the configurationService to set
	 */
	public void setConfigurationService(
			ConfigurationServiceInterface configurationService) {
		this.configurationService = configurationService;
	}

}
