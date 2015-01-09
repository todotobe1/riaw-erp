package com.riawworks.riaw.erp.controller.cost.allocation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.riawworks.riaw.erp.enums.CostView;
import com.riawworks.riaw.erp.framework.model.vo.CodeVo;
import com.riawworks.riaw.erp.service.cost.CostService;

@Controller
@RequestMapping("/cost/allocation/historicalBill")
public class HistoricalBillController {

	private CostService costService;

	public CostService getCostService() {
		return costService;
	}

	@Resource(name = "costService")
	public void setCostService(CostService costService) {
		this.costService = costService;
	}

	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView(CostView.COST_ALLOCATION_HISTORICALBILL_INDEX.getValue());
	}

	@RequestMapping("/periods")
	public List<CodeVo> periods() throws Exception {
		List<String> periods = getCostService().readPeriods();
		List<CodeVo> list = new ArrayList<CodeVo>();
		for (String string : periods) {
			list.add(new CodeVo(string, string));
		}

		Collections.sort(list, new Comparator<CodeVo>() {
			@Override
			public int compare(CodeVo o1, CodeVo o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		list.remove(list.size() - 1);

		return list;
	}

}
