package com.payments.exception;

import com.payments.web.view.View;


public class PaymentsException extends Exception {
    
    private final View viewToRender;
    
    public PaymentsException(View view){
        viewToRender = view;
    }

    public View getViewToRender() {
        return viewToRender;
    }    
}
