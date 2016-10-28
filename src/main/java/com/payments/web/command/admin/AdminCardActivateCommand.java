package com.payments.web.command.admin;

import com.payments.logic.CardService;
import com.payments.logic.OperationService;
import com.payments.web.command.AbstractCommand;
import com.payments.web.command.RequestParameter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminCardActivateCommand extends AbstractCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {    
        String cardIdParameter = request.getParameter(RequestParameter.CARD_ID.getParameter());
        int cardId = Integer.parseInt(cardIdParameter);
        
        CardService cardService = new CardService();
        cardService.activate(cardId);
        
        OperationService operationService = new OperationService();
        operationService.saveCardActivateOperation(cardId);
        
        AbstractCommand command = new AdminCardsListCommand();
        command.execute(request, response);
    }    
}
