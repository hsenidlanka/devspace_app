<!DOCTYPE html>
<head>
    <!--include common CSS, fonts and js-->
    <%@include file="../adminTop.jsp" %>

    <!--my css files-->
    <spring:url value="/themes/hsenid/css/userMgt.css" var="css1"/>
    <spring:url value="/themes/hsenid/css/form-elements.css" var="css2"/>
    <spring:url value="/themes/hsenid/css/jquery_ui.css" var="css3"/>
    <link href="${css1}" rel="stylesheet">
    <link href="${css2}" rel="stylesheet">
    <link href="${css3}" rel="stylesheet">

    <spring:url value="/themes/hsenid/js/userMgt.js" var="js1"/>
    <spring:url value="/themes/hsenid/js/jquery_ui.min.js" var="js4"/>
    <script src="${js1}"></script>
    <script src="${js4}"></script>

</head>
<body>
<%--<fmt:setLocale value="En"/>
<fmt:setBundle basename="messages_en" var="bundle1" />--%>
<fmt:setBundle basename="system" scope="session" var="bundle2"/>

<jsp:include page="../header.jsp"/>

<div class="brand">
    <div style="position: relative; left: -50%;">
        <fmt:message key="user.heading" />
    </div>
</div>
<br>

<div class="breadcrumbPosition" id="userviewBreadcrump" style="margin-left: 421px">
        <ul class="breadcrumb breadcrumb-menu">
            <fmt:message key="admin.home.url" var="url1" bundle="${bundle2}"/>
            <fmt:message key="admin.usermanage.useradd.url" var="url2" bundle="${bundle2}"/>
            <li><a href="<c:out value="${url1}"/>"><fmt:message key="user.breadcrumb.home" /></a></li>
            <li><a href="<c:out value="${url1}"/>"><fmt:message key="user.breadcrumb.usermanagement" /></a></li>
            <li class="active"><a href="<c:out value="${url2}"/>">
                <fmt:message key="user.blockuserview.breadcrumb.viewuser" /></a>
            </li>
        </ul>
</div>
<center>
    <div class="form-box" id="viewuser_box">
        <div class="panel panel-default">

            <div class="panel-heading common-form-headings">
                <h3 class="default-panel-headings"><fmt:message key="user.blockuserview.panel.heading" /></h3>
            </div>

            <div class="panel-body">
                <ul class="nav nav-pills red">
                    <li class="active"><a data-toggle="pill" href="#home">
                        <fmt:message key="user.blockuserview.navpill.customer" /></a></li>
                    <li><a data-toggle="pill" href="#menu1">
                        <fmt:message key="user.blockuserview.navpill.staff" /></a></li>
                </ul>
                <br>
                <div class="tab-content">
                    <!--div to get customer info-->
                    <div id="home" class="tab-pane fade in active" style="margin: -20px -5px -20px -5px;">
                        <div>
                            <jsp:include page="userCustomerBlock_view.jsp"/>

                        </div>
                    </div>
                    <!--div to present staff info-->
                    <div id="menu1" class="tab-pane fade" style="margin: -20px -5px -20px -5px;">
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