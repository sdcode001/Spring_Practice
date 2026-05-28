package com.example.SpringAOPDemo;

import com.example.SpringAOPDemo.dao.AccountDao;
import com.example.SpringAOPDemo.dao.MembershipDao;
import com.example.SpringAOPDemo.entity.Member;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringAopDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAopDemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(AccountDao accountDao, MembershipDao membershipDao){
        return runner -> {

            //demoTheBeforeAdvice(accountDao, membershipDao);
            //demoOrderingAscpets(membershipDao);
            //demoTheAfterReturningAdvise(membershipDao);
            //demoTheAfterThrowingAdvise(membershipDao);
            //demoTheAfterAdvise(membershipDao);
            demoTheAroundAdvise(membershipDao);

        };
    }

    private void demoTheBeforeAdvice(AccountDao accountDao, MembershipDao membershipDao){
        //call the business method
        accountDao.addAccount();
        membershipDao.addAccount();
        accountDao.addMoney();
        accountDao.addMoney(1000);
        accountDao.addMoney(1000, "INR");
        accountDao.setAmount(1000);
        accountDao.setName("HDFC Account");
        accountDao.getAmount();
        accountDao.getName();
        membershipDao.setDetails(1001, "Hi there! Ping me", true);
    }

    private void demoOrderingAscpets(MembershipDao membershipDao){
        /**
         * Here we have 3 aspects to execute in order before cancelMembership()
         * MembershipCheckAspect -> MembershipValidationAspect -> MembershipLoggerAspect -> cancelMembership()
         * */
        membershipDao.cancelMembership();
    }

    private void demoTheAfterReturningAdvise(MembershipDao membershipDao){
        //Demo for AfterReturning Advice
        List<Member> members = membershipDao.getAllMembers();
        System.out.println("Result at caller after AfterReturning Advise: " + members);
    }

    private void demoTheAfterThrowingAdvise(MembershipDao membershipDao){
        //Demo for AfterThrowing Advice
        try{
            Member result = membershipDao.findMemberById(-100);
        }
        catch(Exception ex){
            System.out.println("Caught Exception on caller demoTheAfterReturningAdvise(): " + ex.getMessage());
        }
    }

    private void demoTheAfterAdvise(MembershipDao membershipDao){
        //Demo for After Advice
        try{
            Member result = membershipDao.findMemberById(100);
        }
        catch(Exception ex){
            System.out.println("Caught Exception on caller demoTheAfterReturningAdvise(): " + ex.getMessage());
        }
        System.out.println("Executing caller method demoTheAfterAdvise()");
    }

    private void demoTheAroundAdvise(MembershipDao membershipDao){
        //Demo for Around Advice
        try{
            Member result = membershipDao.updateMemberById(1001);
            System.out.println("Result at caller after Around Advise: " + result);
        }
        catch(Exception ex){
            System.out.println("Caught Exception on caller demoTheAroundAdvise(): " + ex.getMessage());
        }
    }

}
