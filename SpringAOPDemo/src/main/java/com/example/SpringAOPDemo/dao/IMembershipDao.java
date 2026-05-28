package com.example.SpringAOPDemo.dao;

import com.example.SpringAOPDemo.entity.Member;
import java.util.List;


public interface IMembershipDao {
    void addAccount();
    void cancelMembership();
    void setDetails(Integer id, String message, boolean isCore);
    List<Member> getAllMembers();
    Member findMemberById(Integer id);
    Member updateMemberById(int id);
}
