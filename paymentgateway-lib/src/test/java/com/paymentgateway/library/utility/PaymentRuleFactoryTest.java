package com.paymentgateway.library.utility;

import com.paymentgateway.library.service.BraintreePaymentService;
import com.paymentgateway.library.service.PaypalPaymentService;
import com.paymentgateway.library.vo.CreditCardVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentRuleFactoryTest {

    @Autowired
    PaymentRuleFactory paymentRuleFactory;


    public void isAmex() throws Exception {

    }
}
