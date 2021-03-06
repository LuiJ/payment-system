package com.payments.web.command.user;

import com.payments.dao.CardDAO;
import com.payments.dao.DAOFactory;
import com.payments.entity.Card;
import com.payments.entity.User;
import com.payments.web.command.AbstractServletCommand;
import com.payments.web.view.Attribute;
import com.payments.web.view.View;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserPaymentPageCommand extends AbstractServletCommand {
    
    private static final String PAGE = "payment";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {    
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Attribute.LOGGED_USER.getName());
        int userId = user.getId();
                
        CardDAO cardDAO = DAOFactory.INSTANCE.getCardDAO();        
        List<Card> cards = cardDAO.getAllActiveByUserId(userId); 
        
        request.setAttribute(Attribute.PAGE.getName(), PAGE);        
        request.setAttribute(Attribute.CARDS.getName(), cards);
        render(request, response, View.USER_PAYMENT);
    }    
}
