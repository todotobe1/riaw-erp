package com.riawworks.riaw.erp.model.po;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class TPartner implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Timestamp ctime;
	private Timestamp mtime;
	private THuman tHuman;
	private String partnerCode;
	private Set<TCostAllocationSubject> tCostAllocationSubjects = new HashSet<TCostAllocationSubject>();
	private Set<TCostAllocationRecord> tCostAllocationRecords = new HashSet<TCostAllocationRecord>();

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

	public THuman gettHuman() {
		return tHuman;
	}

	public void settHuman(THuman tHuman) {
		this.tHuman = tHuman;
	}

	public String getPartnerCode() {
		return this.partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public Set<TCostAllocationSubject> gettCostAllocationSubjects() {
		return tCostAllocationSubjects;
	}

	public void settCostAllocationSubjects(Set<TCostAllocationSubject> tCostAllocationSubjects) {
		this.tCostAllocationSubjects = tCostAllocationSubjects;
	}

	public Set<TCostAllocationRecord> gettCostAllocationRecords() {
		return tCostAllocationRecords;
	}

	public void settCostAllocationRecords(Set<TCostAllocationRecord> tCostAllocationRecords) {
		this.tCostAllocationRecords = tCostAllocationRecords;
	}

}