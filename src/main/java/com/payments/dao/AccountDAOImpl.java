package com.payments.dao;

import com.payments.entity.account.Account;
import java.util.List;
import java.util.Properties;


public class AccountDAOImpl implements AccountDAO {
    
    private DAOHelper<Account> helper;
    
    public AccountDAOImpl(){
        helper = new DAOHelper<>(Account.class); 
    }
    
    @Override
    public Account getById(int id){
        Account account = helper.getById(id);
        return account;
    }
    
    @Override
    public List<Account> getByUserId(int userId){
        String id = String.valueOf(userId);
        Properties conditions = new Properties();
        conditions.put("user_id", id);
        List<Account> accounts = helper.getAllByConditions(conditions);
        return accounts;
    }
    
    @Override
    public List<Account> getAll(){
        List<Account> accounts = helper.getAll();
        return accounts;
    }
    
}
