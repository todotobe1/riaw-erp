package com.riawworks.riaw.erp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.riawworks.riaw.erp.context.UserContext;
import com.riawworks.riaw.erp.enums.HumanConstants;
import com.riawworks.riaw.erp.model.bo.User;

public class UserContextFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		User user = (User) httpServletRequest.getSession().getAttribute(
				HumanConstants.USER_KEY_SESSION);

		if (user != null)
			UserContext.set(user);

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
