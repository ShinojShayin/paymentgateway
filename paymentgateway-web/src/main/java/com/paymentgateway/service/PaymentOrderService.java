package com.paymentgateway.service;

import com.paymentgateway.dao.OrderDao;
import com.paymentgateway.entity.OrderEntity;
import com.paymentgateway.library.service.PaypalPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentOrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PaypalPaymentService paypalPaymentService;

    @Transactional(propagation = Propagation.REQUIRED)
    public void createOrder() throws Exception {

        paypalPaymentService.processCreditCardPayment();

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setName("check");
        orderDao.createOrder(orderEntity);
    }

}
