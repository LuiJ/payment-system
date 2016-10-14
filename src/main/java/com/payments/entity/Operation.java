package com.payments.entity;

import java.math.BigDecimal;
import java.util.Date;


public class Operation implements Identifiable {
    
    public static final String TABLE_NAME = "operation";
    public static final String FIELD_ID = "id";
    public static final String FIELD_DATE = "date";
    public static final String FIELD_TYPE = "type";
    public static final String FIELD_AMOUNT = "amount";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_USER_ID = "user_id";

    private int id;
    private Date date;
    private OperationType type;
    private BigDecimal amount;
    private String description;
    private int userId;
    private User user;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public String toString(){
        String result = this.getClass().getSimpleName() + ":"
                      + "\n--------------------\n" 
                      + "Date: " + date + "\n" 
                      + "Type: " + type + "\n" 
                      + "Amount: " + amount + "\n"
                      + "Description: " + description + "\n";
        return result;
    }
}
