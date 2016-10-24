package com.payments.logic;


public enum Parameter {
    CARD_ID("cardId"),
    ACCOUNT_ID("accountId");
    
    private final String parameter; 
    
    Parameter(String parameter){
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}
