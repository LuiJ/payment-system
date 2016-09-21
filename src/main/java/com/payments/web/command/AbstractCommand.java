package com.payments.web.command;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public abstract class AbstractCommand implements Command {

    private final String VIEW_PREFIX = "/WEB-INF/views/";
    private final String VIEW_SUFFIX = ".jsp";
    
    @Override
    public abstract void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException;
    
    protected void render(HttpServletRequest request, HttpServletResponse response, String viewName) 
            throws ServletException, IOException
    {
        String view = VIEW_PREFIX + viewName + VIEW_SUFFIX;
        HttpSession session = request.getSession();
        ServletContext context = session.getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
    
}
