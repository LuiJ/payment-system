package com.payments.exception;

import com.payments.web.view.View;


public class AuthentificationException extends PaymentsException {
    
    public AuthentificationException(View view){
        super(view);
    }    
}
