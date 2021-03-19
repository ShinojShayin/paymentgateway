# Payment Gateway Example Demo
Payment Gateway Example is webapp which demonstrate or implement the payment gateway libraries like Braintree and Paypal Java SDK.

Paypal and Braintree sdk has been implmented with sandbox configuration so that we can can basically test the posibilities using these API's


Inorder to run this project we need PostgreSQL(https://www.postgresql.org/download/) has db installed in the system has this system will be saving the state once order is initiated and payment status is received after submitting
the payment request.

```sh
#go to folder location paymentgateway, run below comman
mvn clean install
```
Run the spring boot application by going to PaymentgatewayApplication.java in paymentgateway-web module

view in the browser at http://localhost:8000.
