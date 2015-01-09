package com.riawworks.riaw.erp.factory.resources.impl;

import org.apache.commons.beanutils.BeanUtils;

import com.riawworks.riaw.erp.factory.resources.CustomerBeanFactory;
import com.riawworks.riaw.erp.framework.exception.BeanConversionException;
import com.riawworks.riaw.erp.framework.factory.impl.BeanFactoryImpl;
import com.riawworks.riaw.erp.model.bo.Customer;
import com.riawworks.riaw.erp.model.po.TCustomer;
import com.riawworks.riaw.erp.model.vo.resources.CustomerVo;

public class CustomerBeanFactoryImpl extends BeanFactoryImpl implements
		CustomerBeanFactory {

	@Override
	public CustomerVo createCustomerVo(Customer customer)
			throws BeanConversionException {
		try {
			CustomerVo customerVo = new CustomerVo();
			BeanUtils.copyProperties(customerVo, customer);
			return customerVo;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeanConversionException(e.getMessage(), e);
		}
	}

	@Override
	public Customer createCustomer(TCustomer tCustomer)
			throws BeanConversionException {
		try {
			Customer customer = new Customer();
			BeanUtils.copyProperties(customer, tCustomer);
			return customer;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeanConversionException(e.getMessage(), e);
		}
	}

	@Override
	public TCustomer createTCustomer(Customer customer)
			throws BeanConversionException {
		try {
			TCustomer tCustomer = new TCustomer();
			BeanUtils.copyProperties(tCustomer, customer);
			return tCustomer;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeanConversionException(e.getMessage(), e);
		}
	}

}
