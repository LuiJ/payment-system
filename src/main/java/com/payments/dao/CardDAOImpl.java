package com.payments.dao;

import com.payments.entity.card.Card;
import java.util.List;
import java.util.Properties;


public class CardDAOImpl implements CardDAO {

    private DAOHelper<Card> helper;
    
    public CardDAOImpl(){
        helper = new DAOHelper<>(Card.class); 
    }
    
    @Override
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
    
    
    @Override
    public List<Card> getByAccountId(int accountId){
        String id = String.valueOf(accountId);
        Properties conditions = new Properties();
        conditions.put("account_id", id);
        List<Card> cards = helper.getAllByConditions(conditions);
        return cards;
    }
    
    
    @Override
    public List<Card> getAll(){
        List<Card> cards = helper.getAll();
        return cards;
    }
    
}
