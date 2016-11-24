package com.payments.web.command.user;

import com.payments.exception.PaymentsException;
import com.payments.logic.AccountService;
import com.payments.logic.OperationService;
import com.payments.web.command.AbstractServletCommand;
import com.payments.web.command.RequestParameter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserAccountCloseCommand extends AbstractServletCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, PaymentsException
    {   
        String accountIdParameter = request.getParameter(RequestParameter.ACCOUNT_ID.getParameter());
        int accountId = Integer.parseInt(accountIdParameter);         
        
        AccountService accountService = new AccountService();
        accountService.close(accountId);
        
        OperationService operationService = new OperationService();
        operationService.saveAccountCloseOperation(accountId);
        
        AbstractServletCommand command = new UserAccountsListCommand();
        command.execute(request, response);
    }    
}
