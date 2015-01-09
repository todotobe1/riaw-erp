package com.riawworks.riaw.erp.controller.cost.allocation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.riawworks.riaw.erp.enums.CostView;
import com.riawworks.riaw.erp.framework.json.JacksonMapper;
import com.riawworks.riaw.erp.framework.model.vo.AjaxResponseVo;
import com.riawworks.riaw.erp.framework.model.vo.CodeVo;
import com.riawworks.riaw.erp.model.po.TCostAllocationFormulaVar;
import com.riawworks.riaw.erp.model.po.TCostAllocationRecord;
import com.riawworks.riaw.erp.model.po.TCostAllocationSubject;
import com.riawworks.riaw.erp.model.po.TPartner;
import com.riawworks.riaw.erp.model.vo.cost.CurrentBillVo;
import com.riawworks.riaw.erp.service.cost.CostService;

@Controller
@RequestMapping("/cost/allocation/currentBill")
public class CurrentBillController {

	private CostService costService;

	public CostService getCostService() {
		return costService;
	}

	@Resource(name = "costService")
	public void setCostService(CostService costService) {
		this.costService = costService;
	}

	@RequestMapping("/index")
	public ModelAndView index() throws Exception {
		return new ModelAndView(CostView.COST_ALLOCATION_CURRENTBILL_INDEX.getValue());
	}

	@RequestMapping("/partners")
	public List<CodeVo> partners() throws Exception {
		List<TPartner> tPartners = getCostService().readPartners();
		List<CodeVo> codeVos = new ArrayList<CodeVo>();
		for (TPartner tPartner : tPartners) {
			codeVos.add(new CodeVo(tPartner.getPartnerCode(), tPartner.gettHuman().getLastName()
					+ tPartner.gettHuman().getFirstName()));
		}
		return codeVos;
	}

	@RequestMapping("/partnerCurrentBill")
	public List<CurrentBillVo> partnerCurrentBill(HttpServletRequest request) throws Exception {
		List<CurrentBillVo> currentBillVos = new ArrayList<CurrentBillVo>();

		TPartner tPartner = getCostService().readPartner(request.getParameter("partnerCode"));

		for (TCostAllocationSubject tCostAllocationSubject : tPartner.gettCostAllocationSubjects()) {
			CurrentBillVo currentBillVo = new CurrentBillVo();

			TCostAllocationRecord tCostAllocationRecordCurrent = getCostService()
					.readCurrentPeriodCostAllocationRecord(tPartner.getPartnerCode(),
							tCostAllocationSubject.getSubjectCode());

			TCostAllocationRecord tCostAllocationRecordLast = getCostService()
					.readLastPeriodCostAllocationRecord(tPartner.getPartnerCode(),
							tCostAllocationSubject.getSubjectCode());

			currentBillVo.setDataLastMonth(tCostAllocationRecordLast.getData().toString());

			Double data = tCostAllocationRecordCurrent == null ? 0.0 : tCostAllocationRecordCurrent
					.getData();
			currentBillVo.setDataThisMonth(data.toString());
			currentBillVo.setSubjectCode(tCostAllocationSubject.getSubjectCode());
			currentBillVo.setSubjectName(tCostAllocationSubject.getSubjectName());

			for (TCostAllocationFormulaVar tCostAllocationFormulaVar : tCostAllocationSubject
					.gettCostAllocationFormulaVars()) {
				if (tCostAllocationFormulaVar.getVariableName().equals("x")) {
					currentBillVo.setUnitPrice(tCostAllocationFormulaVar.getVariableValue()
							.toString());
					currentBillVo.setUnit(tCostAllocationFormulaVar.getVariableUnit());
				}
			}

			currentBillVos.add(currentBillVo);
		}

		Collections.sort(currentBillVos, new Comparator<CurrentBillVo>() {
			@Override
			public int compare(CurrentBillVo o1, CurrentBillVo o2) {
				return o1.getSubjectCode().compareTo(o2.getSubjectCode());
			}
		});

		return currentBillVos;
	}

	@RequestMapping("/save")
	public AjaxResponseVo<Object> save(HttpServletRequest request) {
		try {
			String partnerCode = request.getParameter("partnerCode");
			String data = request.getParameter("data");
			List<Map<String, Object>> list = JacksonMapper.getInstance()
					.readValue(data, List.class);
			getCostService().createCurrentBill(partnerCode, list);
			return new AjaxResponseVo<Object>(AjaxResponseVo.SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResponseVo<Object>(AjaxResponseVo.FAIL, e.getMessage());
		}
	}

}
