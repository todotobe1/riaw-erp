package com.riawworks.riaw.erp.dao;

import java.util.List;

import com.riawworks.riaw.erp.framework.dao.Dao;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.model.po.TProductPricing;

public interface ProductPricingDao extends Dao<TProductPricing, Integer> {

	public List<TProductPricing> readProductPricings(Integer productId)
			throws DaoException;

	public Integer readProductPricingsCount() throws DaoException;

	public TProductPricing readProductPricing(Integer productId, Integer unitId)
			throws DaoException;

}
