package com.payments.dao;

import com.payments.entity.User;
import java.util.Properties;


public class UserDAO extends AbstractDAO<User> {

    public UserDAO(){
        super(User.class);
    }
    
    public User getByLogin(String login){
        Properties conditions = new Properties();
        conditions.put(User.FIELD_LOGIN, login);
        User user = getByConditionsSingleResult(conditions);        
        return user;
    }
}
