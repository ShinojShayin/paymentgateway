package com.paymentgateway.library.service;

import com.paymentgateway.library.constant.PaymentMethod;
import com.paymentgateway.library.constant.PaymentProvider;
import com.paymentgateway.library.exception.PaymentException;
import com.paymentgateway.library.exception.PaymentMethodNotSupported;
import com.paymentgateway.library.vo.PaymentResponse;
import com.paymentgateway.library.vo.ThirdPartyPaymentResponse;
import org.springframework.stereotype.Service;

@Service
public class BraintreePaymentService implements PaymentService{
    @Override
    public ThirdPartyPaymentResponse processThirdPartyPayment() throws PaymentException {
        throw new PaymentMethodNotSupported(PaymentProvider.BRAINTREE, PaymentMethod.THIRD_PARTY);
    }

    @Override
    public PaymentResponse processCreditCardPayment() throws PaymentException {
        return null;
    }
}
