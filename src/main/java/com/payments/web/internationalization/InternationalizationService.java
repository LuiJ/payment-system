package com.payments.web.internationalization;

import com.payments.web.command.RequestParameter;
import com.payments.web.view.Attribute;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class InternationalizationService {   
    
    private static final Locale DEFAULT_LOCALE = new Locale("en");
    private static final String BUNDLE_BASE_NAME = "i18n";

    public InternationalizationService(){}
    
    
    public void setLocale(HttpServletRequest request, HttpServletResponse response)
                          throws IOException
    {
        String localeRequestParameter = request.getParameter(RequestParameter.LOCALE.getParameter());
        HttpSession session = request.getSession(); 
        
        session.setAttribute(Attribute.BASE_NAME, BUNDLE_BASE_NAME);
        
        if (localeRequestParameter != null && 
            !localeRequestParameter.equals(""))
        {
            Locale newLocale = new Locale(localeRequestParameter);
            session.setAttribute(Attribute.LOCALE, newLocale);
            String uri = request.getRequestURI();
            response.sendRedirect(uri);
        }   
        else {
            Object localeSessionAttribute = session.getAttribute(Attribute.LOCALE);
            if (localeSessionAttribute == null){
                session.setAttribute(Attribute.LOCALE, DEFAULT_LOCALE);
            }
        }
    }
    
    
    public ResourceBundle getResourceBundle(HttpSession session)
    {
        ResourceBundle resourceBundle = null; 
        Object localeSessionAttribute = session.getAttribute(Attribute.LOCALE);
        if (localeSessionAttribute != null){
            Locale locale = (Locale) localeSessionAttribute;
            resourceBundle = ResourceBundle.getBundle(BUNDLE_BASE_NAME, locale);
        }
        else {
            resourceBundle = ResourceBundle.getBundle(BUNDLE_BASE_NAME, DEFAULT_LOCALE);
        }
        return resourceBundle;
    }
}
