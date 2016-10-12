package com.payments.entity;


public class Admin extends AbstractUser {
    
    public static final String TABLE_NAME = "admin";
    public static final String FIELD_FIRST_NAME = "first_name";
    
    private String firstName;
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
}
