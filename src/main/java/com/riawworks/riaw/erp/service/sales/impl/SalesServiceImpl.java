package com.riawworks.riaw.erp.service.sales.impl;

import java.util.List;

import com.riawworks.riaw.erp.dao.DeliveryNoteDao;
import com.riawworks.riaw.erp.dao.DeliveryNoteDetailDao;
import com.riawworks.riaw.erp.dao.ProductPricingDao;
import com.riawworks.riaw.erp.framework.exception.ServiceException;
import com.riawworks.riaw.erp.framework.service.impl.ServiceImpl;
import com.riawworks.riaw.erp.model.bo.DeliveryNote;
import com.riawworks.riaw.erp.model.bo.DeliveryNoteDetail;
import com.riawworks.riaw.erp.model.po.TDeliveryNote;
import com.riawworks.riaw.erp.model.po.TDeliveryNoteDetail;
import com.riawworks.riaw.erp.service.sales.SalesService;

public class SalesServiceImpl extends ServiceImpl implements SalesService {

	private DeliveryNoteDao deliveryNoteDao;
	private DeliveryNoteDetailDao deliveryNoteDetailDao;
	private ProductPricingDao productPricingDao;

	public DeliveryNoteDao getDeliveryNoteDao() {
		return deliveryNoteDao;
	}

	public void setDeliveryNoteDao(DeliveryNoteDao deliveryNoteDao) {
		this.deliveryNoteDao = deliveryNoteDao;
	}

	public DeliveryNoteDetailDao getDeliveryNoteDetailDao() {
		return deliveryNoteDetailDao;
	}

	public void setDeliveryNoteDetailDao(
			DeliveryNoteDetailDao deliveryNoteDetailDao) {
		this.deliveryNoteDetailDao = deliveryNoteDetailDao;
	}

	public ProductPricingDao getProductPricingDao() {
		return productPricingDao;
	}

	public void setProductPricingDao(ProductPricingDao productPricingDao) {
		this.productPricingDao = productPricingDao;
	}

	@Override
	public DeliveryNote readDeliveryNote(Integer id) throws ServiceException {
		try {
			TDeliveryNote tDeliveryNote = getDeliveryNoteDao().read(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public List<DeliveryNoteDetail> readDeliveryNoteDetailsByDeliveryNoteId(
			Integer id) throws ServiceException {
		try {
			List<TDeliveryNoteDetail> tDeliveryNoteDetails = getDeliveryNoteDetailDao()
					.readDeliveryNoteDetailsByDeliveryNoteId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		return null;
	}

}
