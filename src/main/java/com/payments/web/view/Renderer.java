package com.payments.web.view;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Renderer {
    
    private final String VIEW_PREFIX = "/WEB-INF/views/";
    private final String VIEW_SUFFIX = ".jsp";
    
    public void render(HttpServletRequest request, HttpServletResponse response, String viewName) 
            throws ServletException, IOException
    {
        String view = VIEW_PREFIX + viewName + VIEW_SUFFIX;
        HttpSession session = request.getSession();
        ServletContext context = session.getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
    
}
