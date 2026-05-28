package com.example.SpringAOPDemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/** Demo for Aspect Ordering */

@Aspect
@Component
@Order(3)
public class MembershipLoggerAspect {

    //This will execute before MembershipDao.cancelMembership() in specified order
    @Before("execution(public void com.example.SpringAOPDemo.dao.MembershipDao.cancelMembership())")
    public void beforeLoggerMembership(){
        System.out.println("=====> Executing @Before advice from MembershipLoggerAspect <=====");
    }

}
