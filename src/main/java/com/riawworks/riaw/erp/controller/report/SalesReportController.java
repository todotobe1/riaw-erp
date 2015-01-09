package com.riawworks.riaw.erp.controller.report;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.riawworks.riaw.erp.framework.json.JacksonMapper;
import com.riawworks.riaw.erp.framework.model.vo.JasperReportsVo;
import com.riawworks.riaw.erp.service.report.SalesReportService;

@Controller
@RequestMapping("/report/sales")
public class SalesReportController {

	private SalesReportService salesReportService;

	@RequestMapping("/testReport")
	public ModelAndView test(HttpServletRequest httpServletRequest)
			throws Exception {
		JasperReportsVo jasperReportsVo = new JasperReportsVo();

		String ids = httpServletRequest.getParameter("id");
		Integer[] idArr = JacksonMapper.getInstance().readValue(ids,
				Integer[].class);

		for (Integer id : idArr) {
			List<Object> list = new ArrayList<Object>();
			list.add(getSalesReportService().readDeliveryNote(id));
			jasperReportsVo.addReportData(list);
		}

		return new ModelAndView("sales/delivery-note-report").addObject(
				jasperReportsVo).addObject("format", "pdf");
	}

	public SalesReportService getSalesReportService() {
		return salesReportService;
	}

	@Resource(name = "salesReportService")
	public void setSalesReportService(SalesReportService salesReportService) {
		this.salesReportService = salesReportService;
	}

}
