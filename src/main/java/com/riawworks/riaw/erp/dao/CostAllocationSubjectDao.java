package com.riawworks.riaw.erp.dao;

import com.riawworks.riaw.erp.framework.dao.Dao;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.model.po.TCostAllocationSubject;

public interface CostAllocationSubjectDao extends Dao<TCostAllocationSubject, Integer> {

	public TCostAllocationSubject readByCode(String code) throws DaoException;

}
