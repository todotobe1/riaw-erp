package com.riawworks.riaw.erp.dao;

import java.util.List;

import com.riawworks.riaw.erp.framework.dao.Dao;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.model.po.TDeliveryNoteDetail;

public interface DeliveryNoteDetailDao extends
		Dao<TDeliveryNoteDetail, Integer> {

	public List<TDeliveryNoteDetail> readDeliveryNoteDetailsByDeliveryNoteId(
			Integer id) throws DaoException;

}
