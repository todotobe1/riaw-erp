package com.riawworks.riaw.erp.service.report;

import com.riawworks.riaw.erp.framework.exception.ServiceException;
import com.riawworks.riaw.erp.framework.service.Service;
import com.riawworks.riaw.erp.model.vo.report.DeliveryNoteVo;

public interface SalesReportService extends Service {

	public DeliveryNoteVo readDeliveryNote(Integer id) throws ServiceException;

}
