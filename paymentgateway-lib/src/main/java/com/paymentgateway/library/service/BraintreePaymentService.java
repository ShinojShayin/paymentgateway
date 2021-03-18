package com.paymentgateway.library.service;

import com.braintreegateway.*;
import com.paymentgateway.library.constant.PaymentMethod;
import com.paymentgateway.library.constant.PaymentProvider;
import com.paymentgateway.library.exception.PaymentException;
import com.paymentgateway.library.exception.PaymentMethodNotSupported;
import com.paymentgateway.library.vo.PaymentRequest;
import com.paymentgateway.library.vo.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BraintreePaymentService implements PaymentService{

    @Autowired
    BraintreeGateway braintreeGateway;

    @Override
    public PaymentResponse processThirdPartyPayment(PaymentRequest paymentRequest) throws PaymentException {
        throw new PaymentMethodNotSupported(PaymentProvider.BRAINTREE, PaymentMethod.THIRD_PARTY);
    }

    @Override
    public PaymentResponse processCreditCardPayment(PaymentRequest paymentRequest) throws PaymentException {

        TransactionRequest request = new TransactionRequest()
                .amount(new BigDecimal(paymentRequest.getAmount()))
                .creditCard()
                .number(paymentRequest.getCreditCardVo().getCardnumber())
                .expirationDate(paymentRequest.getCreditCardVo().getExpiryDate())
                .cvv(paymentRequest.getCreditCardVo().getCvv())
                .done()
                .options()
                .submitForSettlement(true)
                .done();

        Result<Transaction> result = braintreeGateway.transaction().sale(request);
        PaymentResponse response = new PaymentResponse(PaymentProvider.BRAINTREE, PaymentMethod.CREDIT_CARD);

        if (result.isSuccess()) {
            Transaction transaction = result.getTarget();
            response.setTransactionId(transaction.getId());
            response.setTransactionStatus(transaction.getStatus().name());
            response.setFailed(false);
            System.out.println("Success!: " + transaction.getId());
        } else if (result.getTransaction() != null) {
            Transaction transaction = result.getTransaction();
            System.out.println("Error processing transaction:");
            System.out.println("  Status: " + transaction.getStatus());
            System.out.println("  Code: " + transaction.getProcessorResponseCode());
            System.out.println("  Text: " + transaction.getProcessorResponseText());
            response.setTransactionId("0");
            response.setTransactionStatus(transaction.getStatus().name());
            response.setFailed(true);
        } else {
            for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
                System.out.println("Attribute: " + error.getAttribute());
                System.out.println("  Code: " + error.getCode());
                System.out.println("  Message: " + error.getMessage());

                response.setTransactionId("0");
                response.setTransactionStatus(error.getMessage());
            }

            response.setFailed(true);
        }

        return response;
    }
}
