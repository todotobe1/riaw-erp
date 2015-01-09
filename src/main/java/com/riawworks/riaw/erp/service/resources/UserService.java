package com.riawworks.riaw.erp.service.resources;

import java.util.List;

import com.riawworks.riaw.erp.framework.exception.ServiceException;
import com.riawworks.riaw.erp.framework.service.Service;
import com.riawworks.riaw.erp.model.bo.MenuNode;
import com.riawworks.riaw.erp.model.bo.User;

public interface UserService extends Service {

	public User readUser(String userName, String password)
			throws ServiceException;

	public List<MenuNode> readMenuNodes(String PMid) throws ServiceException;

}
