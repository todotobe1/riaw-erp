package com.riawworks.riaw.erp.model.po;

import java.sql.Timestamp;

/**
 * TCustomer entity. @author MyEclipse Persistence Tools
 */

public class TCustomer implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp ctime;
	private Timestamp mtime;
	private String enabled;
	private String code;
	private String name;
	private String liaison;
	private String telephone;

	// Constructors

	/** default constructor */
	public TCustomer() {
	}

	/** full constructor */
	public TCustomer(Timestamp ctime, Timestamp mtime, String enabled,
			String code, String name, String liaison, String telephone) {
		this.ctime = ctime;
		this.mtime = mtime;
		this.enabled = enabled;
		this.code = code;
		this.name = name;
		this.liaison = liaison;
		this.telephone = telephone;
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLiaison() {
		return this.liaison;
	}

	public void setLiaison(String liaison) {
		this.liaison = liaison;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}