package com.riawworks.riaw.erp.framework.view.jasperreports;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class JasperReportsPdfView extends AbstractJasperReportsSingleFormatView {

	public JasperReportsPdfView() {
		setContentType("application/pdf");
	}

	@Override
	protected JRExporter createExporter() {
		return new JRPdfExporter();
	}

	@Override
	protected boolean useWriter() {
		return false;
	}

}
