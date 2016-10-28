package com.payments.logic;

import com.payments.dao.AccountDAO;
import com.payments.dao.DAOFactory;
import com.payments.entity.Account;
import com.payments.entity.Status;


public class AccountService {
    
    private final AccountDAO accountDAO;
    
    public AccountService(){
        accountDAO = DAOFactory.INSTANCE.getAccountDAO();
    }
    
    public void close(int accountId){      
        Account account = accountDAO.getById(accountId);
        account.setStatus(Status.CLOSED);
        accountDAO.save(account);
    }    
}
