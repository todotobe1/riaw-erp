package com.riawworks.riaw.erp.model.vo.resources;

public class ProductPricingDifferentiationVo {

	private Integer customizedProductPricingId;
	private String customerCode;
	private String productId;
	private String productName;
	private String unitId;
	private String unitName;
	private double unitPrice;

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getCustomizedProductPricingId() {
		return customizedProductPricingId;
	}

	public void setCustomizedProductPricingId(Integer customizedProductPricingId) {
		this.customizedProductPricingId = customizedProductPricingId;
	}

}
