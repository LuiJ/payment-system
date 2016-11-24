package com.payments.web.command.user;

import com.payments.web.command.AbstractServletCommand;
import com.payments.web.view.View;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserLoginPageCommand extends AbstractServletCommand {
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {    
        render(request, response, View.USER_LOGIN);
    }    
}
