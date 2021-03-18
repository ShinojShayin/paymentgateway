package com.paymentgateway.library.utility;

import com.paymentgateway.library.exception.PaymentException;
import com.paymentgateway.library.service.BraintreePaymentService;
import com.paymentgateway.library.service.PaypalPaymentService;
import com.paymentgateway.library.vo.CreditCardVo;
import com.paymentgateway.library.vo.PaymentRequest;
import com.paymentgateway.library.vo.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class PaymentRuleFactory {

    @Autowired
    PaypalPaymentService paypalService;

    @Autowired
    BraintreePaymentService brainTreeService;

    public PaymentResponse processCreditCardPayment(PaymentRequest paymentRequest) throws PaymentException {
        PaymentResponse paymentResponse = null;

        if(isAmex.test(paymentRequest.getCreditCardVo())){

            if(!"USD".equals(paymentRequest.getCurrency())){
                paymentResponse = new PaymentResponse();
                paymentResponse.setFailed(true);
                paymentResponse.setMessage("AMEX is possible to use only for USD");
                return paymentResponse;
            }
            else{
                return paypalService.processThirdPartyPayment(paymentRequest);
            }

        }
        else if("USD".equals(paymentRequest.getCurrency()) || "EUR".equals(paymentRequest.getCurrency()) ||
                "AUD".equals(paymentRequest.getCurrency()) ){
            return paypalService.processThirdPartyPayment(paymentRequest);
        }
        else {
            return brainTreeService.processCreditCardPayment(paymentRequest);
        }

    }

    Predicate<CreditCardVo> isAmex = cc-> {
        String cardno = cc.getCardnumber();

        if(cardno.length()==15 && cardno.startsWith("3"))
            return true;

        return false;
    };


}
