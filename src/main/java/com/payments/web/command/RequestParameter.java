package com.payments.web.command;


public enum RequestParameter {
    
    CARD_ID("cardId"),
    ACCOUNT_ID("accountId"),
    NUMBER("number"),
    AMOUNT("amount"),
    OPERATION_TYPE("operationType");
    
    private final String parameter; 
    
    RequestParameter(String parameter){
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}
