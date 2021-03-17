package com.paymentgateway.library.exception;

import com.paymentgateway.library.constant.PaymentMethod;
import com.paymentgateway.library.constant.PaymentProvider;

public class PaymentMethodNotSupported extends PaymentException {

    private PaymentMethodNotSupported(){
        super();
    }

    public PaymentMethodNotSupported(PaymentProvider provider, PaymentMethod method){
        super("Payment Method type: "+method.getMethod()+" not supported for provider: "+provider.getProvider());
    }
}
