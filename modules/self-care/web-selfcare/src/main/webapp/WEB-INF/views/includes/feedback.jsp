<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="include.jsp" %>
    <link rel="stylesheet" href="<c:url value="/resources/css/m-buttons.css"/>"/>
    <script src="<c:url value="/resources/js/feedback-operations.js"/>"></script>
</head>
<body>
<div class="panel panel-default">
    <div class="panel-heading">
        <img class="icons" src="<c:url value="/resources/images/icons/purchase_history.png"/>">

        <h3 class="header-panel"><fmt:message key="feedback.header.text" bundle="${lang}"/></h3>
    </div>
    <div class="panel-body">
        <div id="feedback-content-div"></div>
    </div>
</div>
</body>
</html>
