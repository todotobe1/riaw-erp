package com.riawworks.riaw.erp.model.vo.cost;

import java.util.List;

public class BillReportVo {

	private String period;
	private String name;
	List<BillReportDetailVo> billReportDetailVos;

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

	public List<BillReportDetailVo> getBillReportDetailVos() {
		return billReportDetailVos;
	}

	public void setBillReportDetailVos(List<BillReportDetailVo> billReportDetailVos) {
		this.billReportDetailVos = billReportDetailVos;
	}

}
