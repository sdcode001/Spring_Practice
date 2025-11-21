package com.sd.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sd.core.repositories.MyRepository;
import com.sd.core.services.MyService;


/* 
 * Implicit dependency injection will happen by IOC container only if there is a single parameterized constructor
 * otherwise we have to explicitly tell the IOC container to invoke a constructor for dependency injection by 
 * annotating it with @Autowired
 * 
 * NOTE: Always use a single parameterized constructor for multiple dependency injection.
 * */
@Component
public class MyController1 {
   //NOTE: Don't follow this non final dependency ref. This is just for this demo
   private MyService myService;
   private MyRepository myRepository;
   
   //Telling IOC container to invoke this constructor
   @Autowired
   public MyController1(MyService myService, MyRepository myRepository) {
	   this.myService = myService;
	   this.myRepository = myRepository;
   }
   
   public MyController1(MyRepository myRepository) {
	   this.myRepository = myRepository;
   }
   
   //For constructor overloading default constructor will be invoked by IOC container
   public MyController1() {
	    
   }
   
}
