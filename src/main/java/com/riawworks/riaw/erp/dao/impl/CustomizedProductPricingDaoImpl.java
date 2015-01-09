package com.riawworks.riaw.erp.dao.impl;

import java.util.List;

import com.riawworks.riaw.erp.dao.CustomizedProductPricingDao;
import com.riawworks.riaw.erp.framework.dao.impl.DaoImpl;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.model.po.TCustomizedProductPricing;

public class CustomizedProductPricingDaoImpl extends DaoImpl<TCustomizedProductPricing, Integer>
		implements CustomizedProductPricingDao {

	@Override
	public List<TCustomizedProductPricing> readCustomizedProductPricings(String customerCode)
			throws DaoException {
		return (List<TCustomizedProductPricing>) getHibernateTemplate()
				.find("from com.riawworks.riaw.erp.model.po.TCustomizedProductPricing o where o.customerCode = ?",
						new Object[] { customerCode });
	}

	@Override
	public TCustomizedProductPricing readCustomizedProductPricing(String customerCode,
			Integer productPricingId) throws DaoException {

		List<TCustomizedProductPricing> tCustomizedProductPricings = ((List<TCustomizedProductPricing>) getHibernateTemplate()
				.find("from com.riawworks.riaw.erp.model.po.TCustomizedProductPricing o where o.customerCode = ? and o.productPricingId = ?",
						new Object[] { customerCode, productPricingId }));

		return tCustomizedProductPricings.isEmpty() ? null : tCustomizedProductPricings.get(0);
	}

	@Override
	public List<TCustomizedProductPricing> readCustomizedProductPricing(Integer productPricingId)
			throws DaoException {
		List<TCustomizedProductPricing> tCustomizedProductPricings = ((List<TCustomizedProductPricing>) getHibernateTemplate()
				.find("from com.riawworks.riaw.erp.model.po.TCustomizedProductPricing o where o.productPricingId = ?",
						new Object[] { productPricingId }));

		return tCustomizedProductPricings;
	}
}
