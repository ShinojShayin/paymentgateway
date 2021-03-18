package com.paymentgateway.library.vo;

public class PaymentRequest {

    private String amount;
    private String currency;
    private CreditCardVo creditCardVo;

    public CreditCardVo getCreditCardVo() {
        return creditCardVo;
    }

    public void setCreditCardVo(CreditCardVo creditCardVo) {
        this.creditCardVo = creditCardVo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
