package com.riawworks.riaw.erp.framework.view.jasperreports;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;

import org.springframework.util.CollectionUtils;
import org.springframework.web.util.WebUtils;

import com.riawworks.riaw.erp.framework.util.JasperReportsUtils;

public abstract class AbstractJasperReportsSingleFormatView extends AbstractJasperReportsView {

	@Override
	protected boolean generatesDownloadContent() {
		return !useWriter();
	}

	@Override
	protected void renderReport(List<JasperPrint> populatedReport, Map<String, Object> model,
			HttpServletResponse response) throws Exception {
		JRExporter exporter = createExporter();

		Map<JRExporterParameter, Object> mergedExporterParameters = getConvertedExporterParameters();
		if (!CollectionUtils.isEmpty(mergedExporterParameters)) {
			exporter.setParameters(mergedExporterParameters);
		}

		if (useWriter()) {
			renderReportUsingWriter(exporter, populatedReport, response);
		} else {
			renderReportUsingOutputStream(exporter, populatedReport, response);
		}
	}

	protected void renderReportUsingWriter(JRExporter exporter, List<JasperPrint> populatedReport,
			HttpServletResponse response) throws Exception {
		String contentType = getContentType();
		String encoding = (String) exporter.getParameter(JRExporterParameter.CHARACTER_ENCODING);
		if (encoding != null) {
			if (contentType != null && !contentType.toLowerCase().contains(WebUtils.CONTENT_TYPE_CHARSET_PREFIX)) {
				contentType = contentType + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + encoding;
			}
		}
		response.setContentType(contentType);
		JasperReportsUtils.render(exporter, populatedReport, response.getWriter());
	}

	protected void renderReportUsingOutputStream(JRExporter exporter, List<JasperPrint> populatedReport,
			HttpServletResponse response) throws Exception {
		ByteArrayOutputStream baos = createTemporaryOutputStream();
		JasperReportsUtils.render(exporter, populatedReport, baos);
		writeToResponse(response, baos);
	}

	protected abstract JRExporter createExporter();

	protected abstract boolean useWriter();

}
