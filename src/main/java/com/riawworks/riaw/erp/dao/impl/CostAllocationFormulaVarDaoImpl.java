package com.riawworks.riaw.erp.dao.impl;

import java.util.List;

import com.riawworks.riaw.erp.dao.CostAllocationFormulaVarDao;
import com.riawworks.riaw.erp.framework.dao.impl.DaoImpl;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.model.po.TCostAllocationFormulaVar;

public class CostAllocationFormulaVarDaoImpl extends DaoImpl<TCostAllocationFormulaVar, Long>
		implements CostAllocationFormulaVarDao {

	@Override
	public TCostAllocationFormulaVar readVariable(String subjectCode, String variableName)
			throws DaoException {
		return (TCostAllocationFormulaVar) getHibernateTemplate()
				.find("from com.riawworks.riaw.erp.model.po.TCostAllocationFormulaVar o where o.tCostAllocationSubject.subjectCode=? and o.variableName=?",
						new Object[] { subjectCode, variableName }).get(0);
	}

	@Override
	public List<TCostAllocationFormulaVar> readVariables(String subjectCode) throws DaoException {
		return (List<TCostAllocationFormulaVar>) getHibernateTemplate()
				.find("from com.riawworks.riaw.erp.model.po.TCostAllocationFormulaVar o where o.tCostAllocationSubject.subjectCode=? and o.variableName=?",
						new Object[] { subjectCode });
	}

}
