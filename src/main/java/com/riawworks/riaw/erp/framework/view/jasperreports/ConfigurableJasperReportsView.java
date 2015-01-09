package com.riawworks.riaw.erp.framework.view.jasperreports;

import net.sf.jasperreports.engine.JRExporter;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

public class ConfigurableJasperReportsView extends AbstractJasperReportsSingleFormatView {

	private Class<? extends JRExporter> exporterClass;
	private boolean useWriter = true;

	public void setExporterClass(Class<? extends JRExporter> exporterClass) {
		Assert.isAssignable(JRExporter.class, exporterClass);
		this.exporterClass = exporterClass;
	}

	public void setUseWriter(boolean useWriter) {
		this.useWriter = useWriter;
	}

	@Override
	protected void onInit() {
		if (this.exporterClass == null) {
			throw new IllegalArgumentException("exporterClass is required");
		}
	}

	@Override
	protected JRExporter createExporter() {
		return BeanUtils.instantiateClass(this.exporterClass);
	}

	@Override
	protected boolean useWriter() {
		return this.useWriter;
	}

}
