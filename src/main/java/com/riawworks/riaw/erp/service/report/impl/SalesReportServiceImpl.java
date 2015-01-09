package com.riawworks.riaw.erp.service.report.impl;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.beanutils.BeanUtils;

import com.riawworks.riaw.erp.dao.CustomerDao;
import com.riawworks.riaw.erp.dao.DeliveryNoteDao;
import com.riawworks.riaw.erp.dao.DeliveryNoteDetailDao;
import com.riawworks.riaw.erp.dao.MeasurementUnitDao;
import com.riawworks.riaw.erp.dao.ProductDao;
import com.riawworks.riaw.erp.dao.ProductPricingDao;
import com.riawworks.riaw.erp.framework.exception.ServiceException;
import com.riawworks.riaw.erp.framework.service.impl.ServiceImpl;
import com.riawworks.riaw.erp.model.po.TDeliveryNote;
import com.riawworks.riaw.erp.model.po.TDeliveryNoteDetail;
import com.riawworks.riaw.erp.model.vo.report.DeliveryNoteDetailVo;
import com.riawworks.riaw.erp.model.vo.report.DeliveryNoteVo;
import com.riawworks.riaw.erp.service.report.SalesReportService;

public class SalesReportServiceImpl extends ServiceImpl implements
		SalesReportService {

	private DeliveryNoteDao deliveryNoteDao;
	private DeliveryNoteDetailDao deliveryNoteDetailDao;
	private ProductDao productDao;
	private ProductPricingDao productPricingDao;
	private MeasurementUnitDao measurementUnitDao;
	private CustomerDao customerDao;

	@Override
	public DeliveryNoteVo readDeliveryNote(Integer id) throws ServiceException {
		DeliveryNoteVo deliveryNoteVo = new DeliveryNoteVo();
		List<DeliveryNoteDetailVo> deliveryNoteDetailVos = new ArrayList<DeliveryNoteDetailVo>();
		deliveryNoteVo.setDeliveryNoteDetailVos(deliveryNoteDetailVos);

		try {
			TDeliveryNote tDeliveryNote = getDeliveryNoteDao().read(id);

			BeanUtils.copyProperties(deliveryNoteVo, tDeliveryNote);

			deliveryNoteVo.setCustomer(getCustomerDao().readByCode(
					tDeliveryNote.getCustomer()).getName());

			List<TDeliveryNoteDetail> tDeliveryNoteDetails = getDeliveryNoteDetailDao()
					.readDeliveryNoteDetailsByDeliveryNoteId(id);

			for (TDeliveryNoteDetail tDeliveryNoteDetail : tDeliveryNoteDetails) {
				DeliveryNoteDetailVo deliveryNoteDetailVo = new DeliveryNoteDetailVo();
				BeanUtils.copyProperties(deliveryNoteDetailVo,
						tDeliveryNoteDetail);
				deliveryNoteDetailVo.setProductName(getProductDao().read(
						tDeliveryNoteDetail.getProductId()).getProductName());
				deliveryNoteDetailVo.setUnitName(getMeasurementUnitDao().read(
						tDeliveryNoteDetail.getUnitId()).getUnitName());
				deliveryNoteDetailVo.setUnitPrice(tDeliveryNoteDetail
						.getUnitPrice().toString());

				deliveryNoteDetailVos.add(deliveryNoteDetailVo);
			}

			Collections.sort(deliveryNoteDetailVos,
					new Comparator<DeliveryNoteDetailVo>() {
						@Override
						public int compare(DeliveryNoteDetailVo o1,
								DeliveryNoteDetailVo o2) {
							return Collator.getInstance(Locale.CHINA).compare(
									o1.getProductName(), o2.getProductName());
						}
					});
		} catch (Exception e) {
			throw new ServiceException(e);
		}

		return deliveryNoteVo;
	}

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

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public ProductPricingDao getProductPricingDao() {
		return productPricingDao;
	}

	public void setProductPricingDao(ProductPricingDao productPricingDao) {
		this.productPricingDao = productPricingDao;
	}

	public MeasurementUnitDao getMeasurementUnitDao() {
		return measurementUnitDao;
	}

	public void setMeasurementUnitDao(MeasurementUnitDao measurementUnitDao) {
		this.measurementUnitDao = measurementUnitDao;
	}

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

}
