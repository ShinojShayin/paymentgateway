package com.paymentgateway.library.constant;

public enum PaymentProvider {
    PAYPAL("PAYPAL"),
    BRAINTREE("BRAINTREE");

    private final String provider;
    private PaymentProvider(String provider){
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }
}
