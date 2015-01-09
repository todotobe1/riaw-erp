package com.riawworks.riaw.erp.framework.model.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JasperReportsVo {

	private Collection<List<Object>> reportsData;

	public JasperReportsVo() {
		reportsData = new ArrayList<List<Object>>();
	}

	public JasperReportsVo addReportData(List<Object> reportData) {
		this.reportsData.add(reportData);
		return this;
	}

	public List<JRDataSource> createJrDataSource() {
		List<JRDataSource> jrDataSources = null;

		if (!hasReportsData())
			return jrDataSources;

		jrDataSources = new ArrayList<JRDataSource>();

		for (List<Object> reportData : reportsData) {
			jrDataSources.add(new JRBeanCollectionDataSource(reportData));
		}

		return jrDataSources;
	}

	public Boolean hasReportsData() {
		return !this.reportsData.isEmpty();
	}

}
