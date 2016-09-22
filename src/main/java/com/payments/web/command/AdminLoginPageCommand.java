package com.payments.web.command;

import com.payments.entity.admin.Admin;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AdminLoginPageCommand extends AbstractCommand {

    private static final String VIEW = "admin_login";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {
        HttpSession session = request.getSession(true);
        
        Object errorMessageObj = session.getAttribute(ERROR_MESSAGE);
        if (errorMessageObj != null){
            String errorMessage = (String) errorMessageObj;
            session.removeAttribute(ERROR_MESSAGE);
            request.setAttribute(ERROR_MESSAGE, errorMessage);
        }        
        
        Object loginObj = session.getAttribute(Admin.FIELD_LOGIN);
        if (loginObj != null){
            String login = (String) loginObj;
            session.removeAttribute(Admin.FIELD_LOGIN);
            request.setAttribute(Admin.FIELD_LOGIN, login);
        }        
        
        render(request, response, VIEW);
    }
    
}
