package com.payments.web.command;

import javax.servlet.http.HttpServletRequest;


public class CommandFactory {
    
    private static final String HTTP_GET = "GET";
    private static final String HTTP_POST = "POST";
    
    private static final String ACCOUNTS = "accounts";

    
    public static Command create(HttpServletRequest request)
    {
        Command command = null;
        String[] urlParts = getUrlParts(request);
        
        if (urlParts.length == 0){
            return new PageNotFoundCommand();
        }   
        
        String httpMethod = request.getMethod().toUpperCase();
        
        switch (httpMethod){
            case HTTP_GET: 
                command = processGetRequest(urlParts);
                break;
            case HTTP_POST:
                command = processPostRequest(urlParts);
                break;
            default:
                throw new IllegalArgumentException(httpMethod+" method is not supported.");
        }        
        return command;
    }
    
    
    private static String[] getUrlParts(HttpServletRequest request){
        String urlRoot = request.getContextPath();
        String urlWithoutRoot = request.getRequestURI().split(urlRoot)[1];
        String[] urlParts = urlWithoutRoot.split("/"); 
        return urlParts;
    }    
    
    
    private static Command processGetRequest(String[] urlParts){
        Command command = null;
        String urlCommand = urlParts[1];
        switch (urlCommand){
            case ACCOUNTS:
                command = new AccountsListCommand();
                break;
            default: 
                command = new PageNotFoundCommand();
        }
        return command;
    }
    
    
    private static Command processPostRequest(String[] urlParts){
        Command command = null;
        String urlCommand = urlParts[1];
        switch (urlCommand){
            // In Development stage...
        }
        return command;
    }
    
}
