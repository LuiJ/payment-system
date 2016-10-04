package com.payments.web.command.user;

import com.payments.dao.AccountDAO;
import com.payments.dao.CardDAO;
import com.payments.dao.DAOFactory;
import com.payments.entity.account.Account;
import com.payments.entity.card.Card;
import com.payments.entity.user.User;
import com.payments.web.command.AbstractCommand;
import com.payments.web.view.Attribute;
import com.payments.web.view.View;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserAccountsListCommand extends AbstractCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {       
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Attribute.LOGGED_USER);
        int userId = user.getId();
        
        AccountDAO accountDAO = DAOFactory.INSTANCE.getAccountDAO();
        List<Account> accounts = accountDAO.getAllByUserId(userId);
                
        CardDAO cardDAO = DAOFactory.INSTANCE.getCardDAO();
        
        for (Account account : accounts){
            int accountId = account.getId();
            List<Card> cards = cardDAO.getAllByAccountId(accountId);
            account.setCards(cards);
        } 
        
        request.setAttribute(Attribute.ACCOUNTS, accounts);        
        render(request, response, View.ACCOUNTS);
    }
    
}