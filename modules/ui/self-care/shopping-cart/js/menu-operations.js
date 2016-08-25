$(document).ready(function () {
    $('.panel-heading a').on('click', function (e) {
        if ($(this).parents('.panel').children('.panel-collapse').hasClass('in')) {
            e.stopPropagation();
            $('.menu-images').contenthover({
                overlay_background: '#000',
                overlay_opacity: 0.8,
                overlay_y_position: 'center'
            });
        }
    });

    $('.menu-images').contenthover({
        overlay_background: '#000',
        overlay_opacity: 0.8,
        overlay_y_position: 'center'
    });

});