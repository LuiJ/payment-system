package com.payments.web.command;

import javax.servlet.http.HttpServletRequest;


public class CommandFactory {
    
    private static final String HTTP_GET = "GET";
    private static final String HTTP_POST = "POST";
    
    private static final String ACCOUNTS = "accounts";

    public static Command build(HttpServletRequest request)
    {
        Command command = null;
        String[] urlParts = parseUrl(request);
        
        if (urlParts.length > 0){            
            String urlCommand = urlParts[1];            
            String httpMethod = request.getMethod();
            
            if (urlCommand.equalsIgnoreCase(ACCOUNTS) && httpMethod.equalsIgnoreCase(HTTP_GET)){
                command = new GetAccounts();
            }        
            else {
                command = new PageNotFound();
            }
        }       
        else {
            command = new PageNotFound();
        }        
        return command;
    }
    
    
    private static String[] parseUrl(HttpServletRequest request){
        String root = request.getContextPath();
        String path = request.getRequestURI().split(root)[1];
        String[] pathParts = path.split("/"); 
        return pathParts;
    }
    
}
