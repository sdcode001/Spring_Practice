package com.example.SpringAOPDemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


// Demo for After advice.

@Aspect
@Component
public class MyLoggingAfterAspect {

    /**
     * After Advice:
     * This advice executes aspect after exiting from target method(success or error) and before returning to the
     * caller of target method. Basically its like finally block.
     *
     * Note- Here we can't access returned result or thrown exception from target method.
     *
     * Using @After annotation with pointcut expression, Aspect can be advised to target method.
     * */
    @After("execution(public * com.example.SpringAOPDemo.dao.MembershipDao.findMemberById(Integer))")
    public void afterAdviceDemo(JoinPoint joinPoint){
        String method = joinPoint.getSignature().toShortString();
        System.out.println("=====> Executing @After advice on method: "+method);
    }
}
