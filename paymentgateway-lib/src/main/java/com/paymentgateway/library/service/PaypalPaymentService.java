package com.paymentgateway.library.service;

import com.paymentgateway.library.constant.PaymentMethod;
import com.paymentgateway.library.constant.PaymentProvider;
import com.paymentgateway.library.exception.PaymentException;
import com.paymentgateway.library.exception.PaymentMethodNotSupported;
import com.paymentgateway.library.vo.PaymentRequest;
import com.paymentgateway.library.vo.PaymentResponse;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("classpath:application.properties")
public class PaypalPaymentService implements PaymentService{

    @Value("${paypal.cancel.url}")
    private String cancelUrl;

    @Value("${paypal.success.url}")
    private String successUrl;

    @Autowired
    private APIContext apiContext;

    @Override
    public PaymentResponse processThirdPartyPayment(PaymentRequest paymentRequest) throws PaymentException{

        Amount amount = setAmount(paymentRequest.getAmount(), paymentRequest.getCurrency());

        Transaction transaction = new Transaction();
        transaction.setDescription("Processing order from payment gateway sample app");
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);
        try {
            payment = payment.create(apiContext);
            PaymentResponse paymentResponse = new PaymentResponse(PaymentProvider.PAYPAL, PaymentMethod.THIRD_PARTY);
            for(Links link: payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    paymentResponse.setRedirectUrl(link.getHref());
                    return paymentResponse;
                }
            }

        } catch (PayPalRESTException e) {
            throw new PaymentException(e.toString());
        }


        return null;
    }

    @Override
    public PaymentResponse processCreditCardPayment(PaymentRequest paymentRequest) throws PaymentException {
        throw new PaymentMethodNotSupported(PaymentProvider.PAYPAL, PaymentMethod.CREDIT_CARD);
    }

    private Amount setAmount(String amountVal, String currency){
        Double total = Double.parseDouble(amountVal);

        Amount amount = new Amount();
        amount.setCurrency(currency);
        total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
        amount.setTotal(String.format("%.2f", total));

        return amount;
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }
}
