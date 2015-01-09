package com.riawworks.riaw.erp.service.resources.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.riawworks.riaw.erp.dao.CustomerDao;
import com.riawworks.riaw.erp.dao.CustomizedProductPricingDao;
import com.riawworks.riaw.erp.dao.MeasurementUnitDao;
import com.riawworks.riaw.erp.dao.ProductDao;
import com.riawworks.riaw.erp.dao.ProductPricingDao;
import com.riawworks.riaw.erp.factory.resources.CustomerBeanFactory;
import com.riawworks.riaw.erp.factory.resources.ProductBeanFactory;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.framework.exception.ServiceException;
import com.riawworks.riaw.erp.framework.factory.CodeGeneratorFactory;
import com.riawworks.riaw.erp.framework.service.impl.ServiceImpl;
import com.riawworks.riaw.erp.model.bo.Customer;
import com.riawworks.riaw.erp.model.bo.CustomizedProductPricing;
import com.riawworks.riaw.erp.model.bo.ProductPricing;
import com.riawworks.riaw.erp.model.po.TCustomer;
import com.riawworks.riaw.erp.model.po.TCustomizedProductPricing;
import com.riawworks.riaw.erp.model.po.TMeasurementUnit;
import com.riawworks.riaw.erp.model.po.TProduct;
import com.riawworks.riaw.erp.model.po.TProductPricing;
import com.riawworks.riaw.erp.service.resources.CustomerService;

public class CustomerServiceImpl extends ServiceImpl implements CustomerService {

	private CustomerDao customerDao;
	private CustomizedProductPricingDao customizedProductPricingDao;
	private CustomerBeanFactory customerBeanFactory;
	private ProductPricingDao productPricingDao;
	private ProductDao productDao;
	private MeasurementUnitDao measurementUnitDao;
	private ProductBeanFactory productBeanFactory;
	private CodeGeneratorFactory codeGeneratorFactory;

	public CodeGeneratorFactory getCodeGeneratorFactory() {
		return codeGeneratorFactory;
	}

	public void setCodeGeneratorFactory(CodeGeneratorFactory codeGeneratorFactory) {
		this.codeGeneratorFactory = codeGeneratorFactory;
	}

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public CustomerBeanFactory getCustomerBeanFactory() {
		return customerBeanFactory;
	}

	public void setCustomerBeanFactory(CustomerBeanFactory customerBeanFactory) {
		this.customerBeanFactory = customerBeanFactory;
	}

	public CustomizedProductPricingDao getCustomizedProductPricingDao() {
		return customizedProductPricingDao;
	}

	public ProductPricingDao getProductPricingDao() {
		return productPricingDao;
	}

	public void setProductPricingDao(ProductPricingDao productPricingDao) {
		this.productPricingDao = productPricingDao;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public MeasurementUnitDao getMeasurementUnitDao() {
		return measurementUnitDao;
	}

	public void setMeasurementUnitDao(MeasurementUnitDao measurementUnitDao) {
		this.measurementUnitDao = measurementUnitDao;
	}

	public void setCustomizedProductPricingDao(
			CustomizedProductPricingDao customizedProductPricingDao) {
		this.customizedProductPricingDao = customizedProductPricingDao;
	}

	public ProductBeanFactory getProductBeanFactory() {
		return productBeanFactory;
	}

	public void setProductBeanFactory(ProductBeanFactory productBeanFactory) {
		this.productBeanFactory = productBeanFactory;
	}

	@Override
	public List<Customer> readCustomerPagingList(Integer page, Integer rows)
			throws ServiceException {
		try {
			List<TCustomer> tCustomers = getCustomerDao().paging(page, rows);
			List<Customer> customers = new ArrayList<Customer>();
			for (TCustomer tCustomer : tCustomers) {
				if ("1".equals(tCustomer.getEnabled()))
					customers.add(getCustomerBeanFactory().createCustomer(tCustomer));
			}
			return customers;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public Integer countCustomers() throws ServiceException {
		try {
			return getCustomerDao().count();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public void createCustomer(Customer customer) throws ServiceException {
		try {
			TCustomer tCustomer = getCustomerDao().readByName(customer.getName());

			if (tCustomer != null)
				throw new ServiceException("用户【名称：" + tCustomer.getName() + "】已经存在，不能重复新增。");

			customer.setCode(getCodeGeneratorFactory().create("customer").generate());

			getCustomerDao().create(getCustomerBeanFactory().createTCustomer(customer));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public Customer readCustomer(String code) throws ServiceException {
		try {
			return getCustomerBeanFactory().createCustomer(getCustomerDao().readByCode(code));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public void updateCustomer(Customer customer) throws ServiceException {
		try {
			TCustomer tCustomer = getCustomerDao().readByCode(customer.getCode());
			tCustomer.setLiaison(customer.getLiaison());
			tCustomer.setName(customer.getName());
			tCustomer.setTelephone(customer.getTelephone());
			getCustomerDao().update(tCustomer);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}

	}

	@Override
	public void deleteCustomer(String code) throws ServiceException {
		try {
			TCustomer tCustomer = getCustomerDao().readByCode(code);
			tCustomer.setEnabled("0");
			getCustomerDao().update(tCustomer);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public List<Customer> readCustomers() throws ServiceException {
		try {
			List<TCustomer> list = getCustomerDao().readAll();
			List<Customer> clist = new ArrayList<Customer>();
			for (TCustomer tCustomer : list) {
				if ("1".equals(tCustomer.getEnabled()))
					clist.add(getCustomerBeanFactory().createCustomer(tCustomer));
			}
			return clist;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public List<Customer> readAllCustomers() throws ServiceException {
		try {
			List<TCustomer> list = getCustomerDao().readAll();
			List<Customer> clist = new ArrayList<Customer>();
			for (TCustomer tCustomer : list) {
				clist.add(getCustomerBeanFactory().createCustomer(tCustomer));
			}
			return clist;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public List<CustomizedProductPricing> readCustomizedProductPricings(String customerCode)
			throws ServiceException {
		try {
			List<CustomizedProductPricing> list = new ArrayList<CustomizedProductPricing>();
			List<TCustomizedProductPricing> tCustomizedProductPricings = getCustomizedProductPricingDao()
					.readCustomizedProductPricings(customerCode);

			for (TCustomizedProductPricing tCustomizedProductPricing : tCustomizedProductPricings) {
				CustomizedProductPricing customizedProductPricing = new CustomizedProductPricing();

				Integer productPricingId = tCustomizedProductPricing.getProductPricingId();

				TProductPricing tProductPricing = getProductPricingDao().read(productPricingId);

				TMeasurementUnit tMeasurementUnit = getMeasurementUnitDao().read(
						tProductPricing.getUnitId());

				customizedProductPricing.setUnitPrice(tCustomizedProductPricing.getUnitPrice());

				ProductPricing productPricing = new ProductPricing();

				TProduct tProduct = getProductDao().read(tProductPricing.getProductId());

				productPricing.setMeasurementUnit(getProductBeanFactory().createMeasurementUnit(
						tMeasurementUnit));
				productPricing.setProduct(getProductBeanFactory().createProduct(tProduct));
				customizedProductPricing.setProductPricing(productPricing);

				customizedProductPricing.setId(tCustomizedProductPricing.getId());
				list.add(customizedProductPricing);
			}

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public void createCustomizedProductPricings(String customerCode,
			Map<String, List<Map<String, Object>>> map) throws ServiceException {
		try {
			deleteDeletedCustomizedProductPricings(customerCode, map.get("deleted"));
			updateUpdatedCustomizedProductPricings(customerCode, map.get("updated"));
			createInsertedCustomizedProductPricings(customerCode, map.get("inserted"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void createInsertedCustomizedProductPricings(String customerCode,
			List<Map<String, Object>> list) throws Exception {
		for (Map<String, Object> map : list) {
			TCustomizedProductPricing tCustomizedProductPricing = new TCustomizedProductPricing();
			tCustomizedProductPricing.setCustomerCode(customerCode);
			tCustomizedProductPricing.setCustomerId(getCustomerDao().readByCode(customerCode)
					.getId());

			TProductPricing tProductPricing = getProductPricingDao().readProductPricing(
					Integer.parseInt(map.get("productId").toString()),
					Integer.parseInt(map.get("unitId").toString()));

			if (tProductPricing == null) {
				throw new ServiceException("请先录入标准定价");
			}

			tCustomizedProductPricing.setProductPricingId(tProductPricing.getId());
			tCustomizedProductPricing.setUnitPrice(Double.parseDouble(map.get("unitPrice")
					.toString()));
			getCustomizedProductPricingDao().create(tCustomizedProductPricing);
		}
	}

	private void updateUpdatedCustomizedProductPricings(String customerCode,
			List<Map<String, Object>> list) throws Exception {
		for (Map<String, Object> map : list) {
			TCustomizedProductPricing tCustomizedProductPricing = getCustomizedProductPricingDao()
					.read((Integer) map.get("customizedProductPricingId"));
			tCustomizedProductPricing.setCustomerCode(customerCode);
			tCustomizedProductPricing.setCustomerId(getCustomerDao().readByCode(customerCode)
					.getId());
			tCustomizedProductPricing.setProductPricingId(getProductPricingDao()
					.readProductPricing(Integer.parseInt(map.get("productId").toString()),
							Integer.parseInt(map.get("unitId").toString())).getId());
			tCustomizedProductPricing.setUnitPrice(Double.parseDouble(map.get("unitPrice")
					.toString()));
			getCustomizedProductPricingDao().update(tCustomizedProductPricing);
		}
	}

	private void deleteDeletedCustomizedProductPricings(String customerCode,
			List<Map<String, Object>> list) throws Exception {
		for (Map<String, Object> map : list) {
			TCustomizedProductPricing tCustomizedProductPricing = getCustomizedProductPricingDao()
					.read((Integer) map.get("customizedProductPricingId"));
			getCustomizedProductPricingDao().delete(tCustomizedProductPricing);
		}
	}

	@Override
	public String readLastCustomerCode() throws ServiceException {
		try {
			return getCustomerDao().readLastCustomerCode();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
