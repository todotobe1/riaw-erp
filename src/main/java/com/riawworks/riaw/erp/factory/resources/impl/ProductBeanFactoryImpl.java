package com.riawworks.riaw.erp.factory.resources.impl;

import org.apache.commons.beanutils.BeanUtils;

import com.riawworks.riaw.erp.factory.resources.ProductBeanFactory;
import com.riawworks.riaw.erp.framework.exception.BeanConversionException;
import com.riawworks.riaw.erp.framework.factory.impl.BeanFactoryImpl;
import com.riawworks.riaw.erp.model.bo.MeasurementUnit;
import com.riawworks.riaw.erp.model.bo.Product;
import com.riawworks.riaw.erp.model.bo.ProductPricing;
import com.riawworks.riaw.erp.model.po.TMeasurementUnit;
import com.riawworks.riaw.erp.model.po.TProduct;
import com.riawworks.riaw.erp.model.po.TProductPricing;
import com.riawworks.riaw.erp.model.vo.resources.MeasurementUnitVo;
import com.riawworks.riaw.erp.model.vo.resources.ProductPricingVo;
import com.riawworks.riaw.erp.model.vo.resources.ProductVo;

public class ProductBeanFactoryImpl extends BeanFactoryImpl implements
		ProductBeanFactory {

	@Override
	public MeasurementUnitVo createMeasurementUnitVo(
			MeasurementUnit measurementUnit) throws BeanConversionException {
		try {
			MeasurementUnitVo measurementUnitVo = new MeasurementUnitVo();
			BeanUtils.copyProperties(measurementUnitVo, measurementUnit);
			measurementUnitVo
					.setUnitId(String.valueOf(measurementUnit.getId()));
			return measurementUnitVo;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeanConversionException(e.getMessage(), e);
		}
	}

	@Override
	public ProductVo createProductVo(Product product)
			throws BeanConversionException {
		try {
			ProductVo productVo = new ProductVo();
			BeanUtils.copyProperties(productVo, product);
			productVo.setProductId(product.getId());
			return productVo;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeanConversionException(e.getMessage(), e);
		}
	}

	@Override
	public ProductPricingVo createProductPricingVo(ProductPricing productPricing)
			throws BeanConversionException {
		try {
			ProductPricingVo productPricingVo = new ProductPricingVo();
			productPricingVo.setUnitId(productPricing.getMeasurementUnit()
					.getId().toString());
			productPricingVo.setUnitName(productPricing.getMeasurementUnit()
					.getUnitName());
			productPricingVo.setUnitPrice(productPricing.getPrice().toString());
			productPricingVo.setId(productPricing.getId().toString());
			return productPricingVo;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeanConversionException(e.getMessage(), e);
		}
	}

	@Override
	public ProductPricing createProductPricing(TProductPricing tProductPricing)
			throws BeanConversionException {
		try {
			ProductPricing productPricing = new ProductPricing();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public MeasurementUnit createMeasurementUnit(
			TMeasurementUnit tMeasurementUnit) throws BeanConversionException {
		try {
			MeasurementUnit measurementUnit = new MeasurementUnit();
			BeanUtils.copyProperties(measurementUnit, tMeasurementUnit);
			return measurementUnit;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeanConversionException(e.getMessage(), e);
		}
	}

	@Override
	public Product createProduct(TProduct tProduct)
			throws BeanConversionException {
		try {
			Product product = new Product();
			BeanUtils.copyProperties(product, tProduct);
			return product;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeanConversionException(e.getMessage(), e);
		}
	}

}
