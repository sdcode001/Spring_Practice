package com.example.SpringAOPDemo.dao;

import com.example.SpringAOPDemo.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class MembershipDao implements IMembershipDao{

    @Override
    public void addAccount() {
       System.out.println("Executing addAccount() in MembershipDao class");
    }

    @Override
    public void setDetails(Integer id, String message, boolean isCore) {
        System.out.println("Executing setDetails() in MembershipDao class");
    }

    @Override
    public void cancelMembership() {
        System.out.println("Executing cancelMembership() in MembershipDao class");
    }

    @Override
    public List<Member> getAllMembers() {
        System.out.println("Executing getAllMembers() in MembershipDao class");
        List<Member> members = new ArrayList<>();
        members.add(new Member(1001, "john", "dow"));
        members.add(new Member(1001, "alex", "costa"));
        members.add(new Member(1001, "paul", "allen"));
        return members;
    }

    @Override
    public Member findMemberById(Integer id) {
        //This method is used to demo @AfterThrowing Advice.
        System.out.println("Executing findMemberById(Integer) in MembershipDao class");
        if(id < 0){
            throw new RuntimeException("Error: Member could not found with id: "+id);
        }
        return new Member(id, "John", "Dao");
    }

    @Override
    public Member updateMemberById(int id) {
        System.out.println("Executing updateMemberById(int) in MembershipDao class");
        if(id < 0){
            throw new RuntimeException("Error: Invalid Member id: "+id);
        }
        List<Member> members = getAllMembers();
        Member result = members.stream().filter(v -> v.getId()==id).findFirst().orElse(null);
        if(result == null){
            throw new RuntimeException("Error: Member could not found with id: "+id);
        }

        //Updating the member in DB
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

}
