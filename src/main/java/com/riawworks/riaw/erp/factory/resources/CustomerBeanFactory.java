package com.riawworks.riaw.erp.factory.resources;

import com.riawworks.riaw.erp.framework.exception.BeanConversionException;
import com.riawworks.riaw.erp.framework.factory.BeanFactory;
import com.riawworks.riaw.erp.model.bo.Customer;
import com.riawworks.riaw.erp.model.po.TCustomer;
import com.riawworks.riaw.erp.model.vo.resources.CustomerVo;

public interface CustomerBeanFactory extends BeanFactory {

	public CustomerVo createCustomerVo(Customer customer)
			throws BeanConversionException;

	public Customer createCustomer(TCustomer tCustomer)
			throws BeanConversionException;

	public TCustomer createTCustomer(Customer customer)
			throws BeanConversionException;

}
