package com.example.SpringAOPDemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class MyLoggingBeforeAspect2 {

    //Demo - Reusing Pointcut Expression

    /**
     * Using @Pointcut annotation we can create a reusable pointcut expression and use in multiple
     * advice just by passing caller method in advice and we don't need to write same expression
     * again and again.
     *
     * This caller method can be any name with empty body and void return type.
     * */
    @Pointcut("execution(public * add*(Integer))")
    private void getAddPrefixMethodWithIntegerArgument(){}

    /** Applying pointcut expression by calling the caller method */
    @Before("getAddPrefixMethodWithIntegerArgument()")
    public void beforeAdviseDemo2(){
        System.out.println("=====> Executing @Before advice form beforeAdviseDemo2() <=====");
    }


    //Demo - Combining Pointcut Expressions

    /**
     * We can combine multiple pointcut expression into a single point expression using logical
     * operators- &&(AND), ||(OR), !(NOT)
     *
     * Combining Pointcut expression helps us to build complex logic for applying Advice
     *
     * Example- Below is example of applying Advice to all the methods in AccountDao class except getters
     *          and setters.
     * */

     /// Pointcut for all methods in AccountDao class
     @Pointcut("execution(public * com.example.SpringAOPDemo.dao.AccountDao.*(..))")
     private void getAccountDaoClassMethod(){}
     /// Pointcut for getters in AccountDao class
     @Pointcut("execution(public * com.example.SpringAOPDemo.dao.AccountDao.get*())")
     private void getAccountDaoGetters(){}
     /// Pointcut for getters in AccountDao class
     @Pointcut("execution(public * com.example.SpringAOPDemo.dao.AccountDao.set*(..))")
     private void getAccountDaoSetters(){}
     /// Combining Pointcut expressions
     @Before("getAccountDaoClassMethod() && !(getAccountDaoGetters() || getAccountDaoSetters())")
     public void beforeAdviseDemo3(){
         System.out.println("=====> Executing @Before advice form beforeAdviseDemo3() <=====");
     }


     // Reading target method's arguments using JoinPoint

     /**
      * To read arguments and their values of the target method of a Advice, We have to use JoinPoint
      * interface passed as parameter to the Advise method
      * */
     @Before("execution(public void com.example.SpringAOPDemo.dao.MembershipDao.setDetails(..))")
     public void beforeReadArgsDemo(JoinPoint joinPoint){
         System.out.println("=====> Executing @Before advice form beforeReadArgsDemo() <=====");
         //display the target method signature- MembershipDao.setDetails(..)
         MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
         System.out.println("Target Method: " + methodSignature);
         //display target method arguments value captured at runtime.
         Object[] methodArgs = joinPoint.getArgs();
         for (int i=0;i<methodArgs.length;i++){
             System.out.println("Argument "+(i+1)+": "+methodArgs[i]);
         }
     }
     

}
