package com.riawworks.riaw.erp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.riawworks.riaw.erp.enums.HumanConstants;
import com.riawworks.riaw.erp.framework.enums.FrameworkConstants;

public class UserAuthenticationFilter implements Filter {

	public static final String EXCEPTION = "exception";

	private String[] exceptions;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;

		String cleanUrl = StringUtils.delete(request.getRequestURI(),
				request.getContextPath());

		boolean pass = false;

		for (String tmp : exceptions) {
			if (cleanUrl.matches(tmp)) {
				pass = true;
				break;
			}
		}

		if (!pass) {
			if (null == request.getSession().getAttribute(
					HumanConstants.USER_KEY_SESSION)) {
				request.getRequestDispatcher(FrameworkConstants.APP_ROOT)
						.forward(servletRequest, servletResponse);
				return;
			}
		}

		chain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		exceptions = StringUtils.tokenizeToStringArray(
				config.getInitParameter(EXCEPTION), ",; \t\n");
	}
}
