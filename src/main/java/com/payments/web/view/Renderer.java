package com.payments.web.view;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Renderer {
    
    public void render(HttpServletRequest request, HttpServletResponse response, View view) 
            throws ServletException, IOException
    {
        String pageFullName = view.getPageFullName();
        HttpSession session = request.getSession();
        ServletContext context = session.getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(pageFullName);
        dispatcher.forward(request, response);
    }    
}
