package com.payments.entity.builder;

import com.payments.dao.DAOFactory;
import com.payments.dao.RoleDAO;
import com.payments.entity.AbstractUser;
import com.payments.entity.Identifiable;
import com.payments.entity.Role;
import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class AbstractUserBuilder  implements EntityBuilder {

    @Override
    public Identifiable build(ResultSet userResultSet) throws SQLException {
        
        AbstractUser abstractUser = createSpecificUser();
        
        int id = userResultSet.getInt(AbstractUser.FIELD_ID);
        abstractUser.setId(id);
        
        String login = userResultSet.getString(AbstractUser.FIELD_LOGIN);
        abstractUser.setLogin(login);
        
        String password = userResultSet.getString(AbstractUser.FIELD_PASSWORD);
        abstractUser.setPassword(password);
        
        int roleId = userResultSet.getInt(AbstractUser.FIELD_ROLE_ID);
        abstractUser.setRoleId(roleId);
        
        RoleDAO roleDAO = DAOFactory.INSTANCE.getRoleDAO();
        Role role = roleDAO.getById(roleId);
        abstractUser.setRole(role);
        
        return abstractUser;
    }
    
    public abstract AbstractUser createSpecificUser();
    
}
