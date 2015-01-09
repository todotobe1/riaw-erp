package com.riawworks.riaw.erp.controller.resources.product;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.riawworks.riaw.erp.factory.resources.ProductBeanFactory;
import com.riawworks.riaw.erp.model.bo.MeasurementUnit;
import com.riawworks.riaw.erp.model.bo.Product;
import com.riawworks.riaw.erp.model.vo.resources.MeasurementUnitVo;
import com.riawworks.riaw.erp.model.vo.resources.ProductVo;
import com.riawworks.riaw.erp.service.resources.ProductCodeService;

@Controller
@RequestMapping("/product/code")
public class ProductCodeController {

	private ProductCodeService productCodeService;
	private ProductBeanFactory productBeanFactory;

	public ProductCodeService getProductCodeService() {
		return productCodeService;
	}

	@Resource(name = "productCodeService")
	public void setProductCodeService(ProductCodeService productCodeService) {
		this.productCodeService = productCodeService;
	}

	public ProductBeanFactory getProductBeanFactory() {
		return productBeanFactory;
	}

	@Resource(name = "productBeanFactory")
	public void setProductBeanFactory(ProductBeanFactory productBeanFactory) {
		this.productBeanFactory = productBeanFactory;
	}

	@RequestMapping("/measurementUnit")
	public List<MeasurementUnitVo> codeMeasurementUnit() throws Exception {
		List<MeasurementUnit> measurementUnits = getProductCodeService()
				.readMeasurementUnits();
		List<MeasurementUnitVo> measurementUnitVos = new ArrayList<MeasurementUnitVo>();
		for (MeasurementUnit measurementUnit : measurementUnits) {
			measurementUnitVos.add(getProductBeanFactory()
					.createMeasurementUnitVo(measurementUnit));
		}
		return measurementUnitVos;
	}

	@RequestMapping("/product")
	public List<ProductVo> codeProduct() throws Exception {
		List<ProductVo> productVos = new ArrayList<ProductVo>();
		List<Product> products = getProductCodeService().readProducts();
		for (Product product : products) {
			productVos.add(getProductBeanFactory().createProductVo(product));
		}
		return productVos;
	}

}
