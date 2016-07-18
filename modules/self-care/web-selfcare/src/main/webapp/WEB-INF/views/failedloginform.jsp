<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html>


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <fmt:setLocale value="en"/>
    <fmt:setBundle basename="messages" var="lang"/>

    <title>Login Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <script>
        $(window).load(function () {
            $('#myModal').modal('show');
        });
    </script>

    <style>
        body {
            background-image: url("HMS-Logo-2013.png");
            background-repeat: no-repeat;
        }
    </style>

</head>
<body>

<%--<c:out value="${'kjflk'}" />--%>
<div class="container container-fluid">
    <!-- Modal -->
    <div align="center">
        <div class="" id="myModal" role="dialog" data-backdrop="static" style="top: 25%;">
            <div class="">

                <!-- Modal content-->
                <div class="">
                    <div class="">
                        <div align="center"><h3 class="" style="color: red;"><i class="icon-white icon-cog"></i> <fmt:message key="login.title"
                                                                                                     bundle="${lang}"/></h3>
                        </div>

                    </div>
                    <div class="">

                        <form:form action="loginform" commandName="loginForm" method="post">

                        <div class="form-group">
                            <div class="col-md-3"><form:label path="userName"><fmt:message key="login.username"
                                                                                           bundle="${lang}"/> :
                            </form:label></div>
                            <div class="col-md-9"><i class="icon-white icon-cog"></i> <form:input path="userName" id="userName" size="40"/></div>
                        </div>
                        <br><br>

                        <div class="form-group">
                            <div class="col-md-3"><form:label path="password"><fmt:message key="login.password"
                                                                                           bundle="${lang}"/> :
                            </form:label></div>
                            <div class="col-md-9"><form:password path="password" id="password" size="40"/></div>
                        </div>

                    </div>
                    <br>

                    <div class="form-group">
                        <div align="right" class="col-md-6">
                            <div><input type="submit" value=
                                <fmt:message key="login.submit" bundle="${lang}"/> class="btn btn-success btn-block
                                        btn-lg btn-primary btn-default btn-large"/>
                            </div>
                        </div>
                        <div align="left" class="col-md-6">

                            <h3> Or <a><fmt:message key="login.registernow" bundle="${lang}"/></a></h3>
                        </div>
                    </div>
                    <br>

                    <div>

                        <div align="center" class="">
                            <h3><a><fmt:message key="login.forgotpassword" bundle="${lang}"/></a></h3>
                        </div>

                    </div>

                    </form:form>

                    <div class="">
                        <div align="center" style="height:5px;">Copyright © 1997 - 2016 hSenid Mobile Solutions. All
                            Rights Reserved.
                        </div>

                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</body>


</html>