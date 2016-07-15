<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <%@include file="includeFiles/include.jsp" %>

    <fmt:setLocale value="en"/>
    <fmt:setBundle basename="messages" var="lang"/>

    <title>Sign In</title>

    <style>
        .div2 {
            width: 50%;
            height: 60%;
            padding: 50px;
            border: 1px solid red;
            margin-top: 12%;
        }

        .login-footer {
            margin-top: 3%;
        }
    </style>

</head>

<body background="login.jpg">
<div align="center" class="container div2">

    <form:form action="loginform" commandName="loginForm" method="post">

        <div class="raw">
            <h2><form:label path="userName">
                <fmt:message key="login.username" bundle="${lang}"/> :</form:label></h2>
        </div>
        <div class="raw">
            <form:input path="userName" size="40"/>
        </div>

        <div class="raw">
            <h2><form:label path="password">
                <fmt:message key="login.password" bundle="${lang}"/> :</form:label></h2>
        </div>

        <div class="row">

            <form:password path="password" size="40"/>

        </div>
        <br><br>

        <div class="row" style="width: 64%">
            <input type="submit" class="btn btn-block btn-primary btn-default btn-lg" value=
                <fmt:message key="login.submit" bundle="${lang}"/>/>
        </div>


    </form:form>
</div>

<div align="center" class="login-footer">
    <footer>Copyright © 1997 - 2016 hSenid Mobile Solutions. All Rights Reserved.</footer>
</div>
</body>
</html>