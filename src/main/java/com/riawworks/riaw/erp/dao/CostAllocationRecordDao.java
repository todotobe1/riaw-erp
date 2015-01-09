package com.riawworks.riaw.erp.dao;

import java.util.List;

import com.riawworks.riaw.erp.framework.dao.Dao;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.model.po.TCostAllocationRecord;

public interface CostAllocationRecordDao extends Dao<TCostAllocationRecord, Integer> {

	public List<TCostAllocationRecord> readByPeriod(String period) throws DaoException;

	public TCostAllocationRecord read(String period, String partnerCode, String subjectCode)
			throws DaoException;

	public List<String> readDistinctPeriods() throws DaoException;

}
