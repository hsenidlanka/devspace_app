$(document).ready(function () {
    getTotal();
});

function getTotal() {
    $.ajax({
        type: "GET",
        url: "/web-selfcare/payment/get-total",
        success: function (totalPrice) {
            $("#payment-amount-field").text(totalPrice);
        }
    });
}