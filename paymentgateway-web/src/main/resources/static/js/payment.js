$( document ).ready(function() {

    $("#orderSubmit").click(function(){
        $("#payment-section").show();
        $("#order-section").hide();
    });

    $("#backButton").click(function(){
        $("#payment-section").hide();
        $("#order-section").show();
    });

    $("#paymentSubmit").click(function(){
        var cname    = $("#customername").val();
        var price    = $("#price").val();
        var currency = $("#currency").val();
        var chname   = $("#cardholdername").val();
        var cardnum  = $("#cardnumber").val();
        var cardexp  = $("#cardexpire").val();
        var cardcvv  = $("#cardcvv").val();
        $(".btn-final").hide();
        $("#messagebox").show();
        $.ajax({url: "/payment/process",
                type: "GET",
                data: {
                    customername: cname,
                    price: price,
                    currency: currency,
                    cardholdername: chname,
                    cardnumber: cardnum,
                    cardexpire: cardexp,
                    cardcvv: cardcvv
                },
                success: function(result){
                    console.log(JSON.stringify(result));
                    if(result.success){
                        if(result.redirect){
                            location.href = result.redirectUrl;
                        }
                        else{
                            location.href = "/payment/success"
                        }
                    }
                    else {
                        alert(result.message);
                    }
                },
            complete: function(){
                $(".btn-final").show();
                $("#messagebox").hide();
            }
        });
    });


});
