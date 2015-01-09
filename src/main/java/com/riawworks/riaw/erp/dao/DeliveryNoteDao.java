package com.riawworks.riaw.erp.dao;

import java.util.List;

import com.riawworks.riaw.erp.framework.dao.Dao;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.model.po.TDeliveryNote;

public interface DeliveryNoteDao extends Dao<TDeliveryNote, Integer> {

	public List<TDeliveryNote> readDeliveryNotes(int page, int rows)
			throws DaoException;

	public Integer readDeliveryNotesCount() throws DaoException;

}
