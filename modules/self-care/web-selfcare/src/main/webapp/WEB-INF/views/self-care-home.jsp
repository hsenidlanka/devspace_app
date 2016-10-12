<!DOCTYPE html>
<html>
<head>

    <%@include file="include.jsp" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <div class="loader-anim"></div>
    <%--<link href="<c:url value="/resources/css/home-validation.css"/>" rel="stylesheet" >--%>

    <title><fmt:message key="home.title" bundle="${lang}"/></title>
    <script src="<c:url value="/resources/js/validate-bootstrap.jquery.js"/>"></script>

    <style>
        .carousel-inner > .item > img,
        .carousel-inner > .item > a > img {
            width: 58%;
            height: 52%;
            margin: auto;
        }

        #tst {
            color: red;
            font-weight: lighter;
            font-size: smaller;
            text-align: left;
            display: block;
        }
    </style>


    <script>

        $(document).ready(function () {

//            registration validation starts
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
                        /*,
                        '.username': function (input) {

//                            $("#password").off("blur");
                            var uname = input.val();
                            alert("username " + uname);
                            var urlSet = "http://localhost:2222/pizza-shefu/api/v1.0/customers/" + uname + "/";

                            $.ajax({

                                url: urlSet,

                                // Whether this is a POST or GET request
                                type: "GET",

                                // The type of data we expect back
                                dataType: "json",
                                success: function (reply) {

                                    $.each((reply.data).Apps, function(i, el) {
                                        alert(el.groups.length);
                                    });
//
                                },
                                error: function (errorReply) {
                                    console.log(errorReply);
                                }
                            })
                            return false;
                        }*/
                    }
                });

                /* $('form').validator({validHandlers: {

                 '.password':function(input) {
                 alert("password");
                 return (input.val()).match(document.getElementById("rePassword").value);
                 },

                 }
                 });*/

//                $("#submitBtn").off("submit");




/*

*/


                /*$('form').submit(function (e) {
//                    alert("form submit");
                    e.preventDefault();


//                    alert($('form').validator('check'));
                    if ($('form').validator('check') <= 2) {
//                        alert($('form').validator('check'));

                        this.submit();
                    }
                })*/
            });




            $("#submitBtn").off('click');

            $("#submitBtn").click(function () {
                if ($('form').validator('check') <= 3) {

                    $.ajax({

                        url: "register",
                        type: 'post',
                        data: $('#adduser_form').serialize() ,
                        dataType: 'json',
                        success: function (result) {
                            alert("success");
                        },
                        error: function (xhr, status, error) {
                            var err = eval("(" + xhr.responseText + ")");
                            alert(err.Message);
                        }
                    });


/*

                    $.ajax({
                        url: "register",
                        type: 'post',
                        data: $('#adduser_form').serialize(),
                        dataType: 'json',
                    }).then(function(data) {
                        alert("post success");
                    });

*/

/*

                    $.ajax({
                        url: 'register',
                        dataType: 'json',
                        type: 'post',
                        contentType: 'application/json',
                        data: JSON.stringify( {"title":"Mr.","first_name":"value","last_name":"value","username":"valueUser","password":"value","email":"value@mma.com","address_line1":"value1","address_line2":"value2","address_line3":"value3","mobile":"0123456789"} ),
                        processData: false,
                        success: function( data, textStatus, jQxhr ){
                            alert("Test Success");
                        },
                        error: function( jqXhr, textStatus, errorThrown ){
                            console.log( errorThrown );
                        }
                    });
*/


                }

            });
//            registration validation end


            /* $("#btn-submit").click(function (e) {
             //                alert("The paragraph was clicked.");
             e.preventDefault();
             $(function () {
             $('form').validator({
             validHandlers: {
             '.mobileno': function (input) {
             //may do some formatting before validating
             input.val(input.val().toUpperCase());
             //return true if valid
             return input.val() === 'JQUERY' ? true : false;
             }
             }
             });

             if ($('form').validator('check') < 1) {

             var datastring = $("#loginForm").serialize();
             //                        alert(datastring);
             $.ajax({
             type: "POST",
             url: "/ltest",
             data: datastring,
             dataType: "json",
             success: function(data) {
             //var obj = jQuery.parseJSON(data); if the dataType is not specified as json uncomment this
             // do what ever you want with the server response
             alert("passed")
             },
             error: function() {
             alert('error handing here');
             }
             });

             }


             })


             });*/
            /*      $('form').click(function(){
             //           alert($("#login-username").val());
             alert($('form').validator('check'));
             //            $('form').validator('input');

             });*/
            /* $(function () {
             $('form').validator({
             validHandlers: {
             '.mobileno': function (input) {
             //may do some formatting before validating
             input.val(input.val().toUpperCase());
             //return true if valid
             return input.val() === 'JQUERY' ? true : false;
             }
             }
             });

             /!* $('form').submit(function (e) {
             //                    var bla = $('#login-username').val();
             e.preventDefault();
             //                    alert($('form').validator('check'));
             //                     alert(bla);
             /!*alert("test")*!/
             if ($('form').validator('check') < 1) {
             //                        this.submit();
             $.ajax({
             type: "POST",
             url: "login",
             data: $('#loginForm').serialize(),
             success: function (result) {
             /!*if ($.trim(result) == 1) {
             $('#addUserPopup').modal('hide');
             $('#addUserSuccess').modal('show');
             $('#register').trigger('reset');
             } else {
             $('#addUserPopup').modal('hide');
             $('#addUserFail').modal('show');
             }*!/
             alert("reply came");
             },
             error: function (data) {
             console.log(data);
             //                                 alert(data);
             }

             });
             }
             })*!/
             })*/

        });
    </script>


<%--    <script>
        $(document).ready(function(){
            $("#username").blur(function(){
                var username = $("#username").val();
//                alert(username);
                var urlSet = "http://localhost:2222/pizza-shefu/api/v1.0/customers/"+username+"/";

                $.ajax({

                    url: urlSet,

                    // Whether this is a POST or GET request
                    type: "GET",

                    // The type of data we expect back
//                    dataType: "json",
                    success: function (data2) {
                        alert(data2)
                    },
                    error: function (data) {
                        console.log(data);
                    }
                })
            });;
        });
    </script>--%>

</head>
<!-------Header------------------------------------->
<body>

<%@include file="NewHeader.jsp" %>




<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators" hidden="hidden">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
        <li data-target="#myCarousel" data-slide-to="3"></li>

    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">

        <div class="item active">
            <img src="<c:url value="/resources/images/53debcce3a030pizza_transparent_png.png"/>" alt="" width="100"
                 height="220">
        </div>

        <div class="item">
            <img src="<c:url value="/resources/images/SeafoodDeluxe.png"/>"
                 alt="" width="100" height="100">
        </div>

        <div class="item">
            <img src="<c:url value="/resources/images/tagliatelle_roasted_squash.png"/>" alt=""
                 width="460" height="300">
        </div>
        <div class="item">
            <img src="<c:url value="/resources/images/Kids_Ice_Cream_Sundae.png"/>"
                 alt="" width="460" height="300">
        </div>


    </div>

</div>

<!----------------------------order online,surf menu,hot deals--------------->
<div class="row">

    <div class="col-sm-2">
         <span>
             <a href="#"> <img src="<c:url value="/resources/images/hotdeals.png"/>" style="width: 150px;height: 140px"></a>
         </span>
    </div>

    <div class="col-sm-2">
        <span>
            <a href="payment"> <img src="<c:url value="/resources/images/ordernow3.png"/>" style="width: 185px;height: 148px"></a>
        </span>
    </div>

    <div class="col-sm-2">
        <span>
            <a href="menu"> <img src="<c:url value="/resources/images/ordernow2.png"/>" style="width: 185px;height: 148px"></a>
        </span>
    </div>
    <div class="col-sm-2"></div>


    <div class="col-sm-4" style="padding-top: 80px;padding-left: 260px">
        <span>
            <a href="http://www.facebook.com/"> <img src="<c:url value="/resources/images/facebook-flat.png"/>" style="padding-right: 10px"></a>
        </span>

        <span>
            <a href="http://www.twitter.com"> <img src="<c:url value="/resources/images/twitter-flat.png"/>" style="padding-right: 10px"></a>
        </span>

        <span>
            <a href="#"> <img src="<c:url value="/resources/images/google-plus-flat.png"/>" style="padding-right: 10px"></a>
        </span>
    </div>
</div>

<!-------Footer------------------------------------->

<div id="footer" style="position: fixed;height: 50px">
    <div class="form-group row static">

        <label class="col-sm-4 control-label">
            <div style="height: 22px"></div>

            <p class="text-muted" style="color: #545454"><fmt:message key="footer.text" bundle="${lang}"/> <a href="https://www.hsenidmobile.com/"
                                                                        style="color:#313131 "><fmt:message key="company" bundle="${lang}"/> </a></p>
        </label>

        <div class="col-sm-4">

            <label class="  ">
                <div style="height: 22px"></div>

                <h5 style="color: #313131"><fmt:message key="footer.hotline" bundle="${lang}"/>  <fmt:message key="footer.mobile" bundle="${lang}"/></h5>


            </label>
        </div>
        <div class="col-sm-4">

            <label class="radio-inline">
                <img style="width: 130px;height: 55px" src="http://www.moppadop.com/img/google-play-store-button.png">
            </label>
        </div>

    </div>
    <ul class="nav pull-right scroll-top">
        <li><a href="#" title="Scroll to top"><i class="glyphicon glyphicon-chevron-up"></i></a></li>
    </ul>
</div>

<!------------------------------signup modal --------------------------------------------->
<%@include file="signupmodal.jsp" %>

</body>
</html>


