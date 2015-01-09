package com.riawworks.riaw.erp.framework.enums;

public enum ReportFormat {

	PDF("pdf");

	private String value;

	ReportFormat(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
