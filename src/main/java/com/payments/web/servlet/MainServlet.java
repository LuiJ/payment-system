package com.payments.web.servlet;

import com.payments.dao.AdminDAO;
import com.payments.dao.DAOFactory;
import com.payments.dao.UserDAO;
import com.payments.entity.AbstractUser;
import com.payments.web.command.Command;
import com.payments.web.command.CommandFactory;
import com.payments.web.view.Attribute;
import com.payments.web.view.Renderer;
import java.io.IOException;
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
    public void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException 
    {     
        keepUserInSession(request);
        
        Command command = CommandFactory.create(request);
        command.execute(request, response); 
    }
    
    
    private void keepUserInSession(HttpServletRequest request)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        
        HttpSession session = request.getSession(true);
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
