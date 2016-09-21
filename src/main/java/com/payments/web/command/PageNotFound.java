package com.payments.web.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PageNotFound extends AbstractCommand {
    
    private static final String VIEW = "page_not_found";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {
        render(request, response, VIEW);
    }
    
}
