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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminAccountsListCommand extends AbstractCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {
        AccountDAO accountDAO = DAOFactory.INSTANCE.getAccountDAO();
        CardDAO cardDAO = DAOFactory.INSTANCE.getCardDAO();
        List<Account> accounts = accountDAO.getAll();
        
        for (Account account : accounts){
            int accountId = account.getId();
            List<Card> cards = cardDAO.getAllByAccountId(accountId);
            account.setCards(cards);
        }        
        request.setAttribute(Attribute.ACCOUNTS, accounts);        
        render(request, response, View.ACCOUNTS);
    }
    
}
