package com.riawworks.riaw.erp.service.cost;

import java.util.List;
import java.util.Map;

import com.riawworks.riaw.erp.framework.exception.ServiceException;
import com.riawworks.riaw.erp.framework.service.Service;
import com.riawworks.riaw.erp.model.po.TCostAllocationRecord;
import com.riawworks.riaw.erp.model.po.TPartner;
import com.riawworks.riaw.erp.model.vo.cost.BillReportVo;

public interface CostService extends Service {

	public List<TCostAllocationRecord> readBill(String period) throws ServiceException;

	public List<TPartner> readPartners() throws ServiceException;

	public TPartner readPartner(String partnerCode) throws ServiceException;

	public TCostAllocationRecord readCurrentPeriodCostAllocationRecord(String partnerCode,
			String subjectCode) throws ServiceException;

	public TCostAllocationRecord readLastPeriodCostAllocationRecord(String partnerCode,
			String subjectCode) throws ServiceException;

	public void createCurrentBill(String partnerCode, List<Map<String, Object>> data)
			throws ServiceException;

	public List<String> readPeriods() throws ServiceException;

	public List<BillReportVo> readBillReportVos(String period) throws ServiceException;
}
