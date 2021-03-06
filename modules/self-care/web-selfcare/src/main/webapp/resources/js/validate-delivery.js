var background_color = "#fde99c";
var selectedLang;// User selected language
$(document).ready(function () {
    hideErrorLabels();

    // Assign user selected language locale
    selectedLang = $("#hid-lang").val();
    if ($.trim(selectedLang) == "") {
        selectedLang = "en";
    }

    $("#txt-fname").keyup(function () {
        validateFirstName();
    });
    $("#txt-fname").focusout(function () {
        validateFirstName();
    });

    $("#txt-lname").keyup(function () {
        validateLastName();
    });
    $("#txt-lname").focusout(function () {
        validateLastName();
    });

    $("#txt-contactno").keyup(function () {
        validateContactNo();
    });
    $("#txt-contactno").focusout(function () {
        validateContactNo();
    });

    $("#txt-email").keyup(function () {
        validateEmail();
    });
    $("#txt-email").focusout(function () {
        validateEmail();
    });

    $("#txt-deliadd01").keyup(function () {
        validateDeliveryAddress01();
    });
    $("#txt-deliadd01").focusout(function () {
        validateDeliveryAddress01();
    });

    $("#txt-deliadd02").keyup(function () {
        validateDeliveryAddress02();
    });
    $("#txt-deliadd02").focusout(function () {
        validateDeliveryAddress02();
    });

    $("#btn-delivery-submit").click(function () {
        var isFirstNameValid = validateFirstName();
        var isLastNameValid = validateLastName();
        var isContactNoValid = validateContactNo();
        var isEmailValid = validateEmail();
        var isAddress01Valid = validateDeliveryAddress01();
        var isAddress02Valid = validateDeliveryAddress02();
        if (isFirstNameValid == false || isLastNameValid == false || isContactNoValid == false || isEmailValid == false || isAddress01Valid == false || isAddress02Valid == false) {
            $("#validation-error-popup").modal('show');
        } else {
            var selectedVal = $("input[type=radio][name=radiodelwhen]:checked").val();
            if (selectedVal != "del-later" || $(".del-date").val() == "") {
                var date = new Date();
                date.setMinutes(date.getMinutes() + 30);
                var str = date.getFullYear() + "-" + getFormattedPartTime(date.getMonth() + 1) + "-" + getFormattedPartTime(date.getDate());
                $(".del-date").val(str);
                var time = date.getHours() + ":" + getFormattedPartTime(date.getMinutes());
                $(".del-time").val(time);
            }
            $.ajax({
                type: "post",
                url: "delivery/save-delivery-data",
                data: {
                    "firstName": $("#txt-fname").val(),
                    "lastName": $("#txt-lname").val(),
                    "contactNo": $("#txt-contactno").val(),
                    "email": $("#txt-email").val(),
                    "address01": $("#txt-deliadd01").val(),
                    "address02": $("#txt-deliadd02").val(),
                    "address03": $("#txt-deliadd03").val(),
                    "description": $("#txtarea-delivery-description").val(),
                    "date": $(".del-date").val(),
                    "time": $(".del-time").val()
                }
            });
            window.location = 'delivery-summary';
        }
    });

    $("#btn-pickup-submit").click(function () {
        $.ajax({
            type: "post",
            url: "delivery/save-pickup-data",
            data: {
                "time": $(".pickup-time").val(),
                "branch": $("#pickup-branch").val()
            }
        });
        window.location = 'pickup-summary';
    });
});

// Validate the first name
function validateFirstName() {
    if ($("#txt-fname").val().length == 0) {
        $("#fname_error").show();
        $("#txt-fname").css("background-color", background_color);
        console.log(selectedLang);
        $("#fname_error").text(/* Selected language */ja['fname_error_text']);
        /*
        * ja['fname_error_text'] or en['fname_error_text'].
        * If can access data using selectedLang['fname_error_text'] or
        * selectedLang.fname_error_text, problem solved.
        */
        return false;
    } else {
        var inputVal = $("#txt-fname").val();
        var numericReg = /^[a-zA-Z]+$/;
        if (!numericReg.test(inputVal)) {
            $("#fname_error").show();
            $("#txt-fname").css("background-color", background_color);
            $("#fname_error").text("Invalid first name. Only alphabetic characters without spaces allowed.");
            return false;
        } else {
            $("#txt-fname").css("background-color", "white");
            $("#fname_error").hide();
        }
    }
}

// Validate the last name
function validateLastName() {
    if ($("#txt-lname").val().length == 0) {
        $("#txt-lname").css("background-color", "white");
        $("#lname_error").hide();
    } else {
        var inputVal = $("#txt-lname").val();
        var numericReg = /^[a-zA-Z]+$/;
        if (!numericReg.test(inputVal)) {
            $("#lname_error").show();
            $("#txt-lname").css("background-color", background_color);
            $("#lname_error").text("Invalid last name. Only alphabetic characters without spaces allowed.");
            return false;
        } else {
            $("#txt-lname").css("background-color", "white");
            $("#lname_error").hide();
        }
    }
}

// Validate the tel no
function validateContactNo() {
    if ($("#txt-contactno").val().length != 10) {
        $("#contactno_error").show();
        $("#txt-contactno").css("background-color", background_color);
        $("#contactno_error").text("Telephone no should contain 11 characters without the leading zero.");
        return false;
    } else {
        var inputVal = $("#txt-contactno").val();
        //var telReg = /^94[^0]\d+$/;
        var telReg = /^0\d+$/;
        if (!telReg.test(inputVal)) {
            $("#contactno_error").show();
            $("#txt-contactno").css("background-color", background_color);
            $("#contactno_error").text("Please enter a valid telephone no.");
            return false;
        } else {
            $("#txt-contactno").css("background-color", "white");
            $("#contactno_error").hide();
        }
    }
}

//Validate the email address
function validateEmail() {
    if ($("#txt-email").val().length == 0) {
        $("#email_error").show();
        $("#txt-email").css("background-color", background_color);
        $("#email_error").text("Please enter a email.");
    } else {
        var inputVal = $("#txt-email").val();
        var emailReg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if (!emailReg.test(inputVal)) {
            $("#email_error").show();
            $("#txt-email").css("background-color", background_color);
            $("#email_error").text("Please enter a valid email.");
            return false;
        } else {
            $("#txt-email").css("background-color", "white");
            $("#email_error").hide();
        }
    }
}

//Validate the delivery address field 01
function validateDeliveryAddress01() {
    if ($("#txt-deliadd01").val().length == 0) {
        $("#address_error").show();
        $("#txt-deliadd01").css("background-color", background_color);
        $("#address_error").text("Please enter the mandatory fields of the address.");
        return false;
    } else {
        $("#txt-deliadd01").css("background-color", "white");
        $("#address_error").hide();
    }
}

//Validate the delivery address field 04
function validateDeliveryAddress02() {
    if ($("#txt-deliadd02").val().length == 0) {
        $("#address_error").show();
        $("#txt-deliadd02").css("background-color", background_color);
        $("#address_error").text("Please enter the mandatory fields highlighted.");
        return false;
    } else {
        $("#txt-deliadd02").css("background-color", "white");
        $("#address_error").hide();
    }
}

function hideErrorLabels() {
    $("#fname_error").hide();
    $("#lname_error").hide();
    $("#contactno_error").hide();
    $("#email_error").hide();
    $("#address_error").hide();
}