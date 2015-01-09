package com.riawworks.riaw.erp.model.po;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class TCostAllocationSubject implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Timestamp ctime;
	private Timestamp mtime;
	private String subjectName;
	private String subjectCode;
	private String formula;
	private String chargingType;
	private Set<TCostAllocationFormulaVar> tCostAllocationFormulaVars = new HashSet<TCostAllocationFormulaVar>();

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

	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectCode() {
		return this.subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getFormula() {
		return this.formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getChargingType() {
		return this.chargingType;
	}

	public void setChargingType(String chargingType) {
		this.chargingType = chargingType;
	}

	public Set<TCostAllocationFormulaVar> gettCostAllocationFormulaVars() {
		return tCostAllocationFormulaVars;
	}

	public void settCostAllocationFormulaVars(
			Set<TCostAllocationFormulaVar> tCostAllocationFormulaVars) {
		this.tCostAllocationFormulaVars = tCostAllocationFormulaVars;
	}

}