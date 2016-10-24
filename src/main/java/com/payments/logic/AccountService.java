package com.payments.logic;

import com.payments.dao.AccountDAO;
import com.payments.dao.DAOFactory;
import com.payments.entity.Account;
import com.payments.entity.Status;
import javax.servlet.http.HttpServletRequest;


public class AccountService {
    
    private final AccountDAO accountDAO;
    
    public AccountService(){
        accountDAO = DAOFactory.INSTANCE.getAccountDAO();
    }
    
    public void close(HttpServletRequest request){        
        int cardId = getAccountId(request);
        Account account = accountDAO.getById(cardId);
        account.setStatus(Status.CLOSED);
        accountDAO.save(account);
    }
    
    private int getAccountId(HttpServletRequest request){
        String accountIdParameter = request.getParameter(Parameter.ACCOUNT_ID.getParameter());
        int accountId = Integer.parseInt(accountIdParameter); 
        return accountId;
    }
    
}
