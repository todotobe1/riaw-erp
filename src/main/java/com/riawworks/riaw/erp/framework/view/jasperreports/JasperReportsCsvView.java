package com.riawworks.riaw.erp.framework.view.jasperreports;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;

public class JasperReportsCsvView extends AbstractJasperReportsSingleFormatView {

	public JasperReportsCsvView() {
		setContentType("text/csv");
	}

	@Override
	protected JRExporter createExporter() {
		return new JRCsvExporter();
	}

	@Override
	protected boolean useWriter() {
		return true;
	}

}
