package com.payments.web.command;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher(view).forward(request, response);
    }
    
}
