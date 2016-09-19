package com.payments.dao;

import com.payments.entity.card.Card;
import java.util.List;
import java.util.Properties;


public class CardDAO extends AbstractDAO<Card> {
    
    public CardDAO(){
        super(Card.class); 
    }
    
    public Card getByNumber(long cardNumber){
        String number = String.valueOf(cardNumber);
        Properties conditions = new Properties();
        conditions.put("number", number);
        Card card = getByConditionsSingleResult(conditions);
        return card;
    }
    
    public List<Card> getAllByAccountId(int accountId){
        String id = String.valueOf(accountId);
        Properties conditions = new Properties();
        conditions.put("account_id", id);
        List<Card> cards = getAllByConditions(conditions);
        return cards;
    }
    
}
