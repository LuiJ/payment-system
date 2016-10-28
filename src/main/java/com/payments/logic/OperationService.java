package com.payments.logic;

import com.payments.dao.AccountDAO;
import com.payments.dao.CardDAO;
import com.payments.dao.DAOFactory;
import com.payments.dao.OperationDAO;
import com.payments.dao.UserDAO;
import com.payments.entity.Account;
import com.payments.entity.Card;
import com.payments.entity.Operation;
import com.payments.entity.OperationType;
import com.payments.entity.User;
import java.math.BigDecimal;
import java.util.Date;


public class OperationService {
    
    private final OperationDAO operationDAO;
    
    public OperationService(){
        operationDAO = DAOFactory.INSTANCE.getOperationDAO();
    }
    
    
    public void saveCardBlockOperation(int cardId)
    {
        Operation operation = createOperation();        
        operation.setType(OperationType.CARD_BLOCKING);
        String description = OperationType.CARD_BLOCKING.getDescription();
        operation.setDescription(description);
        
        CardDAO cardDAO = DAOFactory.INSTANCE.getCardDAO();
        Card card = cardDAO.getById(cardId);
        long itemNumber = card.getNumber();
        operation.setItemNumber(itemNumber);
        
        operation.setAmount(BigDecimal.ZERO);  
        
        UserDAO userDAO = DAOFactory.INSTANCE.getUserDAO();
        User user = userDAO.getByCardId(cardId);
        int userId = user.getId();
        operation.setUserId(userId);
        
        operationDAO.save(operation);
    }
    
    
    public void saveCardActivateOperation(int cardId)
    {
        Operation operation = createOperation();        
        operation.setType(OperationType.CARD_ACTIVATING);
        String description = OperationType.CARD_ACTIVATING.getDescription();
        operation.setDescription(description);
        
        CardDAO cardDAO = DAOFactory.INSTANCE.getCardDAO();
        Card card = cardDAO.getById(cardId);
        long itemNumber = card.getNumber();
        operation.setItemNumber(itemNumber);
        
        operation.setAmount(BigDecimal.ZERO);  
        
        UserDAO userDAO = DAOFactory.INSTANCE.getUserDAO();
        User user = userDAO.getByCardId(cardId);
        int userId = user.getId();
        operation.setUserId(userId);
        
        operationDAO.save(operation);
    }
    
    
    public void saveAccountCloseOperation(int accountId)
    {
        Operation operation = createOperation();        
        operation.setType(OperationType.ACCOUNT_CLOSING);
        String description = OperationType.ACCOUNT_CLOSING.getDescription();
        operation.setDescription(description);
        
        AccountDAO accountDAO = DAOFactory.INSTANCE.getAccountDAO();
        Account account = accountDAO.getById(accountId);
        long itemNumber = account.getNumber();
        operation.setItemNumber(itemNumber);
        
        operation.setAmount(BigDecimal.ZERO);         
        int userId = account.getUserId();
        operation.setUserId(userId);
        
        operationDAO.save(operation);
    }    
    
    
    private Operation createOperation(){
        Operation operation = new Operation();        
        Date date = new Date();
        operation.setDate(date);
        return operation;
    }    
}
