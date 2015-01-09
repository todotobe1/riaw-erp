package com.riawworks.riaw.erp.service.resources;

import java.util.List;

import com.riawworks.riaw.erp.framework.exception.ServiceException;
import com.riawworks.riaw.erp.framework.service.CodeService;
import com.riawworks.riaw.erp.model.bo.MeasurementUnit;
import com.riawworks.riaw.erp.model.bo.Product;

public interface ProductCodeService extends CodeService {

	public List<MeasurementUnit> readMeasurementUnits() throws ServiceException;

	public List<Product> readProducts() throws ServiceException;

}
