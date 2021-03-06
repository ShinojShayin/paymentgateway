package com.paymentgateway.dao;

import com.paymentgateway.entity.OrderEntity;
import com.paymentgateway.entity.PaymentStatusEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PaymentDao {

    @PersistenceContext
    private EntityManager entityManager;

    public PaymentStatusEntity insertPaymentStatus(PaymentStatusEntity paymentStatusEntity) {
        entityManager.persist(paymentStatusEntity);
        return paymentStatusEntity;
    }

}
