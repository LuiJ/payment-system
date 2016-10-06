package com.payments.entity.builder;

import com.payments.entity.Identifiable;
import com.payments.entity.Role;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RoleBuilder implements EntityBuilder {

    @Override
    public Identifiable build(ResultSet roleResultSet) throws SQLException {
        
        Role role = new Role();
        
        int id = roleResultSet.getInt(Role.FIELD_ID);
        role.setId(id);
        
        String roleName = roleResultSet.getString(Role.FIELD_ROLE_NAME);
        role.setRoleName(roleName);
        
        return role;        
    }    
}
