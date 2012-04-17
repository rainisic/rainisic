/* @(#) Configuration.java
 *
 * Date: 2012-4-17
 * 
 * Author: Rainisic
 */
package com.smt.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Rainisic
 *
 */
@Entity
@Table(name="smms_configuration")
public class Configuration implements Serializable {

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;
	
	private String theme;

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
