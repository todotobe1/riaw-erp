package com.riawworks.riaw.erp.dao.impl;

import java.util.List;

import com.riawworks.riaw.erp.dao.UserDao;
import com.riawworks.riaw.erp.framework.dao.impl.DaoImpl;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.model.po.TUser;

public class UserDaoImpl extends DaoImpl<TUser, Integer> implements UserDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TUser> readUser(String userName, String password)
			throws DaoException {
		return (List<TUser>) getHibernateTemplate()
				.find("from com.riawworks.riaw.erp.model.po.TUser o where o.userName=? and o.password=? and o.enabled=1",
						new Object[] { userName, password });
	}

}
