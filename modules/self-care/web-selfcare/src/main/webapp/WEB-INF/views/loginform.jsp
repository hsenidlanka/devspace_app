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
            border: 10px solid cornflowerblue;
            border-style:outset;
            margin-top: 12%;
        }

        .login-footer {
            margin-top: 3%;
        }

        body {
            background: url('https://goo.gl/IEb9V9') no-repeat center center fixed;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
        }

        a { color: inherit; }
        /*html {
            background: url('https://goo.gl/vZT8nX') no-repeat center center fixed;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
        }*/

        /* enable absolute positioning */
        .inner-addon {
            position: relative;
        }

        /* style icon */
        .inner-addon .glyphicon {
            position: absolute;
            padding: 10px;
            pointer-events: none;
        }

        /* align icon */
        .left-addon .glyphicon  { left:  0px;}
        .right-addon .glyphicon { right: 0px;}

        /* add padding  */
        .left-addon input  { padding-left:  30px; }
        .right-addon input { padding-right: 30px; }


        .btn.btn-success {
            color: #0a010a;
            background-color: #8664e3;
            background-image: linear-gradient(to bottom, #69679e, #ebcee2);
            border-color: #3E8F3E #d2cce8 #f2faf2;
        }
        .btn.btn-success:hover {
            color: #0a010a;
            background-color: #ebcee2;
            background-image: linear-gradient(to bottom, #ebcee2, #ebcee2);
            border-color: #3E8F3E #d2cce8 #f2faf2;
        }

    </style>

</head>

<body background="login.jpg">
<div align="center" class="container div2">

    <form:form action="loginform" commandName="loginForm" method="post">

        <div class="raw">
            <h3><form:label path="userName">
                <fmt:message key="login.username" bundle="${lang}"/> <i class="glyphicon glyphicon-user"></i></form:label></h3>
        </div>
        <div class="raw">
            <form:input path="userName" size="40"/>
        </div>

        <div class="raw">
            <h3><form:label path="password">
                <fmt:message key="login.password" bundle="${lang}"/> <i class="glyphicon glyphicon-lock"></i></form:label></h3>
        </div>

        <div class="row">

            <form:password path="password" size="40"/>

        </div>
        <br><br>

        <div class="row" style="width: 64%">
            <input type="submit" class="btn btn-block btn btn-success btn-lg" value=<fmt:message key="login.submit" bundle="${lang}"/> />
        </div>


    </form:form>
</div>


<div align="center" class="login-footer">
    <footer style="color: whitesmoke"><a href="http://www.hsenid.com/">Copyright © 1997 - 2016 hSenid Mobile Solutions. All Rights Reserved.</a></footer>
</div>
</body>
</html>