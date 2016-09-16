package com.payments.dao;

import com.payments.entity.user.User;
import java.util.List;
import java.util.Properties;


public class UserDAOImpl implements UserDAO {

    private DAOHelper<User> helper;
    
    public UserDAOImpl(){
        helper = new DAOHelper<>(User.class);
    }
    
    @Override
    public User getByLogin(String login){
        User user = null;
        Properties conditions = new Properties();
        conditions.put("login", login);
        List<User> users = helper.getAllByConditions(conditions);        
        if (!users.isEmpty()){
            user = users.get(0);
        }
        return user;
    }
    
    @Override
    public List<User> getAll(){
        List<User> users = helper.getAll();
        return users;
    }
}
