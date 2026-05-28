package com.example.SpringAOPDemo.dao;

import org.springframework.stereotype.Repository;


@Repository
public class AccountDao implements IAccountDao{
    private String name;
    private int amount;

    public String getName() {
        System.out.println("Executing getName() in AccountDao class");
        return name;
    }

    public void setName(String name) {
        System.out.println("Executing setName() in AccountDao class");
        this.name = name;
    }

    public int getAmount() {
        System.out.println("Executing getAmount() in AccountDao class");
        return amount;
    }

    public void setAmount(int amount) {
        System.out.println("Executing setAmount() in AccountDao class");
        this.amount = amount;
    }

    @Override
    public void addAccount() {
        System.out.println("Executing addAccount() in AccountDao class");
    }

    @Override
    public boolean addMoney() {
        System.out.println("Executing addMoney() in AccountDao class");
        return true;
    }

    @Override
    public void addMoney(Integer amount) {
        System.out.println("Executing addMoney(Integer) in AccountDao class");
    }

    @Override
    public void addMoney(Integer amount, String currency) {
        System.out.println("Executing addMoney(Integer, String) in AccountDao class");
    }

}
