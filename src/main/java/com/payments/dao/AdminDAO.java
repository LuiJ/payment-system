package com.payments.dao;

import com.payments.entity.admin.Admin;
import java.util.Properties;


public class AdminDAO extends AbstractDAO<Admin> {
    
    public AdminDAO(){
        super(Admin.class); 
    }

    public Admin getByLogin(String login){
        Properties conditions = new Properties();
        conditions.put("login", login);
        Admin admin = getByConditionsSingleResult(conditions);
        return admin;
    }
    
}
