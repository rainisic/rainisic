/* @(#) User.java
 * 
 * Date: 2012-2-17
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smt.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * @author Rainisic
 * 
 */
@Entity
public class User implements Serializable {

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

	/** User permission: Normal user. */
	public static final int USER = 0;

	/** User permission: Administrator. */
	public static final int ADMIN = 1;

	/** User status: Enabled. */
	public static final int ENABLED = 0;

	/** User status: Detached(By user). */
	public static final int DETACHED = 1;

	/** User status: Blocked(By Administrator). */
	public static final int BLOCKED = 2;

	/** Identifier. */
	@Id
	@GeneratedValue
	private int id;

	/** User name. */
	@Column(nullable = false)
	private String username;

	/** Password. */
	@Column(nullable = false)
	private String password;

	/** Nick name. */
	@Column(nullable = false)
	private String nickname;

	/** E-mail. */
	@Column(nullable = false)
	private String email;

	/** Gender. */
	@Column(nullable = false, columnDefinition = "char(1) default 'M'")
	private String gender = "M";

	/** Description. */
	private String description;

	/** Portrait path. */
	@Column(nullable = false, columnDefinition = "varchar(255) default 'upload/portrait/default.jpg'")
	private String portrait = "upload/portrait/default.jpg";

	/** User status. */
	@Column(nullable = false, columnDefinition = "int default 0")
	private int status = 0;

	/** User permission. */
	@Column(nullable = false, columnDefinition = "int default 0")
	private int permission = 0;

	/** Friends. */
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<User> friends = new HashSet<User>();
	
	/**
	 * Default constructor.
	 */
	public User() {
	}

	/**
	 * Constructed by username and password.
	 * 
	 * @param username
	 * @param password
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

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

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the portrait
	 */
	public String getPortrait() {
		return portrait;
	}

	/**
	 * @param portrait
	 *            the portrait to set
	 */
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the permission
	 */
	public int getPermission() {
		return permission;
	}

	/**
	 * @param permission
	 *            the permission to set
	 */
	public void setPermission(int permission) {
		this.permission = permission;
	}

	/**
	 * @return the friends
	 */
	public Set<User> getFriends() {
		return friends;
	}

	/**
	 * @param friends
	 *            the friends to set
	 */
	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}
}
