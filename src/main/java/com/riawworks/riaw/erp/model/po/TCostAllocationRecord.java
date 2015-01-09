package com.riawworks.riaw.erp.model.po;

import java.sql.Timestamp;

public class TCostAllocationRecord implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Timestamp ctime;
	private Timestamp mtime;
	private String period;
	private TPartner tPartner;
	private TCostAllocationSubject tCostAllocationSubject;
	private Double data;
	private Double charge;

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

	public String getPeriod() {
		return this.period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public TPartner gettPartner() {
		return tPartner;
	}

	public void settPartner(TPartner tPartner) {
		this.tPartner = tPartner;
	}

	public TCostAllocationSubject gettCostAllocationSubject() {
		return tCostAllocationSubject;
	}

	public void settCostAllocationSubject(
			TCostAllocationSubject tCostAllocationSubject) {
		this.tCostAllocationSubject = tCostAllocationSubject;
	}

	public Double getData() {
		return this.data;
	}

	public void setData(Double data) {
		this.data = data;
	}

	public Double getCharge() {
		return this.charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

}