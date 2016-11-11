package com.payments.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Account implements Identifiable {
   
    public static final String TABLE_NAME = "account";
    public static final String FIELD_ID = "id";
    public static final String FIELD_NUMBER = "number";
    public static final String FIELD_STATUS = "status";
    public static final String FIELD_AMOUNT = "amount";
    public static final String FIELD_USER_ID = "user_id";
            
    private int id;
    private long number;
    private Status status;
    private BigDecimal amount;
    private int userId;
    private User user;
    private List<Card> cards;
    
    public Account(){
        cards = new ArrayList<>();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
    
    @Override
    public String toString(){
        String result = this.getClass().getSimpleName() + ":"
                      + "\n--------------------\n" 
                      + "Number: " + number + "\n" 
                      + "Status: " + status + "\n" 
                      + "Amount: " + amount + "\n";
        return result;
    }    
}
