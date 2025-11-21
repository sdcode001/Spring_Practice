package com.sd.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.sd.core.thirdpartylibrary.HttpApiRateLimiter;

/* 
 * This class is used for maintaining IOC container config.
 * 
 * This @Configuration signifies source of bean definitions and configuration instructions for the Spring IoC container.
 * Alternatively this configuration can be done in traditional XML-based configuration.
 * 
 * This @ComponentScan facilitates the automatic detection and registration of Spring beans
 * for the classes annotated with @Component or its sub-types, which are in the same package 
 * and all its sub-packages.
 * 
 * For multiple packages scaning we can use @ComponentScan with mentioning multiple packages in a array
 * @ComponentScan(basePackages = {"com.example.package1", "com.example.package2"})
 * */

@Configuration
@ComponentScan
public class ContextConfig {
	
	/*
	 * The @Bean annotation in Spring is a method-level annotation used to register beans, unlike stereotype 
	 * annotations (@Component or its sub-types) which are class-level and detected via component scanning. 
	 * It is mainly used when working with third-party libraries whose classes cannot be modified and therefore 
	 * lack stereotype annotations. In such cases, @Bean allows us to instantiate and register those classes as 
	 * Spring beans, making them available in the application context. While less commonly used compared to stereotype 
	 * annotations, it is useful when we need to integrate external dependencies into Spring’s IoC container.
	 * 
	 * The method should return the class object for which bean is created and method should annotated with @Bean
	 * annotation and give the bean name(Bean name is same as its class name but first letter in lower-case)
	 * */
	@Bean(name="httpApiRateLimiter")
	public HttpApiRateLimiter createHttpApiRateLimiter() {
		return new HttpApiRateLimiter();
	}

}
