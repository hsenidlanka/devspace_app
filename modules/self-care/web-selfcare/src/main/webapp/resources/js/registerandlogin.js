$(document).ready(function () {
    $("#blockedUser").hide();

    //The fade out of blocked user yet to implement. Waiting for verifivation to complete

    $("#signUpBtn").click(function(){
        $("#blockedUser").show().delay(5000).fadeOut();
    });


    //Registration form submission
    $("#signUpBtn").click(function(){
        $('#formSubmitError').empty();
        $('#titleError').empty();
        $('#fnameError').empty();
        $('#lnameError').empty();
        $('#address1Error').empty();
        $('#address2Error').empty();
        $('#mobileNoError').empty();
        $('#emailError').empty();
        $('#unameError').empty();
        $('#passwordError').empty();
        $('#rePasswordError').empty();

    });

    //Number Validation

    function isNumber(number) {
        var regex = /^0[0-9]{9}$/;
        return regex.test(number);
    }

    $("#mobileNo").blur(function(){
        var registerNumber = $.trim($("#mobileNo").val());
        $('#mobileNoError').empty();
        if(!isNumber(registerNumber)){
            $("#mobileNo").val('');
            $('<p align="left" style="color: red; margin-left: -12px; margin-top: -12px; margin-bottom: 12px;">Please provide a valid number</p>').appendTo('#mobileNoError');
        }else{
            $('#mobileNoError').empty();
        }

    });

    //Email Validation

    function isEmail(email) {
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
    }

    $("#email").blur(function(){
        var registerEmail = $.trim($("#email").val());
        $('#emailError').empty();
        if(!isEmail(registerEmail)){
            $("#email").val('');
            $('<p align="left" style="color: red; margin-left: -12px; margin-top: -12px; margin-bottom: 12px;">Please provide a email</p>').appendTo('#emailError');
        }else{
            $('#emailError').empty();
        }

    });

    //$("#submitBtn").off('click');
    $("#submitBtn").click(function () {

        var registerFirst = $.trim($("#fname").val());
        var registerLast = $.trim($("#lname").val());
        var registerAddr1 = $.trim($("#address1").val());
        var registerAddr2 = $.trim($("#address2").val());
        var registerPhone = $.trim($("#mobileNo").val());
        var registerEmail = $.trim($("#email").val());
        var registerUname = $.trim($("#uname").val());
        var registerPass1 = $.trim($("#password").val());
        var registerPass2 = $.trim($("#rePassword").val());

        $('#formSubmitError').empty();
        $('#titleError').empty();
        $('#fnameError').empty();
        $('#lnameError').empty();
        $('#address1Error').empty();
        $('#address2Error').empty();
        $('#mobileNoError').empty();
        $('#emailError').empty();
        $('#unameError').empty();
        $('#passwordError').empty();
        $('#rePasswordError').empty();
        //alert(registerUname);


        if (($('input[name=title]:checked').length > 0) && (registerFirst.length > 0) && (registerLast.length > 0) && (registerAddr1.length > 0) && (registerAddr2.length > 0) && (registerPhone.length > 0) && (registerEmail.length > 0) && (registerUname.length > 0) && (registerPass1.length > 0) && (registerPass2.length > 0)) {
            //alert("Test Heat")
            if (registerPass1 === registerPass2) {
                $('#formSubmitError').empty();
                $.ajax({
                    type: 'post',
                    url: "register",
                    data: $('#adduser_form').serialize(),
                    success: function (result) {
                        if(result.userAvailable){
                            console.log("success");
                            $('#registerSuccesful').modal('show');
                        }else{
                            $('<p align="left" style="color: red; margin-left: -12px; margin-top: -12px; margin-bottom: 12px;">Form Submition failed</p>').appendTo('#formSubmitError');
                            return false;
                        }

                    },
                    error: function () {
                        $("#ajaccall").append("<b>Appended text</b>");
                    }
                });
            }else{
                $('<p align="left" style="color: red; margin-left: -12px; margin-top: -12px; margin-bottom: 12px;">Passwords do not match</p>').appendTo('#rePasswordError');
                return false;
            }

        }else{
            if($('input[name=title]:checked').length <= 0){
                $('<p align="left" style="color: red; margin-left: -12px; margin-top: -12px; margin-bottom: 12px;">Please select a title</p>').appendTo('#titleError');
            }

            if(registerFirst.length <= 0){
                $('<p align="left" style="color: red; margin-left: -12px; margin-top: -12px; margin-bottom: 12px;">Please provide a first name</p>').appendTo('#fnameError');
            }

            if(registerLast.length <= 0){
                $('<p align="left" style="color: red; margin-left: -12px; margin-top: -12px; margin-bottom: 12px;">Please provide a last name</p>').appendTo('#lnameError');
            }

            if(registerAddr1.length <= 0){
                $('<p align="left" style="color: red; margin-left: -12px; margin-top: -12px; margin-bottom: 12px;">Please provide a address line 1</p>').appendTo('#address1Error');
            }

            if(registerAddr2.length <= 0){
                $('<p align="left" style="color: red; margin-left: -12px; margin-top: -12px; margin-bottom: 12px;">Please provide a address line 2</p>').appendTo('#address2Error');
            }

            //(registerPhone.length > 0) && (registerEmail.length > 0) && (registerUname.length > 0) && (registerPass1.length > 0) && (registerPass2.length > 0)) {
            if(registerPhone.length <= 0){
                $('<p align="left" style="color: red; margin-left: -12px; margin-top: -12px; margin-bottom: 12px;">Please provide a contact number</p>').appendTo('#mobileNoError');
            }

            if(registerEmail.length <= 0){
                $('<p align="left" style="color: red; margin-left: -12px; margin-top: -12px; margin-bottom: 12px;">Please provide a email</p>').appendTo('#emailError');
            }

            if(registerUname.length <= 0){
                $('<p align="left" style="color: red; margin-left: -12px; margin-top: -12px; margin-bottom: 12px;">Please provide a valid username</p>').appendTo('#unameError');
            }

            if(registerPass1.length <= 0){
                $('<p align="left" style="color: red; margin-left: -12px; margin-top: -12px; margin-bottom: 12px;">Please provide a password</p>').appendTo('#passwordError');
            }

            if(registerPass2.length <= 0){
                $('<p align="left" style="color: red; margin-left: -12px; margin-top: -12px; margin-bottom: 12px;">Please provide a confirm password</p>').appendTo('#rePasswordError');
            }
            return false;
        }


        /*$.ajax({

            type: 'post',
            url: "register",
            data: $('#adduser_form').serialize(),
            success: function (result) {
                console.log("success");
                $('#registerSuccesful').modal('show');
            },
            error: function () {
                $("#ajaccall").append("<b>Appended text</b>");
            }
        });*/


    });


    //Login form submition

    //$("#loginFormSubmit").off('click');

    $("#loginBtn").click(function () {
        $('#usernameError').empty();
        $('#passwordError2').empty();
    });

    $("#loginFormSubmit").click(function () {
        var loginUsername = $.trim($("#loginUsername").val());
        var loginPassword = $.trim($("#loginPassword").val());

        $('#usernameError').empty();
        $('#passwordError2').empty();


        if (loginUsername.length > 0 && loginPassword.length > 0) {
            $.ajax({
                type: 'post',
                url: "login",
                data: {username: loginUsername, password: loginPassword},
                success: function (result) {
                    if (result.userAvailable) {
                        window.location = "/web-selfcare/";
                    } else {
                        alert("Invalied Username of Password");
                    }
                    console.log("success");
                },
                error: function () {
                    $("#ajaccall").append("<b>Appended text</b>");
                }
            });

        } else {
            if (loginUsername.length <= 0) {
                $('<p align="left" style="color: red; margin-left: -12px; margin-top: -12px; margin-bottom: 12px;">Please provide a valid username</p>').appendTo('#usernameError');
            }
            if (loginPassword.length <= 0) {
                $('<p align="left" style="color: red; margin-left: -12px; margin-top: -12px; margin-bottom: 12px;">Please provide a valid password</p>').appendTo('#passwordError2');
            }
            return false;
        }

    });


    //Check a username is blocked or not

    $("#loginUsername").blur(function () {
        var chkName = $("#loginUsername").val();
        $.ajax({

            type: "POST",
            // The URL for the request
            url: "chechBlocked",
            // The data to send (will be converted to a query string)
            data: {
                checkName: chkName
            },

            // The type of data we expect back
            dataType: "json",
            success: function (data2) {

                if (!data2.userAvailable) {
                    $("#loginUsername").val('');
                    alert("Username is blocked");
                } else {
                    return fasle;
                }
            },
            error: function (data) {
                alert("error worked");
                console.log("error work");
            }
        });

    });

    //Check unique username

    $("#username").blur(function () {
        var chkName = $("#uname").val();
        $.ajax({


            type: "GET",
            // The URL for the request
            url: "UniqueUser",
            // The data to send (will be converted to a query string)
            data: {
                checkName: chkName
            },

            // The type of data we expect back
            dataType: "json",
            success: function (data2) {

                if (!data2.userAvailable) {
                    $("#username").val('');
                    alert("Username is taken. Add another.");
                }
            },
            error: function (data) {
                console.log("error work");
            }
        });

    });
});
