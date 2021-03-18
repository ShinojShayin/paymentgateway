package com.paymentgateway.vo;

public class PaymentOrderVo {

    private String customername;
    private String price;
    private String currency;
    private String cardholdername;
    private String cardnumber;
    private String cardexpire;
    private String cardcvv;

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCardholdername() {
        return cardholdername;
    }

    public void setCardholdername(String cardholdername) {
        this.cardholdername = cardholdername;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getCardexpire() {
        return cardexpire;
    }

    public void setCardexpire(String cardexpire) {
        this.cardexpire = cardexpire;
    }

    public String getCardcvv() {
        return cardcvv;
    }

    public void setCardcvv(String cardcvv) {
        this.cardcvv = cardcvv;
    }
}
