<html xmlns:jsp="http://java.sun.com/JSP/Page">


<!--including JSTL to the page -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <style scoped="">
        #logo {
            position: absolute;
            height: 150px;
            top: -5px;
            left: 15px;
        }

        #logout {
            position: absolute;
            top: 45px;
            right: 40px;
            color: #1c6218;
        }

        a:hover {
            color: black;
        }
    </style>
</head>
<body>

<div class="row">

    <div>
        <a href="https://localhost:8443/admin/users/list"><img id="logo" src="<c:url value="/themes/hsenid/images/logo.png"/>" alt="logo"/></a>

    </div>

    <div>
        <a id="logout" href="#">Log Out</a>
    </div>

</div>
</body>
</html>