package com.riawworks.riaw.erp.controller.resources.customer;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.riawworks.riaw.erp.enums.ResourcesView;
import com.riawworks.riaw.erp.factory.resources.CustomerBeanFactory;
import com.riawworks.riaw.erp.framework.model.vo.AjaxResponseVo;
import com.riawworks.riaw.erp.framework.model.vo.PagingDataVo;
import com.riawworks.riaw.erp.model.bo.Customer;
import com.riawworks.riaw.erp.model.vo.resources.CustomerVo;
import com.riawworks.riaw.erp.service.resources.CustomerService;

@Controller("customerManagementController")
@RequestMapping("/resources/customer/management")
public class ManagementController {

	private CustomerService customerService;
	private CustomerBeanFactory customerBeanFactory;

	public CustomerService getCustomerService() {
		return customerService;
	}

	@Resource(name = "customerService")
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public CustomerBeanFactory getCustomerBeanFactory() {
		return customerBeanFactory;
	}

	@Resource(name = "customerBeanFactory")
	public void setCustomerBeanFactory(CustomerBeanFactory customerBeanFactory) {
		this.customerBeanFactory = customerBeanFactory;
	}

	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView(ResourcesView.RESOURCES_CUSTOMER_MANAGEMENT_INDEX.getValue());
	}

	@RequestMapping("/list")
	public PagingDataVo<CustomerVo> list(HttpServletRequest httpServletRequest) {
		String page = httpServletRequest.getParameter("page");
		String rows = httpServletRequest.getParameter("rows");

		PagingDataVo<CustomerVo> pagingDataVo = new PagingDataVo<CustomerVo>();

		try {
			List<Customer> customers = getCustomerService().readCustomerPagingList(
					Integer.valueOf(page), Integer.valueOf(rows));

			List<CustomerVo> customerVos = new ArrayList<CustomerVo>();
			for (Customer customer : customers) {
				customerVos.add(getCustomerBeanFactory().createCustomerVo(customer));
			}

			pagingDataVo.setRows(customerVos);
			pagingDataVo.setTotal(getCustomerService().countCustomers());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pagingDataVo;
	}

	@RequestMapping("/save")
	public AjaxResponseVo<Object> save(HttpServletRequest request) {
		try {
			String name = request.getParameter("name");
			String liaison = request.getParameter("liaison");
			String telephone = request.getParameter("telephone");
			Customer customer = new Customer();
			customer.setName(name);
			customer.setLiaison(liaison);
			customer.setTelephone(telephone);
			getCustomerService().createCustomer(customer);
			return new AjaxResponseVo<Object>(AjaxResponseVo.SUCCEED);
		} catch (Exception exception) {
			exception.printStackTrace();
			return new AjaxResponseVo<Object>(AjaxResponseVo.FAIL, exception.getMessage());
		}
	}

	@RequestMapping("/read")
	public AjaxResponseVo<CustomerVo> read(HttpServletRequest request) {
		try {
			String code = request.getParameter("code");
			Customer customer = getCustomerService().readCustomer(code);
			return new AjaxResponseVo<CustomerVo>(AjaxResponseVo.SUCCEED, null,
					getCustomerBeanFactory().createCustomerVo(customer));
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResponseVo<CustomerVo>(AjaxResponseVo.FAIL, e.getMessage());
		}
	}

	@RequestMapping("/edit")
	public AjaxResponseVo<Object> edit(HttpServletRequest request) {
		try {
			String code = request.getParameter("code");
			String name = request.getParameter("name");
			String liaison = request.getParameter("liaison");
			String telephone = request.getParameter("telephone");
			Customer customer = new Customer();
			customer.setCode(code);
			customer.setName(name);
			customer.setLiaison(liaison);
			customer.setTelephone(telephone);
			getCustomerService().updateCustomer(customer);
			return new AjaxResponseVo<Object>(AjaxResponseVo.SUCCEED);
		} catch (Exception exception) {
			exception.printStackTrace();
			return new AjaxResponseVo<Object>(AjaxResponseVo.FAIL, exception.getMessage());
		}
	}

	@RequestMapping("/delete")
	public AjaxResponseVo<CustomerVo> delete(HttpServletRequest request) {
		try {
			String code = request.getParameter("code");
			getCustomerService().deleteCustomer(code);
			return new AjaxResponseVo<CustomerVo>(AjaxResponseVo.SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResponseVo<CustomerVo>(AjaxResponseVo.FAIL, e.getMessage());
		}
	}

}
