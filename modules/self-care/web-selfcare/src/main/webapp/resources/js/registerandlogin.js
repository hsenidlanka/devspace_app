$(document).ready(function () {

    var loginUsername = $.trim($("#loginUsername").val());
    var loginPassword = $.trim($("#loginPassword").val());

    /*
     if(loginUsername.length <= 0){
     $('<p>Username</p>').appendTo('#usernameError');
     }

     if(loginPassword.length <= 0){
     $('<p>Password</p>').appendTo('#passwordError');
     }
     */

    /*  var loginUsername2 =  $.trim($("#loginUsername").val());
     var loginPassword2 =  $.trim($("#loginPassword").val());*/

    /*
     if(loginUsername2.length > 0){
     $('#usernameError').empty();
     }
     if(loginPassword2.length > 0){
     $('#passwordError').empty();
     }
     */
    //alert("test");
    /* $("#loginUsername").blur(function(){
     var loginUsername =  $.trim($("#loginUsername").val());
     var loginPassword =  $.trim($("#loginPassword").val());

     if(loginUsername.length > 0){
     $('#usernameError').empty();
     }

     alert("Username blur");
     if(loginPassword.length > 0){
     $('#passwordError').empty();
     }

     if(loginUsername.length <= 0){
     $('<p>Provide your username</p>').appendTo('#usernameError');
     }

     if(loginPassword.length <= 0){
     $('<p>Give a valid password</p>').appendTo('#passwordError');
     }

     });*/
    /* $(function () {
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

     });*/

    //Registration form submission

    /* $("#submitBtn").off('click');
     $("#submitBtn").click(function () {

     var loginUsername =  $.trim($("#loginUsername").val());
     var loginPassword =  $.trim($("#loginPassword").val());

     if(loginUsername.length <= 0){
     $('<p>Username</p>').appendTo('#usernameError');
     }

     if(loginPassword.length <= 0){
     $('<p>Password</p>').appendTo('#passwordError');
     }
     if (loginUsername.length >= 0 && loginPassword.length >= 0) {
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
     */


    //Login form submition

    //$("#loginFormSubmit").off('click');

    $("#loginBtn").click(function () {
        $('#usernameError').empty();
        $('#passwordError').empty();
    });

    $("#loginFormSubmit").click(function () {
        var loginUsername = $.trim($("#loginUsername").val());
        var loginPassword = $.trim($("#loginPassword").val());
        $('#usernameError').empty();
        $('#passwordError').empty();
        //alert(loginUsername.length + ' ' + loginPassword.length)


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
            if(loginUsername.length <= 0){
                $('<p align="left" style="color: red; margin-left: -12px; margin-top: -12px; margin-bottom: 12px;">Please provide a valid username</p>').appendTo('#usernameError');
            }
            if(loginPassword.length <= 0){
                $('<p align="left" style="color: red;  margin-left: -12px; margin-top: -12px; margin-bottom: 12px;">Please provide a valid password</p>').appendTo('#passwordError');
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
