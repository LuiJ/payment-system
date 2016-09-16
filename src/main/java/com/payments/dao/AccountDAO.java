package com.payments.dao;

import com.payments.entity.account.Account;
import java.util.List;
import java.util.Properties;


public class AccountDAO extends AbstractDAO<Account> {
    
    public AccountDAO(){
        super(Account.class); 
    }
    
    public List<Account> getByUserId(int userId){
        String id = String.valueOf(userId);
        Properties conditions = new Properties();
        conditions.put("user_id", id);
        List<Account> accounts = helper.getAllByConditions(conditions);
        return accounts;
    }
    
}
