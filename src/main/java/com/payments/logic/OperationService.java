package com.payments.logic;

import com.payments.dao.AccountDAO;
import com.payments.dao.DAOFactory;
import com.payments.dao.OperationDAO;
import com.payments.dao.UserDAO;
import com.payments.entity.Account;
import com.payments.entity.Operation;
import com.payments.entity.OperationType;
import com.payments.entity.Payment;
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
        operation.setCardId(cardId);
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
        operation.setCardId(cardId);        
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
        operation.setAccountId(accountId);
        operation.setAmount(BigDecimal.ZERO); 
        
        AccountDAO accountDAO = DAOFactory.INSTANCE.getAccountDAO();
        Account account = accountDAO.getById(accountId);
        int userId = account.getUserId();
        operation.setUserId(userId);
        
        operationDAO.save(operation);
    } 
   
    
    public void savePaymentOperation(Payment payment, OperationType operationType)
    {
        Operation payerOperation = createOperation();        
        payerOperation.setType(operationType);
        
        Account payerAccount = payment.getPayerAccount();
        int payerAccountId = payerAccount.getId();
        payerOperation.setAccountId(payerAccountId);
        
        BigDecimal amount = payment.getAmount();
        payerOperation.setAmount(amount);
        
        int payerId = payerAccount.getUserId();
        payerOperation.setUserId(payerId);
        
        operationDAO.save(payerOperation);
        
        Operation receiverOperation = createOperation();        
        receiverOperation.setType(OperationType.RECEIVE_MONEY);
        
        Account targetAccount = payment.getTargetAccount();
        int targetAccountId = targetAccount.getId();
        receiverOperation.setAccountId(targetAccountId);
        
        receiverOperation.setAmount(amount);
        
        int paymentReceiverId = targetAccount.getUserId();
        receiverOperation.setUserId(paymentReceiverId);
        
        operationDAO.save(receiverOperation);
    }
    
    
    private Operation createOperation(){
        Operation operation = new Operation();        
        Date date = new Date();
        operation.setDate(date);
        return operation;
    }    
}
