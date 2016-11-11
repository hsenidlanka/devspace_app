$(document).ready(function(){

    //Data validation using plugin http://www.jqueryscript.net/form/Simplest-jQuery-Form-Validator-For-Bootstrap-Validate-Bootstrap.html
    //plugin has change accordingly

    $(function () {
        $('form').validator({
            validHandlers: {

                '.mobileno': function (input) {
                    return /^[0-9]{10}$/.test(input.val());

                },
                '.password': function (input) {
                    var bla = $('#rePassword').val();
                    return input.val() == bla;

                }
            }
        });

    });

    //Registration form submission

    $("#submitBtn").off('click');
    $("#submitBtn").click(function () {
        if ($('form').validator('check') <= 2) {
            $.ajax({

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
            });

        }else{
            return false;
        }


    });



    //Login form submition

    //$("#loginFormSubmit").off('click');
    $("#loginFormSubmit").click(function () {
        var loginUsername =  $.trim($("#loginUsername").val());
        var loginPassword =  $.trim($("#loginPassword").val());

        alert(loginUsername.length + ' ' + loginPassword.length)

        if ($('form').validator('check') <= 2) {

            $.ajax({
                type: 'post',
                url: "login",
                data: {username:loginUsername, password:loginPassword},
                success: function (result) {
                    if(result.userAvailable){
                        window.location = "/web-selfcare/";
                    }else{
                        alert("Invalied Username of Password");
                    }

                    console.log("success");

                },
                error: function () {
                    $("#ajaccall").append("<b>Appended text</b>");
                }
            });

        }else{
            return false;
        }


    });


    //Check a username is blocked or not

    $("#loginUsername").blur(function(){
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

                if(!data2.userAvailable){
                    $("#loginUsername").val('');
                    alert("Username is blocked");
                }else{
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

    $("#username").blur(function(){
        var chkName = $("#username").val();
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

                if(!data2.userAvailable){
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