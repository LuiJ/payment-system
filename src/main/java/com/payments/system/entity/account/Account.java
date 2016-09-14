package com.payments.system.entity.account;

import com.payments.system.entity.Identifiable;
import com.payments.system.entity.card.Card;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Account implements Identifiable {

    private int id;
    private long number;
    private Status status;
    private BigDecimal amount;
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

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
        
}
