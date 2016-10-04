package com.payments.security;

import com.payments.dao.AdminDAO;
import com.payments.dao.DAOFactory;
import com.payments.dao.RoleDAO;
import com.payments.dao.UserDAO;
import com.payments.entity.AbstractUser;
import com.payments.entity.role.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Override
    public UserDetails loadUserByUsername(String userName) 
            throws UsernameNotFoundException 
    {
        AbstractUser user = null;
        
        UserDAO userDAO = DAOFactory.INSTANCE.getUserDAO();
        user = userDAO.getByLogin(userName);
                
        if (user == null){
            AdminDAO adminDAO = DAOFactory.INSTANCE.getAdminDAO();
            user = adminDAO.getByLogin(userName);
            if (user == null){
                throw new UsernameNotFoundException("User doesn't exist");
            }
        }
        
        int roleId = user.getRoleId();
        RoleDAO roleDAO = DAOFactory.INSTANCE.getRoleDAO();
        Role role = roleDAO.getById(roleId);
        
        UserDetails userDetails = new UserDetailsImpl(user, role);       
        return userDetails;
    }
    
}
