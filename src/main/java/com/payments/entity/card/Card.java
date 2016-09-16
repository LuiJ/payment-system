package com.payments.entity.card;

import com.payments.entity.Identifiable;
import com.payments.entity.Status;


public class Card implements Identifiable {
    
    public static final String TABLE_NAME = "card";
    public static final String FIELD_ID = "id";
    public static final String FIELD_NUMBER = "number";
    public static final String FIELD_PIN_CODE = "pin_code";
    public static final String FIELD_SALT = "salt";
    public static final String FIELD_STATUS = "status";
    public static final String FIELD_ACCOUNT_ID = "account_id";

    private int id;
    private long number;
    private String pinCode;
    private String salt;
    private Status status;
    private int accountId;

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

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    
    @Override
    public String toString(){
        String result = this.getClass().getSimpleName() + ":"
                      + "\n--------------------\n" 
                      + "Number: " + number + "\n" 
                      + "PIN Code: " + pinCode + "\n" 
                      + "Salt: " + salt + "\n"
                      + "Status: " + status + "\n";
        return result;
    }
    
}
