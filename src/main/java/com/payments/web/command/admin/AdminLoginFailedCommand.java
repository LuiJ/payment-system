package com.payments.web.command.admin;

import com.payments.web.command.AbstractCommand;
import com.payments.web.view.Attribute;
import com.payments.web.view.View;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminLoginFailedCommand extends AbstractCommand {
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {
        request.setAttribute(Attribute.ERROR_MESSAGE, "Incorrect login or password.");
        render(request, response, View.ADMIN_LOGIN);
    }
    
}
