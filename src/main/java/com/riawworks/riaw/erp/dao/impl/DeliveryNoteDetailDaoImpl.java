package com.riawworks.riaw.erp.dao.impl;

import java.util.List;

import com.riawworks.riaw.erp.dao.DeliveryNoteDetailDao;
import com.riawworks.riaw.erp.framework.dao.impl.DaoImpl;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.model.po.TDeliveryNoteDetail;

public class DeliveryNoteDetailDaoImpl extends
		DaoImpl<TDeliveryNoteDetail, Integer> implements DeliveryNoteDetailDao {

	@Override
	public List<TDeliveryNoteDetail> readDeliveryNoteDetailsByDeliveryNoteId(
			Integer id) throws DaoException {
		return (List<TDeliveryNoteDetail>) getHibernateTemplate()
				.find("from com.riawworks.riaw.erp.model.po.TDeliveryNoteDetail o where o.deliveryNoteId = ?",
						new Object[] { id });
	}

}
