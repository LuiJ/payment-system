package com.payments.exception;

import com.payments.web.view.View;


public class PaymentAmountException extends PaymentsException {
    
    public PaymentAmountException(View view){
        super(view);
    }    
}
