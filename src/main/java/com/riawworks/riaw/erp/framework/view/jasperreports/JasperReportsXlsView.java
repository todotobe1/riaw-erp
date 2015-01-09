package com.riawworks.riaw.erp.framework.view.jasperreports;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

public class JasperReportsXlsView extends AbstractJasperReportsSingleFormatView {

	public JasperReportsXlsView() {
		setContentType("application/vnd.ms-excel");
	}

	@Override
	protected JRExporter createExporter() {
		return new JRXlsExporter();
	}

	@Override
	protected boolean useWriter() {
		return false;
	}

}
