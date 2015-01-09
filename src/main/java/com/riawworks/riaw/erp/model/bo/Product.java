package com.riawworks.riaw.erp.model.bo;

import java.util.List;

public class Product {

	private Integer id;
	private String productName;
	private List<ProductPricing> productPricings;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<ProductPricing> getProductPricings() {
		return productPricings;
	}

	public void setProductPricings(List<ProductPricing> productPricings) {
		this.productPricings = productPricings;
	}

	public void addProductPricings(ProductPricing productPricing) {
		getProductPricings().add(productPricing);
	}

}
