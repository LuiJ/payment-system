package com.payments.web.command.admin;

import com.payments.entity.Admin;
import com.payments.logic.CardService;
import com.payments.logic.OperationService;
import com.payments.web.command.AbstractCommand;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminCardBlockCommand extends AbstractCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {           
        CardService cardService = new CardService();
        cardService.block(request);
        
        OperationService operationService = new OperationService();
        operationService.saveCardBlockOperation(request, Admin.class);
        
        AbstractCommand command = new AdminCardsListCommand();
        command.execute(request, response);
    }    
}
