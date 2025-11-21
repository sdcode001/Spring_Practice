package com.sd.SpringBootInitial.services;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*
* Bean Scope:
*  Bean scopes in Spring Boot define the lifecycle and visibility of Spring-managed beans within the
*  application context. They determine how instances of a particular bean are created, managed, and
*  shared. By default all bean scope is singleton.
*
*  There are several bean scopes in sping boot- (singleton, prototype, request, session, application)
*
*  The @Scope annotation is used to explicitly define the scope of a bean:
*  Example: @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) - it defines bean as prototype
*           PROTOTYPE Bean - new object instance for each injection and its also Lazy initialization
* */

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FootballCoach implements Coach {

    public FootballCoach(){
        System.out.println("FootballCoach bean Initialized...");
    }

}
