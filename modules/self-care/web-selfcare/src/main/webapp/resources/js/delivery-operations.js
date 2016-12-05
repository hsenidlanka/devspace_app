$(document).ready(function () {
    $("#pickup-div").hide();
    $("#date").prop("disabled", true);
    $("#timepicker1").prop("disabled", true);
    $("#timepicker2").prop("disabled", true);
    $('.bootstrap-timepicker1').timepicker({
        minuteStep: 5,
        showMeridian: false,
        icons: {
            up: 'fa fa-chevron-up',
            down: 'fa fa-chevron-down'
        }
    });
    $('.bootstrap-timepicker2').timepicker({
        minuteStep: 5,
        showMeridian: false,
        icons: {
            up: 'fa fa-chevron-up',
            down: 'fa fa-chevron-down'
        }
    });
    fillDeliveryFormData();

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

    // Enable/disable date/time fields according to the selection (deliver now/later)
    $("input[type=radio][name=radiodelwhen]").change(function () {
        var selectedVal = $("input[type=radio][name=radiodelwhen]:checked").val();
        if (selectedVal == "del-later") {
            $("#date").prop("disabled", false);
            $("#timepicker1").prop("disabled", false);
        } else {
            $("#date").prop("disabled", true);
            $("#timepicker1").prop("disabled", true);
        }
    });

    // Enable/disable date/time fields according to the selection (pickup now/later)
    $("input[type=radio][name=radiopickwhen]").change(function () {
        var selectedVal = $("input[type=radio][name=radiopickwhen]:checked").val();
        if (selectedVal == "pick-later") {
            $("#timepicker2").prop("disabled", false);
        } else {
            $("#timepicker2").prop("disabled", true);
        }
    });
});

function fillDeliveryFormData() {
    $.ajax({
        type: 'get',
        dataType: "json",
        url: "delivery/get-delivery-data",
        success: function (result) {
            if (result.length > 0) {
                $("#txt-fname").val(result[0].firstName);
                $("#txt-lname").val(result[0].lastName);
                $("#txt-deliadd01").val(result[0].addressLine01);
                $("#txt-deliadd02").val(result[0].addressLine02);
                if (typeof result[0].addressLine03 !== "undefined") {
                    $("#txt-deliadd03").val(result[0].addressLine03);
                }
                $("#txt-email").val(result[0].email);
                $("#txt-contactno").val(result[0].mobile);
                if (typeof result[0].description !== "undefined") {
                    $("#txtarea-delivery-description").val(result[0].description);
                }
            }
        }
    });
}