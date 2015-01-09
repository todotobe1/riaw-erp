package com.riawworks.riaw.erp.generator.code;

import com.riawworks.riaw.erp.framework.generator.code.CodeGenerator;
import com.riawworks.riaw.erp.framework.util.apache.ApacheStringUtils;
import com.riawworks.riaw.erp.framework.util.spring.SpringStringUtils;
import com.riawworks.riaw.erp.service.resources.CustomerService;

public class CustomerCodeGenerator extends CodeGenerator {

	public static final String KEY = "CUSTOMER";

	public static final String INITIAL_VALUE = "C0001";

	private CustomerService customerService;

	@Override
	public Boolean support(String key) {
		if (SpringStringUtils.isEmpty(key))
			return Boolean.FALSE;

		if (KEY.equalsIgnoreCase(key))
			return Boolean.TRUE;

		return Boolean.FALSE;
	}

	@Override
	public String generate() throws Exception {
		String code = getCustomerService().readLastCustomerCode();

		if (SpringStringUtils.isEmpty(code)) {
			return INITIAL_VALUE;
		}

		String prefix = code.substring(0, 1);
		String suffix = code.substring(1);

		suffix = ApacheStringUtils.leftPad(Integer.parseInt(suffix) + 1 + "", 4, "0");

		return prefix + suffix;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

}
