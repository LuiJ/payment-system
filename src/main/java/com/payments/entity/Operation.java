package com.payments.entity;

import java.math.BigDecimal;
import java.util.Date;


public class Operation implements Identifiable {
    
    public static final String TABLE_NAME = "operation";
    public static final String FIELD_ID = "id";
    public static final String FIELD_DATE = "date";
    public static final String FIELD_TYPE = "type";
    public static final String FIELD_AMOUNT = "amount";
    public static final String FIELD_USER_ID = "user_id";
    public static final String FIELD_ACCOUNT_ID = "account_id";
    public static final String FIELD_CARD_ID = "card_id";

    private Integer id;
    private Date date;
    private OperationType type;
    private BigDecimal amount;
    private Integer userId;
    private User user;
    private Integer accountId;
    private Account account;
    private Integer cardId;
    private Card card;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
    
    @Override
    public String toString(){
        String result = this.getClass().getSimpleName() + ":"
                      + "\n--------------------\n" 
                      + "Date: " + date + "\n" 
                      + "Type: " + type + "\n" 
                      + "Amount: " + amount + "\n";
        return result;
    }
}
