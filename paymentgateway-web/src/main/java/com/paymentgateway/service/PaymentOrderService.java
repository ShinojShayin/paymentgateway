package com.paymentgateway.service;

import com.paymentgateway.dao.OrderDao;
import com.paymentgateway.dao.PaymentDao;
import com.paymentgateway.entity.OrderEntity;
import com.paymentgateway.entity.PaymentStatusEntity;
import com.paymentgateway.library.constant.PaymentMethod;
import com.paymentgateway.library.constant.PaymentProvider;
import com.paymentgateway.library.utility.PaymentRuleFactory;
import com.paymentgateway.library.vo.CreditCardVo;
import com.paymentgateway.library.vo.PaymentRequest;
import com.paymentgateway.library.vo.PaymentResponse;
import com.paymentgateway.vo.PaymentOrderVo;
import com.paymentgateway.vo.PaymentOrderResponseVo;
import com.paypal.api.payments.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentOrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private PaymentRuleFactory paymentFactory;

    private OrderEntity getOrderEntity(PaymentOrderVo paymentOrderVo){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCurrency(paymentOrderVo.getCurrency());
        orderEntity.setCustomername(paymentOrderVo.getCustomername());
        orderEntity.setPrice(paymentOrderVo.getPrice());
        return orderEntity;
    }

    private PaymentStatusEntity getPaymentStatusEntity( PaymentResponse response){
        PaymentStatusEntity paymentStatusEntity = new PaymentStatusEntity();

        paymentStatusEntity.setStatus(response.getTransactionStatus());
        paymentStatusEntity.setPaymentMethod(response.getPaymentMethod());
        paymentStatusEntity.setTransactionId(response.getTransactionId());
        paymentStatusEntity.setPaymentProvider(response.getPaymentProvider());

        return paymentStatusEntity;

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public PaymentOrderResponseVo processPaymentOrder(PaymentOrderVo paymentOrderVo) throws Exception {

        OrderEntity orderEntity = getOrderEntity(paymentOrderVo);
        orderDao.insertOrder(orderEntity);

        PaymentRequest paymentRequest = new PaymentRequest();
        CreditCardVo creditCard = new CreditCardVo( paymentOrderVo.getCardnumber(),  paymentOrderVo.getCardexpire(),
                paymentOrderVo.getCardcvv());
        paymentRequest.setCreditCardVo(creditCard);
        paymentRequest.setCurrency(paymentOrderVo.getCurrency());
        paymentRequest.setAmount(paymentOrderVo.getPrice());
        PaymentResponse paymentResponse = paymentFactory.processCreditCardPayment(paymentRequest);

        PaymentOrderResponseVo paymentOrderResponseVo = new PaymentOrderResponseVo();

        if(paymentResponse.isFailed()){
            paymentOrderResponseVo.setSuccess(false);
            paymentOrderResponseVo.setMessage(paymentResponse.getMessage());
        }else{
            if(PaymentMethod.THIRD_PARTY.getMethod().equals(paymentResponse.getPaymentMethod())){
                paymentOrderResponseVo.setRedirect(true);
                paymentOrderResponseVo.setSuccess(true);
                paymentOrderResponseVo.setRedirectUrl(paymentResponse.getRedirectUrl());
            }
            else {

                PaymentStatusEntity paymentStatusEntity = getPaymentStatusEntity(paymentResponse);
                paymentDao.insertPaymentStatus(paymentStatusEntity);
                paymentOrderResponseVo.setRedirect(false);
                paymentOrderResponseVo.setSuccess(true);
                paymentOrderResponseVo.setMessage(paymentResponse.getMessage());
            }
        }

        return paymentOrderResponseVo;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void logPaymentStatus(Payment payment) {
        PaymentStatusEntity paymentStatusEntity = new PaymentStatusEntity();
        paymentStatusEntity.setTransactionId(payment.getId());
        paymentStatusEntity.setPaypalPayerId(payment.getPayer().getPayerInfo().getPayerId());
        paymentStatusEntity.setStatus(payment.getState());
        paymentStatusEntity.setPaymentMethod(PaymentMethod.THIRD_PARTY.getMethod());
        paymentStatusEntity.setPaymentProvider(PaymentProvider.PAYPAL.getProvider());
        paymentDao.insertPaymentStatus(paymentStatusEntity);
    }

}
