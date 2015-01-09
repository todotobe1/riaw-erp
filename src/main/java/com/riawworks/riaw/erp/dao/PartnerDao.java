package com.riawworks.riaw.erp.dao;

import com.riawworks.riaw.erp.framework.dao.Dao;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.model.po.TPartner;

public interface PartnerDao extends Dao<TPartner, Integer> {

	public TPartner readByCode(String code) throws DaoException;

}
