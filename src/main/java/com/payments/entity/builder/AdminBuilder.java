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
        
        String firstName = adminResultSet.getString(Admin.FIELD_FIRST_NAME);
        admin.setFirstName(firstName);
        
        return admin;
    }
    
    @Override
    public AbstractUser createSpecificUser(){
        return new Admin();
    }
    
}
