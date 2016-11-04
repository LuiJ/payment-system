package com.payments.exception;

import com.payments.web.view.View;


public class IncorrectPaymentDataException extends PaymentsException {
    
    public IncorrectPaymentDataException(View view){
        super(view);
    }    
}
