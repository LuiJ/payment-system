package com.payments.web.command.user;

import com.payments.entity.User;
import com.payments.logic.CardService;
import com.payments.logic.OperationService;
import com.payments.web.command.AbstractCommand;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserCardBlockCommand extends AbstractCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {    
        CardService cardService = new CardService();
        cardService.block(request);
        
        OperationService operationService = new OperationService();
        operationService.saveCardBlockOperation(request, User.class);
        
        AbstractCommand command = new UserCardsListCommand();
        command.execute(request, response);
    }    
}
