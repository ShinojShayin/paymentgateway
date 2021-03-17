package com.paymentgateway.controller;

import com.paymentgateway.service.PaymentOrderService;
import com.paymentgateway.vo.PaymentOrderVo;
import com.paymentgateway.vo.PaymentResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentOrderController {

    @Autowired
    private PaymentOrderService paymentOrderService;

    @RequestMapping(method = RequestMethod.GET, path = "/process", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PaymentResponseVo>  getPaymentOrderRequest(PaymentOrderVo paymentOrderVo) throws Exception {
        PaymentResponseVo responseVo = new PaymentResponseVo();


        System.out.println(paymentOrderVo.toString());
        paymentOrderService.createOrder();
        responseVo.setMessage("success "+paymentOrderVo.getId());
        return new ResponseEntity<PaymentResponseVo>(responseVo, HttpStatus.OK);
    }
}
