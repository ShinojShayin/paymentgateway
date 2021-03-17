package com.paymentgateway.vo;

public class PaymentOrderVo {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PaymentOrderVo{" +
                "id='" + id + '\'' +
                '}';
    }
}
