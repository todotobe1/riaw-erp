package com.riawworks.riaw.erp.model.po;

import java.sql.Timestamp;

/**
 * TUserMenu entity. @author MyEclipse Persistence Tools
 */

public class TUserMenu implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp ctime;
	private Timestamp mtime;
	private Integer userId;
	private Integer menuId;

	// Constructors

	/** default constructor */
	public TUserMenu() {
	}

	/** full constructor */
	public TUserMenu(Timestamp ctime, Timestamp mtime, Integer userId,
			Integer menuId) {
		this.ctime = ctime;
		this.mtime = mtime;
		this.userId = userId;
		this.menuId = menuId;
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

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

}