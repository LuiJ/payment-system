package com.payments.entity;


public enum OperationType {
    
    PAYMENT("Payment has been done"), 
    RECEIVE_MONEY("Money have been received"), 
    CARD_BLOCKING("Card has been blocked"), 
    CARD_ACTIVATING("Card has been activated"), 
    ACCOUNT_CLOSING("Account has been closed");
    
    private final String description;

    OperationType(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
