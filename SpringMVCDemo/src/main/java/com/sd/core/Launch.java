package com.sd.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.sd.core.services.MyService;
import com.sd.core.services.MyServiceImpl;

/*
 * In traditional Spring MVC application We need to manually configure- DispatcherServlet and 
 * its internal Beans(Handler-Adapter, Handler-Mappings, View-Resolver), IOC container(Web Aware) etc.
 * 
 * Here DispatcherServlet will initialize IOC container. We don't need to initialize it here manually, SO
 * just need to pass ApplicationConfig, ContexConfig as init-param in web.xml 
 * 
 * But in SpringBoot all of these are configured automatically.
 * 
 * Steps to configure---
 * 1) Add spring-mvc dependency to pom.xml file.
 * 2) Register DispatcherServlet with ApplicationConfig, ContexConfig as init-param in web.xml file of
 *    WEB-INF folder.
 * 3) Annotate ContextConfig class with @EnableWebMvc initialize internal Beans(Handler-Adapter, Handler-Mappings)
 */


public class Launch {


	public static void main(String[] args) {
        
	}

}
