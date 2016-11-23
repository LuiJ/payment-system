package com.payments.web.servlet;

import com.payments.dao.AdminDAO;
import com.payments.dao.DAOFactory;
import com.payments.dao.UserDAO;
import com.payments.entity.AbstractUser;
import com.payments.exception.IncorrectPaymentDataException;
import com.payments.exception.PaymentAmountException;
import com.payments.exception.PaymentsException;
import com.payments.web.command.Command;
import com.payments.web.command.CommandFactory;
import com.payments.web.internationalization.InternationalizationService;
import com.payments.web.view.Attribute;
import com.payments.web.view.Renderer;
import com.payments.web.view.View;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class MainServlet extends HttpServlet {
    
    private static final String INCORRECT_DATA_ERROR_PROP_NAME = "error.incorrectPaymentData";
    private static final String INCORRECT_AMOUNT_ERROR_PROP_NAME = "error.notEnoughMoney";
    
    private final Renderer renderer;    
    
    public MainServlet(){
        renderer = new Renderer();
    }
    
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException 
    {    
        HttpSession session = request.getSession(true);   
        keepUserInSession(session);   
        
        InternationalizationService i18nService = new InternationalizationService();
        i18nService.setLocale(request, response);
        ResourceBundle resourceBundle = i18nService.getResourceBundle(session);
        
        try {
            Command command = CommandFactory.create(request);
            command.execute(request, response); 
        }
        catch (IncorrectPaymentDataException e){   
            String errorMessage = resourceBundle.getString(INCORRECT_DATA_ERROR_PROP_NAME);
            request.setAttribute(Attribute.ERROR_MESSAGE, errorMessage);
            View view = e.getViewToRender();
            renderer.render(request, response, view);            
        } 
        catch (PaymentAmountException e){
            String errorMessage = resourceBundle.getString(INCORRECT_AMOUNT_ERROR_PROP_NAME);
            request.setAttribute(Attribute.ERROR_MESSAGE, errorMessage);
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
        session.setAttribute(Attribute.LOGGED_USER, user);
    } 
}
