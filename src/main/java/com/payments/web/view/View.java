package com.payments.web.view;


public enum View {
    
    PAGE_NOT_FOUND("page_not_found"), 
    ADMIN_LOGIN("admin_login"),
    ACCOUNTS("accounts");
    
    private final String VIEW_PREFIX = "/WEB-INF/views/";
    private final String VIEW_SUFFIX = ".jsp";
    
    private final String viewName;
    
    View(String viewName){
        this.viewName = viewName;
    }
    
    public String getPageFullName(){
        String pageFullName = VIEW_PREFIX + viewName + VIEW_SUFFIX;
        return pageFullName;
    }   
}
