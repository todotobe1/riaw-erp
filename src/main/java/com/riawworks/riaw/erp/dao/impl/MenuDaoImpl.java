package com.riawworks.riaw.erp.dao.impl;

import java.util.List;

import com.riawworks.riaw.erp.dao.MenuDao;
import com.riawworks.riaw.erp.framework.dao.impl.DaoImpl;
import com.riawworks.riaw.erp.framework.exception.DaoException;
import com.riawworks.riaw.erp.model.po.TMenu;

public class MenuDaoImpl extends DaoImpl<TMenu, Integer> implements MenuDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TMenu> readMenus(String PMid, String userName)
			throws DaoException {
		String hql = "  from com.riawworks.riaw.erp.model.po.TMenu a "
						+ "where exists ("
						+ "				select 1 from com.riawworks.riaw.erp.model.po.TUserMenu b, com.riawworks.riaw.erp.model.po.TUser c"
						+ "				where a.id = b.menuId and b.userId = c.id and c.userName = ?"
						+ "			) and a.PMid = ?";
		return (List<TMenu>) getHibernateTemplate().find(hql, new Object[] {userName, PMid});
	}

}
