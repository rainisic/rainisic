/* @(#) Category.java
 * Project:	plus
 * Package: com.rainisic.plus.entity
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.plus.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The entity class of category.
 * @author rainisic
 * @version 1.0.0
 */
@Entity
public class Category {

	/** Category ID. */
	@Id
	@GeneratedValue
	private int id;
	
	/** Category name. */
	private String name;
	
	/** Category query URL. */
	private String url;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
