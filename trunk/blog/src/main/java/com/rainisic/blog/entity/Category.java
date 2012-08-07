/* @(#) Category.java
 * Project:	blog
 * Package: com.rainisic.blog.entity
 * Author:	rainisic
 * Copyright © 2012 by RainRhyme Internet Studio. All rights reserved.
 */
package com.rainisic.blog.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The entity class of category.
 * 
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
