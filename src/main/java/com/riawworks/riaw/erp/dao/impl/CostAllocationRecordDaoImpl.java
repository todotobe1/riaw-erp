package com.riawworks.riaw.erp.dao.impl;

import java.util.List;

import com.riawworks.riaw.erp.dao.CostAllocationRecordDao;
import com.riawworks.riaw.erp.framework.dao.impl.DaoImpl;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.model.po.TCostAllocationRecord;

public class CostAllocationRecordDaoImpl extends DaoImpl<TCostAllocationRecord, Integer> implements
		CostAllocationRecordDao {

	@Override
	public List<TCostAllocationRecord> readByPeriod(String period) throws DaoException {
		return (List<TCostAllocationRecord>) getHibernateTemplate().find(
				"from com.riawworks.riaw.erp.model.po.TCostAllocationRecord o where o.period=?",
				new Object[] { period });
	}

	@Override
	public TCostAllocationRecord read(String period, String partnerCode, String subjectCode)
			throws DaoException {
		List<TCostAllocationRecord> tCostAllocationRecords = (List<TCostAllocationRecord>) getHibernateTemplate()
				.find("from com.riawworks.riaw.erp.model.po.TCostAllocationRecord o where o.period=? and o.tPartner.partnerCode=? and o.tCostAllocationSubject.subjectCode=?",
						new Object[] { period, partnerCode, subjectCode });
		return tCostAllocationRecords.isEmpty() ? null : tCostAllocationRecords.get(0);
	}

	@Override
	public List<String> readDistinctPeriods() throws DaoException {
		return (List<String>) getHibernateTemplate()
				.find("select distinct o.period from com.riawworks.riaw.erp.model.po.TCostAllocationRecord o",
						new Object[] {});
	}

}
