package com.riawworks.riaw.erp.controller.cost.allocation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.riawworks.riaw.erp.enums.CostView;
import com.riawworks.riaw.erp.framework.enums.ReportFormat;
import com.riawworks.riaw.erp.framework.model.vo.JasperReportsVo;
import com.riawworks.riaw.erp.model.vo.cost.BillReportVo;
import com.riawworks.riaw.erp.service.cost.CostService;

@Controller
@RequestMapping("/cost/allocation/billReport")
public class BillReportController {

	private CostService costService;

	public CostService getCostService() {
		return costService;
	}

	@Resource(name = "costService")
	public void setCostService(CostService costService) {
		this.costService = costService;
	}

	@RequestMapping("/print")
	public ModelAndView print(HttpServletRequest request) throws Exception {
		String period = request.getParameter("period");

		List<BillReportVo> billVos = getCostService().readBillReportVos(period);

		JasperReportsVo jasperReportsVo = new JasperReportsVo();

		for (BillReportVo billVo : billVos) {
			List<Object> list = new ArrayList<Object>();
			list.add(billVo);
			jasperReportsVo.addReportData(list);
		}

		return new ModelAndView(CostView.COST_ALLOCATION_BILLREPORT.getValue()).addObject(
				jasperReportsVo).addObject("format", ReportFormat.PDF.getValue());
	}
}
