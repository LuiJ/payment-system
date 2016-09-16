package com.payments.parser.resultset;

import com.payments.entity.Identifiable;
import com.payments.entity.user.User;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserBuilder implements EntityBuilder {

    @Override
    public Identifiable build(ResultSet userResultSet) throws SQLException {
    
        User user = new User();
        
        int id = userResultSet.getInt(User.FIELD_ID);
        user.setId(id);
        
        String login = userResultSet.getString(User.FIELD_LOGIN);
        user.setLogin(login);
        
        String password = userResultSet.getString(User.FIELD_PASSWORD);
        user.setPassword(password);
        
        String salt = userResultSet.getString(User.FIELD_SALT);
        user.setSalt(salt);
        
        String firstName = userResultSet.getString(User.FIELD_FIRST_NAME);
        user.setFirstName(firstName);
        
        String lastName = userResultSet.getString(User.FIELD_LAST_NAME);
        user.setLastName(lastName);
        
        return user;
    }
    
}
