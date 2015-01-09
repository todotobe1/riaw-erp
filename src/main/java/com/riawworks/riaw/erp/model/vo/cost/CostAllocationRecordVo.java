package com.riawworks.riaw.erp.model.vo.cost;

import java.util.Map;

public class CostAllocationRecordVo {

	private String period;
	private String name;
	private String subjectCode;
	private String subjectName;
	private Map<String, CostAllocationFormulaVarVo> map;
	private Double data;
	private Double charge;
	private String partnerName;

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Map<String, CostAllocationFormulaVarVo> getMap() {
		return map;
	}

	public void setMap(Map<String, CostAllocationFormulaVarVo> map) {
		this.map = map;
	}

	public Double getData() {
		return data;
	}

	public void setData(Double data) {
		this.data = data;
	}

	public Double getCharge() {
		return charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

}
