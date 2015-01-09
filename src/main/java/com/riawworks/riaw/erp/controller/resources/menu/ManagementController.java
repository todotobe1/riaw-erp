package com.riawworks.riaw.erp.controller.resources.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.riawworks.riaw.erp.enums.ResourcesView;

@Controller("menuManagementController")
@RequestMapping("/resources/menu/management")
public class ManagementController {

	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView(
				ResourcesView.RESOURCES_MENU_MANAGEMENT_INDEX.getValue());
	}

}
