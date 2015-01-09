package com.riawworks.riaw.erp.service.sales.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.riawworks.riaw.erp.dao.CustomerDao;
import com.riawworks.riaw.erp.dao.DeliveryNoteDao;
import com.riawworks.riaw.erp.dao.DeliveryNoteDetailDao;
import com.riawworks.riaw.erp.dao.MeasurementUnitDao;
import com.riawworks.riaw.erp.dao.ProductDao;
import com.riawworks.riaw.erp.dao.ProductPricingDao;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.framework.exception.ServiceException;
import com.riawworks.riaw.erp.framework.service.impl.ServiceImpl;
import com.riawworks.riaw.erp.framework.util.RiawStringUtils;
import com.riawworks.riaw.erp.model.bo.DeliveryNote;
import com.riawworks.riaw.erp.model.po.TCustomer;
import com.riawworks.riaw.erp.model.po.TDeliveryNote;
import com.riawworks.riaw.erp.model.po.TDeliveryNoteDetail;
import com.riawworks.riaw.erp.model.po.TMeasurementUnit;
import com.riawworks.riaw.erp.model.po.TProduct;
import com.riawworks.riaw.erp.model.vo.sales.DeliveryNoteDetailVo;
import com.riawworks.riaw.erp.service.sales.DeliveryService;

public class DeliveryServiceImpl extends ServiceImpl implements DeliveryService {

	private DeliveryNoteDao deliveryNoteDao;
	private DeliveryNoteDetailDao deliveryNoteDetailDao;
	private ProductPricingDao productPricingDao;
	private MeasurementUnitDao measurementUnitDao;
	private ProductDao productDao;
	private CustomerDao customerDao;

	@Override
	public List<DeliveryNote> readDeliveryNotes(int page, int rows)
			throws ServiceException {
		List<DeliveryNote> deliveryNotes = new ArrayList<DeliveryNote>();
		try {
			List<TDeliveryNote> tDeliveryNotes = getDeliveryNoteDao()
					.readDeliveryNotes(page, rows);
			for (TDeliveryNote tDeliveryNote : tDeliveryNotes) {
				TCustomer tCustomer = getCustomerDao().readByCode(
						tDeliveryNote.getCustomer());
				DeliveryNote deliveryNote = new DeliveryNote();
				BeanUtils.copyProperties(deliveryNote, tDeliveryNote);
				deliveryNote.setCustomer(tCustomer.getName());
				deliveryNotes.add(deliveryNote);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		return deliveryNotes;
	}

	@Override
	public Integer readDeliveryNotesCount() throws ServiceException {
		try {
			return getDeliveryNoteDao().readDeliveryNotesCount();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
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

	@Override
	public Integer createDeliveryNote(String recordNo, String customer,
			String deliveryVan, String deliveryDate, String deliveryMan,
			String containerDeliver, String containerTake,
			Map<String, Object> effectRows) throws ServiceException {
		TDeliveryNote tDeliveryNote = new TDeliveryNote();

		Integer deliveryNoteId;
		try {
			tDeliveryNote
					.setContainerDeliver(Integer.valueOf(containerDeliver));
			tDeliveryNote.setContainerTake(Integer.valueOf(containerTake));
			tDeliveryNote.setCustomer(customer);
			tDeliveryNote.setDeliveryDate(new SimpleDateFormat("yyyy-MM-dd")
					.parse(deliveryDate));
			tDeliveryNote.setDeliveryMan(deliveryMan);
			tDeliveryNote.setDeliveryVan(deliveryVan);
			tDeliveryNote.setRecordNo(recordNo);

			tDeliveryNote.setOrderNo(String.valueOf(new Date().getTime()));
			deliveryNoteId = getDeliveryNoteDao().create(tDeliveryNote);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}

		deleteDeletedDeliveryNoteDetail(deliveryNoteId,
				(List<Map<String, Object>>) effectRows.get("deleted"));
		updateUpdatedDeliveryNoteDetail(deliveryNoteId,
				(List<Map<String, Object>>) effectRows.get("updated"));
		createInsertedDeliveryNoteDetail(deliveryNoteId,
				(List<Map<String, Object>>) effectRows.get("inserted"));

		return deliveryNoteId;
	}

	private void createInsertedDeliveryNoteDetail(Integer deliveryNoteId,
			List<Map<String, Object>> list) throws ServiceException {
		try {
			for (Map<String, Object> map : list) {
				TDeliveryNoteDetail tDeliveryNoteDetail = new TDeliveryNoteDetail();
				tDeliveryNoteDetail.setAmount(Double.valueOf(RiawStringUtils
						.emptyIfNull(map.get("amount"))));
				tDeliveryNoteDetail.setComments(RiawStringUtils.emptyIfNull(map
						.get("comments")));
				tDeliveryNoteDetail.setProductionDate(new SimpleDateFormat(
						"yyyy-MM-dd").parse(RiawStringUtils.emptyIfNull(map
						.get("productionDate"))));
				tDeliveryNoteDetail.setProductId(Integer
						.valueOf(RiawStringUtils.emptyIfNull(map
								.get("productId"))));
				tDeliveryNoteDetail.setUnitId(Integer.valueOf(RiawStringUtils
						.emptyIfNull(map.get("unitId"))));
				tDeliveryNoteDetail.setQuantity(Integer.valueOf(RiawStringUtils
						.emptyIfNull(map.get("quantity"))));
				tDeliveryNoteDetail.setUnitPrice(Double.valueOf(RiawStringUtils
						.emptyIfNull(map.get("unitPrice"))));
				tDeliveryNoteDetail.setDeliveryNoteId(deliveryNoteId);
				getDeliveryNoteDetailDao().create(tDeliveryNoteDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void updateUpdatedDeliveryNoteDetail(Integer deliveryNoteId,
			List<Map<String, Object>> list) throws ServiceException {
		try {
			for (Map<String, Object> map : list) {
				TDeliveryNoteDetail tDeliveryNoteDetail = getDeliveryNoteDetailDao()
						.read(Integer.valueOf(RiawStringUtils.emptyIfNull(map
								.get("detailId"))));
				tDeliveryNoteDetail.setAmount(Double
						.parseDouble(RiawStringUtils.emptyIfNull(map
								.get("amount"))));
				tDeliveryNoteDetail.setComments(RiawStringUtils.emptyIfNull(map
						.get("comments")));
				tDeliveryNoteDetail.setProductionDate(new SimpleDateFormat(
						"yyyy-MM-dd").parse(RiawStringUtils.emptyIfNull(map
						.get("productionDate"))));
				tDeliveryNoteDetail.setQuantity(Integer.valueOf(RiawStringUtils
						.emptyIfNull(map.get("quantity"))));
				tDeliveryNoteDetail.setDeliveryNoteId(deliveryNoteId);
				tDeliveryNoteDetail.setUnitPrice(Double.valueOf(RiawStringUtils
						.emptyIfNull(map.get("unitPrice"))));

				tDeliveryNoteDetail.setProductId(Integer
						.valueOf(RiawStringUtils.emptyIfNull(map
								.get("productId"))));
				tDeliveryNoteDetail.setUnitId(Integer.valueOf(RiawStringUtils
						.emptyIfNull(map.get("unitId"))));
				getDeliveryNoteDetailDao().update(tDeliveryNoteDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void deleteDeletedDeliveryNoteDetail(Integer deliveryNoteId,
			List<Map<String, Object>> list) throws ServiceException {
		try {
			for (Map<String, Object> map : list) {
				TDeliveryNoteDetail tDeliveryNoteDetail = getDeliveryNoteDetailDao()
						.read(Integer.valueOf(RiawStringUtils.emptyIfNull(map
								.get("detailId"))));
				getDeliveryNoteDetailDao().delete(tDeliveryNoteDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public ProductPricingDao getProductPricingDao() {
		return productPricingDao;
	}

	public void setProductPricingDao(ProductPricingDao productPricingDao) {
		this.productPricingDao = productPricingDao;
	}

	@Override
	public void deleteDeliveryNote(Integer id) throws ServiceException {
		try {
			TDeliveryNote tDeliveryNote = getDeliveryNoteDao().read(id);
			tDeliveryNote.setEnabled("0");
			getDeliveryNoteDao().update(tDeliveryNote);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public DeliveryNote readDeliveryNote(Integer id) throws ServiceException {
		DeliveryNote deliveryNote = new DeliveryNote();
		try {
			TDeliveryNote tDeliveryNote = getDeliveryNoteDao().read(id);
			BeanUtils.copyProperties(deliveryNote, tDeliveryNote);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		return deliveryNote;
	}

	@Override
	public List<DeliveryNoteDetailVo> readDeliveryNoteDetail(Integer id)
			throws ServiceException {
		List<DeliveryNoteDetailVo> deliveryNoteDetailVos = new ArrayList<DeliveryNoteDetailVo>();
		try {
			List<TDeliveryNoteDetail> tDeliveryNoteDetails = getDeliveryNoteDetailDao()
					.readDeliveryNoteDetailsByDeliveryNoteId(id);
			for (TDeliveryNoteDetail tDeliveryNoteDetail : tDeliveryNoteDetails) {
				DeliveryNoteDetailVo deliveryNoteDetailVo = new DeliveryNoteDetailVo();
				BeanUtils.copyProperties(deliveryNoteDetailVo,
						tDeliveryNoteDetail);
				deliveryNoteDetailVo.setDetailId(tDeliveryNoteDetail.getId()
						.toString());
				deliveryNoteDetailVo.setProductionDate(new SimpleDateFormat(
						"yyyy-MM-dd").format(tDeliveryNoteDetail
						.getProductionDate()));
				TMeasurementUnit tMeasurementUnit = getMeasurementUnitDao()
						.read(tDeliveryNoteDetail.getUnitId());
				TProduct tProduct = getProductDao().read(
						tDeliveryNoteDetail.getProductId());

				deliveryNoteDetailVo.setProductId(tProduct.getId().toString());
				deliveryNoteDetailVo.setProductName(tProduct.getProductName());
				deliveryNoteDetailVo.setUnitId(tMeasurementUnit.getId()
						.toString());
				deliveryNoteDetailVo
						.setUnitName(tMeasurementUnit.getUnitName());
				deliveryNoteDetailVo.setUnitPrice(tDeliveryNoteDetail
						.getUnitPrice());
				deliveryNoteDetailVos.add(deliveryNoteDetailVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		return deliveryNoteDetailVos;
	}

	public MeasurementUnitDao getMeasurementUnitDao() {
		return measurementUnitDao;
	}

	public void setMeasurementUnitDao(MeasurementUnitDao measurementUnitDao) {
		this.measurementUnitDao = measurementUnitDao;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public void updateDeliveryNote(String deliveryNoteIdStr, String recordNo,
			String customer, String deliveryVan, String deliveryDate,
			String deliveryMan, String containerDeliver, String containerTake,
			Map<String, Object> effectRows) throws ServiceException {

		int deliveryNoteId = Integer.valueOf(deliveryNoteIdStr);

		try {
			TDeliveryNote tDeliveryNote = getDeliveryNoteDao().read(
					deliveryNoteId);
			tDeliveryNote
					.setContainerDeliver(Integer.valueOf(containerDeliver));
			tDeliveryNote.setContainerTake(Integer.valueOf(containerTake));
			tDeliveryNote.setCustomer(customer);
			tDeliveryNote.setDeliveryDate(new SimpleDateFormat("yyyy-MM-dd")
					.parse(deliveryDate));
			tDeliveryNote.setDeliveryMan(deliveryMan);
			tDeliveryNote.setDeliveryVan(deliveryVan);
			tDeliveryNote.setRecordNo(recordNo);

			tDeliveryNote.setOrderNo(String.valueOf(new Date().getTime()));
			getDeliveryNoteDao().update(tDeliveryNote);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}

		deleteDeletedDeliveryNoteDetail(deliveryNoteId,
				(List<Map<String, Object>>) effectRows.get("deleted"));
		updateUpdatedDeliveryNoteDetail(deliveryNoteId,
				(List<Map<String, Object>>) effectRows.get("updated"));
		createInsertedDeliveryNoteDetail(deliveryNoteId,
				(List<Map<String, Object>>) effectRows.get("inserted"));
	}

}
