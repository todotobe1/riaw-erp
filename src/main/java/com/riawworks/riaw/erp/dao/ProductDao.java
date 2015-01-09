package com.riawworks.riaw.erp.dao;

import java.util.List;

import com.riawworks.riaw.erp.framework.dao.Dao;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.model.po.TProduct;

public interface ProductDao extends Dao<TProduct, Integer> {

	public List<TProduct> readProducts(int page, int rows) throws DaoException;

	public List<TProduct> readProducts() throws DaoException;

	public Integer readProductsCount() throws DaoException;

	public TProduct readProductByProductName(String productName)
			throws DaoException;

}
