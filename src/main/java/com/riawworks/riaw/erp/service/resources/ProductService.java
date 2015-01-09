package com.riawworks.riaw.erp.service.resources;

import java.util.List;
import java.util.Map;

import com.riawworks.riaw.erp.framework.exception.ServiceException;
import com.riawworks.riaw.erp.framework.service.Service;
import com.riawworks.riaw.erp.model.bo.MeasurementUnit;
import com.riawworks.riaw.erp.model.bo.Product;
import com.riawworks.riaw.erp.model.bo.ProductPricing;

public interface ProductService extends Service {

	public List<Product> readProducts(int page, int rows)
			throws ServiceException;

	public Integer readProductsCount() throws ServiceException;

	public void createProduct(String productName,
			Map<String, Object> productPrice) throws ServiceException;

	public void updateProduct(String productId, String productName,
			Map<String, Object> productPrice) throws ServiceException;

	public void deleteProduct(String id) throws ServiceException;

	public Product readProduct(Integer id) throws ServiceException;

	public List<ProductPricing> readProductPricings(String id)
			throws ServiceException;

	public ProductPricing readProductPricingByProductIdAndUnitId(
			Integer productId, Integer unitId, String customerCode)
			throws ServiceException;

	public ProductPricing readProductPricingById(Integer id)
			throws ServiceException;

	public MeasurementUnit readMeasurementUnitById(Integer id)
			throws ServiceException;

}
