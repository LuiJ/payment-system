package com.payments.entity;

import java.util.ArrayList;
import java.util.List;


public class User extends AbstractUser {
    
    public static final String TABLE_NAME = "user";
    public static final String FIELD_FIRST_NAME = "first_name";
    public static final String FIELD_LAST_NAME = "last_name";

    private String firstName;
    private String lastName;
    private List<Account> accounts;
    private List<Operation> operations;

    public User(){
        super();
        accounts = new ArrayList<>();
        operations = new ArrayList<>();
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
        String result = super.toString() + "\n"
                      + "First name: " + firstName + "\n"
                      + "Last name: " + lastName + "\n";
        return result;
    }
    
}
