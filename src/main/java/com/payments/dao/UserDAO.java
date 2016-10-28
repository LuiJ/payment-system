package com.payments.dao;

import com.payments.entity.Account;
import com.payments.entity.Card;
import com.payments.entity.User;
import java.util.Properties;


public class UserDAO extends AbstractDAO<User> {

    public UserDAO(){
        super(User.class);
    }
    
    public User getByLogin(String login){
        Properties conditions = new Properties();
        conditions.put(User.FIELD_LOGIN, login);
        User user = getByConditionsSingleResult(conditions);        
        return user;
    }
    
    public User getByCardId(int cardId){
        CardDAO cardDAO = DAOFactory.INSTANCE.getCardDAO();
        Card card = cardDAO.getById(cardId);
        int accountId = card.getAccountId();
        AccountDAO accountDAO = DAOFactory.INSTANCE.getAccountDAO();
        Account account = accountDAO.getById(accountId);
        int userId = account.getUserId();
        User user = getById(userId);
        return user;
    }
}
