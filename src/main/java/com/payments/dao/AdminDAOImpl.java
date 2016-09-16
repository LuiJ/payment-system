package com.payments.dao;

import com.payments.entity.admin.Admin;
import java.util.List;
import java.util.Properties;


public class AdminDAOImpl implements AdminDAO {
    
    private DAOHelper<Admin> helper;
    
    public AdminDAOImpl(){
        helper = new DAOHelper<>(Admin.class); 
    }

    @Override
    public Admin getByLogin(String login){
        Admin admin = null;
        Properties conditions = new Properties();
        conditions.put("login", login);
        List<Admin> admins = helper.getAllByConditions(conditions);
        if (!admins.isEmpty()){
            admin = admins.get(0);
        }
        return admin;
    }
    
}
