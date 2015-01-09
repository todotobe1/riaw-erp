package com.riawworks.riaw.erp.factory.resources.impl;

import org.apache.commons.beanutils.BeanUtils;

import com.riawworks.riaw.erp.factory.resources.HumanBeanFactory;
import com.riawworks.riaw.erp.framework.exception.BeanConversionException;
import com.riawworks.riaw.erp.framework.factory.impl.BeanFactoryImpl;
import com.riawworks.riaw.erp.model.bo.MenuNode;
import com.riawworks.riaw.erp.model.bo.User;
import com.riawworks.riaw.erp.model.po.TMenu;
import com.riawworks.riaw.erp.model.po.TUser;
import com.riawworks.riaw.erp.model.vo.resources.MenuAttributesVo;
import com.riawworks.riaw.erp.model.vo.resources.MenuNodeVo;

public class HumanBeanFactoryImpl extends BeanFactoryImpl implements
		HumanBeanFactory {

	@Override
	public User createUser(TUser tUser) throws BeanConversionException {
		try {
			User user = new User();
			BeanUtils.copyProperties(user, tUser);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeanConversionException(e.getMessage(), e);
		}
	}

	@Override
	public MenuNodeVo createMenuNodeVo(MenuNode menuNode)
			throws BeanConversionException {
		try {
			MenuNodeVo menuNodeVo = new MenuNodeVo();
			MenuAttributesVo menuAttributesVo = new MenuAttributesVo();
			menuNodeVo.setAttributes(menuAttributesVo);

			menuAttributesVo.setUrl(menuNode.getUrl());
			BeanUtils.copyProperties(menuNodeVo, menuNode);
			menuNodeVo.setId(menuNode.getMid());
			if ("1".equals(menuNode.getLeaf()))
				menuNodeVo.setState("open");
			else
				menuNodeVo.setState("closed");

			return menuNodeVo;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeanConversionException(e.getMessage(), e);
		}
	}

	@Override
	public MenuNode createMenuNode(TMenu tMenu) throws BeanConversionException {
		try {
			MenuNode menuNode = new MenuNode();
			BeanUtils.copyProperties(menuNode, tMenu);
			return menuNode;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeanConversionException(e.getMessage(), e);
		}
	}

}
