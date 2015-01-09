package com.riawworks.riaw.erp.controller.resources.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.riawworks.riaw.erp.context.UserContext;
import com.riawworks.riaw.erp.enums.HumanConstants;
import com.riawworks.riaw.erp.enums.HumanView;
import com.riawworks.riaw.erp.framework.enums.FrameworkConstants;
import com.riawworks.riaw.erp.framework.enums.FrameworkView;
import com.riawworks.riaw.erp.model.bo.User;
import com.riawworks.riaw.erp.service.resources.UserService;

@Controller
@RequestMapping("/human/user/log")
public class LogController {

	private UserService UserService;

	public UserService getUserService() {
		return UserService;
	}

	@Resource(name = "userService")
	public void setUserService(UserService userService) {
		UserService = userService;
	}

	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest httpServletRequest) {
		if (httpServletRequest.getSession().getAttribute(
				HumanConstants.USER_KEY_SESSION) != null) {
			return new ModelAndView(FrameworkView.MAIN);
		}

		return new ModelAndView(HumanView.USER_LOGIN);
	}

	@RequestMapping("/in")
	public ModelAndView in(HttpServletRequest httpServletRequest)
			throws Exception {

		String userName = httpServletRequest.getParameter("userName");
		String password = httpServletRequest.getParameter("password");

		User user = getUserService().readUser(userName, password);

		if (user == null) {
			return new ModelAndView(HumanView.USER_LOGIN).addObject(
					FrameworkConstants.PAGE_MESSAGE, "error:用户名或密码错误");
		}

		httpServletRequest.getSession().setAttribute(
				HumanConstants.USER_KEY_SESSION, user);

		UserContext.set(user);

		return new ModelAndView(FrameworkView.MAIN);
	}

	@RequestMapping("/out")
	public ModelAndView out(HttpServletRequest httpServletRequest) {
		UserContext.set(null);
		httpServletRequest.getSession().invalidate();
		return new ModelAndView(HumanView.USER_LOGIN);
	}

}
