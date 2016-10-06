package com.payments.entity;

import com.payments.entity.Identifiable;
import org.springframework.security.core.GrantedAuthority;


public class Role implements Identifiable, GrantedAuthority {

    public static final String TABLE_NAME = "role";
    public static final String FIELD_ID = "id";
    public static final String FIELD_ROLE_NAME = "role_name";
    
    private int id;
    private String roleName;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    @Override
    public String getAuthority(){
        return roleName;
    }
    
    @Override
    public String toString(){
        String result = this.getClass().getSimpleName() + ":"
                      + "\n--------------------\n" 
                      + "Role Name: " + roleName + "\n";
        return result;
    }
            
}
