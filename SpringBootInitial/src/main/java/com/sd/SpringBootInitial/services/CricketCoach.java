package com.sd.SpringBootInitial.services;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/*
* Lazy Initialization:
* In Spring Boot the @Lazy annotation enables lazy initialization of beans, meaning a bean instance
* is created only when it is first requested or during dependency injection, rather than at application
* startup. By default, Spring initializes all singleton beans eagerly when the application context loads.
*
* You can enable lazy initialization for all beans in your application by setting the
* spring.main.lazy-initialization=true in your application.properties
*
* If bean is web related(like- @Controller, @RestController) then when request comes first time then only
* bean will get initialized.
*
* Advantages:
*  1) May help with faster startup time if you have large number of components.
*  2) Only create bean objects as needed.
* Disadvantages:
*  1) If you have web related components like @RestController, not created until requested which slow down
*     response time.
*  2) May not discover configuration issues until too late.
* */


/*
* The Spring Bean Life Cycle in Spring Boot describes the sequence of events a bean undergoes from its
* creation to its destruction within the Spring IoC container.
*
* SpringBoot provides annotation to mark method as lifecycle hook/method:
* @PostConstruct:
*    This annotation marks a method to be executed after the bean has been constructed and its
*    dependencies have been injected.
* @PreDestroy:
*    This annotation marks a method to be executed before the bean is destroyed by the Spring container.
 * */

@Component
@Lazy
public class CricketCoach implements Coach {

    public CricketCoach(){
        System.out.println("CricketCoach bean Initialized...");
    }

    @PostConstruct
    public void PostConstructHook(){
        System.out.println("CricketCoach: PostConstructHook() is called...");
    }

    @PreDestroy
    public void PreDestroyHook(){
        System.out.println("CricketCoach: PreDestroyHook() is called...");
    }

}
