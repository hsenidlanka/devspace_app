$(document).ready(function () {
    getTotal();
    fillCustomerMobileNo();
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

function fillCustomerMobileNo() {
    $.ajax({
        type: "GET",
        url: "/web-selfcare/payment/customer-mobile",
        success: function (mobileNo) {
            alert(mobileNo);
            if (mobileNo == "") {
                alert("empty");
            } else {
                alert("ok");
            }
        }
    });
}