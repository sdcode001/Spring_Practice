package com.sd.core.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sd.core.services.MyPaymentServiceImpl;
import com.sd.core.services.MyService;

/*
 * Here we'll see how to handle multiple implementation of dependency classes
 * We have 2 implementation of MyService- MyServiceImpl & MyPaymentServiceImpl, Hence if we 
 * try to inject MyService dependency then IOC container will get confused which class implementation
 * bean to inject and throw an error.
 * 
 * To solve this issue we can tell IOC container which bean to inject by using @Qualifier annotation
 * or marking a default dependency class with @Primary annotation
 * */

@Component
public class MyController3 {
   private final MyService paymentService;
   
   /*@Qualifier accepts name of the target bean.
    * Bean name is same as its class name but first letter in lower-case
    */
   public MyController3(@Qualifier("myPaymentServiceImpl")MyService myService) {
	   this.paymentService = myService;
	   
	   if(this.paymentService instanceof MyPaymentServiceImpl) {
		   System.out.println("MyPaymentServiceImpl injected: "+this.paymentService);
	   }
	   
   }
}
