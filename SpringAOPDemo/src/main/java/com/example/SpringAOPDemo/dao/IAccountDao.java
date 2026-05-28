package com.example.SpringAOPDemo.dao;

public interface IAccountDao {
    void addAccount();
    boolean addMoney();
    void addMoney(Integer amount);
    void addMoney(Integer amount, String currency);
}
