package com.riawworks.riaw.erp.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.riawworks.riaw.erp.framework.enums.FrameworkConstants;

public class ContextPathSetterFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		String path = httpServletRequest.getContextPath();
		httpServletRequest.setAttribute(FrameworkConstants.REQUEST_PATH, path);

		String basePath = httpServletRequest.getScheme() + "://"
				+ httpServletRequest.getServerName() + ":"
				+ httpServletRequest.getServerPort() + path;
		httpServletRequest.setAttribute(FrameworkConstants.REQUEST_BASEPATH,
				basePath);

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
