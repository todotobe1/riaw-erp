package com.riawworks.riaw.erp.model.po;

import java.sql.Timestamp;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */

public class TUser implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp ctime;
	private Timestamp mtime;
	private String enabled;
	private Integer humanId;
	private String userName;
	private String password;

	// Constructors

	/** default constructor */
	public TUser() {
	}

	/** full constructor */
	public TUser(Timestamp ctime, Timestamp mtime, String enabled,
			Integer humanId, String userName, String password) {
		this.ctime = ctime;
		this.mtime = mtime;
		this.enabled = enabled;
		this.humanId = humanId;
		this.userName = userName;
		this.password = password;
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

	public Integer getHumanId() {
		return this.humanId;
	}

	public void setHumanId(Integer humanId) {
		this.humanId = humanId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}