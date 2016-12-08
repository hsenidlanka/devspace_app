$(document).ready(function () {
    $.ajax({
        type: 'get',
        dataType: "json",
        url: "delivery/get-pickup-data",
        success: function (result) {
            if (result.length > 0) {
                $("#text-picktime").val(result[0].time);
                $("#text-branch").val(result[0].branch);
            }
        }
    });
});