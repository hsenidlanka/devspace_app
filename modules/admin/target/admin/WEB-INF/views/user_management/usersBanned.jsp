<html xmlns:jsp="http://java.sun.com/JSP/Page">

<!--including JSTL to the page -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>

    <!--include common CSS, fonts and js-->
    <jsp:include page="../adminTop.jsp"/>
    <spring:url value="/themes/hsenid/css/form-elements.css" var="css2"/>
    <link href="${css2}" rel="stylesheet">


    <!--my css files-->
    <spring:url value="/themes/hsenid/css/userMgt.css" var="css1"/>
    <spring:url value="/themes/hsenid/js/userMgt.js" var="js1"/>


    <link href="${css1}" rel="stylesheet">
    <script src="${js1}"></script>


    <!--datepicker-->
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>



</head>
<body>
<fmt:setLocale value="En"/>
<fmt:setBundle basename="messages_en" var="bundle1" />
<fmt:setBundle basename="system" scope="session" var="bundle2"/>

<jsp:include page="../header.jsp"/>

<div class="brand">
    <div style="position: relative; left: -50%;">
        <fmt:message key="user.heading" bundle="${bundle1}"/>
    </div>
</div>
<br>

<div class="breadcrumbPosition" id="userviewBreadcrump" style="margin-left: 421px">
        <ul class="breadcrumb breadcrumb-menu">
            <fmt:message key="admin.home.url" var="url1" bundle="${bundle2}"/>
            <fmt:message key="admin.usermanage.useradd.url" var="url2" bundle="${bundle2}"/>
            <li><a href="<c:out value="${url1}"/>"><fmt:message key="user.breadcrumb.home" bundle="${bundle1}"/></a></li>
            <li><a href="<c:out value="${url1}"/>"><fmt:message key="user.breadcrumb.usermanagement" bundle="${bundle1}"/></a></li>
            <li class="active"><a href="<c:out value="${url2}"/>">
                <fmt:message key="user.blockuserview.breadcrumb.viewuser" bundle="${bundle1}"/></a>
            </li>
        </ul>
</div>
<center>
    <div class="form-box" id="viewuser_box">
        <div class="panel panel-default">

            <div class="panel-heading common-form-headings">
                <h3 class="default-panel-headings"><fmt:message key="user.blockuserview.panel.heading" bundle="${bundle1}"/></h3>
            </div>

            <div class="panel-body">
                <ul class="nav nav-pills red">
                    <li class="active"><a data-toggle="pill" href="#home">
                        <fmt:message key="user.blockuserview.navpill.customer" bundle="${bundle1}"/></a></li>
                    <li><a data-toggle="pill" href="#menu1">
                        <fmt:message key="user.blockuserview.navpill.staff" bundle="${bundle1}"/></a></li>
                </ul>
                <br>
                <div class="tab-content">
                    <!--div to get customer info-->
                    <div id="home" class="tab-pane fade in active">
                        <div>
                            <jsp:include page="userCustomerBlock_view.jsp"/>

                        </div>
                    </div>
                    <!--div to present staff info-->
                    <div id="menu1" class="tab-pane fade">
                        <div>
                            <jsp:include page="userStaffBlock_view.jsp"/>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</center>




</body>
</html>