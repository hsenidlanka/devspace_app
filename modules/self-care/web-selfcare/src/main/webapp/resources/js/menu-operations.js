$(document).ready(function () {
    var priceMap;
    $(".btn-addtocart").click(function () {
        // Initialize the toast notification
        $.notify("Selected item added to the shopping cart.", {
            align: "center",
            verticalAlign: "top",
            type: 'toast',
            delay: 2000,
            animationType: "fade"
        });
    });

    $("#sizes").change(function () {
        var selectedSize = $("#sizes").val();
        var fldId = '#hiddenFld-' + selectedSize;
        $(this).closest("div").find("#lbl-sizeprice").text($(fldId).val());
    });

    $(".btn-addtocart-pizza").click(function () {
        var price;
        priceMap = new Object();
        var itemDesc = $(this).closest(".caption").find("#menu-item-desc").val();
        $("#item-desc-modal").text(itemDesc);
        var itemName = $(this).closest(".col-md-3").find("#menu-item-name").text();
        $("#item-title-modal").text(itemName);

        var select = $("#addtocart-pizza-sizes"), options = '';
        select.empty();

        if ($.trim($(this).closest(".caption").find("#pizza-price-regular").text()) != "") {
            priceMap["regular"] = $.trim($(this).closest(".caption").find("#pizza-price-regular").text());
            options += "<option value='regular'>Regular</option>";
        }
        if ($.trim($(this).closest(".caption").find("#pizza-price-medium").text()) != "") {
            priceMap["medium"] = $.trim($(this).closest(".caption").find("#pizza-price-medium").text());
            options += "<option value='medium'>Medium</option>";
        }
        if ($.trim($(this).closest(".caption").find("#pizza-price-large").text()) != "") {
            priceMap["large"] = $.trim($(this).closest(".caption").find("#pizza-price-large").text());
            options += "<option value='large'>Large</option>";
        }
        select.append(options);

        var selectedSize = $("#addtocart-pizza-sizes").val();
        var priceFieldId = "#pizza-price-" + selectedSize;
        price = $.trim($(this).closest(".caption").find(priceFieldId).text());
        $("#addtocart-pizza-price").val(price);

        $("#add-to-cart-popup").modal('show');
        calculateTotal();
    });

    $("#addtocart-pizza-sizes").change(function () {
        var selectedSize = $("#addtocart-pizza-sizes").val();
        var price = priceMap[selectedSize];
        var quantity = $(':input[type="number"]').val();
        $("#addtocart-pizza-price").val(price);
        calculateTotal();
    });

    $(':input[type="number"]').change(function(){
        calculateTotal();
    });

    $(".btn-popup-add-to-cart-pizza").click(function () {
        $("#add-to-cart-popup").modal('hide');
        // Initialize the toast notification
        $.notify("Selected pizza added to the shopping cart.", {
            align: "center",
            verticalAlign: "top",
            type: 'toast',
            delay: 2000,
            animationType: "fade"
        });
    });

    // Validate the search textbox
    $("#btn-menu-search").click(function () {
        if ($("#txt-menu-search").val().length == 0) {
            $("#txt-menu-search").css("border-color", "red");
            $.notify(" Search field is empty...", {
                align: "center",
                verticalAlign: "top",
                delay: 2000,
                animationType: "fade",
                color: "#fff",
                background: "#D44950"
            });
        } else {
            $("#txt-menu-search").css("border-color", "blue");
            window.location.href = "";
        }
    });
});

function calculateTotal(){
    var price = $("#addtocart-pizza-price").val();
    var quantity = $(':input[type="number"]').val()
    var total = price * quantity;
    $("#addtocart-pizza-total").val(total.toFixed(2));
}
