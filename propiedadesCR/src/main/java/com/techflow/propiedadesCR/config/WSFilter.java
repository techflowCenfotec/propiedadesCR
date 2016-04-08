package com.techflow.propiedadesCR.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.techflow.propiedadesCR.services.GeneralService;

@WebFilter(filterName="wsFilter",urlPatterns="/rest/protected/*")
public class WSFilter implements Filter, ApplicationContextAware{
	
	final Logger logger = LoggerFactory.getLogger(WSFilter.class);
	private GeneralService generalService;
	private ApplicationContext applicationContext = null;
	
	private void setGeneralService(GeneralService generalService) {
		this.generalService = generalService;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		this.applicationContext = ac;
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
		ServletContext servletContext = config.getServletContext();
		
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		setGeneralService((GeneralService) webApplicationContext.getBean("generalService"));
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest)request;
	    HttpServletResponse servletResponse = (HttpServletResponse) response;
	    HttpSession currentSession = servletRequest.getSession();
	   	    if(generalService.isLocal()){
   	chain.doFilter(servletRequest, servletResponse);
	    }

	else{
	 		if (currentSession.getAttribute("idUser") != null) {
	 			chain.doFilter(servletRequest, servletResponse);
	 		} else {
	 			logger.debug("Rejected: " + servletRequest.toString());
	 			servletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	 		}
	 		
	    }
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
