<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../includes/include.jsp" %>
    <title>Verify Your account</title>
</head>
<body>
<%@include file="../includes/new-header.jsp" %>
<script>
    $(document).ready(function () {


        function GetURLParameter(sParam) {
            var sPageURL = window.location.search.substring(1);
            var sURLVariables = sPageURL.split('&');
            for (var i = 0; i < sURLVariables.length; i++) {
                var sParameterName = sURLVariables[i].split('=');
                if (sParameterName[0] == sParam) {
                    return sParameterName[1];
                }
            }
        }

        var user = GetURLParameter('username');
        if(typeof user != 'undefined'){
            $("#hiddenUsername").val(user);
        }

        if(typeof user === 'undefined'){
            var test = $("#hiddenUsername").val();
        }

        $("#resendVerificationCode").click(function(){
            $.ajax({


                type: "GET",
                // The URL for the request
                url: "resendVerification",
                // The data to send (will be converted to a query string)
                data: {
                    username: user
                },

                // The type of data we expect back
                dataType: "json",
                success: function (data2) {

                    if (data2.userAvailable) {
                        $('#verificationContent').empty();
//                        $('<p align="left" style="color: red; margin-left: -12px; margin-top: -12px; margin-bottom: 12px;">Form Submition failed</p>')
                        $('<div class="form-group"><div class="col-sm-2"></div> <div class="col-sm-10"> <h4 style="color: red;">Verification code resending successful. Please check emails.</h4> </div> </div>')
                                .appendTo('#verificationContent');
                    }else{
                        $('#verificationContent').empty();
                        $('<div class="form-group"><div class="col-sm-2"></div> <div class="col-sm-10"> <h3 style="color: red;">Verification code resending Failed.</h3> </div> </div>')
                                .appendTo('#verificationContent');
                    }
                },
                error: function (data) {
                    console.log("error work");
                }
            });

//            alert("The paragraph was clicked.");
        });
//            alert("Mama Thama -> "+test);
    })
</script>

<div class="container">
    <%--<%= request.getParameter("username") %>--%>
    <div style="height: 80%" class="panel panel-success">

        <div class="panel-heading">
            <img class="icons" src="<c:url value="/resources/images/icons/emailVerify.png"/>">

            <h3 style="display: inline-block;"><fmt:message key="email.verify.title" bundle="${lang}"/></h3>
        </div>
        <div style="height: 80%" class="panel-body">
            <c:if test="${empty verifyPass}">
                <div id="verificationContent">
                    <div class="form-group">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-10">
                            <h4>We just sent you verification code, Check your email account</h4>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-10">
                            <h6 style="color: red">If you can't find the email, please check your spam folder</h6>
                        </div>
                    </div>
                </div>

                <form:form action="/web-selfcare/email-verification" method="post"
                           modelAttribute="emailverification" cssStyle="margin-top: 120px">
                    <div class="form-group">
                        <div class="row">
                            <div class="col-sm-2"></div>
                            <div class="col-sm-2">
                                <label class="control-label">Username : </label>
                            </div>
                            <div class="col-sm-6">
                                <strong><div id="usernameSet"><%= request.getParameter("username") %></div>
                                </strong>
                            </div>
                            <div class="col-sm-2"></div>
                        </div>
                        <form:input id="hiddenUsername" path="username" type="hidden" readonly="true" />
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-sm-2"></div>
                            <div class="col-sm-2">
                                <label class="control-label" for="verificationCode">Verification Code</label>
                            </div>
                            <div class="col-sm-6">
                                <form:input cssClass="form-control input-sm" id="verificationCode"
                                            path="verificationCode"
                                            placeholder="Please Enter verification code here"/>
                            </div>
                            <div class="col-sm-2"></div>

                        </div>
                        <div class="row">
                            <div class="col-sm-4"></div>
                            <div class="col-sm-6">
                                <form:errors path="verificationCode" cssStyle="color: red"/>
                            </div>
                            <div class="col-sm-2"></div>
                        </div>
                    </div>
                    <br>
                    <br>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-sm-1"></div>
                            <div class="col-sm-10">
                                <center>
                                    <button type="submit" class="btn btn-warning">
                                        <i class="fa fa-envelope-o" aria-hidden="true"></i> Verify Email
                                    </button>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <button type="button" id="resendVerificationCode" class="btn btn-warning">
                                        <i class="fa fa-share-square" aria-hidden="true"></i> Resend verification code
                                    </button>
                                </center>
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                    </div>
                </form:form>

            </c:if>
            <%--Here Start the Email Verified Massege--%>
            <c:if test="${not empty verifyPass}">
                <div class="">
                    <div class="row">
                        <div class="col-sm-4"></div>
                        <div class="col-sm-4">
                            <center>
                                <img style="width:125px;height:105px;" class="icons"
                                     src="<c:url value="/resources/images/icons/verifySuccess.png"/>">
                            </center>
                        </div>
                        <div class="col-sm-4"></div>
                    </div>

                    <div class="row">
                        <div class="col-sm-4"></div>
                        <div class="col-sm-4">
                            <center>
                                <h2>
                                    <strong>
                                        Verification Successful
                                    </strong>
                                </h2>
                            </center>
                        </div>
                        <div class="col-sm-4"></div>
                    </div>
                    <div class="row">
                        <div class="col-sm-4"></div>
                        <div class="col-sm-4">
                            <center>
                                <h3>
                                    Do you Like to Menu?
                                </h3>
                            </center>
                        </div>
                        <div class="col-sm-4"></div>
                    </div>
                    <div style="margin-top: 50px" class="row">
                        <div class="col-sm-4"></div>
                        <div class="col-sm-4">
                            <center><a href="/web-selfcare/menu" class="btn btn-warning"><i class="fa fa-shopping-cart"
                                                                               aria-hidden="true"></i> Check Menu</a>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a class="btn btn-warning" href="/web-selfcare/profile"><i
                                        class="fa fa-user" aria-hidden="true"></i> Check Profile</a></center>
                        </div>
                        <div class="col-sm-4"></div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
