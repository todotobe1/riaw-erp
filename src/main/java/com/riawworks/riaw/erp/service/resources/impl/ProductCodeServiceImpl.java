package com.riawworks.riaw.erp.service.resources.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.riawworks.riaw.erp.dao.MeasurementUnitDao;
import com.riawworks.riaw.erp.dao.ProductDao;
import com.riawworks.riaw.erp.framework.exception.ServiceException;
import com.riawworks.riaw.erp.framework.service.impl.CodeServiceImpl;
import com.riawworks.riaw.erp.model.bo.MeasurementUnit;
import com.riawworks.riaw.erp.model.bo.Product;
import com.riawworks.riaw.erp.model.po.TMeasurementUnit;
import com.riawworks.riaw.erp.model.po.TProduct;
import com.riawworks.riaw.erp.service.resources.ProductCodeService;

public class ProductCodeServiceImpl extends CodeServiceImpl implements
		ProductCodeService {

	private MeasurementUnitDao measurementUnitDao;
	private ProductDao productDao;

	@Override
	public List<MeasurementUnit> readMeasurementUnits() throws ServiceException {
		List<MeasurementUnit> measurementUnits = new ArrayList<MeasurementUnit>();
		try {
			List<TMeasurementUnit> tMeasurementUnits = getMeasurementUnitDao()
					.readAll();
			for (TMeasurementUnit tMeasurementUnit : tMeasurementUnits) {
				MeasurementUnit measurementUnit = new MeasurementUnit();
				BeanUtils.copyProperties(measurementUnit, tMeasurementUnit);
				measurementUnits.add(measurementUnit);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return measurementUnits;
	}

	@Override
	public List<Product> readProducts() throws ServiceException {
		List<Product> products = new ArrayList<Product>();
		try {
			List<TProduct> tProducts = getProductDao().readProducts();
			for (TProduct tProduct : tProducts) {
				Product product = new Product();
				BeanUtils.copyProperties(product, tProduct);
				products.add(product);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return products;
	}

	public MeasurementUnitDao getMeasurementUnitDao() {
		return measurementUnitDao;
	}

	public void setMeasurementUnitDao(MeasurementUnitDao measurementUnitDao) {
		this.measurementUnitDao = measurementUnitDao;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

}
