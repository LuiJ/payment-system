package com.payments.web.command;

import com.payments.web.view.View;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PageNotFoundCommand extends AbstractServletCommand {
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {
        render(request, response, View.PAGE_NOT_FOUND);
    }
    
}
