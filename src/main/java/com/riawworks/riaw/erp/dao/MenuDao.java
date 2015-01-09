package com.riawworks.riaw.erp.dao;

import java.util.List;

import com.riawworks.riaw.erp.framework.dao.Dao;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.model.po.TMenu;

public interface MenuDao extends Dao<TMenu, Integer> {

	public List<TMenu> readMenus(String PMid, String userName)
			throws DaoException;

}
