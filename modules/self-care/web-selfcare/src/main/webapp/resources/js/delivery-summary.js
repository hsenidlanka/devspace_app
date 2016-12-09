$(document).ready(function () {
    $.ajax({
        type: 'get',
        dataType: "json",
        url: "delivery/get-delivery-data",
        success: function (result) {
            if (result.length > 0) {
                $("#del-fname").val(result[0].firstName);
                $("#del-lname").val(result[0].lastName);
                $("#del-contactno").val(result[0].mobile);
                var address = result[0].addressLine01 + ", " + result[0].addressLine02 + ", ";
                if (typeof result[0].addressLine03 !== "undefined") {
                    address += result[0].addressLine03;
                }
                $("#del-address").val(address);
                $("#del-description").val(result[0].description);
                $("#delsum-text-date").val(result[0].date);
                $("#delsum-text-time").val(result[0].time);
            }
        }
    });
});