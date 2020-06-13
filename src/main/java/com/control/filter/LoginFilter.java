package com.control.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	private static final Logger logger = Logger.getLogger(LoginFilter.class.getName());

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			HttpSession session = req.getSession(false);
			if (session.getAttribute("email")==null) {
				logger.warning("Sesssion invalid - redirect");
				res.sendRedirect("/DemoGame/");
			} else {
				logger.info("Filter pass");
				logger.info(session.getId());
				chain.doFilter(request, response);
			}
		} catch (Exception e) {
			logger.severe("Exception " + e);
			((HttpServletResponse) response).sendRedirect("/DemoGame/");

		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
