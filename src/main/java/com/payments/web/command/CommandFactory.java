package com.payments.web.command;

import com.payments.web.command.admin.AdminLoginFailedCommand;
import com.payments.web.command.admin.AdminLoginPageCommand;
import com.payments.web.command.admin.AdminAccountsListCommand;
import com.payments.web.command.admin.AdminCardsActivateCommand;
import com.payments.web.command.admin.AdminCardsBlockCommand;
import com.payments.web.command.admin.AdminCardsListCommand;
import com.payments.web.command.admin.AdminOperationsListCommand;
import com.payments.web.command.user.UserAccountsListCommand;
import com.payments.web.command.user.UserCardsBlockCommand;
import com.payments.web.command.user.UserLoginPageCommand;
import com.payments.web.command.user.UserCardsListCommand;
import com.payments.web.command.user.UserLoginFailedCommand;
import com.payments.web.command.user.UserOperationsListCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.web.savedrequest.SavedRequest;


public class CommandFactory {
    
    private static final String HTTP_GET = "GET";
    private static final String HTTP_POST = "POST";
           
    private static final String ADMIN = "admin";    
    private static final String ADMIN_HOME = "/admin";
    private static final String ADMIN_ACCOUNTS = "/admin/accounts";
    private static final String ADMIN_CARDS = "/admin/cards";
    private static final String ADMIN_CARD_BLOCK = "/admin/card-block";
    private static final String ADMIN_CARD_ACTIVATE = "/admin/card-activate";
    private static final String ADMIN_OPERATIONS = "/admin/operations";    
       
    private static final String USER = "user";     
    private static final String USER_HOME = "/user";
    private static final String USER_ACCOUNTS = "/user/accounts";
    private static final String USER_CARDS = "/user/cards";
    private static final String USER_CARD_BLOCK = "/user/card-block";
    private static final String USER_OPERATIONS = "/user/operations";
        
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
            case ADMIN_HOME:
                command = new AdminAccountsListCommand();
                break;
            case ADMIN_ACCOUNTS:
                command = new AdminAccountsListCommand();
                break;
            case ADMIN_CARDS:
                command = new AdminCardsListCommand();
                break;
            case ADMIN_OPERATIONS:
                command = new AdminOperationsListCommand();
                break;
            case USER_HOME:
                command = new UserAccountsListCommand();
                break;
            case USER_ACCOUNTS:
                command = new UserAccountsListCommand();
                break;
            case USER_CARDS:
                command = new UserCardsListCommand();
                break;
            case USER_OPERATIONS:
                command = new UserOperationsListCommand();
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
            case ADMIN_CARD_BLOCK:
                command = new AdminCardsBlockCommand();
                break;                
            case ADMIN_CARD_ACTIVATE:
                command = new AdminCardsActivateCommand();
                break;
            case USER_CARD_BLOCK:
                command = new UserCardsBlockCommand();
                break;
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
        commandString = removeLastSlash(commandString);
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
    
    
    private static String removeLastSlash(String value) {
        String result = value;
        if (value != null && value.length() > 1 && value.charAt(value.length()-1) == '/') {
            result = value.substring(0, value.length()-1);
        }
        return result;
    }
    
}
