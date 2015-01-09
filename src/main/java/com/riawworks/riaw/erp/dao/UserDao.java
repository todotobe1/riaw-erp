package com.riawworks.riaw.erp.dao;

import java.util.List;

import com.riawworks.riaw.erp.framework.dao.Dao;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.model.po.TUser;

public interface UserDao extends Dao<TUser, Integer> {

	public List<TUser> readUser(String userName, String password)
			throws DaoException;

}
