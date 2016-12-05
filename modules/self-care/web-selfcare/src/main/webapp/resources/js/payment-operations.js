$(document).ready(function () {
    $("input[type=checkbox][name=checkbox-same-sender]").change(function () {
    });

    $("#btn-pay").click(function () {
        var selectedVal = $("input[type=radio][name=paymentradio]:checked").val();
        if (selectedVal == "payondel") {
            window.location.href = "success";
        } else if (selectedVal == "paymobile") {
            window.location.href = "pay-mobile";
        } else {
            window.location.href = "pay-card";
        }
    });
});