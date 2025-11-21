package com.sd.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sd.core.controllers.MyController2;
import com.sd.core.services.MyService;
import com.sd.core.services.MyServiceImpl;

public class Launch {

/* 
 * To use spring cores IOC container, first we need to created and initialize IOC container
 * with ApplicationContext in the main() method of application startup class.
 * 
 * First IOC container will be created and it'll register all the beans within container for future uses.
 * By Default Bean scope is- Singleton
 * 
 * NOTE: For SpringBoot application we don't need to do this IOC container initialization manually like this.
 * */
	public static void main(String[] args) {
         ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfig.class);
         System.out.println("**** Created IOC Container ****");
         
         //Get a Bean reference from IOC container
         MyService service1 = context.getBean(MyServiceImpl.class);
         MyService service2 = context.getBean(MyServiceImpl.class);
         //check Beans are singleton or not.
         if(service1==service2) {
        	 System.out.println("Bean Scope: Singleton...");
         }
         
         
         //check Field injection worked or not in MyController2
         MyController2 myController2 = context.getBean(MyController2.class);
         System.out.println("Field injection dependency: "+myController2.getMyService());
         //check Setter injection worked or not in MyController2
         System.out.println("Setter injection dependency: "+myController2.getMyRepository());
	}

}
