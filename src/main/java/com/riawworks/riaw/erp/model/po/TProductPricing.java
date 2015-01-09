package com.riawworks.riaw.erp.model.po;

import java.sql.Timestamp;

/**
 * TProductPricing entity. @author MyEclipse Persistence Tools
 */

public class TProductPricing implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp ctime;
	private Timestamp mtime;
	private Integer productId;
	private Integer unitId;
	private Double unitPrice;

	// Constructors

	/** default constructor */
	public TProductPricing() {
	}

	/** full constructor */
	public TProductPricing(Timestamp ctime, Timestamp mtime, Integer productId,
			Integer unitId, Double unitPrice) {
		this.ctime = ctime;
		this.mtime = mtime;
		this.productId = productId;
		this.unitId = unitId;
		this.unitPrice = unitPrice;
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

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

}