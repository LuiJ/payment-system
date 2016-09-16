package com.payments.entity.user;

import com.payments.entity.Identifiable;
import com.payments.entity.account.Account;
import com.payments.entity.operation.Operation;
import java.util.ArrayList;
import java.util.List;


public class User implements Identifiable {
    
    public static final String TABLE_NAME = "user";
    public static final String FIELD_ID = "id";
    public static final String FIELD_LOGIN = "login";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_SALT = "salt";
    public static final String FIELD_FIRST_NAME = "first_name";
    public static final String FIELD_LAST_NAME = "last_name";

    private int id;
    private String login;
    private String password;
    private String salt;
    private String firstName;
    private String lastName;
    private List<Account> accounts;
    private List<Operation> operations;

    public User(){
        accounts = new ArrayList<>();
        operations = new ArrayList<>();
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
    
    @Override
    public String toString(){
        String result = this.getClass().getSimpleName() + ":"
                      + "\n--------------------\n" 
                      + "Login: " + login + "\n" 
                      + "Password: " + password + "\n" 
                      + "Salt: " + salt + "\n"
                      + "First name: " + firstName + "\n"
                      + "Last name: " + lastName + "\n";
        return result;
    }
    
}
