var background_color = "#fde99c";

$(document).ready(function () {
    hideErrorLabels();

    $("#btn-confirm-cardpay").click(function () {
        var isCardNameValid = validateCardName();
        var isCardNumberValid = validateCardNumber();
        var isCvvValid = validateCVV();
        if (isCardNameValid == false || isCardNumberValid == false || isCvvValid == false) {
            $("#pay-validation-error-popup").modal('show');
        } else {
            $("#confirm-payment-popup").modal('show');
        }
    });

    $("#btn-confirm-mobilepay").click(function () {
        if (validateMobileNo() == false) {
            $("#pay-validation-error-popup").modal('show');
        } else {
            $("#confirm-payment-popup").modal('show');
            $("#btn-pay-popup").off('click');
            $("#btn-pay-popup").click(function () {
                $.ajax({
                    type: "POST",
                    url: "/web-selfcare/payment/checkout",
                    dataType: "json",
                    data: {
                        "mobileNo": $("#txt-mobile-no").val(),
                        "totalAmount": $("#payment-amount-field").text()
                    },
                    success: function (code) {
                        if (parseInt(code) == 200) {
                            window.location.href = "success"
                        } else {
                            $("#confirm-payment-popup").modal('hide');
                            $("#pay-fail-popup").modal('show');
                        }
                    }
                });
            });
        }
    });
});

// Validate the card name
function validateCardName() {
    if ($("#txt-card-name").val().length == 0) {
        $("#cardname_error").show();
        $("#txt-card-name").css("background-color", background_color);
        $("#cardname_error").text("Please enter the mandatory fields of the address.");
        return false;
    } else {
        $("#txt-card-name").css("background-color", "white");
        $("#cardname_error").hide();
    }
}

// Validate the card no
function validateCardNumber() {
    if ($("#txt-card-no").val().length == 0) {
        $("#cardno_error").show();
        $("#txt-card-no").css("background-color", background_color);
        $("#cardno_error").text("Please enter the mandatory fields of the address.");
        return false;
    } else {
        $("#txt-card-no").css("background-color", "white");
        $("#cardno_error").hide();
    }
}

// Validate the CVV
function validateCVV() {
    if ($("#txt-cvv-no").val().length == 0) {
        $("#cvv_error").show();
        $("#txt-cvv-no").css("background-color", background_color);
        $("#cvv_error").text("Please enter the mandatory fields of the address.");
        return false;
    } else {
        $("#txt-cvv-no").css("background-color", "white");
        $("#cvv_error").hide();
    }
}

function validateMobileNo() {
    if ($("#txt-mobile-no").val().length != 10) {
        $("#mobileno_error").show();
        $("#txt-mobile-no").css("background-color", background_color);
        $("#mobileno_error").text("Telephone no should contain 10 characters including the leading zero.");
        return false;
    } else {
        var inputVal = $("#txt-mobile-no").val();
        //var telReg = /^94[^0]\d+$/;
        var telReg = /^0\d+$/;
        if (!telReg.test(inputVal)) {
            $("#mobileno_error").show();
            $("#txt-mobile-no").css("background-color", background_color);
            $("#mobileno_error").text("Please enter a valid telephone no.");
            return false;
        } else {
            $("#txt-mobile-no").css("background-color", "white");
            $("#mobileno_error").hide();
        }
    }
}

function hideErrorLabels() {
    $("#cardname_error").hide();
    $("#cardno_error").hide();
    $("#cvv_error").hide();
    $("#mobileno_error").hide();
}

