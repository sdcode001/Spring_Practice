package com.sd.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/* 
 * This class is used for maintaining IOC container config.
 * 
 * This @EnableWebMvc initialize internal Beans(Handler-Adapter, Handler-Mappings) for 
 * DispatcherServlet in IOC container
 * */

@Configuration
@ComponentScan
@EnableWebMvc
public class ContextConfig implements WebMvcConfigurer{
	
	/*
	 * This configureViewResolvers() from WebMvcConfigurer used to configure and register
	 * View-Resolver to serve jsp pages as view
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registery) {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		registery.viewResolver(resolver);
	}

}
