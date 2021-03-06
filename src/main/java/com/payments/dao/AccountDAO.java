package com.payments.dao;

import com.payments.entity.Account;
import com.payments.entity.Card;
import com.payments.entity.Status;
import java.util.List;
import java.util.Properties;


public class AccountDAO extends AbstractDAO<Account> {
    
    public AccountDAO(){
        super(Account.class); 
    }
    
    public Account getByNumber(long accountNumber){
        String number = String.valueOf(accountNumber);
        Properties conditions = new Properties();
        conditions.put(Account.FIELD_NUMBER, number);
        Account account = getByConditionsSingleResult(conditions);
        return account;
    }
    
    public List<Account> getActiveAccountsByUserId(int userId){
        String id = String.valueOf(userId);
        Properties conditions = new Properties();
        conditions.put(Account.FIELD_USER_ID, id);
        conditions.put(Account.FIELD_STATUS, Status.ACTIVE.name());
        List<Account> accounts = getAllByConditions(conditions);
        return accounts;
    }
    
    public List<Account> getAllActiveAccounts(){
        Properties conditions = new Properties();
        conditions.put(Account.FIELD_STATUS, Status.ACTIVE.name());
        List<Account> accounts = getAllByConditions(conditions);
        return accounts;
    }
    
    public List<Account> getAllWithCards(){
        CardDAO cardDAO = DAOFactory.INSTANCE.getCardDAO();
        List<Account> accounts = getAll();
        for (Account account : accounts){
            int accountId = account.getId();
            List<Card> cards = cardDAO.getAllByAccountId(accountId);
            account.setCards(cards);
        }
        return accounts;
    }
    
}
