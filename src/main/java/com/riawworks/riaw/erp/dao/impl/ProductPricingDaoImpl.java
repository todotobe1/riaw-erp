package com.riawworks.riaw.erp.dao.impl;

import java.util.List;

import com.riawworks.riaw.erp.dao.ProductPricingDao;
import com.riawworks.riaw.erp.framework.dao.impl.DaoImpl;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.model.po.TProductPricing;

public class ProductPricingDaoImpl extends DaoImpl<TProductPricing, Integer>
		implements ProductPricingDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TProductPricing> readProductPricings(Integer productId)
			throws DaoException {
		return (List<TProductPricing>) getHibernateTemplate()
				.find("from com.riawworks.riaw.erp.model.po.TProductPricing o where o.productId=?",
						new Object[] { productId });
	}

	@Override
	public Integer readProductPricingsCount() throws DaoException {
		return count("from com.riawworks.riaw.erp.model.po.TProductPricing o");
	}

	@SuppressWarnings("unchecked")
	@Override
	public TProductPricing readProductPricing(Integer productId, Integer unitId)
			throws DaoException {
		List<TProductPricing> tProductPricings = (List<TProductPricing>) getHibernateTemplate()
				.find("from com.riawworks.riaw.erp.model.po.TProductPricing o where o.productId=? and unitId=?",
						new Object[] { productId, unitId });
		return tProductPricings.isEmpty() ? null : tProductPricings.get(0);
	}

}
