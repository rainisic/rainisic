/* @(#) ThemeManagerAction.java
 *
 * Date: 2012-4-17
 * 
 * Author: Rainisic
 */
package com.smt.impl.action;

import java.util.LinkedList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.smt.impl.service.ConfigurationService;

/**
 * @author Rainisic
 *
 */
public class ThemeManagerAction extends ActionSupport {

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;
	
	private ConfigurationService configurationService = ConfigurationService.getInstance();
	
	/** The data contents. */
	private List<String[]> contents;
	
	private String theme;
	
	public String loadThemes() {
		contents = new LinkedList<String[]>();
		contents.add(new String[] { "default", "默认主题" });
		contents.add(new String[] { "fashion", "时尚主题" });
		return SUCCESS;
	}
	
	public String changeTheme() {
		if (theme != null && !theme.equals("")) {
			configurationService.changeTheme(theme);
			return SUCCESS;
		}
		return ERROR;
	}

	/**
	 * @return the contents
	 */
	public List<String[]> getContents() {
		return contents;
	}

	/**
	 * @param contents the contents to set
	 */
	public void setContents(List<String[]> contents) {
		this.contents = contents;
	}

	/**
	 * @return the theme
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * @param theme the theme to set
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}
}
