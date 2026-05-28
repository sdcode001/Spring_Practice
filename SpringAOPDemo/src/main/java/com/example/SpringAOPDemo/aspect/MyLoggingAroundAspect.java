package com.example.SpringAOPDemo.aspect;

import com.example.SpringAOPDemo.entity.Member;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.awt.*;


// Demo for Around advice.


@Aspect
@Component
public class MyLoggingAroundAspect {

    /**
     * Around Advice:
     * This Advice executes Aspect before and after execution of the target code/method. Its like the
     * combination of Before and After Advices but gives you more fine-grained control over the flow.
     *
     * Using @Around annotation with pointcut expression, Aspect can be advised to target method.
     *
     * Around Advice method has ProceedingJoinPoint as parameter, Which is the handle to the target method
     * and executes the target method by forwarding the control. proceedingJoinPoint.proceed()
     *
     * Around Advice method has throws declaration as it manages exception from target method. Hence
     * it can Throw/Handle/Stop Exception from target method.
     *
     * Around Advice method must return the result from target method to caller. proceedingJoinPoint.proceed()
     * returns the result from target method upon successful execution. If target method is a void method then
     * it returns null.
     *
     * Here we can also modify the result from target method and returns to caller.
     * */
    @Around("execution(public * com.example.SpringAOPDemo.dao.MembershipDao.updateMemberById(int))")
    public Object aroundAdviseDemo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        //Pre-processing
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("=====> Executing @Around advice on method: "+method);

        long beginTime = System.currentTimeMillis();

        Member result = null;
        try{
            //Execute the target method by forwarding the control and get returned result.
            result = (Member) proceedingJoinPoint.proceed();
        }
        catch(Exception ex){
            System.out.println("Caught Exception in MyLoggingAroundAspect.aroundAdviseDemo(): " + ex.getMessage());
            //Re-throwing the exception to the caller.
            throw ex;
        }

        //Post-processing
        long endTime = System.currentTimeMillis();
        long duration = endTime - beginTime;
        System.out.println("=====> Duration of "+method+" execution: "+duration+" Ms");

        return result;
    }

}
