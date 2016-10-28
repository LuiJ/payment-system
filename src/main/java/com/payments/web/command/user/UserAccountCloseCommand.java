package com.payments.web.command.user;

import com.payments.logic.AccountService;
import com.payments.logic.OperationService;
import com.payments.web.command.AbstractCommand;
import com.payments.web.command.RequestParameter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserAccountCloseCommand extends AbstractCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {   
        String accountIdParameter = request.getParameter(RequestParameter.ACCOUNT_ID.getParameter());
        int accountId = Integer.parseInt(accountIdParameter);         
        
        AccountService accountService = new AccountService();
        accountService.close(accountId);
        
        OperationService operationService = new OperationService();
        operationService.saveAccountCloseOperation(accountId);
        
        AbstractCommand command = new UserAccountsListCommand();
        command.execute(request, response);
    }    
}
