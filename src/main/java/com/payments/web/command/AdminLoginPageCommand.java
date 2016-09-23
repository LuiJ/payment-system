package com.payments.web.command;

import com.payments.exception.PaymentsException;
import com.payments.web.view.View;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminLoginPageCommand extends AbstractCommand {
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, PaymentsException
    {    
        render(request, response, View.ADMIN_LOGIN);
    }    
}
