package com.riawworks.riaw.erp.framework.view.jasperreports;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;

public class JasperReportsHtmlView extends AbstractJasperReportsSingleFormatView {

	public JasperReportsHtmlView() {
		setContentType("text/html");
	}

	@Override
	protected JRExporter createExporter() {
		return new JRHtmlExporter();
	}

	@Override
	protected boolean useWriter() {
		return true;
	}

}
