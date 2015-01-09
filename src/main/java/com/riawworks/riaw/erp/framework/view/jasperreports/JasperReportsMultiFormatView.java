package com.riawworks.riaw.erp.framework.view.jasperreports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperPrint;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

public class JasperReportsMultiFormatView extends AbstractJasperReportsView {

	public static final String DEFAULT_FORMAT_KEY = "format";
	private String formatKey = DEFAULT_FORMAT_KEY;
	private Map<String, Class<? extends AbstractJasperReportsView>> formatMappings;
	private Properties contentDispositionMappings;

	public JasperReportsMultiFormatView() {
		this.formatMappings = new HashMap<String, Class<? extends AbstractJasperReportsView>>(4);
		this.formatMappings.put("csv", JasperReportsCsvView.class);
		this.formatMappings.put("html", JasperReportsHtmlView.class);
		this.formatMappings.put("pdf", JasperReportsPdfView.class);
		this.formatMappings.put("xls", JasperReportsXlsView.class);
	}

	public void setFormatKey(String formatKey) {
		this.formatKey = formatKey;
	}

	public void setFormatMappings(Map<String, Class<? extends AbstractJasperReportsView>> formatMappings) {
		if (CollectionUtils.isEmpty(formatMappings)) {
			throw new IllegalArgumentException("'formatMappings' must not be empty");
		}
		this.formatMappings = formatMappings;
	}

	public void setContentDispositionMappings(Properties mappings) {
		this.contentDispositionMappings = mappings;
	}

	public Properties getContentDispositionMappings() {
		if (this.contentDispositionMappings == null) {
			this.contentDispositionMappings = new Properties();
		}
		return this.contentDispositionMappings;
	}

	@Override
	protected boolean generatesDownloadContent() {
		return true;
	}

	@Override
	protected void renderReport(List<JasperPrint> populatedReport, Map<String, Object> model,
			HttpServletResponse response) throws Exception {

		String format = (String) model.get(this.formatKey);
		if (format == null) {
			throw new IllegalArgumentException("No format format found in model");
		}

		Class<? extends AbstractJasperReportsView> viewClass = this.formatMappings.get(format);
		if (viewClass == null) {
			throw new IllegalArgumentException("Format discriminator [" + format + "] is not a configured mapping");
		}

		AbstractJasperReportsView view = BeanUtils.instantiateClass(viewClass);
		view.setExporterParameters(getExporterParameters());
		view.setConvertedExporterParameters(getConvertedExporterParameters());
		populateContentDispositionIfNecessary(response, format);
		view.renderReport(populatedReport, model, response);
	}

	private void populateContentDispositionIfNecessary(HttpServletResponse response, String format) {
		if (this.contentDispositionMappings != null) {
			String header = this.contentDispositionMappings.getProperty(format);
			if (header != null) {
				response.setHeader(HEADER_CONTENT_DISPOSITION, header);
			}
		}
	}

}
