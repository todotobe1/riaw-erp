package com.riawworks.riaw.erp.model.po;

import java.sql.Timestamp;

/**
 * TMeasurementUnit entity. @author MyEclipse Persistence Tools
 */

public class TMeasurementUnit implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp ctime;
	private Timestamp mtime;
	private String unitName;

	// Constructors

	/** default constructor */
	public TMeasurementUnit() {
	}

	/** full constructor */
	public TMeasurementUnit(Timestamp ctime, Timestamp mtime, String unitName) {
		this.ctime = ctime;
		this.mtime = mtime;
		this.unitName = unitName;
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

	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

}