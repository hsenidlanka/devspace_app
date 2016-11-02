$(document).ready(function () {
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

    $(".btn-addtocart-pizza").click(function () {
        var itemDesc=$(this).closest(".caption").find("#menu-item-desc").val();
        $("#item-desc-modal").text(itemDesc);
        var itemName=$(this).closest(".col-md-3").find("#menu-item-name").text();
        $("#item-title-modal").text(itemName);
        $("#add-to-cart-popup").modal('show');
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
            window.location.href = "../pages/search-results.html";
        }
    });
});