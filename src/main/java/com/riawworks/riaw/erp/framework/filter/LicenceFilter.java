package com.riawworks.riaw.erp.framework.filter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LicenceFilter implements Filter {

	private Date expiredDate;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		if (new Date().after(expiredDate))
			arg1.getWriter().print("system expired!");
		else
			arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		try {
			expiredDate = new SimpleDateFormat("yyyy-MM-dd")
					.parse("2015-06-30");
		} catch (ParseException e) {
			throw new ServletException(e);
		}
	}

}
