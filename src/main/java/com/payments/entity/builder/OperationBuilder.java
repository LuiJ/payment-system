package com.payments.entity.builder;

import com.payments.dao.AccountDAO;
import com.payments.dao.CardDAO;
import com.payments.dao.DAOFactory;
import com.payments.dao.UserDAO;
import com.payments.entity.Account;
import com.payments.entity.Card;
import com.payments.entity.Identifiable;
import com.payments.entity.Operation;
import com.payments.entity.OperationType;
import com.payments.entity.User;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;


public class OperationBuilder implements EntityBuilder {

    @Override
    public Identifiable build(ResultSet operationResultSet) throws SQLException {
    
        Operation operation = new Operation();
        
        Integer id = (Integer) operationResultSet.getObject(Operation.FIELD_ID);
        operation.setId(id);
        
        Timestamp timestamp = operationResultSet.getTimestamp(Operation.FIELD_DATE);
        long time = timestamp.getTime();
        Date date = new Date(time);
        operation.setDate(date);
        
        OperationType type = OperationType.valueOf(operationResultSet.getString(Operation.FIELD_TYPE));
        operation.setType(type);
        
        BigDecimal amount = operationResultSet.getBigDecimal(Operation.FIELD_AMOUNT);
        operation.setAmount(amount);
        
        Integer userId = operationResultSet.getInt(Operation.FIELD_USER_ID);
        operation.setUserId(userId);
        
        UserDAO userDAO = DAOFactory.INSTANCE.getUserDAO();
        User user = userDAO.getById(userId);
        operation.setUser(user);
        
        Integer accountId = (Integer) operationResultSet.getObject(Operation.FIELD_ACCOUNT_ID);        
        if (accountId != null){
            operation.setAccountId(accountId);
            AccountDAO accountDAO = DAOFactory.INSTANCE.getAccountDAO();
            Account account = accountDAO.getById(accountId);
            operation.setAccount(account);
        }       
        
        Integer cardId = (Integer) operationResultSet.getObject(Operation.FIELD_CARD_ID);        
        if (cardId != null){
            operation.setCardId(cardId);
            CardDAO cardDAO = DAOFactory.INSTANCE.getCardDAO();
            Card card = cardDAO.getById(cardId);
            operation.setCard(card);
        }
            
        return operation;
    }
    
}
