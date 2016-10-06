package com.payments.dao;

import com.payments.entity.Admin;
import java.util.Properties;


public class AdminDAO extends AbstractDAO<Admin> {
    
    public AdminDAO(){
        super(Admin.class); 
    }

    public Admin getByLogin(String login){
        Properties conditions = new Properties();
        conditions.put(Admin.FIELD_LOGIN, login);
        Admin admin = getByConditionsSingleResult(conditions);
        return admin;
    }
    
}
