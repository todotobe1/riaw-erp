package com.riawworks.riaw.erp.controller.resources.customer;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.riawworks.riaw.erp.factory.resources.CustomerBeanFactory;
import com.riawworks.riaw.erp.model.bo.Customer;
import com.riawworks.riaw.erp.model.vo.resources.CustomerVo;
import com.riawworks.riaw.erp.service.resources.CustomerService;

@Controller("customerCodeController")
@RequestMapping("/resources/customer/code")
public class CodeController {

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

	@RequestMapping("/customers")
	public List<CustomerVo> readCustomers() throws Exception {
		List<Customer> bolist = getCustomerService().readCustomers();
		List<CustomerVo> volist = new ArrayList<CustomerVo>();
		for (Customer customer : bolist) {
			volist.add(getCustomerBeanFactory().createCustomerVo(customer));
		}
		return volist;
	}

	@RequestMapping("/allCustomers")
	public List<CustomerVo> readAllCustomers() throws Exception {
		List<Customer> bolist = getCustomerService().readAllCustomers();
		List<CustomerVo> volist = new ArrayList<CustomerVo>();
		for (Customer customer : bolist) {
			volist.add(getCustomerBeanFactory().createCustomerVo(customer));
		}
		return volist;
	}

}
