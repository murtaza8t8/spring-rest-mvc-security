package com.spring.sample.web.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;

import com.spring.sample.web.filter.CORSFilter;


public class SampleWebApplicationInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.addFilter("corsFilter", new CORSFilter())
		.addMappingForUrlPatterns(null, false, "/*");		
	}

	

}
