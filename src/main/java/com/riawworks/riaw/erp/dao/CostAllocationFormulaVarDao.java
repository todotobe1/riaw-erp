package com.riawworks.riaw.erp.dao;

import java.util.List;

import com.riawworks.riaw.erp.framework.dao.Dao;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.model.po.TCostAllocationFormulaVar;

public interface CostAllocationFormulaVarDao extends Dao<TCostAllocationFormulaVar, Long> {

	public TCostAllocationFormulaVar readVariable(String subjectCode, String variableName)
			throws DaoException;

	public List<TCostAllocationFormulaVar> readVariables(String subjectCode) throws DaoException;

}
