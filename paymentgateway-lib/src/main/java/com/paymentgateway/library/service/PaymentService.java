package com.paymentgateway.library.service;

import com.paymentgateway.library.exception.PaymentException;
import com.paymentgateway.library.vo.PaymentResponse;
import com.paymentgateway.library.vo.ThirdPartyPaymentResponse;

public interface PaymentService {

    public ThirdPartyPaymentResponse processThirdPartyPayment() throws PaymentException;
    public PaymentResponse processCreditCardPayment() throws PaymentException;

}
