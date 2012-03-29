/* @(#) Admin.java
 * 
 * Date: 2012-3-9
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Rainisic
 * 
 */
@Entity
@Table(name="smms_admin")
public class Admin implements Serializable {

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

	/** Administrator id. */
	@Id
	@GeneratedValue
	private int id;

	/** username. */
	@Column(nullable = false)
	private String username;

	/** Password. */
	@Column(nullable = false)
	private String password;

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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
}
