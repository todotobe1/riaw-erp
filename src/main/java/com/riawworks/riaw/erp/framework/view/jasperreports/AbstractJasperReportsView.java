package com.riawworks.riaw.erp.framework.view.jasperreports;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDataSourceProvider;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.springframework.context.ApplicationContextException;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.core.io.Resource;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

import com.riawworks.riaw.erp.framework.model.vo.JasperReportsVo;

public abstract class AbstractJasperReportsView extends AbstractUrlBasedView {

	protected static final String HEADER_CONTENT_DISPOSITION = "Content-Disposition";
	protected static final String CONTENT_DISPOSITION_INLINE = "inline";

	private String reportDataKey;
	private Properties subReportUrls;
	private Properties headers;
	private Map<?, ?> exporterParameters = new HashMap<Object, Object>();
	private Map<JRExporterParameter, Object> convertedExporterParameters;
	private JasperReport report;
	private Map<String, JasperReport> subReports;

	public void setReportDataKey(String reportDataKey) {
		this.reportDataKey = reportDataKey;
	}

	public void setSubReportUrls(Properties subReports) {
		this.subReportUrls = subReports;
	}

	public void setHeaders(Properties headers) {
		this.headers = headers;
	}

	public void setExporterParameters(Map<?, ?> parameters) {
		this.exporterParameters = parameters;
	}

	public Map<?, ?> getExporterParameters() {
		return this.exporterParameters;
	}

	protected void setConvertedExporterParameters(Map<JRExporterParameter, Object> convertedExporterParameters) {
		this.convertedExporterParameters = convertedExporterParameters;
	}

	protected Map<JRExporterParameter, Object> getConvertedExporterParameters() {
		return this.convertedExporterParameters;
	}

	@Override
	protected boolean isUrlRequired() {
		return false;
	}

	@Override
	protected final void initApplicationContext() throws ApplicationContextException {
		this.report = loadReport();

		if (this.subReportUrls != null) {
			this.subReports = new HashMap<String, JasperReport>(this.subReportUrls.size());
			for (Enumeration<?> urls = this.subReportUrls.propertyNames(); urls.hasMoreElements();) {
				String key = (String) urls.nextElement();
				String path = this.subReportUrls.getProperty(key);
				Resource resource = getApplicationContext().getResource(path);
				this.subReports.put(key, loadReport(resource));
			}
		}

		convertExporterParameters();

		if (this.headers == null) {
			this.headers = new Properties();
		}
		if (!this.headers.containsKey(HEADER_CONTENT_DISPOSITION)) {
			this.headers.setProperty(HEADER_CONTENT_DISPOSITION, CONTENT_DISPOSITION_INLINE);
		}

		onInit();
	}

	protected void onInit() {
	}

	protected final void convertExporterParameters() {
		if (!CollectionUtils.isEmpty(this.exporterParameters)) {
			this.convertedExporterParameters = new HashMap<JRExporterParameter, Object>(this.exporterParameters.size());
			for (Map.Entry<?, ?> entry : this.exporterParameters.entrySet()) {
				JRExporterParameter exporterParameter = getExporterParameter(entry.getKey());
				this.convertedExporterParameters.put(exporterParameter,
						convertParameterValue(exporterParameter, entry.getValue()));
			}
		}
	}

	protected Object convertParameterValue(JRExporterParameter parameter, Object value) {
		if (value instanceof String) {
			String str = (String) value;
			if ("true".equals(str)) {
				return Boolean.TRUE;
			} else if ("false".equals(str)) {
				return Boolean.FALSE;
			} else if (str.length() > 0 && Character.isDigit(str.charAt(0))) {
				try {
					return new Integer(str);
				} catch (NumberFormatException ex) {
					return str;
				}
			}
		}
		return value;
	}

	protected JRExporterParameter getExporterParameter(Object parameter) {
		if (parameter instanceof JRExporterParameter) {
			return (JRExporterParameter) parameter;
		}
		if (parameter instanceof String) {
			return convertToExporterParameter((String) parameter);
		}
		throw new IllegalArgumentException("Parameter [" + parameter
				+ "] is invalid type. Should be either String or JRExporterParameter.");
	}

	protected JRExporterParameter convertToExporterParameter(String fqFieldName) {
		int index = fqFieldName.lastIndexOf('.');
		if (index == -1 || index == fqFieldName.length()) {
			throw new IllegalArgumentException("Parameter name [" + fqFieldName + "] is not a valid static field. "
					+ "The parameter name must map to a static field such as "
					+ "[net.sf.jasperreports.engine.export.JRHtmlExporterParameter.IMAGES_URI]");
		}
		String className = fqFieldName.substring(0, index);
		String fieldName = fqFieldName.substring(index + 1);

		try {
			Class<?> cls = ClassUtils.forName(className, getApplicationContext().getClassLoader());
			Field field = cls.getField(fieldName);

			if (JRExporterParameter.class.isAssignableFrom(field.getType())) {
				try {
					return (JRExporterParameter) field.get(null);
				} catch (IllegalAccessException ex) {
					throw new IllegalArgumentException("Unable to access field [" + fieldName + "] of class ["
							+ className + "]. " + "Check that it is static and accessible.");
				}
			} else {
				throw new IllegalArgumentException("Field [" + fieldName + "] on class [" + className
						+ "] is not assignable from JRExporterParameter - check the type of this field.");
			}
		} catch (ClassNotFoundException ex) {
			throw new IllegalArgumentException("Class [" + className + "] in key [" + fqFieldName
					+ "] could not be found.");
		} catch (NoSuchFieldException ex) {
			throw new IllegalArgumentException("Field [" + fieldName + "] in key [" + fqFieldName
					+ "] could not be found on class [" + className + "].");
		}
	}

	protected JasperReport loadReport() {
		String url = getUrl();
		if (url == null) {
			return null;
		}
		Resource mainReport = getApplicationContext().getResource(url);
		return loadReport(mainReport);
	}

	protected final JasperReport loadReport(Resource resource) {
		try {
			String filename = resource.getFilename();
			if (filename != null) {
				if (filename.endsWith(".jasper")) {
					InputStream is = resource.getInputStream();
					try {
						return (JasperReport) JRLoader.loadObject(is);
					} finally {
						is.close();
					}
				} else if (filename.endsWith(".jrxml")) {
					InputStream is = resource.getInputStream();
					try {
						JasperDesign design = JRXmlLoader.load(is);
						return JasperCompileManager.compileReport(design);
					} finally {
						is.close();
					}
				}
			}
			throw new IllegalArgumentException("Report filename [" + filename
					+ "] must end in either .jasper or .jrxml");
		} catch (IOException ex) {
			throw new ApplicationContextException("Could not load JasperReports report from " + resource, ex);
		} catch (JRException ex) {
			throw new ApplicationContextException("Could not parse JasperReports report from " + resource, ex);
		}
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (this.subReports != null) {
			model.putAll(this.subReports);
		}
		exposeLocalizationContext(model, request);
		List<JasperPrint> filledReport = fillReport(model);
		postProcessReport(filledReport, model);
		populateHeaders(response);
		renderReport(filledReport, model, response);
	}

	protected void exposeLocalizationContext(Map<String, Object> model, HttpServletRequest request) {
		RequestContext rc = new RequestContext(request, getServletContext());
		Locale locale = rc.getLocale();
		if (!model.containsKey(JRParameter.REPORT_LOCALE)) {
			model.put(JRParameter.REPORT_LOCALE, locale);
		}
		TimeZone timeZone = rc.getTimeZone();
		if (timeZone != null && !model.containsKey(JRParameter.REPORT_TIME_ZONE)) {
			model.put(JRParameter.REPORT_TIME_ZONE, timeZone);
		}
		JasperReport report = getReport();
		if ((report == null || report.getResourceBundle() == null)
				&& !model.containsKey(JRParameter.REPORT_RESOURCE_BUNDLE)) {
			model.put(JRParameter.REPORT_RESOURCE_BUNDLE,
					new MessageSourceResourceBundle(rc.getMessageSource(), locale));
		}
	}

	protected List<JasperPrint> fillReport(Map<String, Object> model) throws Exception {
		JasperReport report = getReport();
		if (report == null) {
			throw new IllegalStateException("No main report defined for 'fillReport' - "
					+ "specify a 'url' on this view or override 'getReport()' or 'fillReport(Map)'");
		}

		JasperReportsVo data = null;
		if (this.reportDataKey != null) {
			data = (JasperReportsVo) (model.get(this.reportDataKey));
		} else {
			data = CollectionUtils.findValueOfType(model.values(), JasperReportsVo.class);
		}

		List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
		if (data.hasReportsData()) {
			for (JRDataSource reportData : data.createJrDataSource()) {
				jasperPrints.add(JasperFillManager.fillReport(report, model, reportData));
			}
		} else {
			jasperPrints.add(JasperFillManager.fillReport(report, model));
		}
		return jasperPrints;
	}

	private void populateHeaders(HttpServletResponse response) {
		// Apply the headers to the response.
		for (Enumeration<?> en = this.headers.propertyNames(); en.hasMoreElements();) {
			String key = (String) en.nextElement();
			response.addHeader(key, this.headers.getProperty(key));
		}
	}

	protected JasperReport getReport() {
		return this.report;
	}

	protected JRDataSource createReport(JRDataSourceProvider provider) {
		try {
			JasperReport report = getReport();
			if (report == null) {
				throw new IllegalStateException("No main report defined for JRDataSourceProvider - "
						+ "specify a 'url' on this view or override 'getReport()'");
			}
			return provider.create(report);
		} catch (JRException ex) {
			throw new IllegalArgumentException("Supplied JRDataSourceProvider is invalid", ex);
		}
	}

	protected void postProcessReport(List<JasperPrint> populatedReport, Map<String, Object> model) throws Exception {
	}

	protected abstract void renderReport(List<JasperPrint> populatedReport, Map<String, Object> model,
			HttpServletResponse response) throws Exception;

}
