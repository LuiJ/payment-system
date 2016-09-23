package com.payments.exception;


public class PaymentsException extends Exception {
    
    private String pageToRender;

    public String getPageToRender() {
        return pageToRender;
    }

    public void setPageToRender(String pageToRender) {
        this.pageToRender = pageToRender;
    }
    
}
