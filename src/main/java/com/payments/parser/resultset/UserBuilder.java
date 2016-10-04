package com.payments.parser.resultset;

import com.payments.entity.AbstractUser;
import com.payments.entity.Identifiable;
import com.payments.entity.user.User;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserBuilder extends AbstractUserBuilder {

    @Override
    public Identifiable build(ResultSet userResultSet) throws SQLException {
    
        User user = (User) super.build(userResultSet);
        
        String firstName = userResultSet.getString(User.FIELD_FIRST_NAME);
        user.setFirstName(firstName);
        
        String lastName = userResultSet.getString(User.FIELD_LAST_NAME);
        user.setLastName(lastName);
        
        return user;
    }
    
    @Override
    public AbstractUser createSpecificUser(){
        return new User();
    }
    
}
