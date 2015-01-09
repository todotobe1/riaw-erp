package com.riawworks.riaw.erp.service.sales;

import java.util.List;
import java.util.Map;

import com.riawworks.riaw.erp.framework.exception.ServiceException;
import com.riawworks.riaw.erp.framework.service.Service;
import com.riawworks.riaw.erp.model.bo.DeliveryNote;
import com.riawworks.riaw.erp.model.vo.sales.DeliveryNoteDetailVo;

public interface DeliveryService extends Service {

	public List<DeliveryNote> readDeliveryNotes(int page, int rows)
			throws ServiceException;

	public DeliveryNote readDeliveryNote(Integer id) throws ServiceException;

	public Integer readDeliveryNotesCount() throws ServiceException;

	public Integer createDeliveryNote(String recordNo, String customer,
			String deliveryVan, String deliveryDate, String deliveryMan,
			String containerDeliver, String containerTake,
			Map<String, Object> effectRows) throws ServiceException;

	public void deleteDeliveryNote(Integer id) throws ServiceException;

	public List<DeliveryNoteDetailVo> readDeliveryNoteDetail(Integer id)
			throws ServiceException;

	public void updateDeliveryNote(String deliveryNoteId, String recordNo,
			String customer, String deliveryVan, String deliveryDate,
			String deliveryMan, String containerDeliver, String containerTake,
			Map<String, Object> effectRows) throws ServiceException;

}
