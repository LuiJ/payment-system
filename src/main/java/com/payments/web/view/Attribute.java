package com.payments.web.view;


public enum Attribute {
        
    SUCCESS_MESSAGE("successMessage"),
    ERROR_MESSAGE("errorMessage"),
    LOGGED_USER("loggedUser"),
    PAGE("page"),
    ACCOUNTS("accounts"),
    CARDS("cards"),
    OPERATIONS("operations"),
    LOCALE("locale"),
    BASE_NAME("baseName");
    
    private String name;
    
    Attribute(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
}
