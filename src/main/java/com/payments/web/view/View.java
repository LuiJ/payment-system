package com.payments.web.view;


public enum View {
    
    HOME("home"),
    PAGE_NOT_FOUND("page_not_found"), 
    ACCESS_DENIED("denied"),
    
    ADMIN_LOGIN("admin_login"),
    USER_LOGIN("user_login"),
    ACCOUNTS("accounts");
    
    private final String viewName;
    
    View(String viewName){
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }    
}
