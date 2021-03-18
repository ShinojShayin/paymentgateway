package com.paymentgateway.controller;

import com.paymentgateway.library.service.PaypalPaymentService;
import com.paymentgateway.service.PaymentOrderService;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentStatusController {

    @Autowired
    PaypalPaymentService paypalPaymentService;

    @Autowired
    private PaymentOrderService paymentOrderService;

    @GetMapping(value = "/payment/failed")
    public String cancelPay() {
        return "failed";
    }

    @GetMapping(value = "/payment/paypal/success")
    public String paypalSuccessPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalPaymentService.executePayment(paymentId, payerId);

            paymentOrderService.logPaymentStatus(payment);

            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/payment/failed";
    }

    @GetMapping(value = "/payment/success")
    public String successPay() {
        return "success";
    }

}
