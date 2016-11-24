package com.payments.web.internationalization;

import com.payments.web.command.RequestParameter;
import com.payments.web.view.Attribute;
import java.io.IOException;
import java.util.Locale;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class InternationalizationFilter implements Filter {
            
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
                         throws IOException, ServletException 
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String localeRequestParameter = httpRequest.getParameter(RequestParameter.LOCALE.getParameter());
        HttpSession session = httpRequest.getSession(); 
        
        session.setAttribute(Attribute.BASE_NAME.getName(), 
                InternationalizationHelper.BUNDLE_BASE_NAME);
        
        if (localeRequestParameter != null)
        {
            Locale newLocale = new Locale(localeRequestParameter);
            session.setAttribute(Attribute.LOCALE.getName(), newLocale);
            
            // Redirect is used to remove GET params from URL and address line
            String uri = httpRequest.getRequestURI();
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect(uri);
        }   
        else {
            Object localeSessionAttribute = session.getAttribute(Attribute.LOCALE.getName());
            if (localeSessionAttribute == null){
                session.setAttribute(Attribute.LOCALE.getName(), 
                        InternationalizationHelper.DEFAULT_LOCALE);
            }
            chain.doFilter(request, response);
        }        
    }   
    
    
    @Override
    public void destroy(){}    
    
}