package com.riawworks.riaw.erp.service.resources.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.riawworks.riaw.erp.dao.CustomizedProductPricingDao;
import com.riawworks.riaw.erp.dao.MeasurementUnitDao;
import com.riawworks.riaw.erp.dao.ProductDao;
import com.riawworks.riaw.erp.dao.ProductPricingDao;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.framework.exception.ServiceException;
import com.riawworks.riaw.erp.framework.service.impl.ServiceImpl;
import com.riawworks.riaw.erp.model.bo.MeasurementUnit;
import com.riawworks.riaw.erp.model.bo.Product;
import com.riawworks.riaw.erp.model.bo.ProductPricing;
import com.riawworks.riaw.erp.model.po.TCustomizedProductPricing;
import com.riawworks.riaw.erp.model.po.TMeasurementUnit;
import com.riawworks.riaw.erp.model.po.TProduct;
import com.riawworks.riaw.erp.model.po.TProductPricing;
import com.riawworks.riaw.erp.service.resources.ProductService;

public class ProductServiceImpl extends ServiceImpl implements ProductService {

	private ProductPricingDao productPricingDao;
	private CustomizedProductPricingDao customizedProductPricingDao;
	private ProductDao productDao;
	private MeasurementUnitDao measurementUnitDao;

	public CustomizedProductPricingDao getCustomizedProductPricingDao() {
		return customizedProductPricingDao;
	}

	public void setCustomizedProductPricingDao(
			CustomizedProductPricingDao customizedProductPricingDao) {
		this.customizedProductPricingDao = customizedProductPricingDao;
	}

	@Override
	public List<Product> readProducts(int page, int rows) throws ServiceException {
		List<Product> products = new ArrayList<Product>();

		try {
			List<TProduct> tProducts = getProductDao().readProducts(page, rows);

			if (tProducts.isEmpty()) {
				return products;
			}

			for (TProduct tProduct : tProducts) {
				Product product = new Product();
				BeanUtils.copyProperties(product, tProduct);
				products.add(product);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		return products;
	}

	@Override
	public Integer readProductsCount() throws ServiceException {
		try {
			return getProductDao().readProductsCount();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void createProduct(String productName, Map<String, Object> productPrice)
			throws ServiceException {
		try {
			if (getProductDao().readProductByProductName(productName) != null)
				throw new ServiceException("不能新增已存在的产品");

			TProduct tProduct = new TProduct();
			tProduct.setProductName(productName);
			Integer id = getProductDao().create(tProduct);

			createInsertedProductPricings(id,
					(List<Map<String, String>>) productPrice.get("inserted"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void createInsertedProductPricings(Integer productId, List<Map<String, String>> inserted)
			throws Exception {
		for (Map<String, String> temp : inserted) {
			TProductPricing tProductPricingInDB = getProductPricingDao().readProductPricing(
					productId, Integer.valueOf(temp.get("unitId")));

			if (tProductPricingInDB != null)
				throw new ServiceException("不能新增已存在的价目");

			TProductPricing tProductPricing = new TProductPricing();
			tProductPricing.setProductId(productId);
			tProductPricing.setUnitId(Integer.valueOf(temp.get("unitId")));
			tProductPricing.setUnitPrice(Double.valueOf(temp.get("unitPrice")));
			getProductPricingDao().create(tProductPricing);
		}
	}

	public void updateUpdatedProductPricings(Integer productId, List<Map<String, String>> updated)
			throws Exception {
		for (Map<String, String> temp : updated) {
			TProductPricing tProductPricing = getProductPricingDao().read(
					Integer.valueOf(temp.get("id")));
			tProductPricing.setProductId(productId);
			tProductPricing.setUnitId(Integer.valueOf(temp.get("unitId")));
			tProductPricing.setUnitPrice(Double.valueOf(temp.get("unitPrice")));
			getProductPricingDao().update(tProductPricing);
		}
	}

	public void deleteDeletedProductPricings(Integer productId, List<Map<String, String>> deleted)
			throws Exception {
		for (Map<String, String> temp : deleted) {
			TProductPricing tProductPricing = getProductPricingDao().read(
					Integer.valueOf(Integer.valueOf(temp.get("id"))));
			tProductPricing.setProductId(productId);
			tProductPricing.setUnitId(Integer.valueOf(temp.get("unitId")));
			tProductPricing.setUnitPrice(Double.valueOf(temp.get("unitPrice")));
			getProductPricingDao().delete(tProductPricing);

			List<TCustomizedProductPricing> tCustomizedProductPricings = getCustomizedProductPricingDao()
					.readCustomizedProductPricing(Integer.valueOf(temp.get("id")));

			for (TCustomizedProductPricing tCustomizedProductPricing : tCustomizedProductPricings) {
				getCustomizedProductPricingDao().delete(tCustomizedProductPricing);
			}

		}
	}

	@Override
	public void deleteProduct(String id) throws ServiceException {
		try {
			TProduct tProduct = getProductDao().read(Integer.valueOf(id));
			tProduct.setEnabled("0");
			getProductDao().update(tProduct);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public Product readProduct(Integer id) throws ServiceException {
		try {
			Product product = new Product();
			TProduct tProduct = getProductDao().read(id);
			BeanUtils.copyProperties(product, tProduct);
			return product;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public List<ProductPricing> readProductPricings(String id) throws ServiceException {
		List<ProductPricing> productPricings = new ArrayList<ProductPricing>();
		try {
			List<TProductPricing> tProductPricings = getProductPricingDao().readProductPricings(
					Integer.valueOf(id));
			for (TProductPricing tProductPricing : tProductPricings) {
				ProductPricing productPricing = new ProductPricing();
				productPricing.setPrice(tProductPricing.getUnitPrice());
				MeasurementUnit measurementUnit = new MeasurementUnit();
				productPricing.setMeasurementUnit(measurementUnit);
				measurementUnit.setId(tProductPricing.getUnitId());
				measurementUnit.setUnitName(getMeasurementUnitDao().read(
						tProductPricing.getUnitId()).getUnitName());
				productPricing.setId(tProductPricing.getId());
				productPricings.add(productPricing);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		return productPricings;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateProduct(String productId, String productName, Map<String, Object> productPrice)
			throws ServiceException {
		try {
			TProduct sameNameProduct = getProductDao().readProductByProductName(productName);
			if (sameNameProduct != null && !productId.equals(sameNameProduct.getId().toString()))
				throw new ServiceException("不能新增已存在的产品");

			TProduct tProduct = getProductDao().read(Integer.valueOf(productId));
			tProduct.setProductName(productName);
			getProductDao().update(tProduct);

			deleteDeletedProductPricings(Integer.valueOf(productId),
					(List<Map<String, String>>) productPrice.get("deleted"));
			updateUpdatedProductPricings(Integer.valueOf(productId),
					(List<Map<String, String>>) productPrice.get("updated"));
			createInsertedProductPricings(Integer.valueOf(productId),
					(List<Map<String, String>>) productPrice.get("inserted"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public MeasurementUnitDao getMeasurementUnitDao() {
		return measurementUnitDao;
	}

	public void setMeasurementUnitDao(MeasurementUnitDao measurementUnitDao) {
		this.measurementUnitDao = measurementUnitDao;
	}

	public ProductPricingDao getProductPricingDao() {
		return productPricingDao;
	}

	public void setProductPricingDao(ProductPricingDao productPricingDao) {
		this.productPricingDao = productPricingDao;
	}

	@Override
	public ProductPricing readProductPricingByProductIdAndUnitId(Integer productId, Integer unitId,
			String customerCode) throws ServiceException {
		try {
			TProductPricing tProductPricing = getProductPricingDao().readProductPricing(productId,
					unitId);

			if (tProductPricing == null)
				throw new ServiceException("系统尚未录入该产品对应计量单位标准单价，请重新选择。");

			TCustomizedProductPricing tCustomizedProductPricing = getCustomizedProductPricingDao()
					.readCustomizedProductPricing(customerCode, tProductPricing.getId());

			ProductPricing productPricing = new ProductPricing();

			if (tCustomizedProductPricing != null) {
				productPricing.setPrice(tCustomizedProductPricing.getUnitPrice());
			} else {
				productPricing.setPrice(tProductPricing.getUnitPrice());
			}

			return productPricing;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public ProductPricing readProductPricingById(Integer id) throws ServiceException {
		try {
			TProductPricing tProductPricing = getProductPricingDao().read(id);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public MeasurementUnit readMeasurementUnitById(Integer id) throws ServiceException {
		try {
			TMeasurementUnit tMeasurementUnit = getMeasurementUnitDao().read(id);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
