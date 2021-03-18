package com.paymentgateway.controller;

import com.paymentgateway.service.PaymentOrderService;
import com.paymentgateway.vo.PaymentOrderVo;
import com.paymentgateway.vo.PaymentOrderResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaymentOrderController {

    @Autowired
    private PaymentOrderService paymentOrderService;

    @RequestMapping(method = RequestMethod.GET, path = "/payment/process", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    ResponseEntity<PaymentOrderResponseVo>  getPaymentOrderRequest(PaymentOrderVo paymentOrderVo) throws Exception {
        System.out.println(paymentOrderVo.toString());
        PaymentOrderResponseVo responseVo = paymentOrderService.processPaymentOrder(paymentOrderVo);

        return new ResponseEntity<PaymentOrderResponseVo>(responseVo, HttpStatus.OK);
    }


}
