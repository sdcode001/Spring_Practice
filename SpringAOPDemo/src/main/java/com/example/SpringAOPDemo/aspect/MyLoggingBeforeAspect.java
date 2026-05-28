package com.example.SpringAOPDemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


/**
 * Here @Aspect annotation marking this class as Aspect, So that Spring can use this for
 * cross cutting concern.
 */
@Aspect
@Component
public class MyLoggingBeforeAspect {

    /**
     * Here @Before Advice defines that beforeAdviseDemo() method should execute before
     * the target object method mentioned in the pointcut expression.
     *
     * "execution(public void addAccount())" this pointcut expression defines that execute this
     * beforeAdviseDemo() before execution of the method with signature: public void addAccount()
     * in any class
     * */
    @Before("execution(public void addAccount())")
    public void beforeAdviseDemo(){
        System.out.println("=====> Executing @Before advice from beforeAdviseDemo() <=====");
    }


    //Examples of different pointcut expressions with @Before Advice

    /**
     * This pointcut expression uses fully qualified class name to tell Spring to apply this @Before
     * advice before execution of addAccount() method of MembershipDao class.
     * */
    // @Before("execution(public void com.example.SpringAOPDemo.dao.MembershipDao.addAccount())")


    /**
     * This pointcut expression uses wildcard method name to tell Spring to apply this @Before
     * advice before execution of any class method whose name starts with 'add', has no args and return type is void.
     * */
    // @Before("execution(public void add*())")

    /**
     * This pointcut expression uses wildcard method name and return type to tell Spring to apply this @Before
     * advice before execution of any class method whose name starts with 'add', has no args and return type is any.
     * */
    // @Before("execution(public * add*())")

    /**
     * This pointcut expression uses wildcard method name and return type to tell Spring to apply this @Before
     * advice before execution of any class method whose name starts with 'add', has only single Integer argument
     * and return type is any.
     * */
    // @Before("execution(public * add*(Integer))")

    /**
     * This pointcut expression uses wildcard method name to tell Spring to apply this @Before advice before
     * execution of any class method whose name starts with 'add', has 1st argument Integer followed by any
     * other arguments and return type is void.
     * */
    // @Before("execution(public void add*(Integer, ..))")

    /**
     * This pointcut expression uses wildcard method name to tell Spring to apply this @Before advice before
     * execution of any class method whose name starts with 'add', has any arguments(probably 0) and return
     * type is void.
     * */
    // @Before("execution(public void add*(..))")

    /**
     * This pointcut expression uses wildcard class, method name and return type to tell Spring to apply
     * this @Before advice before execution of any method of any class, having any arguments(probably 0)
     * and return type is any inside com.example.SpringAOPDemo.dao package.
     * */
    // @Before("execution(public * com.example.SpringAOPDemo.dao.*.*(..))")
}
