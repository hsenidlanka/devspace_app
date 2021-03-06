// This JavaScript file contains the operations of shopping cart.
$(document).ready(function () {
    initCalculations();

    $(".item-qty").keydown(function (e) {
        e.preventDefault();
    });

    $('#myCarousel').carousel({
        interval: 5000
    });

    $("#coupon-alert-div").hide();

    if ($("#txt-coupon").val() != "") {
        couponValidator();
    }

    $("#coupon-submit").click(function () {
        if ($("#coupon-submit").val() == "remove") {
            removeCoupon();
        } else {
            couponValidator();
        }
    });

    /*$(".mod").click(function () {
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
    });*/

    // Remove item from the shopping cart
    $(".del").click(function () {
        var closest = $(this).closest('tr'); // find the closest table row
        var itemTitle = $.trim(closest.find("td.item-title").text());
        var itemQty = $.trim(closest.find("input.item-qty").val());
        var itemIndex = $.trim(closest.find("td.item-index").text());
        $("#delete-confirm-popup").modal('show');
        $("#header-item-title").text("   Item name: " + itemTitle);
        $("#p-item-qty").text("   Quantity: " + itemQty);
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
        var price = $(this).closest('tr').children('td:eq(4)').text();
        var toppingsTotal = $(this).closest('tr').children('td:eq(0)').text();
        var total = (parseFloat(price) + parseFloat(toppingsTotal)) * qty;
        var itemIndex = $.trim($(this).closest('tr').find('td.item-index').text());
        $(this).closest('tr').children('td:eq(6)').text(total.toFixed(2));
        calculateTotal(".tot-price");
        recalculateTotals();
        storeQtyOnSession(itemIndex, qty);
    });

    $(".spin").keyup(function () {
        var qty = $(this).val();
        var price = $(this).closest('tr').children('td:eq(4)').text();
        var toppingsTotal = $(this).closest('tr').children('td:eq(0)').text();
        var total = (parseFloat(price) + parseFloat(toppingsTotal)) * qty;
        var itemIndex = $.trim($(this).closest('tr').find('td.item-index').text());
        $(this).closest('tr').children('td:eq(6)').text(total.toFixed(2));
        calculateTotal(".tot-price");
        recalculateTotals();
        storeQtyOnSession(itemIndex, qty);
    });

    $("#checkoutButton").click(function () {
        if (!$("td.empty-cart").length) {
            $.ajax({
                type: "GET",
                url: "/web-selfcare/shopping-cart/proceed",
                data: {"total": $("#net-amount-field").text()},
                success: function (result) {
                    if (result == 1) {
                        window.location.href = "/web-selfcare/delivery";
                    } else {
                        $("#proceed-checkout-confirm").modal('show');
                        $("#btn-cart-login").click(function () {
                            $("#proceed-checkout-confirm").modal('hide');
                            $("#modal-login").modal('show');
                        });
                        $("#btn-cart-signup").click(function () {
                            $("#proceed-checkout-confirm").modal('hide');
                            $("#modal-signup").modal('show');
                        });
                    }
                }
            });
        } else {
            $.notify("There are no items to checkout.", {
                align: "center",
                verticalAlign: "top",
                delay: 2000,
                animationType: "fade",
                color: "#fff",
                background: "#D44950"
            });
        }
    });
});

// Validate the coupon
function couponValidator() {
    var enteredCouponVal = $("#txt-coupon").val();
    if ($("#label-tot").text() === "0.00") {
        $("#coupon-validate-msg").text("Add items to cart to apply coupon.");
        $("#coupon-alert-div").attr('class', 'alert alert-danger');
        $("#coupon-alert-div").show();
        return false;
    } else if (enteredCouponVal.length == 0) {
        $("#coupon-validate-msg").text("Enter coupon code to validate.");
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
                    $("#coupon-submit").val("remove");
                    $("#coupon-submit").attr('class', 'btn btn-danger btn-sm');
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

function removeCoupon() {
    if ($("#txt-coupon").val().length > 0) {
        $.ajax({
            type: "GET",
            url: "/web-selfcare/shopping-cart/removecoupon",
            success: function (result) {
                $("#txt-coupon").val("");
                $("#txt-coupon").attr("readonly", false);
                $("#coupon-submit").text("validate");
                $("#coupon-submit").attr('class', 'btn btn-primary btn-sm');
                location.reload();
            }
        });
    }
}

// Re-calculate the total upon the modifications to the shopping cart
function recalculateTotals() {
    if ($("#coupon-alert-div").hasClass("alert alert-success")) {
        var enteredCouponVal = $("#txt-coupon").val();
        $.ajax({
            type: 'POST',
            url: "/web-selfcare/shopping-cart/validatecoupon",
            data: {"checkCoupon": enteredCouponVal},
            success: function (result) {
                var couponCodeRate = $.trim(result);
                if (couponCodeRate > 0) {
                    var discount = $("#label-tot").text() * couponCodeRate / 100;
                    $("#label-dis").text(discount.toFixed(2));
                    $("#label-dis-rate").text(couponCodeRate);
                    $("#txt-coupon").attr("readonly", true);
                    calculateDicountedTotal();
                    calculateNetAmount();
                }
            }
        });
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
    var serviceCharge = 5;
    if (parseInt(discountedTotal) == 0) {
        serviceCharge = 0;
    } else if (parseInt(discountedTotal) > 10000) {
        serviceCharge = 8;
    }
    $("#label-serv").text(serviceCharge.toFixed(2));
    var serviceChargeForCalculation = 100 + parseInt(serviceCharge);
    console.log(serviceCharge);
    console.log(serviceChargeForCalculation);
    $("#net-amount-field").text(parseFloat(discountedTotal * serviceChargeForCalculation / 100).toFixed(2));
}

// Store quantity details on the session when quantity gets updated
function storeQtyOnSession(itemIndex, quantity) {
    $.ajax({
        type: 'POST',
        url: "/web-selfcare/shopping-cart/updateitem",
        data: {
            "ItemIndex": itemIndex,
            "itemQty": quantity
        },
        success: function (result) {
        }
    });
}

function initCalculations() {
    calculateTotal(".tot-price");
    calculateDicountedTotal();
    calculateNetAmount();
    $('.spin').each(function (i, obj) {
        var qty = $(this).val();
        var price = $(this).closest('tr').children('td:eq(4)').text();
        var toppingsTotal = $(this).closest('tr').children('td:eq(0)').text();
        var total = (parseFloat(price) + parseFloat(toppingsTotal)) * qty;
        $(this).closest('tr').children('td:eq(6)').text(total.toFixed(2));
        calculateTotal(".tot-price");
        recalculateTotals();
    });
}