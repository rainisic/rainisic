/* @(#) Category.java
 * Project:	Test
 * Package:	com.test.entity
 * Author:	Rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.test.entity;

/**
 * @author Rainisic
 *
 */
public class Category {

	private int id;
	
	private String name;
	
	private String url;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
