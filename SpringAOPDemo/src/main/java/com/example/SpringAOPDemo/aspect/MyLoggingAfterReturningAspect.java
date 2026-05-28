package com.example.SpringAOPDemo.aspect;

import com.example.SpringAOPDemo.entity.Member;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.util.List;



// Demo for AfterReturning advice.

@Aspect
@Component
public class MyLoggingAfterReturningAspect {

    /**
     * AfterReturning Advice:
     * This advice executes aspect on successful return from target method and before returning to the
     * caller of target method.
     *
     * Using @AfterReturning annotation with pointcut expression, Aspect can be advised to target method.
     *
     * In @AfterReturning advise, post-processing of returned data is possible by accessing with "returning"
     * attribute and binding its value to a parameter in the advice method with same name any type.
     *
     * Here in this advice method- The returned data from MembershipDao.getAllMembers() is post-processed by
     * updating all members firstName to uppercase.
     * */
    @AfterReturning(
            pointcut = "execution(public * com.example.SpringAOPDemo.dao.MembershipDao.getAllMembers())",
            returning = "result"
    )
    public void afterReturningAdviceDemo(JoinPoint joinPoint, List<Member> result){
        String method = joinPoint.getSignature().toShortString();
        System.out.println("=====> Executing @AfterReturning advice on method: "+method);
        System.out.println("Returned Result: "+result);
        System.out.println("-----Modifying returned result-----");
        //converting all members firstName to uppercase.
        for (Member member: result){
            member.setFirstName(member.getFirstName().toUpperCase());
        }
    }

}
