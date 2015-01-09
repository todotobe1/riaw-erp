package com.riawworks.riaw.erp.dao.impl;

import java.util.List;

import com.riawworks.riaw.erp.dao.CustomerDao;
import com.riawworks.riaw.erp.framework.dao.impl.DaoImpl;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.model.po.TCustomer;

public class CustomerDaoImpl extends DaoImpl<TCustomer, Integer> implements CustomerDao {

	@Override
	public TCustomer readByCode(String code) throws DaoException {
		List<TCustomer> list = (List<TCustomer>) getHibernateTemplate().find(
				"from com.riawworks.riaw.erp.model.po.TCustomer o where o.code = ?",
				new Object[] { code });
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public String readLastCustomerCode() throws DaoException {
		if (this.count() == 0)
			return null;

		TCustomer tCustomer = (TCustomer) getHibernateTemplate()
				.find("from com.riawworks.riaw.erp.model.po.TCustomer o where o.id = (select max(p.id) from com.riawworks.riaw.erp.model.po.TCustomer p)",
						new Object[] {}).get(0);

		return tCustomer.getCode();
	}

	@Override
	public TCustomer readByName(String name) throws DaoException {
		List<TCustomer> list = (List<TCustomer>) getHibernateTemplate().find(
				"from com.riawworks.riaw.erp.model.po.TCustomer o where o.name = ? and o.enabled='1'",
				new Object[] { name });
		return list.isEmpty() ? null : list.get(0);
	}

}
