package com.payments.web.command.admin;

import com.payments.dao.AccountDAO;
import com.payments.dao.CardDAO;
import com.payments.dao.DAOFactory;
import com.payments.entity.Account;
import com.payments.entity.Card;
import com.payments.web.command.AbstractCommand;
import com.payments.web.view.Attribute;
import com.payments.web.view.View;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminCardsListCommand extends AbstractCommand {
    
    private static final String PAGE = "cards";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {       
        AccountDAO accountDAO = DAOFactory.INSTANCE.getAccountDAO();
        List<Account> accounts = accountDAO.getAll();
                
        CardDAO cardDAO = DAOFactory.INSTANCE.getCardDAO();
        
        List<Card> cards = new ArrayList<>();
        
        for (Account account : accounts){
            int accountId = account.getId();
            List<Card> cardsInAccoutn = cardDAO.getAllByAccountId(accountId);
            cards.addAll(cardsInAccoutn);
        } 
        
        request.setAttribute(Attribute.PAGE, PAGE);
        request.setAttribute(Attribute.CARDS, cards);        
        render(request, response, View.ADMIN_CARDS);
    }    
}
