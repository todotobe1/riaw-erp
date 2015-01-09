package com.riawworks.riaw.erp.model.po;

import java.sql.Timestamp;

/**
 * TCostAllocationPartner entity. @author MyEclipse Persistence Tools
 */

public class TCostAllocationPartner implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp ctime;
	private Timestamp mtime;
	private Integer subjectId;
	private Integer partnerId;

	// Constructors

	/** default constructor */
	public TCostAllocationPartner() {
	}

	/** full constructor */
	public TCostAllocationPartner(Timestamp ctime, Timestamp mtime,
			Integer subjectId, Integer partnerId) {
		this.ctime = ctime;
		this.mtime = mtime;
		this.subjectId = subjectId;
		this.partnerId = partnerId;
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

	public Integer getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}

}