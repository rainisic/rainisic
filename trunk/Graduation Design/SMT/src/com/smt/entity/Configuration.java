/* @(#) Configuration.java
 *
 * Date: 2012-4-17
 * 
 * Author: Rainisic
 */
package com.smt.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Rainisic
 * 
 */
@Entity
@Table(name = "smms_configuration")
public class Configuration implements Serializable {

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	private String theme;

	private boolean allowRegister;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
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
	 * @param theme
	 *            the theme to set
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * @return the allowRegister
	 */
	public boolean isAllowRegister() {
		return allowRegister;
	}

	/**
	 * @param allowRegister
	 *            the allowRegister to set
	 */
	public void setAllowRegister(boolean allowRegister) {
		this.allowRegister = allowRegister;
	}
}
