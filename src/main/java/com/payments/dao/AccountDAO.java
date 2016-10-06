package com.payments.dao;

import com.payments.entity.Account;
import java.util.List;
import java.util.Properties;


public class AccountDAO extends AbstractDAO<Account> {
    
    public AccountDAO(){
        super(Account.class); 
    }
    
    public List<Account> getAllByUserId(int userId){
        String id = String.valueOf(userId);
        Properties conditions = new Properties();
        conditions.put(Account.FIELD_USER_ID, id);
        List<Account> accounts = getAllByConditions(conditions);
        return accounts;
    }
    
}
