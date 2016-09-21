package com.payments.web.command;

import com.payments.dao.AccountDAO;
import com.payments.dao.CardDAO;
import com.payments.dao.DAOFactory;
import com.payments.entity.account.Account;
import com.payments.entity.card.Card;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetAccounts extends AbstractCommand {
    
    private static final String VIEW = "accounts";

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
        request.setAttribute("accounts", accounts);        
        render(request, response, VIEW);
    }
    
}
