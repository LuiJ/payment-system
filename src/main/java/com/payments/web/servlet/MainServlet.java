package com.payments.web.servlet;

import com.payments.web.command.Command;
import com.payments.web.command.CommandFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MainServlet extends HttpServlet {
    
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException 
    {        
        Command command = CommandFactory.create(request);
        command.execute(request, response);       
    }
    
}
