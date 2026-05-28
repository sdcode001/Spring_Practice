package com.example.SpringAOPDemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * Demo for Aspect Ordering
 *
 * To control the order of Aspects being applied on same place/code-section we have @Order annotation, because
 * by default ordering of Aspects in not defined and Spring applies random order.
 *
 * Here we need to execute these 3 aspects in this order before MembershipDao.cancelMembership()
 * MembershipCheckAspect.beforeCheckMembership() -> MembershipValidationAspect.beforeValidationMembership() -> MembershipLoggerAspect.beforeLoggerMembership()
 *
 * So we've annotated Aspects classes with @Order(order_number), It guaranties the order of execution of the Aspects.
 *
 * @Order() is class level annotation, Hence all the Advices in that class follows the that order.
 *
 * Lower order number have higher precedence.
 * Range for order number: Integer.MIN_VALUE - Integer.MAX_VALUE, So negative num ber is allowed.
 * Order numbers don't have to be consecutive for correct ordering, It'll always be lower to higher.
 * */

@Aspect
@Component
@Order(1)
public class MembershipCheckAspect {

    //This will execute before MembershipDao.cancelMembership() in specified order
    @Before("execution(public void com.example.SpringAOPDemo.dao.MembershipDao.cancelMembership())")
    public void beforeCheckMembership(){
        System.out.println("=====> Executing @Before advice from MembershipCheckAspect <=====");
    }
}
