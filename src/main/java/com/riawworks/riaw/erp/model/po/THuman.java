package com.riawworks.riaw.erp.model.po;

import java.sql.Timestamp;
import java.util.Date;

/**
 * THuman entity. @author MyEclipse Persistence Tools
 */

public class THuman implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp ctime;
	private Timestamp mtime;
	private String enabled;
	private String firstName;
	private String lastName;
	private String sex;
	private Date birthday;

	// Constructors

	/** default constructor */
	public THuman() {
	}

	/** minimal constructor */
	public THuman(Timestamp ctime, Timestamp mtime, String enabled,
			String firstName, String lastName, String sex) {
		this.ctime = ctime;
		this.mtime = mtime;
		this.enabled = enabled;
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
	}

	/** full constructor */
	public THuman(Timestamp ctime, Timestamp mtime, String enabled,
			String firstName, String lastName, String sex, Date birthday) {
		this.ctime = ctime;
		this.mtime = mtime;
		this.enabled = enabled;
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.birthday = birthday;
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

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}