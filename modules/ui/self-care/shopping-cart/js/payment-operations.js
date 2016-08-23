$(document).ready(function () {
    $("#btn-pay").click(function () {
        var selectedVal = $("input[type=radio][name=paymentradio]:checked").val();
        if (selectedVal == "payondel") {
            window.location.href = "../pages/success.html";
        } else {
            window.location.href = "../pages/pay.html";
        }
    });

    $("#btn-confirm-pay").click(function () {
        $("#confirm-payment-popup").modal('show');
    });

    $("#btn-pay-popup").click(function () {
        window.location.href = "../pages/success.html";
    });
});