package com.payments.entity.admin;

import com.payments.entity.Checkable;
import com.payments.entity.Identifiable;


public class Admin  implements Identifiable, Checkable {
    
    public static final String TABLE_NAME = "admin";
    public static final String FIELD_ID = "id";
    public static final String FIELD_LOGIN = "login";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_SALT = "salt";

    private int id;
    private String login;
    private String password;
    private String salt;

    public Admin(){}
    
    public Admin(String login, String password, String salt){
        this.login = login;
        this.password = password;
        this.salt = salt;
    }

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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    @Override
    public String toString(){
        String result = this.getClass().getSimpleName() + ":"
                      + "\n--------------------\n" 
                      + "Login: " + login + "\n" 
                      + "Password: " + password + "\n" 
                      + "Salt: " + salt + "\n";
        return result;
    }
    
}
