package com.riawworks.riaw.erp.service.sales;

import java.util.List;

import com.riawworks.riaw.erp.framework.exception.ServiceException;
import com.riawworks.riaw.erp.framework.service.Service;
import com.riawworks.riaw.erp.model.bo.DeliveryNote;
import com.riawworks.riaw.erp.model.bo.DeliveryNoteDetail;

public interface SalesService extends Service {

	public DeliveryNote readDeliveryNote(Integer id) throws ServiceException;

	public List<DeliveryNoteDetail> readDeliveryNoteDetailsByDeliveryNoteId(
			Integer id) throws ServiceException;

}
