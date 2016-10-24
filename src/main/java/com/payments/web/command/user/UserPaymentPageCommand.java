package com.payments.web.command.user;

import com.payments.dao.AccountDAO;
import com.payments.dao.CardDAO;
import com.payments.dao.DAOFactory;
import com.payments.entity.Account;
import com.payments.entity.Card;
import com.payments.entity.User;
import com.payments.web.command.AbstractCommand;
import com.payments.web.view.Attribute;
import com.payments.web.view.View;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserPaymentPageCommand extends AbstractCommand {
    
    private static final String PAGE = "payment";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {    
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Attribute.LOGGED_USER);
        int userId = user.getId();
        
        AccountDAO accountDAO = DAOFactory.INSTANCE.getAccountDAO();
        List<Account> accounts = accountDAO.getActiveAccountsByUserId(userId);
                
        CardDAO cardDAO = DAOFactory.INSTANCE.getCardDAO();
        
        List<Card> cards = new ArrayList<>();
        
        for (Account account : accounts){
            int accountId = account.getId();
            List<Card> cardsInAccoutn = cardDAO.getAllByAccountId(accountId);
            cards.addAll(cardsInAccoutn);
        } 
        
        request.setAttribute(Attribute.PAGE, PAGE);        
        request.setAttribute(Attribute.CARDS, cards);
        render(request, response, View.USER_PAYMENT);
    }    
}
