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

    $(".tab-beverages").click(function(){
        $("#desserts-collapse").hide();
        $("#pizza-collapse").hide();
        $("#beverage-collapse").show();
    });
});

function menuThumbnailsOverlay() {
    $('.menu-images').contenthover({
        overlay_background: '#000',
        overlay_opacity: 0.8,
        overlay_y_position: 'center'
    });
}