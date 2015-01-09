package com.riawworks.riaw.erp.framework.view.jasperreports;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

public class JasperReportsViewResolver extends UrlBasedViewResolver {

	private String reportDataKey;
	private Properties subReportUrls;
	private Properties headers;
	private Map<String, Object> exporterParameters = new HashMap<String, Object>();

	@Override
	protected Class<?> requiredViewClass() {
		return AbstractJasperReportsView.class;
	}

	public void setReportDataKey(String reportDataKey) {
		this.reportDataKey = reportDataKey;
	}

	public void setSubReportUrls(Properties subReportUrls) {
		this.subReportUrls = subReportUrls;
	}

	public void setHeaders(Properties headers) {
		this.headers = headers;
	}

	public void setExporterParameters(Map<String, Object> exporterParameters) {
		this.exporterParameters = exporterParameters;
	}

	@Override
	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		AbstractJasperReportsView view = (AbstractJasperReportsView) super.buildView(viewName);
		view.setReportDataKey(this.reportDataKey);
		view.setSubReportUrls(this.subReportUrls);
		view.setHeaders(this.headers);
		view.setExporterParameters(this.exporterParameters);
		return view;
	}

}
