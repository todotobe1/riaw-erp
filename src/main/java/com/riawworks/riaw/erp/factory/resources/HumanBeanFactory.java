package com.riawworks.riaw.erp.factory.resources;

import com.riawworks.riaw.erp.framework.exception.BeanConversionException;
import com.riawworks.riaw.erp.framework.factory.BeanFactory;
import com.riawworks.riaw.erp.model.bo.MenuNode;
import com.riawworks.riaw.erp.model.bo.User;
import com.riawworks.riaw.erp.model.po.TMenu;
import com.riawworks.riaw.erp.model.po.TUser;
import com.riawworks.riaw.erp.model.vo.resources.MenuNodeVo;

public interface HumanBeanFactory extends BeanFactory {

	public User createUser(TUser tUser) throws BeanConversionException;

	public MenuNodeVo createMenuNodeVo(MenuNode menuNode)
			throws BeanConversionException;

	public MenuNode createMenuNode(TMenu tMenu) throws BeanConversionException;

}
