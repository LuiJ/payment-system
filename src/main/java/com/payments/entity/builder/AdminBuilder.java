package com.payments.entity.builder;

import com.payments.entity.AbstractUser;
import com.payments.entity.Identifiable;
import com.payments.entity.Admin;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminBuilder extends AbstractUserBuilder {

    @Override
    public Identifiable build(ResultSet adminResultSet) throws SQLException {
    
        Admin admin = (Admin) super.build(adminResultSet);        
        return admin;
    }
    
    @Override
    public AbstractUser createSpecificUser(){
        return new Admin();
    }
    
}
