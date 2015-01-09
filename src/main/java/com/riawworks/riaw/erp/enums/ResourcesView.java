package com.riawworks.riaw.erp.enums;

public enum ResourcesView {

	RESOURCES_MENU_MANAGEMENT_INDEX("resources/menu/management/index"),

	RESOURCES_CUSTOMER_MANAGEMENT_INDEX("resources/customer/management/index"),

	RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX(
			"resources/customer/productPricingDifferentiation/index");

	private String value;

	ResourcesView(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
