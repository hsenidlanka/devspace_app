<!DOCTYPE html>
<head>

    <!--include common CSS, fonts and js-->
    <%@include file="../adminTop.jsp" %>
    <%--<jsp:include page="../adminTop.jsp"/>--%>

    <!--my css files-->
    <spring:url value="/themes/hsenid/css/userMgt.css" var="css1"/>
    <spring:url value="/themes/hsenid/css/form-elements.css" var="css2"/>
    <spring:url value="/themes/hsenid/css/jquery_ui.css" var="css3"/>

    <link href="${css1}" rel="stylesheet">
    <link href="${css2}" rel="stylesheet">
    <link href="${css3}" rel="stylesheet">

    <spring:url value="/themes/hsenid/js/userMgt.js" var="js3"/>
    <spring:url value="/themes/hsenid/js/jquery_ui.min.js" var="js4"/>
    <script src="${js3}"></script>
    <script src="${js4}"></script>

    <!--datepicker-->
   <%-- <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>--%>

</head>
<body>
<%--<fmt:setLocale value="En"/>
<fmt:setBundle basename="messages_en" var="bundle1" />--%>
<fmt:setBundle basename="system" scope="session" var="bundle2"/>

<jsp:include page="../header.jsp"/>

<div class="brand">
    <div style="position: relative; left: -50%;">
        <fmt:message key="user.heading"/>
    </div>
</div>
<br>

<div class="breadcrumbPosition" id="userviewBreadcrump">
        <ul class="breadcrumb breadcrumb-menu">

            <fmt:message key="admin.home.url" var="url1" bundle="${bundle2}"/>
            <fmt:message key="admin.usermanage.userview.url" var="url2" bundle="${bundle2}"/>

            <li><a href="<c:out value="${url1}"/>"><fmt:message key="user.breadcrumb.home"/></a></li>
            <li><a href="<c:out value="${url1}"/>"><fmt:message key="user.breadcrumb.usermanagement"/></a></li>
            <li class="active"><a href="<c:out value="${url2}"/>">
                <fmt:message key="user.userview.breadcrumb.viewuser" /></a>
            </li>
        </ul>
</div>
<center>
    <div class="form-box" id="viewuser_box">
        <div class="panel panel-default">

            <div class="panel-heading common-form-headings">
                <h3 class="default-panel-headings"><fmt:message key="user.userview.panel.heading" /></h3>
            </div>

            <div class="panel-body">
                <ul class="nav nav-pills red">
                    <li class="active"><a data-toggle="pill" href="#home">
                        <fmt:message key="user.userview.navpill.customer" /></a></li>
                    <li><a data-toggle="pill" href="#menu1">
                        <fmt:message key="user.userview.navpill.staff" /></a></li>
                </ul>
                <br>
                <div class="tab-content">
                    <!--div to get customer info-->
                    <div id="home" class="tab-pane fade in active" style="margin: -20px -5px 0 -10px;" >
                        <div>
                            <jsp:include page="userCustomer_view.jsp"/>
                        </div>
                    </div>
                    <!--div to present staff info-->
                    <div id="menu1" class="tab-pane fade" style="margin: -20px -5px 0 -10px;">
                        <div >
                            <jsp:include page="userStaff_view.jsp"/>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</center>
</body>
</html>