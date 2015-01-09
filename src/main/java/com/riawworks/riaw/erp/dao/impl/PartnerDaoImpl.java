package com.riawworks.riaw.erp.dao.impl;

import com.riawworks.riaw.erp.dao.PartnerDao;
import com.riawworks.riaw.erp.framework.dao.impl.DaoImpl;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.model.po.TPartner;

public class PartnerDaoImpl extends DaoImpl<TPartner, Integer> implements
		PartnerDao {

	@Override
	public TPartner readByCode(String code) throws DaoException {
		return (TPartner) getHibernateTemplate()
				.find("from com.riawworks.riaw.erp.model.po.TPartner o where o.partnerCode=?",
						new Object[] { code }).get(0);
	}

}
