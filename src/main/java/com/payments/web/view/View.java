package com.payments.web.view;


public enum View {
    
    HOME("home"),
    PAGE_NOT_FOUND("page_not_found"), 
    ACCESS_DENIED("denied"),
    
    ADMIN_LOGIN("admin_login"),
    ADMIN_ACCOUNTS("admin_accounts"), 
    ADMIN_CARDS("admin_cards"),
    ADMIN_OPERATIONS("admin_operations"),
    
    USER_LOGIN("user_login"),
    USER_ACCOUNTS("user_accounts"),
    USER_CARDS("user_cards"),
    USER_OPERATIONS("user_operations");
        
    private final String viewName;
    
    View(String viewName){
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }    
}
