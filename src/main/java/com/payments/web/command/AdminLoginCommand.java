package com.payments.web.command;

import com.payments.checker.PasswordChecker;
import com.payments.dao.AdminDAO;
import com.payments.dao.DAOFactory;
import com.payments.entity.admin.Admin;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AdminLoginCommand extends AbstractCommand {
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {
        
        AdminDAO adminDAO = DAOFactory.INSTANCE.getAdminDAO();
        
        String login = request.getParameter(Admin.FIELD_LOGIN);
        Admin admin = adminDAO.getByLogin(login);
        
        if (admin != null){            
            String password = request.getParameter(Admin.FIELD_PASSWORD);
            boolean correct = PasswordChecker.check(admin, password);
            if (correct){
                response.sendRedirect("accounts");
            }
            else {
                String errorMessage = "Incorrect password";
                sendError(request, response, errorMessage);
            }
        }
        else {            
            String errorMessage = "Incorrect login";
            sendError(request, response, errorMessage);
        }
    }   
    
    
    private void sendError(HttpServletRequest request, HttpServletResponse response, String message) 
            throws IOException
    {
        HttpSession session = request.getSession(false); 
        String login = request.getParameter(Admin.FIELD_LOGIN);
        session.setAttribute(Admin.FIELD_LOGIN, login);
        session.setAttribute(ERROR_MESSAGE, message);
        response.sendRedirect("admin");
    }
}
