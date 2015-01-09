package com.riawworks.riaw.erp.dao.impl;

import java.util.List;

import com.riawworks.riaw.erp.dao.DeliveryNoteDao;
import com.riawworks.riaw.erp.framework.dao.impl.DaoImpl;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.model.po.TDeliveryNote;

public class DeliveryNoteDaoImpl extends DaoImpl<TDeliveryNote, Integer>
		implements DeliveryNoteDao {

	@Override
	public List<TDeliveryNote> readDeliveryNotes(int page, int rows)
			throws DaoException {
		return paging(
				"from com.riawworks.riaw.erp.model.po.TDeliveryNote o where o.enabled = 1",
				page, rows);
	}

	@Override
	public Integer readDeliveryNotesCount() throws DaoException {
		return count("from com.riawworks.riaw.erp.model.po.TDeliveryNote o where o.enabled = 1");
	}

}
