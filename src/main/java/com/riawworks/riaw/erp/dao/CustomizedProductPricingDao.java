package com.riawworks.riaw.erp.dao;

import java.util.List;

import com.riawworks.riaw.erp.framework.dao.Dao;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.model.po.TCustomizedProductPricing;

public interface CustomizedProductPricingDao extends Dao<TCustomizedProductPricing, Integer> {

	public List<TCustomizedProductPricing> readCustomizedProductPricings(String customerCode)
			throws DaoException;

	public TCustomizedProductPricing readCustomizedProductPricing(String customerCode,
			Integer productPricingId) throws DaoException;

	public List<TCustomizedProductPricing> readCustomizedProductPricing(Integer productPricingId)
			throws DaoException;

}
