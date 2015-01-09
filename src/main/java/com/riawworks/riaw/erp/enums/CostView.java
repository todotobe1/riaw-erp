package com.riawworks.riaw.erp.enums;

public enum CostView {

	COST_ALLOCATION_CURRENTBILL_INDEX("cost/allocation/currentBill/index"),

	COST_ALLOCATION_HISTORICALBILL_INDEX("cost/allocation/historicalBill/index"),

	COST_ALLOCATION_BILLREPORT("cost/allocation/bill-report");

	private String value;

	CostView(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
