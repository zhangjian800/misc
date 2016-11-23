package com.dolphin.webapp.common.web.struts;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig; 
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang.StringUtils;

public class CharacterEncodingFilter implements javax.servlet.Filter {
	private String encoding = "UTF-8";

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		if (config != null
				&& !StringUtils.isBlank(config.getInitParameter("encoding"))) {
			encoding = config.getInitParameter("encoding");
		}
	}

	public void destroy() {
	}

}
