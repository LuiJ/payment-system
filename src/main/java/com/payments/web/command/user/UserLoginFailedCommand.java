package com.payments.web.command.user;

import com.payments.web.command.AbstractCommand;
import com.payments.web.internationalization.InternationalizationHelper;
import com.payments.web.internationalization.TextPropertiesEnum;
import com.payments.web.view.Attribute;
import com.payments.web.view.View;
import java.io.IOException;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserLoginFailedCommand extends AbstractCommand {
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Attribute.LOCALE.getName());
        InternationalizationHelper i18nHelper = new InternationalizationHelper(locale);
        String errorMessage = i18nHelper.getTextByTextProperty(TextPropertiesEnum.LOGIN_ERROR);
        
        request.setAttribute(Attribute.ERROR_MESSAGE.getName(), errorMessage);
        render(request, response, View.USER_LOGIN);
    }
    
}
