package com.payments.web.command;

import com.payments.web.command.admin.AdminLoginFailedCommand;
import com.payments.web.command.user.UserAccountsListCommand;
import com.payments.web.command.user.UserLoginPageCommand;
import com.payments.web.command.admin.AdminLoginPageCommand;
import com.payments.web.command.admin.AdminAccountsListCommand;
import com.payments.web.command.user.UserLoginFailedCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.web.savedrequest.SavedRequest;


public class CommandFactory {
    
    private static final String HTTP_GET = "GET";
    private static final String HTTP_POST = "POST";
           
    private static final String ADMIN = "admin";
    private static final String ADMIN_ACCOUNTS = "/admin/accounts";
       
    private static final String USER = "user"; 
    private static final String USER_ACCOUNTS = "/user/accounts";
        
    private static final String HOME = "/";
    private static final String LOGIN = "/login";
    private static final String LOGIN_FAILED = "/login-failed";
    private static final String ACCESS_DENIED = "/denied";
    
    
    public static Command create(HttpServletRequest request)
    {
        Command command = null;                  
        String httpMethod = request.getMethod().toUpperCase();
        
        switch (httpMethod){
            case HTTP_GET: 
                command = processGetRequest(request);
                break;
            case HTTP_POST:
                command = processPostRequest(request);
                break;
            default:
                throw new IllegalArgumentException(httpMethod+" method is not supported.");
        }        
        return command;
    }    
    
    
    private static Command processGetRequest(HttpServletRequest request)
    {
        Command command = null;
        String requestCommand = getRequestCommand(request);
        
        switch (requestCommand){
            case HOME:
                command = new HomeCommand();
                break;
            case ACCESS_DENIED:
                command = new AccessDeniedCommand();
                break;
            case LOGIN:
                command = getLoginPageCommand(request);
                break;
            case LOGIN_FAILED:
                command = getLoginFailedCommand(request);
                break;
            case ADMIN_ACCOUNTS:
                command = new AdminAccountsListCommand();
                break;
            case USER_ACCOUNTS:
                command = new UserAccountsListCommand();
                break;
            default: 
                command = new PageNotFoundCommand();
        }
        return command;
    }
    
    
    private static Command processPostRequest(HttpServletRequest request)
    {
        Command command = null;
        String requestCommand = getRequestCommand(request);
        
        switch (requestCommand){
            default: 
                command = new PageNotFoundCommand();
        }
        return command;
    }
    
    
    private static Command getLoginPageCommand(HttpServletRequest request)
    {    
        Command command = null;
        String userType = getUserType(request);
        
        switch (userType){
            case USER:
                command = new UserLoginPageCommand();   
                break;
            case ADMIN: 
                command = new AdminLoginPageCommand();
                break;
            default:
                command = new PageNotFoundCommand();
        }   
        return command;
    }   
    
    
    private static Command getLoginFailedCommand(HttpServletRequest request)
    {    
        Command command = null;
        String userType = getUserType(request);
        
        switch (userType){
            case USER:
                command = new UserLoginFailedCommand();   
                break;
            case ADMIN: 
                command = new AdminLoginFailedCommand();
                break;
            default:
                command = new PageNotFoundCommand();
        }   
        return command;
    } 
    
    
    private static String getRequestCommand(HttpServletRequest request){
        String urlRoot = request.getContextPath();
        String uri = request.getRequestURI();
        String commandString = uri.split(urlRoot)[1];
        return commandString;
    }
    
    
    private static String getUserType(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        SavedRequest savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        String requestedUrl = savedRequest.getRedirectUrl();
        String urlRoot = request.getContextPath();        
        String savedRequestCommand = requestedUrl.split(urlRoot)[1];
        String userType = savedRequestCommand.split("/")[1];
        return userType;
    }
    
}
