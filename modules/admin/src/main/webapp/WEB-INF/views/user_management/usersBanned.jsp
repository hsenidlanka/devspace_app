<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
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

<jsp:include page="../header.jsp"/>

<div class="brand">
    <div style="position: relative; left: -50%;">
        User Management
    </div>
</div>
<br>

<div class="breadcrumbPosition" id="userBreadcrump">
        <ul class="breadcrumb breadcrumb-menu">
            <li><a href="https://localhost:8443/admin/users/list">Home</a></li>
            <li><a href="https://localhost:8443/admin/users/list">User Management</a></li>
            <li class="active"><a href="https://localhost:8443/admin/users/viewBlocked">View Blocked Users</a></li>
        </ul>
</div>
<center>
    <div class="form-box" id="viewuser_box">
        <div class="panel panel-default">

            <div class="panel-heading common-form-headings">
                <h3 class="default-panel-headings">View Blocked Users</h3>
            </div>

            <div class="panel-body">
                <ul class="nav nav-pills red">
                    <li class="active"><a data-toggle="pill" href="#home">Blocked Customers</a></li>
                    <li><a data-toggle="pill" href="#menu1">Blocked Staff</a></li>
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