package com.payments.entity;


public class AbstractUser implements Identifiable {

    public static final String FIELD_ID = "id";
    public static final String FIELD_LOGIN = "login";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_ROLE_ID = "role_id";

    private int id;
    private String login;
    private String password;
    private int roleId;
    
    public AbstractUser(){}

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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
