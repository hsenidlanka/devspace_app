$(document).ready(function () {
    $("#desserts-collapse").hide(menuThumbnailsOverlay());
    $("#beverage-collapse").hide(menuThumbnailsOverlay());

    // Keep only one collapsible group open.
    /*    $('.panel-heading a').on('click', function (e) {
     if ($(this).parents('.panel').children('.panel-collapse').hasClass('in')) {
     e.stopPropagation();
     }
     });*/

    menuThumbnailsOverlay();

    $(".tab-desserts").click(function () {
        $("#pizza-collapse").hide();
        $("#beverage-collapse").hide();
        $("#desserts-collapse").show();
    });

    $(".tab-pizzas").click(function () {
        $("#desserts-collapse").hide();
        $("#beverage-collapse").hide();
        $("#pizza-collapse").show();
    });

    $(".tab-beverages").click(function () {
        $("#desserts-collapse").hide();
        $("#pizza-collapse").hide();
        $("#beverage-collapse").show();
    });

    $(".btn-addtocart-dessert").click(function () {
        $.notify("Selected dessert added to the shopping cart.", {
            align: "center",
            verticalAlign: "top",
            type: 'toast',
            delay: 2000,
            icon: 'check'
        });
    });

    $(".btn-addtocart-beverage").click(function () {
        $.notify("Selected beverage added to the shopping cart.", {
            align: "center",
            verticalAlign: "top",
            type: 'toast',
            delay: 2000,
            icon: 'check'
        });
    });

    $(".btn-addtocart-pizza").click(function () {
        $("#add-to-cart-popup").modal('show');
    });

    $(".btn-popup-add-to-cart-pizza").click(function () {
        $.notify("Selected pizza added to the shopping cart.", {
            align: "center",
            verticalAlign: "top",
            type: 'toast',
            delay: 2000,
            icon: 'check'
        });
    });
});

function menuThumbnailsOverlay() {
    $('.menu-images').contenthover({
        overlay_background: '#000',
        overlay_opacity: 0.8,
        overlay_y_position: 'center'
    });
}