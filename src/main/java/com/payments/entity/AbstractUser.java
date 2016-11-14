package com.payments.entity;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class AbstractUser implements Identifiable, UserDetails {

    public static final String FIELD_ID = "id";
    public static final String FIELD_LOGIN = "login";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_ROLE_ID = "role_id";

    private Integer id;
    private String login;
    private String password;
    private Integer roleId;
    private Role role;
    
    public AbstractUser(){}

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Override
    public String getUsername(){
        return login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    @Override
    public Collection<GrantedAuthority> getAuthorities(){
        GrantedAuthority grantedAuthority = (GrantedAuthority) role;
        Collection<GrantedAuthority> grantedAuthorities = Collections.singleton(grantedAuthority);
        return grantedAuthorities;
    }
    
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }
    
    @Override
    public boolean isEnabled(){
        return true;
    }    
    
    @Override
    public String toString(){
        String result = this.getClass().getSimpleName() + ":"
                      + "\n--------------------\n" 
                      + "Login: " + login + "\n" 
                      + "Password: " + password + "\n";
        return result;
    }
}
