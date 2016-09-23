package com.payments.exception;

import com.payments.web.view.View;


public class AccountNotFoundException extends PaymentsException {
    
    public AccountNotFoundException(View view){
        super(view);
    }    
}
