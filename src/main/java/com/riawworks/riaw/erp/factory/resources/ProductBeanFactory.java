package com.riawworks.riaw.erp.factory.resources;

import com.riawworks.riaw.erp.framework.exception.BeanConversionException;
import com.riawworks.riaw.erp.model.bo.MeasurementUnit;
import com.riawworks.riaw.erp.model.bo.Product;
import com.riawworks.riaw.erp.model.bo.ProductPricing;
import com.riawworks.riaw.erp.model.po.TMeasurementUnit;
import com.riawworks.riaw.erp.model.po.TProduct;
import com.riawworks.riaw.erp.model.po.TProductPricing;
import com.riawworks.riaw.erp.model.vo.resources.MeasurementUnitVo;
import com.riawworks.riaw.erp.model.vo.resources.ProductPricingVo;
import com.riawworks.riaw.erp.model.vo.resources.ProductVo;

public interface ProductBeanFactory {

	public MeasurementUnitVo createMeasurementUnitVo(
			MeasurementUnit measurementUnit) throws BeanConversionException;

	public ProductVo createProductVo(Product product)
			throws BeanConversionException;

	public ProductPricingVo createProductPricingVo(ProductPricing productPricing)
			throws BeanConversionException;

	public ProductPricing createProductPricing(TProductPricing tProductPricing)
			throws BeanConversionException;

	public MeasurementUnit createMeasurementUnit(
			TMeasurementUnit tMeasurementUnit) throws BeanConversionException;

	public Product createProduct(TProduct tProduct)
			throws BeanConversionException;

}
