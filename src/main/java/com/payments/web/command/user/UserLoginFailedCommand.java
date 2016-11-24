package com.payments.web.command.user;

import com.payments.web.command.AbstractServletCommand;
import com.payments.web.internationalization.TextPropertiesEnum;
import com.payments.web.view.Attribute;
import com.payments.web.view.View;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserLoginFailedCommand extends AbstractServletCommand {
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {
        String errorMessage = getLocalizedText(TextPropertiesEnum.LOGIN_ERROR);        
        request.setAttribute(Attribute.ERROR_MESSAGE.getName(), errorMessage);
        
        render(request, response, View.USER_LOGIN);
    }
    
}
