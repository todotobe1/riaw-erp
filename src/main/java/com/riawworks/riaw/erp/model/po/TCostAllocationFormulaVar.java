package com.riawworks.riaw.erp.model.po;

import java.sql.Timestamp;

public class TCostAllocationFormulaVar implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Timestamp ctime;
	private Timestamp mtime;
	private TCostAllocationSubject tCostAllocationSubject;
	private String variableName;
	private Double variableValue;
	private String variableUnit;

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

	public TCostAllocationSubject gettCostAllocationSubject() {
		return tCostAllocationSubject;
	}

	public void settCostAllocationSubject(TCostAllocationSubject tCostAllocationSubject) {
		this.tCostAllocationSubject = tCostAllocationSubject;
	}

	public String getVariableName() {
		return this.variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public Double getVariableValue() {
		return this.variableValue;
	}

	public void setVariableValue(Double variableValue) {
		this.variableValue = variableValue;
	}

	public String getVariableUnit() {
		return this.variableUnit;
	}

	public void setVariableUnit(String variableUnit) {
		this.variableUnit = variableUnit;
	}

}