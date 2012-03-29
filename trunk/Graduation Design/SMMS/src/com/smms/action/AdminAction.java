/* @(#) AdminAction.java
 * 
 * Date: 2012-3-9
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smms.action;

import com.smms.entity.Admin;
import com.smms.service.AdminServiceInterface;

/**
 * @author Rainisic
 * 
 */
public class AdminAction extends BaseActionSupport {

	/** Add default serial version UID. */
	private static final long serialVersionUID = 1L;

	/** User service. */
	private AdminServiceInterface adminService;

	/** User. */
	private Admin admin;

	/**
	 * Administrator login.
	 * 
	 * @return
	 */
	public String login() {

		if (admin != null && admin.getUsername() != null
				&& admin.getUsername().length() > 0
				&& admin.getPassword() != null
				&& admin.getPassword().length() > 0) {
			
			// Administrator login.
			admin = adminService.login(admin);

			// Check login result.
			if (admin != null) {
				
				// Put to session.
				session.put("login_admin", admin);
				
				return SUCCESS;
			}
		}
		
		return ERROR;
	}

	/**
	 * @return the administrator
	 */
	public Admin getAdmin() {
		return admin;
	}

	/**
	 * @param admin
	 *            the administrator to set
	 */
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	/**
	 * @param adminService
	 *            the adminService to set
	 */
	public void setAdminService(AdminServiceInterface adminService) {
		this.adminService = adminService;
	}
}
