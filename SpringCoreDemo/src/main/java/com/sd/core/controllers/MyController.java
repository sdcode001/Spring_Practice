package com.sd.core.controllers;

import org.springframework.stereotype.Component;

import com.sd.core.repositories.MyRepository;
import com.sd.core.services.MyService;

/* 
 * Here MyService dependency need to be injected via IOC containers dependency injection
 * IOC container provides 3 types on injection
 * 1) Constructor Injection
 * 2) Field Injection via @AutoWired
 * 3) Setter Injection
 * */

/*
 * this @Component annotation will register this class object as bean in IOC container 
 * and can be used for dependency injection.
 * */

@Component
public class MyController {
	//Good practice for dependency reference to make it final
	private final MyService myService;
	private final MyRepository myRepository;

	//Constructor Injection of MyService and MyRepository
	public MyController(MyService myService, MyRepository myRepository) {
		   this.myService = myService;
		   System.out.println("MyService dependency injected: "+this.myService);
		   this.myRepository = myRepository;
		   System.out.println("MyRepositoryImpl dependency injected: "+this.myRepository);
		   System.out.println("MyController bean initialized...");
	}
   
}



