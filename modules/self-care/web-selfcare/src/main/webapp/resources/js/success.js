$(document).ready(function () {
    $.ajax({
        type: 'get',
        //dataType: "json",
        url: "/web-selfcare/payment/receipt-data",
        success: function (result) {
            $("#lbl-rec-orderDate").text(result[0].orderDate);
            $("#lbl-rec-orderId").text(result[0].orderId);
            $("#lbl-rec-amount").text(result[0].amount);
            $("#lbl-rec-status").text(result[0].orderStatus);
            $("#lbl-rec-paymentOption").text(result[0].paymentOption);
            $("#lbl-rec-usedMobile").text(result[0].usedMobile);
        }
    });

    $("#btn-print").click(function () {
        $(".container").print();
    });
});
