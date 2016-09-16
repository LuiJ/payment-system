package com.payments.parser.resultset;

import com.payments.entity.Identifiable;
import com.payments.entity.admin.Admin;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminBuilder implements EntityBuilder {

    @Override
    public Identifiable build(ResultSet adminResultSet) throws SQLException {
    
        Admin admin = new Admin();
    
        int id = adminResultSet.getInt(Admin.FIELD_ID);
        admin.setId(id);
        
        String login = adminResultSet.getString(Admin.FIELD_LOGIN);
        admin.setLogin(login);
        
        String password = adminResultSet.getString(Admin.FIELD_PASSWORD);
        admin.setPassword(password);
        
        String salt = adminResultSet.getString(Admin.FIELD_SALT);
        admin.setSalt(salt);
        
        return admin;
    }
    
}
