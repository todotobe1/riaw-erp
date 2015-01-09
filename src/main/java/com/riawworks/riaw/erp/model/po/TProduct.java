package com.riawworks.riaw.erp.model.po;

import java.sql.Timestamp;

/**
 * TProduct entity. @author MyEclipse Persistence Tools
 */

public class TProduct implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp ctime;
	private Timestamp mtime;
	private String enabled;
	private String productName;

	// Constructors

	/** default constructor */
	public TProduct() {
	}

	/** full constructor */
	public TProduct(Timestamp ctime, Timestamp mtime, String enabled,
			String productName) {
		this.ctime = ctime;
		this.mtime = mtime;
		this.enabled = enabled;
		this.productName = productName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public Timestamp getMtime() {
		return this.mtime;
	}

	public void setMtime(Timestamp mtime) {
		this.mtime = mtime;
	}

	public String getEnabled() {
		return this.enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}