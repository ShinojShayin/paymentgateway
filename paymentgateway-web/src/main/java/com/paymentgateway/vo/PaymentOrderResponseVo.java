package com.paymentgateway.vo;

public class PaymentOrderResponseVo {

    private boolean isRedirect;
    private boolean isSuccess;
    private String redirectUrl;
    private String message;

    public boolean isRedirect() {
        return isRedirect;
    }

    public void setRedirect(boolean redirect) {
        isRedirect = redirect;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PaymentResponseVo{" +
                "isRedirect=" + isRedirect +
                ", isSuccess=" + isSuccess +
                ", redirectUrl='" + redirectUrl + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
