package com.riawworks.riaw.erp.dao;

import com.riawworks.riaw.erp.framework.dao.Dao;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.model.po.TCustomer;

public interface CustomerDao extends Dao<TCustomer, Integer> {

	public TCustomer readByCode(String code) throws DaoException;

	public TCustomer readByName(String name) throws DaoException;

	public String readLastCustomerCode() throws DaoException;

}
