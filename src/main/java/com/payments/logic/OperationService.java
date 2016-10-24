package com.payments.logic;

import com.payments.dao.AccountDAO;
import com.payments.dao.CardDAO;
import com.payments.dao.DAOFactory;
import com.payments.dao.OperationDAO;
import com.payments.entity.AbstractUser;
import com.payments.entity.Account;
import com.payments.entity.Admin;
import com.payments.entity.Card;
import com.payments.entity.Operation;
import com.payments.entity.OperationType;
import com.payments.entity.User;
import java.math.BigDecimal;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;


public class OperationService {
    
    private final OperationDAO operationDAO;
    
    public OperationService(){
        operationDAO = DAOFactory.INSTANCE.getOperationDAO();
    }
    
    
    public void saveCardBlockOperation(HttpServletRequest request, 
                                       Class<? extends AbstractUser> userType)
    {
        Operation operation = createOperation();        
        operation.setType(OperationType.CARD_BLOCKING);
        operation.setAmount(BigDecimal.ZERO);
        
        Card card = getCard(request);
        long cardNumber = card.getNumber();
        String description = getDescription(OperationType.CARD_BLOCKING, userType, cardNumber);
        operation.setDescription(description);  
        
        int userId = getUserIdByCard(card);
        operation.setUserId(userId);
        
        operationDAO.save(operation);
    }
    
    
    public void saveCardActivateOperation(HttpServletRequest request, 
                                       Class<? extends AbstractUser> userType)
    {
        Operation operation = createOperation();        
        operation.setType(OperationType.ACCOUNT_CLOSING);
        operation.setAmount(BigDecimal.ZERO);
        
        Card card = getCard(request);
        long cardNumber = card.getNumber();
        String description = getDescription(OperationType.CARD_ACTIVATING, userType, cardNumber);
        operation.setDescription(description);  
        
        int userId = getUserIdByCard(card);
        operation.setUserId(userId);
        
        operationDAO.save(operation); 
    }
    
    
    public void saveAccountCloseOperation(HttpServletRequest request, 
                                       Class<? extends AbstractUser> userType)
    {
        Operation operation = createOperation();        
        operation.setType(OperationType.ACCOUNT_CLOSING);
        operation.setAmount(BigDecimal.ZERO);
        
        Account account = getAccount(request);
        long accountNumber = account.getNumber();
        String description = getDescription(OperationType.ACCOUNT_CLOSING, userType, accountNumber);
        operation.setDescription(description);  
        
        int userId = getUserIdByAccount(account);
        operation.setUserId(userId);
        
        operationDAO.save(operation);
    }
    
    
    
    private Operation createOperation(){
        Operation operation = new Operation();        
        Date date = new Date();
        operation.setDate(date);
        return operation;
    }
    
    private Card getCard(HttpServletRequest request){
        String cardIdParameter = request.getParameter(Parameter.CARD_ID.getParameter());
        int cardId = Integer.parseInt(cardIdParameter); 
        CardDAO cardDAO = DAOFactory.INSTANCE.getCardDAO();
        Card card = cardDAO.getById(cardId);
        return card;
    }
    
    private Account getAccount(HttpServletRequest request){
        String accountIdParameter = request.getParameter(Parameter.ACCOUNT_ID.getParameter());
        int accountId = Integer.parseInt(accountIdParameter); 
        AccountDAO accountDAO = DAOFactory.INSTANCE.getAccountDAO();
        Account account = accountDAO.getById(accountId);
        return account;
    }
    
    private int getUserIdByAccount(Account account){
        int userId = account.getUserId();
        return userId;
    }
    
    private int getUserIdByCard(Card card){
        int accountId = card.getAccountId();
        AccountDAO accountDAO = DAOFactory.INSTANCE.getAccountDAO();
        Account account = accountDAO.getById(accountId);
        int userId = getUserIdByAccount(account);
        return userId;
    }
    
    private String getDescription(OperationType operationType, 
                                  Class<? extends AbstractUser> userType,
                                  long itemNumber)
    {
        String description = null;
        if (operationType == OperationType.CARD_BLOCKING && 
                 userType == User.class)
        {
            description = "Card " + itemNumber + " has been blocked by user.";
        }
        else if (operationType == OperationType.CARD_BLOCKING && 
                      userType == Admin.class)
        {
            description = "Card " + itemNumber + " has been blocked by admin.";
        }
        else if (operationType == OperationType.CARD_ACTIVATING && 
                      userType == Admin.class)
        {
            description = "Card " + itemNumber + " has been activated by admin.";
        }
        else if (operationType == OperationType.ACCOUNT_CLOSING && 
                      userType == User.class)
        {
            description = "Account " + itemNumber + " has been closed.";
        }
        else {
            throw new IllegalArgumentException();
        }
        return description;
    }
    
}
