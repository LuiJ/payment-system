package com.payments.web.internationalization;


public enum TextPropertiesEnum {

    INCORRECT_PAYMENT_DATA_ERROR("error.incorrectPaymentData"),
    INCORRECT_AMOUNT_ERROR("error.notEnoughMoney"),
    LOGIN_ERROR("error.login"),
    OPERATION_SUCCESS("success.operationSuccess");
    
    private final String name;

    TextPropertiesEnum(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
