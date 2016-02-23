package com.techflow.propiedadesCR.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = {"com.techflow.propiedadesCR"})
@EnableAutoConfiguration

@EnableJpaRepositories("com.techflow.propiedadesCR.repositories")
@EnableTransactionManagement
@EntityScan(basePackages = {"com.techflow.propiedadesCR.ejb"})
public class PropiedadesCRApp {
	public static void main(String[] args) {
        SpringApplication.run(PropiedadesCRApp.class, args);
    }
	
	@Bean
	public FilterRegistrationBean filterRegsitrationBean() {
		List<String> urls = new ArrayList<String>();
		urls.add("/*");
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		PassthroughFilter passFilter = new PassthroughFilter();
		registrationBean.setFilter(passFilter);
		registrationBean.setUrlPatterns(urls);
		return registrationBean;
	}
	
	@Bean	
	public FilterRegistrationBean filterRegistrationBean2() {
		
		List<String> urls = new ArrayList<String>();
		urls.add("/rest/protected/*");
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		WSFilter wsFilter = new WSFilter();
		registrationBean.setFilter(wsFilter);
		registrationBean.setUrlPatterns(urls);
		return registrationBean;
	}
}
