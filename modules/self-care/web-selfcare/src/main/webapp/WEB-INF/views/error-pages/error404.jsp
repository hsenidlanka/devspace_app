<!DOCTYPE HTML>
<html>
<head lang="en">
    <%@include file="../includes/include.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><fmt:message key="error404.page.title" bundle="${lang}"/></title>
    <link href="<c:url value="/resources/css/error.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<div class="not-found" style="width: 900px;">
    <div class="notfound-top">
        <h1><fmt:message key="error404.code" bundle="${lang}"/></h1>
        <h2><fmt:message key="error404.description" bundle="${lang}"/></h2>
    </div>
    <div class="content">
        <img src="<c:url value="/resources/images/green-warn.png"/>" width="120px" height="120px">
        <h3><fmt:message key="error404.detailed.msg" bundle="${lang}"/></h3>
        <ul>
            <li><a href="home"><fmt:message key="error.page.home.link" bundle="${lang}"/></a></li>
            <li><a href="menu"><fmt:message key="error.page.menu.link" bundle="${lang}"/></a></li>
            <li><a href="contact-us"><fmt:message key="error.page.contactus.link" bundle="${lang}"/></a></li>
        </ul>
    </div>
    <div class="clear"></div>
</div>
<div class="copyright">
    <p><fmt:message key="error.page.footer" bundle="${lang}"/></p>
</div>
</body>
</html>