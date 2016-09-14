package com.payments.system.entity.admin;

import com.payments.system.entity.Identifiable;


public class Admin  implements Identifiable {

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
    
}
