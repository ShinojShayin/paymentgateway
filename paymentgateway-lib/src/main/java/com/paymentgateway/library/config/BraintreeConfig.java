package com.paymentgateway.library.config;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BraintreeConfig {

    @Value("${braintree.merchant.id}")
    private String merchantId;

    @Value("${braintree.public.key}")
    private String publicKey;

    @Value("${braintree.private.key}")
    private String privateKey;

    @Bean
    public BraintreeGateway getBraintreeGateway() throws PayPalRESTException {
        BraintreeGateway braintreeGateway = new BraintreeGateway(
                Environment.SANDBOX,
                merchantId,
                publicKey,
                privateKey
        );

        return braintreeGateway;
    }
}
