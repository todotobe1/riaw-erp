package com.riawworks.riaw.erp.model.po;

import java.sql.Timestamp;

/**
 * TCustomizedProductPricing entity. @author MyEclipse Persistence Tools
 */

public class TCustomizedProductPricing implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp ctime;
	private Timestamp mtime;
	private Integer productPricingId;
	private Integer customerId;
	private String customerCode;
	private Double unitPrice;

	// Constructors

	/** default constructor */
	public TCustomizedProductPricing() {
	}

	/** full constructor */
	public TCustomizedProductPricing(Timestamp ctime, Timestamp mtime,
			Integer productPricingId, Integer customerId, String customerCode,
			Double unitPrice) {
		this.ctime = ctime;
		this.mtime = mtime;
		this.productPricingId = productPricingId;
		this.customerId = customerId;
		this.customerCode = customerCode;
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

	public Integer getProductPricingId() {
		return this.productPricingId;
	}

	public void setProductPricingId(Integer productPricingId) {
		this.productPricingId = productPricingId;
	}

	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerCode() {
		return this.customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public Double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

}