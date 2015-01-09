package com.riawworks.riaw.erp.controller.resources.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.riawworks.riaw.erp.enums.ResourcesView;
import com.riawworks.riaw.erp.factory.resources.CustomerBeanFactory;
import com.riawworks.riaw.erp.framework.json.JacksonMapper;
import com.riawworks.riaw.erp.framework.model.vo.AjaxResponseVo;
import com.riawworks.riaw.erp.model.bo.CustomizedProductPricing;
import com.riawworks.riaw.erp.model.vo.resources.ProductPricingDifferentiationVo;
import com.riawworks.riaw.erp.service.resources.CustomerService;

@Controller
@RequestMapping("/resources/customer/productPricingDifferentiation")
public class ProductPricingDifferentiationController {

	private CustomerBeanFactory customerBeanFactory;
	private CustomerService customerService;

	public CustomerBeanFactory getCustomerBeanFactory() {
		return customerBeanFactory;
	}

	@Resource(name = "customerBeanFactory")
	public void setCustomerBeanFactory(CustomerBeanFactory customerBeanFactory) {
		this.customerBeanFactory = customerBeanFactory;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	@Resource(name = "customerService")
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView(
				ResourcesView.RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX
						.getValue());
	}

	@RequestMapping("/pricing")
	public List<ProductPricingDifferentiationVo> pricing(
			HttpServletRequest request) throws Exception {
		List<ProductPricingDifferentiationVo> list = new ArrayList<ProductPricingDifferentiationVo>();
		String customerCode = request.getParameter("customerCode");

		if (StringUtils.isEmpty(customerCode)) {
			return list;
		}

		List<CustomizedProductPricing> customizedProductPricings = getCustomerService()
				.readCustomizedProductPricings(customerCode);

		for (CustomizedProductPricing customizedProductPricing : customizedProductPricings) {
			ProductPricingDifferentiationVo productPricingDifferentiationVo = new ProductPricingDifferentiationVo();
			productPricingDifferentiationVo.setCustomerCode(customerCode);
			productPricingDifferentiationVo.setProductId(String
					.valueOf(customizedProductPricing.getProductPricing()
							.getProduct().getId()));
			productPricingDifferentiationVo
					.setProductName(customizedProductPricing
							.getProductPricing().getProduct().getProductName());
			productPricingDifferentiationVo.setUnitId(String
					.valueOf(customizedProductPricing.getProductPricing()
							.getMeasurementUnit().getId()));
			productPricingDifferentiationVo
					.setUnitName(customizedProductPricing.getProductPricing()
							.getMeasurementUnit().getUnitName());
			productPricingDifferentiationVo
					.setUnitPrice(customizedProductPricing.getUnitPrice());
			productPricingDifferentiationVo
					.setCustomizedProductPricingId(customizedProductPricing
							.getId());
			list.add(productPricingDifferentiationVo);
		}

		return list;
	}

	@RequestMapping("/save")
	public AjaxResponseVo<Object> save(HttpServletRequest request) {
		try {
			String customerCode = request.getParameter("customerCode");

			String rows = request.getParameter("effectRows");
			Map<String, List<Map<String, Object>>> map = JacksonMapper
					.getInstance().readValue(rows, Map.class);
			getCustomerService().createCustomizedProductPricings(customerCode,
					map);
			return new AjaxResponseVo<Object>(AjaxResponseVo.SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResponseVo<Object>(AjaxResponseVo.FAIL, e.getMessage());
		}
	}
}
