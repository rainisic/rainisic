/* @(#) Configuration.java
 *
 * Date: 2012-4-17
 * 
 * Author: Rainisic
 */
package com.smt.impl.entity;

import java.io.Serializable;

/**
 * @author Rainisic
 *
 */
public class Configuration implements Serializable {

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String theme;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
