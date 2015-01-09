package com.riawworks.riaw.erp.controller.resources.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.riawworks.riaw.erp.enums.ProductView;
import com.riawworks.riaw.erp.factory.resources.ProductBeanFactory;
import com.riawworks.riaw.erp.framework.exception.ServiceException;
import com.riawworks.riaw.erp.framework.json.JacksonMapper;
import com.riawworks.riaw.erp.framework.model.vo.AjaxResponseVo;
import com.riawworks.riaw.erp.framework.model.vo.PagingDataVo;
import com.riawworks.riaw.erp.model.bo.Product;
import com.riawworks.riaw.erp.model.bo.ProductPricing;
import com.riawworks.riaw.erp.model.vo.resources.ProductPricingVo;
import com.riawworks.riaw.erp.model.vo.resources.ProductVo;
import com.riawworks.riaw.erp.service.resources.ProductService;

@Controller("productManagementController")
@RequestMapping("/product/management")
public class ManagementController {

	private ProductService productService;
	private ProductBeanFactory productBeanFactory;

	public ProductService getProductService() {
		return productService;
	}

	@Resource(name = "productService")
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public ProductBeanFactory getProductBeanFactory() {
		return productBeanFactory;
	}

	@Resource(name = "productBeanFactory")
	public void setProductBeanFactory(ProductBeanFactory productBeanFactory) {
		this.productBeanFactory = productBeanFactory;
	}

	@RequestMapping("/view")
	public ModelAndView view(HttpServletRequest httpServletRequest) {
		return new ModelAndView(ProductView.MANAGEMENT);
	}

	@RequestMapping("/additionView")
	public ModelAndView additionView(HttpServletRequest httpServletRequest) {
		return new ModelAndView(ProductView.ADDITION);
	}

	@RequestMapping("/editView")
	public ModelAndView editView(HttpServletRequest httpServletRequest)
			throws Exception {
		String id = httpServletRequest.getParameter("id");
		Product product = getProductService().readProduct(Integer.valueOf(id));
		return new ModelAndView(ProductView.EDIT).addObject("product",
				getProductBeanFactory().createProductVo(product));
	}

	@RequestMapping("/list")
	public PagingDataVo<ProductVo> list(HttpServletRequest httpServletRequest)
			throws Exception {
		String page = httpServletRequest.getParameter("page");
		String rows = httpServletRequest.getParameter("rows");

		PagingDataVo<ProductVo> pagingData = new PagingDataVo<ProductVo>();

		pagingData.setTotal(getProductService().readProductsCount());

		List<Product> products = getProductService().readProducts(
				Integer.valueOf(page), Integer.valueOf(rows));

		List<ProductVo> productVos = new ArrayList<ProductVo>();
		for (Product product : products) {
			productVos.add(getProductBeanFactory().createProductVo(product));
		}

		pagingData.setRows(productVos);

		return pagingData;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/add")
	public AjaxResponseVo<Object> add(HttpServletRequest httpServletRequest) {
		AjaxResponseVo<Object> ajaxResponseVo = new AjaxResponseVo<Object>();
		try {
			String productName = httpServletRequest.getParameter("productName");
			Map<String, Object> map = JacksonMapper.getInstance().readValue(
					httpServletRequest.getParameter("effectRows"), Map.class);

			getProductService().createProduct(productName, map);
			ajaxResponseVo.setStatus(AjaxResponseVo.SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResponseVo.setStatus(AjaxResponseVo.FAIL);
			ajaxResponseVo.setMsg(e.getMessage());
		}
		return ajaxResponseVo;
	}

	@RequestMapping("/delete")
	public AjaxResponseVo<Object> delete(HttpServletRequest httpServletRequest) {
		AjaxResponseVo<Object> ajaxResponseVo = new AjaxResponseVo<Object>();
		try {
			String productId = httpServletRequest.getParameter("productId");
			getProductService().deleteProduct(productId);
			ajaxResponseVo.setStatus(AjaxResponseVo.SUCCEED);
		} catch (ServiceException e) {
			e.printStackTrace();
			ajaxResponseVo.setStatus(AjaxResponseVo.FAIL);
			ajaxResponseVo.setMsg(e.getMessage());
		}
		return ajaxResponseVo;
	}

	@RequestMapping("/productPricing")
	public List<ProductPricingVo> loadProductPricings(
			HttpServletRequest httpServletRequest) throws Exception {
		String id = httpServletRequest.getParameter("id");
		List<ProductPricing> productPricings = getProductService()
				.readProductPricings(id);
		List<ProductPricingVo> productPricingVos = new ArrayList<ProductPricingVo>();
		for (ProductPricing productPricing : productPricings) {
			productPricingVos.add(getProductBeanFactory()
					.createProductPricingVo(productPricing));
		}
		return productPricingVos;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/edit")
	public AjaxResponseVo<Object> edit(HttpServletRequest httpServletRequest) {
		AjaxResponseVo<Object> ajaxResponseVo = new AjaxResponseVo<Object>();
		try {
			String productId = httpServletRequest.getParameter("productId");
			String productName = httpServletRequest.getParameter("productName");
			Map<String, Object> map = JacksonMapper.getInstance().readValue(
					httpServletRequest.getParameter("effectRows"), Map.class);
			getProductService().updateProduct(productId, productName, map);
			ajaxResponseVo.setStatus(AjaxResponseVo.SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResponseVo.setStatus(AjaxResponseVo.FAIL);
			ajaxResponseVo.setMsg(e.getMessage());
		}
		return ajaxResponseVo;
	}

	@RequestMapping("/unitPrice")
	public AjaxResponseVo<Double> unitPrice(
			HttpServletRequest httpServletRequest) {
		String productId = httpServletRequest.getParameter("productId");
		String unitId = httpServletRequest.getParameter("unitId");
		String customerCode = httpServletRequest.getParameter("customerCode");

		try {
			ProductPricing pricing = getProductService()
					.readProductPricingByProductIdAndUnitId(
							Integer.valueOf(productId),
							Integer.valueOf(unitId), customerCode);

			return new AjaxResponseVo<Double>(AjaxResponseVo.SUCCEED, null,
					pricing.getPrice());
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResponseVo<Double>(AjaxResponseVo.FAIL,
					e.getMessage());
		}

	}

}
