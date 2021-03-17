package com.paymentgateway.library.constant;

public enum PaymentMethod {
    THIRD_PARTY("THIRD_PARTY"),
    CREDIT_CARD("CREDIT_CARD");

    private final String method;
    private PaymentMethod(String method){
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}
