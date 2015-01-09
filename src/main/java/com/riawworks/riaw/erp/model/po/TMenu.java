package com.riawworks.riaw.erp.model.po;

import java.sql.Timestamp;

/**
 * TMenu entity. @author MyEclipse Persistence Tools
 */

public class TMenu implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp ctime;
	private Timestamp mtime;
	private String mid;
	private String text;
	private String PMid;
	private String url;
	private String leaf;

	// Constructors

	/** default constructor */
	public TMenu() {
	}

	/** minimal constructor */
	public TMenu(Timestamp ctime, Timestamp mtime, String mid, String text,
			String PMid, String leaf) {
		this.ctime = ctime;
		this.mtime = mtime;
		this.mid = mid;
		this.text = text;
		this.PMid = PMid;
		this.leaf = leaf;
	}

	/** full constructor */
	public TMenu(Timestamp ctime, Timestamp mtime, String mid, String text,
			String PMid, String url, String leaf) {
		this.ctime = ctime;
		this.mtime = mtime;
		this.mid = mid;
		this.text = text;
		this.PMid = PMid;
		this.url = url;
		this.leaf = leaf;
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

	public String getMid() {
		return this.mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPMid() {
		return this.PMid;
	}

	public void setPMid(String PMid) {
		this.PMid = PMid;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLeaf() {
		return this.leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

}