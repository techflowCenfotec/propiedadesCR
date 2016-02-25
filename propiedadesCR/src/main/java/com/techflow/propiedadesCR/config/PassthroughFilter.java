package com.techflow.propiedadesCR.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebFilter(filterName="passthroughFilter",urlPatterns="/*")
public class PassthroughFilter implements Filter{

	final Logger logger = LoggerFactory.getLogger(PassthroughFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest)request;
	    HttpServletResponse servletResponse = (HttpServletResponse) response;
	    chain.doFilter(servletRequest, servletResponse);
	}
	
	@Override
	public void destroy() {
	}
}
