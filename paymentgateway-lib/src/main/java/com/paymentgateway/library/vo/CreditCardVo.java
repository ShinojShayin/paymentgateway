package com.paymentgateway.library.vo;

public class CreditCardVo {

    private String cardnumber;
    private String expiryDate;
    private String cvv;

    public CreditCardVo(String cardnumber, String expiryDate, String cvv) {
        this.cardnumber = cardnumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
