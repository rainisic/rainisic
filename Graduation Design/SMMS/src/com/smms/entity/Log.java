/* @(#) Log.java
 * 
 * Date: 2012-3-9
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smms.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Rainisic
 * 
 */
@Entity
@Table(name="smms_log")
public class Log implements Serializable {

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

	/** Log id. */
	@Id
	@GeneratedValue
	private int id;
	
	/** Log create time. */
	@Column(nullable = false)
	private Calendar createTime;

	/** Operator. */
	@ManyToOne
	@JoinColumn(nullable = false)
	private Admin operator;

	/** Operation. */
	@Column(nullable = false)
	private String operation;

	/** Details. */
	@Column(nullable = false)
	private String details;

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
	 * @return the createTime
	 */
	public Calendar getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the operator
	 */
	public Admin getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	public void setOperator(Admin operator) {
		this.operator = operator;
	}

	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @param operation
	 *            the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details
	 *            the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}
}
