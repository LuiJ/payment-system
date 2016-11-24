package com.payments.web.servlet;

import com.payments.dao.AdminDAO;
import com.payments.dao.DAOFactory;
import com.payments.dao.UserDAO;
import com.payments.entity.AbstractUser;
import com.payments.exception.IncorrectPaymentDataException;
import com.payments.exception.PaymentAmountException;
import com.payments.exception.PaymentsException;
import com.payments.web.command.AbstractServletCommand;
import com.payments.web.command.CommandFactory;
import com.payments.web.internationalization.InternationalizationHelper;
import com.payments.web.internationalization.TextPropertiesEnum;
import com.payments.web.view.Attribute;
import com.payments.web.view.Renderer;
import com.payments.web.view.View;
import java.io.IOException;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class MainServlet extends HttpServlet {
    
    private final Renderer renderer;    
    
    public MainServlet(){
        renderer = new Renderer();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException 
    {    
        handleRequest(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException 
    {    
        handleRequest(request, response);
    }
    
    
    private void handleRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException 
    {    
        HttpSession session = request.getSession();   
        keepUserInSession(session);   
        
        Locale locale = (Locale) session.getAttribute(Attribute.LOCALE.getName());
        InternationalizationHelper i18nHelper = new InternationalizationHelper(locale);
        
        try {
            AbstractServletCommand command = CommandFactory.create(request);
            command.setInternationalizationHelper(i18nHelper);
            command.execute(request, response); 
        }
        catch (IncorrectPaymentDataException e){   
            String errorMessage = i18nHelper
                    .getTextByTextProperty(TextPropertiesEnum.INCORRECT_PAYMENT_DATA_ERROR);
            request.setAttribute(Attribute.ERROR_MESSAGE.getName(), errorMessage);
            View view = e.getViewToRender();
            renderer.render(request, response, view);            
        } 
        catch (PaymentAmountException e){
            String errorMessage = i18nHelper
                    .getTextByTextProperty(TextPropertiesEnum.INCORRECT_AMOUNT_ERROR);
            request.setAttribute(Attribute.ERROR_MESSAGE.getName(), errorMessage);
            View view = e.getViewToRender();
            renderer.render(request, response, view);            
        } 
        catch (PaymentsException e){
            throw new IllegalStateException();
        }
    }
    
    
    private void keepUserInSession(HttpSession session)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();        
        
        AbstractUser user = null;
        
        UserDAO userDAO = DAOFactory.INSTANCE.getUserDAO();
        user = userDAO.getByLogin(userName);
        
        if (user == null){
            AdminDAO adminDAO = DAOFactory.INSTANCE.getAdminDAO();
            user = adminDAO.getByLogin(userName);
        }        
        session.setAttribute(Attribute.LOGGED_USER.getName(), user);
    } 
}
