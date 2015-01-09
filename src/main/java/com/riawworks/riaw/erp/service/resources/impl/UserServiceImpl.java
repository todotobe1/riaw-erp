package com.riawworks.riaw.erp.service.resources.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.riawworks.riaw.erp.context.UserContext;
import com.riawworks.riaw.erp.dao.MenuDao;
import com.riawworks.riaw.erp.dao.UserDao;
import com.riawworks.riaw.erp.factory.resources.HumanBeanFactory;
import com.riawworks.riaw.erp.framework.exception.ServiceException;
import com.riawworks.riaw.erp.framework.service.impl.ServiceImpl;
import com.riawworks.riaw.erp.model.bo.MenuNode;
import com.riawworks.riaw.erp.model.bo.User;
import com.riawworks.riaw.erp.model.po.TMenu;
import com.riawworks.riaw.erp.model.po.TUser;
import com.riawworks.riaw.erp.service.resources.UserService;

public class UserServiceImpl extends ServiceImpl implements UserService {

	private UserDao userDao;
	private MenuDao menuDao;
	private HumanBeanFactory humanBeanFactory;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public MenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public HumanBeanFactory getHumanBeanFactory() {
		return humanBeanFactory;
	}

	public void setHumanBeanFactory(HumanBeanFactory humanBeanFactory) {
		this.humanBeanFactory = humanBeanFactory;
	}

	@Override
	public User readUser(String userName, String password)
			throws ServiceException {
		try {
			User user = null;
			List<TUser> tUsers = getUserDao().readUser(userName, password);
			if (!tUsers.isEmpty()) {
				user = humanBeanFactory.createUser(tUsers.get(0));
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public List<MenuNode> readMenuNodes(String PMid) throws ServiceException {
		try {
			PMid = StringUtils.hasText(PMid) ? PMid : "ROOT";
			List<MenuNode> menuNodes = new ArrayList<MenuNode>();
			List<TMenu> tMenus = getMenuDao().readMenus(PMid,
					UserContext.get().getUserName());
			for (TMenu tMenu : tMenus) {
				menuNodes.add(getHumanBeanFactory().createMenuNode(tMenu));
			}
			return menuNodes;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}

	}

}
