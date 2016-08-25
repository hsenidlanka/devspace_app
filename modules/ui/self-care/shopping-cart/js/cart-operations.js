$(document).ready(function () {
    calculateTotal(".tot-price");
    calculateDicountedTotal();
    calculateNetAmount();

    $("#coupon-alert-div").hide();

    $("#coupon-submit").click(function () {
        couponValidator();
    });

    $(".mod").click(function () {
        $(this).closest('tr').remove();
    });

    $(".del").click(function () {
        var closest = $(this).closest('tr');
        $("#delete-confirm-popup").modal('show');
        $('#removeOk').off('click');
        $('#removeOk').click(function () {
            closest.remove();
            calculateTotal(".tot-price");
            calculateDicountedTotal();
            calculateNetAmount();
            $("#delete-confirm-popup").modal('hide');
            if ($("#label-tot").val() !== 0) {
                alert("Sdf");
                $("#coupon-submit").prop('disabled', false);
                $("#checkoutButton").prop('disabled', false);
            } else {
                $("#coupon-submit").prop('disabled', true);
                $("#checkoutButton").prop('disabled', true);
            }
        });
    });

    $(".spin").click(function () {
        var qty = $(this).val();
        var price = $(this).closest('tr').children('td:eq(1)').text();
        var total = qty * price;
        $(this).closest('tr').children('td:eq(3)').text(total.toFixed(2));
        calculateTotal(".tot-price");
        recalculateTotals();
    });

    $(".spin").keyup(function () {
        var qty = $(this).val();
        var price = $(this).closest('tr').children('td:eq(1)').text();
        var total = qty * price;
        $(this).closest('tr').children('td:eq(3)').text(total.toFixed(2));
        calculateTotal(".tot-price");
        recalculateTotals();
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
        $("#label-dis").text(discount.toFixed(2));
        $("#txt-coupon").attr("disabled",true);
        calculateDicountedTotal();
        calculateNetAmount();
        return true;
    }
}

function recalculateTotals(){
    if($("#coupon-alert-div").hasClass("alert alert-success")){
        var discount = $("#label-tot").text() * 20 / 100;
        $("#label-dis").text(discount.toFixed(2));
        calculateDicountedTotal();
        calculateNetAmount();
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
    $("#label-tot").text(sum.toFixed(2));
}

function calculateDicountedTotal(element) {
    var total = $("#label-tot").text();
    var discount = $("#label-dis").text();
    $("#label-distot").text(parseFloat(total - discount).toFixed(2));
}

function calculateNetAmount() {
    var discountedTotal = $("#label-distot").text();
    $("#net-amount-field").text(parseFloat(discountedTotal * 105 / 100).toFixed(2));
}