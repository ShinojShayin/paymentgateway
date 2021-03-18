package com.paymentgateway.library.service;

import com.paymentgateway.library.exception.PaymentException;
import com.paymentgateway.library.vo.PaymentRequest;
import com.paymentgateway.library.vo.PaymentResponse;

public interface PaymentService {

    public PaymentResponse processThirdPartyPayment(PaymentRequest paymentRequest) throws PaymentException;
    public PaymentResponse processCreditCardPayment(PaymentRequest paymentRequest) throws PaymentException;

}
