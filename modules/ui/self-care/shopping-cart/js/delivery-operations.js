$(document).ready(function () {
    $('#timepicker1').timepicker();
    $("#pickup-div").hide();

    // Dynamically change the content according to the delivery options
    $("input[type=radio][name=optradio]").change(function () {
        var selectedVal = $("input[type=radio][name=optradio]:checked").val();
        if (selectedVal == "pickup") {
            $("#delivery-div").hide();
            $("#pickup-div").show();
        } else {
            $("#pickup-div").hide();
            $("#delivery-div").show();
        }
    });
});