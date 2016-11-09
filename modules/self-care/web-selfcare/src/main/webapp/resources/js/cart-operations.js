// This JavaScript file contains the operations of shopping cart.
$(document).ready(function () {
    initCalculations();
    $('#myCarousel').carousel({
        interval: 5000
    })

    $("#coupon-alert-div").hide();

    $("#coupon-submit").click(function () {
        couponValidator();
    });

    $(".mod").click(function () {
        $("#modify-item-popup").modal("show");
        $('#modifyOk').off('click');
        $('#modifyOk').click(function () {
            $.notify("Shopping cart updated.", {
                align: "center",
                verticalAlign: "top",
                type: 'toast',
                delay: 2000,
                animationType: "fade"
            });
            $("#modify-item-popup").modal("hide");
        });
    });

    // Remove item from the shopping cart
    $(".del").click(function () {
        var closest = $(this).closest('tr'); // find the closest table row
        var itemTitle = $.trim(closest.find("td.item-title").text());
        var itemPrice = $.trim(closest.find("td.item-price").text());
        var itemQty = $.trim(closest.find(".item-qty").val());
        var itemIndex = $.trim(closest.find("td.item-index").text());
        $("#delete-confirm-popup").modal('show');
        $("#header-item-title").text("Item name: "+itemTitle);
        $("#p-item-price").text("Price: "+itemPrice);
        $("#p-item-qty").text("Quantity: "+itemQty);
        $('#removeOk').off('click');
        $('#removeOk').click(function () {
            $.ajax({
                type: "POST",
                url: "/web-selfcare/shopping-cart/removeitem",
                data: {
                    /*"itemTitle": itemTitle,
                     "itemPrice": itemPrice,
                     "itemQty": itemQty*/
                    "itemIndex": itemIndex
                },
                success: function (result) {
                    if (result) {
                        closest.remove(); // remove the closest table row
                        calculateTotal(".tot-price");
                        recalculateTotals();
                        $("#delete-confirm-popup").modal('hide');
                        $.notify("Selected item removed from the shopping cart.", {
                            align: "center",
                            verticalAlign: "top",
                            type: 'toast',
                            delay: 2000,
                            animationType: "fade"
                        });
                        if ($("#label-tot").val() !== 0) {
                            $("#coupon-submit").prop('disabled', false);
                            $("#checkoutButton").prop('disabled', false);
                        } else {
                            $("#coupon-submit").prop('disabled', true);
                            $("#checkoutButton").prop('disabled', true);
                        }
                        location.reload();
                    }
                }
            });
        });
    });

    $(".spin").click(function () {
        var qty = $(this).val();
        var price = $(this).closest('tr').children('td:eq(3)').text();
        var total = qty * price;
        var itemIndex = $.trim($(this).closest('tr').find('td.item-index').text());
        $(this).closest('tr').children('td:eq(5)').text(total.toFixed(2));
        calculateTotal(".tot-price");
        recalculateTotals();
        storeQtyOnSession(itemIndex, qty);
    });

    $(".spin").keyup(function () {
        var qty = $(this).val();
        var price = $(this).closest('tr').children('td:eq(3)').text();
        var total = qty * price;
        var itemIndex = $.trim($(this).closest('tr').find('td.item-index').text());
        $(this).closest('tr').children('td:eq(5)').text(total.toFixed(2));
        calculateTotal(".tot-price");
        recalculateTotals();
        storeQtyOnSession(itemIndex, qty);
    });

    $("#checkoutButton").click(function () {
        $("#proceed-checkout-confirm").modal('show');
    });
});

// Validate the coupon
function couponValidator() {
    var enteredCouponVal = $("#txt-coupon").val();
    if (enteredCouponVal.length == 0) {
        $("#coupon-validate-msg").text("Enter coupon code to validate.");
        $("#coupon-alert-div").attr('class', 'alert alert-danger');
        $("#coupon-alert-div").show();
        return false;
    } else if ($("#label-tot").text() === "0.00") {
        $("#coupon-validate-msg").text("Add items to cart to apply coupon.");
        $("#coupon-alert-div").attr('class', 'alert alert-danger');
        $("#coupon-alert-div").show();
        return false;
    } else {
        $.ajax({
            type: 'POST',
            url: "/web-selfcare/shopping-cart/validatecoupon",
            data: {"checkCoupon": enteredCouponVal},
            success: function (result) {
                var couponCodeRate = $.trim(result);
                if (couponCodeRate > 0) {
                    $("#coupon-alert-div").attr("class", "alert alert-success");
                    $("#coupon-validate-msg").text("Coupon code validated.");
                    $("#coupon-alert-div").show();
                    var discount = $("#label-tot").text() * couponCodeRate / 100;
                    $("#label-dis").text(discount.toFixed(2));
                    $("#label-dis-rate").text(couponCodeRate);
                    $("#txt-coupon").attr("readonly", true);
                    calculateDicountedTotal();
                    calculateNetAmount();
                    return true;
                } else {
                    $("#coupon-validate-msg").text("Coupon code is not valid.");
                    $("#coupon-alert-div").attr('class', 'alert alert-danger');
                    $("#coupon-alert-div").show();
                    return false;
                }
            }
        });
    }
}

// Re-calculate the total upon the modifications to the shopping cart
function recalculateTotals() {
    if ($("#coupon-alert-div").hasClass("alert alert-success")) {
        var discount = $("#label-tot").text() * 20 / 100;
        $("#label-dis").text(discount.toFixed(2));
        calculateDicountedTotal();
        calculateNetAmount();
    } else {
        calculateDicountedTotal();
        calculateNetAmount();
    }
}

// Calculate the total of shopping cart items
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

// Calculate the total after deducting the discounts
function calculateDicountedTotal(element) {
    var total = $("#label-tot").text();
    var discount = $("#label-dis").text();
    $("#label-distot").text(parseFloat(total - discount).toFixed(2));
}

// Calculate the net amount by adding 5% service charge
function calculateNetAmount() {
    var discountedTotal = $("#label-distot").text();
    $("#net-amount-field").text(parseFloat(discountedTotal * 105 / 100).toFixed(2));
}

// Store quantity details on the session when quantity gets updated
function storeQtyOnSession(itemIndex, quantity) {
    $.ajax({
        type: 'POST',
        url: "/web-selfcare/shopping-cart/updateitem",
        data: {"ItemIndex": itemIndex, "itemQty": quantity},
        success: function (result) {
        }
    });
}

function initCalculations() {
    calculateTotal(".tot-price");
    calculateDicountedTotal();
    calculateNetAmount();
    $('.spin').each(function(i, obj) {
        var qty = $(this).val();
        var price = $(this).closest('tr').children('td:eq(3)').text();
        var total = qty * price;
        $(this).closest('tr').children('td:eq(5)').text(total.toFixed(2));
        calculateTotal(".tot-price");
        recalculateTotals();
    });
}