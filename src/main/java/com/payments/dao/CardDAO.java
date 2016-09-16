package com.payments.dao;

import com.payments.entity.card.Card;
import java.util.List;
import java.util.Properties;


public class CardDAO extends AbstractDAO<Card> {
    
    public CardDAO(){
        super(Card.class); 
    }
    
    public Card getByNumber(long cardNumber){
        Card card = null;
        String number = String.valueOf(cardNumber);
        Properties conditions = new Properties();
        conditions.put("number", number);
        List<Card> cards = helper.getAllByConditions(conditions);
        if (!cards.isEmpty()){
            card = cards.get(0);
        }
        return card;
    }
    
    public List<Card> getByAccountId(int accountId){
        String id = String.valueOf(accountId);
        Properties conditions = new Properties();
        conditions.put("account_id", id);
        List<Card> cards = helper.getAllByConditions(conditions);
        return cards;
    }
    
}
