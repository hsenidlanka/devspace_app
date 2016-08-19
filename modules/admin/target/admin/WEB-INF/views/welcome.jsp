<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<fmt:setLocale value="En"/>
<fmt:bundle basename="messages_en">


    <h3><a href=" http://localhost:8080/users/list">User Management</a></h3>

<form:form id="welcomeForm" commandName="welcomeForm" modelAttribute="welcomeForm">
    <%--<div class="clearfix">--%>
    <div class="table-cell">

        <div class="welcome_logo">
            <img src="<c:url value="themes/hsenid/images/banner-logo.png"/>" alt="NOO"  /><br/><br/>
        </div>

    </div>
    <div class="table-cell-2">
        <div class="welcome_image">
            <img src="<c:url value="themes/hsenid/images/banner-pic.png" />" alt=""/>
        </div>
    </div>
    <%--</div>--%>
    <%--<div class="clearfix">--%>
    <div class="table-cell">
        <div>
            <label class="welcome_Page_text">
                <fmt:message key="cm.welcome.text"/>
            </label>
        </div>
    </div>
    <%--</div>--%>
    <%--<div class="clearfix">--%>
    <div class="table-cell">
        <div class="welcome_footer">
            <img src="<c:url value="themes/hsenid/images/banner-footer.png" />" alt=""/>
        </div>
    </div>
    <%--</div>--%>
</form:form>

</fmt:bundle>

<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WelcomePage Test</title>
</head>
<body>
<img src="resources/33.jpg" alt="NOO" width="600" height="503" /><br/><br/>

<h1>Hiiiiiiiiiiiiiiii</h1>
</body>
</html>--%>

