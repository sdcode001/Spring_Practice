package com.example.SpringAOPDemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;



// Demo for AfterThrowing advice.

@Aspect
@Component
public class MyLoggingAfterThrowingAspect {
    /**
     * AfterThrowing Advice:
     * This advice executes aspect on exception thrown from target method and before returning to the
     * caller of target method.
     *
     * Using @AfterThrowing annotation with pointcut expression, Aspect can be advised to target method.
     *
     * In @AfterThrowing advise, post-processing of thrown exception is possible by accessing with "throwing"
     * attribute and binding the exception to a parameter in the advice method with same name and type Throwable.
     *
     * NOTE- At this point we're only intercepting the exception(reading it). However this exception is still
     *       propagated to calling program.
     *
     * Here in this advice method- The thrown exception from MembershipDao.findMemberById(Integer) is post-processed by
     * logging and taking action.
     * */
    @AfterThrowing(
            pointcut = "execution(public * com.example.SpringAOPDemo.dao.MembershipDao.findMemberById(Integer))",
            throwing = "throwException"
    )
    public void afterThrowingAdviceDemo(JoinPoint joinPoint, Throwable throwException){
        String method = joinPoint.getSignature().toShortString();
        System.out.println("=====> Executing @AfterThrowing advice on method: "+method);
        System.out.println("Caught Exception in MyLoggingAfterThrowingAspect.afterThrowingAdviceDemo(): " + throwException.getMessage());
        System.out.println("-----Action taken against the exception-----");
    }
}
