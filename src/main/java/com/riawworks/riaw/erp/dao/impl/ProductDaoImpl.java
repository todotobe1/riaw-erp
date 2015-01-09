package com.riawworks.riaw.erp.dao.impl;

import java.util.List;

import com.riawworks.riaw.erp.dao.ProductDao;
import com.riawworks.riaw.erp.framework.dao.impl.DaoImpl;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.model.po.TProduct;

public class ProductDaoImpl extends DaoImpl<TProduct, Integer> implements
		ProductDao {

	@Override
	public List<TProduct> readProducts(int page, int rows) throws DaoException {
		return paging(
				"from com.riawworks.riaw.erp.model.po.TProduct o where o.enabled=1",
				page, rows);
	}

	@Override
	public Integer readProductsCount() throws DaoException {
		return count("from com.riawworks.riaw.erp.model.po.TProduct o where o.enabled=1");
	}

	@SuppressWarnings("unchecked")
	@Override
	public TProduct readProductByProductName(String productName)
			throws DaoException {
		List<TProduct> tProducts = (List<TProduct>) getHibernateTemplate()
				.find("from com.riawworks.riaw.erp.model.po.TProduct o where o.enabled=1 and o.productName=?",
						new Object[] { productName });
		return tProducts.isEmpty() ? null : tProducts.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TProduct> readProducts() throws DaoException {
		return (List<TProduct>) getHibernateTemplate()
				.find("from com.riawworks.riaw.erp.model.po.TProduct o where o.enabled=1");
	}

}
