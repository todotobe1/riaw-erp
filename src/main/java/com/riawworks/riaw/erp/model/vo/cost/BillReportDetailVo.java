package com.riawworks.riaw.erp.model.vo.cost;

public class BillReportDetailVo {

	private String subjectName;
	private Double data;
	private Double lastMonthData;
	private String formula;
	private Double charge;
	private String chargingType;

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Double getData() {
		return data;
	}

	public void setData(Double data) {
		this.data = data;
	}

	public Double getLastMonthData() {
		return lastMonthData;
	}

	public void setLastMonthData(Double lastMonthData) {
		this.lastMonthData = lastMonthData;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public Double getCharge() {
		return charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

	public String getChargingType() {
		return chargingType;
	}

	public void setChargingType(String chargingType) {
		this.chargingType = chargingType;
	}

}
