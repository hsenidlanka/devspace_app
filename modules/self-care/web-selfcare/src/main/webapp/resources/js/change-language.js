$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "language",
        success: function (lang) {
            $("#lang-select").val(lang);
        }
    });

    $("#lang-select").change(function () {
        var language = $(this).val();

        $.ajax({
            type: "POST",
            url: "language",
            data: {"lang": language},
            success: function (result) {
                location.reload();
            }
        });
    });
});