package com.payments.web.command.admin;

import com.payments.entity.Admin;
import com.payments.logic.CardService;
import com.payments.logic.OperationService;
import com.payments.web.command.AbstractCommand;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminCardActivateCommand extends AbstractCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {       
        CardService cardService = new CardService();
        cardService.activate(request);
        
        OperationService operationService = new OperationService();
        operationService.saveCardActivateOperation(request, Admin.class);
        
        AbstractCommand command = new AdminCardsListCommand();
        command.execute(request, response);
    }    
}
