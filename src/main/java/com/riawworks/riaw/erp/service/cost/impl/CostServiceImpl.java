package com.riawworks.riaw.erp.service.cost.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.riawworks.riaw.erp.dao.CostAllocationFormulaVarDao;
import com.riawworks.riaw.erp.dao.CostAllocationRecordDao;
import com.riawworks.riaw.erp.dao.CostAllocationSubjectDao;
import com.riawworks.riaw.erp.dao.HumanDao;
import com.riawworks.riaw.erp.dao.PartnerDao;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.framework.exception.ServiceException;
import com.riawworks.riaw.erp.framework.service.impl.ServiceImpl;
import com.riawworks.riaw.erp.framework.util.RiawDateUtils;
import com.riawworks.riaw.erp.model.po.TCostAllocationFormulaVar;
import com.riawworks.riaw.erp.model.po.TCostAllocationRecord;
import com.riawworks.riaw.erp.model.po.TCostAllocationSubject;
import com.riawworks.riaw.erp.model.po.TPartner;
import com.riawworks.riaw.erp.model.vo.cost.BillReportDetailVo;
import com.riawworks.riaw.erp.model.vo.cost.BillReportVo;
import com.riawworks.riaw.erp.service.cost.CostService;

import fr.expression4j.core.Expression;
import fr.expression4j.core.Parameters;
import fr.expression4j.factory.ExpressionFactory;
import fr.expression4j.factory.NumberFactory;

public class CostServiceImpl extends ServiceImpl implements CostService {

	private CostAllocationRecordDao costAllocationRecordDao;
	private PartnerDao partnerDao;
	private CostAllocationSubjectDao costAllocationSubjectDao;
	private HumanDao humanDao;
	private CostAllocationFormulaVarDao costAllocationFormulaVarDao;

	public CostAllocationRecordDao getCostAllocationRecordDao() {
		return costAllocationRecordDao;
	}

	public void setCostAllocationRecordDao(CostAllocationRecordDao costAllocationRecordDao) {
		this.costAllocationRecordDao = costAllocationRecordDao;
	}

	public PartnerDao getPartnerDao() {
		return partnerDao;
	}

	public void setPartnerDao(PartnerDao partnerDao) {
		this.partnerDao = partnerDao;
	}

	public CostAllocationSubjectDao getCostAllocationSubjectDao() {
		return costAllocationSubjectDao;
	}

	public void setCostAllocationSubjectDao(CostAllocationSubjectDao costAllocationSubjectDao) {
		this.costAllocationSubjectDao = costAllocationSubjectDao;
	}

	public HumanDao getHumanDao() {
		return humanDao;
	}

	public void setHumanDao(HumanDao humanDao) {
		this.humanDao = humanDao;
	}

	public CostAllocationFormulaVarDao getCostAllocationFormulaVarDao() {
		return costAllocationFormulaVarDao;
	}

	public void setCostAllocationFormulaVarDao(
			CostAllocationFormulaVarDao costAllocationFormulaVarDao) {
		this.costAllocationFormulaVarDao = costAllocationFormulaVarDao;
	}

	@Override
	public List<TCostAllocationRecord> readBill(String period) throws ServiceException {
		List<TCostAllocationRecord> list;
		try {
			list = getCostAllocationRecordDao().readByPeriod(period);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		return list;
	}

	@Override
	public List<TPartner> readPartners() throws ServiceException {
		try {
			return getPartnerDao().readAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public TPartner readPartner(String partnerCode) throws ServiceException {
		try {
			return getPartnerDao().readByCode(partnerCode);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public TCostAllocationRecord readCurrentPeriodCostAllocationRecord(String partnerCode,
			String subjectCode) throws ServiceException {
		try {
			return getCostAllocationRecordDao()
					.read(RiawDateUtils.formatDate(RiawDateUtils.backClock(Calendar.MONTH, -1),
							"yyyyMM"), partnerCode, subjectCode);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public TCostAllocationRecord readLastPeriodCostAllocationRecord(String partnerCode,
			String subjectCode) throws ServiceException {
		try {
			return getCostAllocationRecordDao()
					.read(RiawDateUtils.formatDate(RiawDateUtils.backClock(Calendar.MONTH, -2),
							"yyyyMM"), partnerCode, subjectCode);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public void createCurrentBill(String partnerCode, List<Map<String, Object>> data)
			throws ServiceException {
		try {
			String currentPeriod = RiawDateUtils.formatDate(
					RiawDateUtils.backClock(Calendar.MONTH, -1), "yyyyMM");

			String lastPeriod = RiawDateUtils.formatDate(
					RiawDateUtils.backClock(Calendar.MONTH, -2), "yyyyMM");

			for (Map<String, Object> map : data) {

				TCostAllocationRecord tCostAllocationRecord = getCostAllocationRecordDao().read(
						currentPeriod, partnerCode, map.get("name").toString());

				if (tCostAllocationRecord == null)
					tCostAllocationRecord = new TCostAllocationRecord();

				tCostAllocationRecord.setData(Double.parseDouble(map.get("value").toString()));
				tCostAllocationRecord.setPeriod(currentPeriod);

				TCostAllocationSubject tCostAllocationSubject = getCostAllocationSubjectDao()
						.readByCode(map.get("name").toString());
				tCostAllocationRecord.settCostAllocationSubject(tCostAllocationSubject);

				tCostAllocationRecord.settPartner(getPartnerDao().readByCode(partnerCode));

				TCostAllocationRecord tCostAllocationRecordLast = getCostAllocationRecordDao()
						.read(lastPeriod, partnerCode, map.get("name").toString());

				Double lastData = tCostAllocationRecordLast == null ? 0.0
						: tCostAllocationRecordLast.getData();

				Double deltaData = tCostAllocationRecord.getData() - lastData;

				Expression expression = ExpressionFactory.createExpression(tCostAllocationSubject
						.getFormula().replaceAll("data", deltaData + ""));
				Parameters parameters = ExpressionFactory.createParameters();

				Set<TCostAllocationFormulaVar> set = tCostAllocationSubject
						.gettCostAllocationFormulaVars();

				for (TCostAllocationFormulaVar tCostAllocationFormulaVar : set) {
					parameters.addParameter(tCostAllocationFormulaVar.getVariableName(),
							NumberFactory.createReal(tCostAllocationFormulaVar.getVariableValue()));
				}

				tCostAllocationRecord.setCharge(expression.evaluate(parameters).getRealValue());

				getCostAllocationRecordDao().createOrUpdate(tCostAllocationRecord);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}

	}

	@Override
	public List<String> readPeriods() throws ServiceException {
		try {
			return getCostAllocationRecordDao().readDistinctPeriods();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public List<BillReportVo> readBillReportVos(String period) throws ServiceException {
		try {
			List<BillReportVo> billReportVos = new ArrayList<BillReportVo>();

			List<TPartner> tPartners = getPartnerDao().readAll();
			for (TPartner tPartner : tPartners) {
				BillReportVo billReportVo = new BillReportVo();
				billReportVo.setName(tPartner.gettHuman().getLastName()
						+ tPartner.gettHuman().getFirstName());
				billReportVo.setPeriod(period);

				Set<TCostAllocationRecord> tCostAllocationRecords = tPartner
						.gettCostAllocationRecords();

				List<BillReportDetailVo> billReportDetailVos = new ArrayList<BillReportDetailVo>();
				billReportVo.setBillReportDetailVos(billReportDetailVos);

				for (TCostAllocationRecord tCostAllocationRecord : tCostAllocationRecords) {
					TCostAllocationSubject tCostAllocationSubject = tCostAllocationRecord
							.gettCostAllocationSubject();

					BillReportDetailVo billReportDetailVo = new BillReportDetailVo();
					billReportDetailVo.setCharge(tCostAllocationRecord.getCharge());
					billReportDetailVo.setChargingType(tCostAllocationSubject.getChargingType());
					billReportDetailVo.setData(tCostAllocationRecord.getData());
					billReportDetailVo.setFormula(tCostAllocationSubject.getFormula());

					TCostAllocationRecord lastMonthData = getCostAllocationRecordDao().read(
							RiawDateUtils.formatDate(RiawDateUtils.backClock(Calendar.MONTH, -2),
									"yyyyMM"),
							tCostAllocationRecord.gettPartner().getPartnerCode(),
							tCostAllocationRecord.gettCostAllocationSubject().getSubjectCode());

					billReportDetailVo.setLastMonthData(lastMonthData.getData());
					billReportDetailVo.setSubjectName(tCostAllocationSubject.getSubjectName());

					billReportDetailVos.add(billReportDetailVo);
				}

				billReportVos.add(billReportVo);
			}
			return billReportVos;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
