# Payment Gateway Example Demo
Payment Gateway Example is webapp which demonstrate the payment gateway libraries like Braintree and Paypal Java SDK.

Paypal and Braintree sdk has been implmented with sandbox configuration so that we can can basically test the posibilities using these API's


## How to run

Inorder to run this project we need PostgreSQL(https://www.postgresql.org/download/) has db installed in the system has this system will be saving the state once order is initiated and payment status is received after submitting
the payment request.

```sh
#go to folder location paymentgateway, run below command
mvn clean install
```
Run the spring boot application by going to PaymentgatewayApplication.java in paymentgateway-web module

view in the browser at http://localhost:8080


## Sample Data
Sample paypal user for this activity
username: sb-hdl2475409648@personal.example.com
password: 12345678


## Requirment | Restriction set while doing this assignment
Create a payment gateway library, that can handle payments with:
● Paypal REST API
● Braintree payments
NOTE on Braintree: In official [spec]
(https://developers.braintreepayments.com/reference/request/transaction/sale/node) stated that either
paymentMethodNonce or paymentMethodToken could be used. But actually there is one more option to
use a creditCard. In sake of the assignment's simplicity please go with a creditCard.
[Credit Card Payload]
(https://developers.braintreepayments.com/reference/request/transaction/sale/node#credit_card)
Example:
{
"amount": "100",
"creditCard": {
"number": "4111111111111111",
"expirationMonth": "2",
"expirationYear": "2020",
"cvv": "111"
}
}

2

Library should be designed to easily add another additional payment gateways.
3. Create a simple form for making payment. Form should have this fields:
● In order section:
○ Price (amount)
○ Currency (USD, EUR, THB, HKD, SGD, AUD)
○ Customer Full name
● In payment section:
○ Credit card holder name
○ Credit card number
○ Credit card expiration
○ Credit card CCV
● Submit button
Show success or error message after payment.
Use appropriate form validations.
4. Save order data + response from payment gateway to database table.
5. Create a public repository on Github and push the solution there. Send us the link to the
repository.


## Specification
● Create your own sandbox accounts for Paypal and Braintree
● To make it easier, implement only single payment with credit card. No need to implement saving
credit card and authorization of payments (unless you really want to try it out).
● After submitting the form, use a different gateway based on these rules:
○ if credit card type is AMEX, then use Paypal.
○ if currency is USD, EUR, or AUD, then use Paypal. Otherwise use Braintree.
○ if currency is not USD and credit card is AMEX, return error message, that AMEX is
possible to use only for USD

● Use any Node.js framework you want or no framework at all, it's up to you.
○ Usually we use Express.js - feel free to use this one, if you don't have any preferred one.
● Don't bother with any graphics, just simple HTML, simple form, no CSS needed. Or just use
Twitter Bootstrap.
● Use only Paypal and Braintree Node.js libraries, not any other 3rd party libraries.
● Cover code with unit tests.
● The code needs to work after we pull it and try it (no bugs) and should process the payments.
● No Angular.js (React or jquery is fine)


## Deviation from requirement
While implmenting payment using paypal SDK, payment method i added was paypal wallet instead of credit card due to one misunderstanding as per this document https://developer.paypal.com/docs/business/payment-methods/ it says PayPal Credit supported only in UK and US which i misunderstood has paypal doesnt support credit card payment in other country developer app, so i didn't Java SDK doc for paypal credit card implementation. So right now instead of payment method credit card i am using Paypal Wallet
