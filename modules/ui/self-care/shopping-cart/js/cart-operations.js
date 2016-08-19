$(document).ready(function () {
    calculateTotal(".tot-price");
    calculateDicountedTotal();
    $("#coupon-alert-div").hide();

    $("#coupon-submit").click(function () {
        couponValidator();
    });

    $(".mod").click(function () {
        $(this).closest('tr').remove();
    });

    $(".del").click(function () {
        var closest=$(this).closest('tr');
        $("#delete-confirm-popup").modal('show');
        $('#removeOk').off('click');
        $('#removeOk').click(function(){
            closest.remove();
            calculateTotal(".tot-price");
            calculateDicountedTotal();
            $("#delete-confirm-popup").modal('hide');
        });
    });

    $(".spin").click(function () {
        var qty = $(this).val();
        var price = $(this).closest('tr').children('td:eq(1)').text();
        var total = qty * price;
        $(this).closest('tr').children('td:eq(3)').text(total.toFixed(2));
        calculateTotal(".tot-price");
    });

    $(".spin").keyup(function () {
        var qty = $(this).val();
        var price = $(this).closest('tr').children('td:eq(1)').text();
        var total = qty * price;
        $(this).closest('tr').children('td:eq(3)').text(total.toFixed(2));
        calculateTotal(".tot-price");
        calculateDicountedTotal();
    });

    $("#checkoutButton").click(function () {
        $("#proceed-checkout-confirm").modal('show');
    });
});
function couponValidator() {
    var enteredCouponVal = $("#txt-coupon").val();
    if (enteredCouponVal.length === 0) {
        $("#coupon-validate-msg").text("Enter coupon code to validate.");
        $("#coupon-alert-div").attr('class', 'alert alert-danger');
        $("#coupon-alert-div").show();
        return false;
    } else if (enteredCouponVal !== "abc123") {
        $("#coupon-validate-msg").text("Invalid coupon code.");
        $("#coupon-alert-div").attr("class", "alert alert-danger");
        $("#coupon-alert-div").show();
        return false;
    } else {
        $("#coupon-alert-div").attr("class", "alert alert-success");
        $("#coupon-validate-msg").text("Coupon code validated (20% discount).");
        $("#coupon-alert-div").show();
        var discount = $("#label-tot").text() * 20 / 100;
        $("#label-dis").text(discount);
        calculateDicountedTotal();
        return false;
    }
}

function calculateTotal(element) {
    var sum = 0;
    $(element).each(function () {
        var value = $(this).text();
        // add only if the value is number
        if (!isNaN(value) && value.length != 0) {
            sum += parseFloat(value);
        }
    });
    $("#label-tot").text(sum);
}

function calculateDicountedTotal(element) {
    var total = $("#label-tot").text();
    var discount = $("#label-dis").text();
    $("#label-distot").text(total - discount);
}