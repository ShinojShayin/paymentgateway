package com.paymentgateway.vo;

public class PaymentResponseVo {

    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PaymentResponseVo{" +
                "message='" + message + '\'' +
                '}';
    }
}
