package com.payments.dao;

import com.payments.entity.Account;
import com.payments.entity.Card;
import com.payments.entity.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class CardDAO extends AbstractDAO<Card> {
    
    public CardDAO(){
        super(Card.class); 
    }
    
    public Card getByNumber(long cardNumber){
        String number = String.valueOf(cardNumber);
        Properties conditions = new Properties();
        conditions.put(Card.FIELD_NUMBER, number);
        Card card = getByConditionsSingleResult(conditions);
        return card;
    }
    
    public List<Card> getAllByAccountId(int accountId){
        String id = String.valueOf(accountId);
        Properties conditions = new Properties();
        conditions.put(Card.FIELD_ACCOUNT_ID, id);
        List<Card> cards = getAllByConditions(conditions);
        return cards;
    }
    
    public List<Card> getAllActiveByAccountId(int accountId){
        String id = String.valueOf(accountId);
        Properties conditions = new Properties();
        conditions.put(Card.FIELD_ACCOUNT_ID, id);
        conditions.put(Card.FIELD_STATUS, Status.ACTIVE.name());
        List<Card> cards = getAllByConditions(conditions);
        return cards;
    }
    
    public List<Card> getAllByUserId(int userId){
        AccountDAO accountDAO = DAOFactory.INSTANCE.getAccountDAO();
        List<Account> activeAccounts = accountDAO.getActiveAccountsByUserId(userId);
        List<Card> cards = new ArrayList<>();
        for (Account account : activeAccounts){
            int accountId = account.getId();
            List<Card> accountCards = getAllByAccountId(accountId);
            cards.addAll(accountCards);
        }
        return cards;
    }
    
    public List<Card> getAllActiveByUserId(int userId){
        AccountDAO accountDAO = DAOFactory.INSTANCE.getAccountDAO();
        List<Account> activeAccounts = accountDAO.getActiveAccountsByUserId(userId);
        List<Card> cards = new ArrayList<>();
        for (Account account : activeAccounts){
            int accountId = account.getId();
            List<Card> accountCards = getAllActiveByAccountId(accountId);
            cards.addAll(accountCards);
        }
        return cards;
    }
    
    @Override
    public List<Card> getAll(){
        AccountDAO accountDAO = DAOFactory.INSTANCE.getAccountDAO();
        List<Account> activeAccounts = accountDAO.getAllActiveAccounts();
        List<Card> cards = new ArrayList<>();
        for (Account account : activeAccounts){
            int accountId = account.getId();
            List<Card> accountCards = getAllByAccountId(accountId);
            cards.addAll(accountCards);
        }
        return cards;
    }
    
}
