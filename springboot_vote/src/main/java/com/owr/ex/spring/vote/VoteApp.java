package com.owr.ex.spring.vote;

import org.apache.catalina.filters.RemoteAddrFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan({ "com.owr.ex.spring.vote.*" })
@PropertySource("classpath:app.properties")
public class VoteApp {
	
	public static void main(String[] args) {
        SpringApplication.run(VoteApp.class, args);
    }
	
	@Value("${adminip}")
	private String adminip;
	
	@Bean
	public FilterRegistrationBean<RemoteAddrFilter> remoteAddressFilter() {

	    FilterRegistrationBean<RemoteAddrFilter> filterRegistrationBean = new FilterRegistrationBean<>();
	    RemoteAddrFilter filter = new RemoteAddrFilter();

	    filter.setAllow(adminip);
	    filter.setDenyStatus(404);

	    filterRegistrationBean.setFilter(filter);
	    filterRegistrationBean.addUrlPatterns("/admin/*");

	    return filterRegistrationBean;

	}

}


