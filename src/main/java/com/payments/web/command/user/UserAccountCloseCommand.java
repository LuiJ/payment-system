package com.payments.web.command.user;

import com.payments.entity.User;
import com.payments.logic.AccountService;
import com.payments.logic.OperationService;
import com.payments.web.command.AbstractCommand;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserAccountCloseCommand extends AbstractCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {       
        AccountService accountService = new AccountService();
        accountService.close(request);
        
        OperationService operationService = new OperationService();
        operationService.saveAccountCloseOperation(request, User.class);
        
        AbstractCommand command = new UserAccountsListCommand();
        command.execute(request, response);
    }    
}
