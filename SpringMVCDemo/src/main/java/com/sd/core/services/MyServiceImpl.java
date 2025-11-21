package com.sd.core.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


/*
 * This @Component annotation will register this class object as bean in IOC container 
 * and can be used for dependency injection.
 * 
 * This @Primary annotation marking this MyServiceImpl as default bean for MyService 
 * dependency injection and MyService has multiple implementations
 * */

@Component
@Primary
public class MyServiceImpl implements MyService{
   
	public MyServiceImpl() {
		System.out.println("MyServiceImpl bean initialized...");
	}
	
}
