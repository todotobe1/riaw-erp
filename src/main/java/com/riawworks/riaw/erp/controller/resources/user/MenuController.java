package com.riawworks.riaw.erp.controller.resources.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.riawworks.riaw.erp.factory.resources.HumanBeanFactory;
import com.riawworks.riaw.erp.model.bo.MenuNode;
import com.riawworks.riaw.erp.model.vo.resources.MenuNodeVo;
import com.riawworks.riaw.erp.service.resources.UserService;

@Controller
@RequestMapping("/human/user/menu")
public class MenuController {

	private UserService UserService;
	private HumanBeanFactory humanBeanFactory;

	public UserService getUserService() {
		return UserService;
	}

	@Resource(name = "userService")
	public void setUserService(UserService userService) {
		UserService = userService;
	}

	public HumanBeanFactory getHumanBeanFactory() {
		return humanBeanFactory;
	}

	@Resource(name = "humanBeanFactory")
	public void setHumanBeanFactory(HumanBeanFactory humanBeanFactory) {
		this.humanBeanFactory = humanBeanFactory;
	}

	@RequestMapping("/load")
	public List<MenuNodeVo> load(HttpServletRequest httpServletRequest)
			throws Exception {
		String id = httpServletRequest.getParameter("id");

		List<MenuNode> menuNodes = getUserService().readMenuNodes(id);
		List<MenuNodeVo> menuNodeVos = new ArrayList<MenuNodeVo>();

		for (MenuNode menuNode : menuNodes) {
			menuNodeVos.add(getHumanBeanFactory().createMenuNodeVo(menuNode));
		}

		return menuNodeVos;
	}

}