package com.riawworks.riaw.erp.service.resources;

import java.util.List;
import java.util.Map;

import com.riawworks.riaw.erp.framework.exception.ServiceException;
import com.riawworks.riaw.erp.framework.service.Service;
import com.riawworks.riaw.erp.model.bo.Customer;
import com.riawworks.riaw.erp.model.bo.CustomizedProductPricing;

public interface CustomerService extends Service {

	public List<Customer> readCustomerPagingList(Integer page, Integer rows)
			throws ServiceException;

	public List<Customer> readCustomers() throws ServiceException;

	public List<Customer> readAllCustomers() throws ServiceException;

	public Integer countCustomers() throws ServiceException;

	public void createCustomer(Customer customer) throws ServiceException;

	public Customer readCustomer(String code) throws ServiceException;

	public void updateCustomer(Customer customer) throws ServiceException;

	public void deleteCustomer(String code) throws ServiceException;

	public List<CustomizedProductPricing> readCustomizedProductPricings(String customerCode)
			throws ServiceException;

	public void createCustomizedProductPricings(String customerCode,
			Map<String, List<Map<String, Object>>> map) throws ServiceException;

	public String readLastCustomerCode() throws ServiceException;

}
