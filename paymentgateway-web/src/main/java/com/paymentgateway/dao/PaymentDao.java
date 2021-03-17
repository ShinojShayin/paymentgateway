package com.paymentgateway.dao;

import com.paymentgateway.entity.OrderEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PaymentDao {

    @PersistenceContext
    private EntityManager entityManager;

    public OrderEntity createUser(OrderEntity orderEntity) {
        entityManager.persist(orderEntity);
        return orderEntity;
    }

}
