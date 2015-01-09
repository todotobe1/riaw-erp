package com.riawworks.riaw.erp.model.vo.cost;

public class CurrentBillVo {

	private String subjectName;
	private String subjectCode;
	private String unitPrice;
	private String unit;
	private String dataLastMonth;
	private String dataThisMonth;

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getDataLastMonth() {
		return dataLastMonth;
	}

	public void setDataLastMonth(String dataLastMonth) {
		this.dataLastMonth = dataLastMonth;
	}

	public String getDataThisMonth() {
		return dataThisMonth;
	}

	public void setDataThisMonth(String dataThisMonth) {
		this.dataThisMonth = dataThisMonth;
	}

}
