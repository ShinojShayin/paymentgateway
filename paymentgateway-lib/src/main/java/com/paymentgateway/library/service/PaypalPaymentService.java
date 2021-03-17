package com.paymentgateway.library.service;

import com.paymentgateway.library.constant.PaymentMethod;
import com.paymentgateway.library.constant.PaymentProvider;
import com.paymentgateway.library.exception.PaymentException;
import com.paymentgateway.library.exception.PaymentMethodNotSupported;
import com.paymentgateway.library.vo.PaymentRequest;
import com.paymentgateway.library.vo.PaymentResponse;
import com.paymentgateway.library.vo.ThirdPartyPaymentResponse;
import com.paypal.api.payments.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaypalPaymentService implements PaymentService{

    @Override
    public ThirdPartyPaymentResponse processThirdPartyPayment(PaymentRequest paymentRequest) throws PaymentException{

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
//        redirectUrls.setCancelUrl(cancelUrl);
//        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        return null;
    }

    @Override
    public PaymentResponse processCreditCardPayment() throws PaymentException {
        throw new PaymentMethodNotSupported(PaymentProvider.PAYPAL, PaymentMethod.CREDIT_CARD);
    }

    private Amount setAmount(String amountVal, String currency){
        Double total = Double.parseDouble(amountVal);

        Amount amount = new Amount();
        amount.setCurrency(currency);
        total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
        amount.setTotal(String.format("%.2f", total));
    }
}
