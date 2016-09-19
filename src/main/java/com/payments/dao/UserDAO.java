package com.payments.dao;

import com.payments.entity.user.User;
import java.util.Properties;


public class UserDAO extends AbstractDAO<User> {

    public UserDAO(){
        super(User.class);
    }
    
    public User getByLogin(String login){
        Properties conditions = new Properties();
        conditions.put("login", login);
        User user = getByConditionsSingleResult(conditions);        
        return user;
    }
}
