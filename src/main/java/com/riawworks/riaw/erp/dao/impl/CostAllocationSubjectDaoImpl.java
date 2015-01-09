package com.riawworks.riaw.erp.dao.impl;

import com.riawworks.riaw.erp.dao.CostAllocationSubjectDao;
import com.riawworks.riaw.erp.framework.dao.impl.DaoImpl;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.model.po.TCostAllocationSubject;

public class CostAllocationSubjectDaoImpl extends
		DaoImpl<TCostAllocationSubject, Integer> implements
		CostAllocationSubjectDao {

	@Override
	public TCostAllocationSubject readByCode(String code) throws DaoException {
		return (TCostAllocationSubject) getHibernateTemplate()
				.find("from com.riawworks.riaw.erp.model.po.TCostAllocationSubject o where o.subjectCode=?",
						new Object[] { code }).get(0);
	}

}
