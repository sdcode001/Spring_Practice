package com.sd.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sd.core.repositories.MyRepository;
import com.sd.core.services.MyService;


/* 
 * Here we'll be showing Field and Getter dependency injection using @Autowired.
 * 
 * 
 * NOTE: Always prefer constructor injection instead of Field and Getter injection as
 *       these two approach brings the issue in - 1)Code Readbility, 2)Unit Testing etc
 * */

@Component
public class MyController2 {

  /*
   * This is Field injection. For this IOC container uses JAVA reflection mechanism
   * */
  @Autowired
  private final MyService myService = null;
  
  /*
   * This is Setter injection using @Autowired. Its like constructor injection
   * */
  private MyRepository myRepository = null;
  @Autowired
  public void setMyRepository(MyRepository myRepository) {
	  this.myRepository = myRepository;
  }
  
  
  public MyService getMyService() {
	  return this.myService;
  }
  
  public MyRepository getMyRepository() {
	  return this.myRepository;
  }
  
}
