<html xmlns:jsp="http://java.sun.com/JSP/Page">

<!--including JSTL to the page -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="org.jasig.cas.client.authentication.AttributePrincipal"%>
<head>
    <style scoped="">
        #logo {
            position: absolute;
            height: 150px;
            margin: -30px 10px -30px 10px;
        }

        #logout {
            position: absolute;
            color: #1c6218;
            margin: 10px 10px 0 200px;
            /*margin: 5px 350px 0 345px;*/
            width: 100px;
            font-style: italic;
        }

        a:hover {
            color: black;
        }
        #user {
            margin: 0 10px 0 600px;
            font-size: 18px;
        }
    </style>
    <spring:url value="/themes/hsenid/js/logout.js" var="jsLogout"/>
    <script src="${jsLogout}"></script>

</head>
<body>


<%

    AttributePrincipal principal = (AttributePrincipal)request.getUserPrincipal();

    session.setAttribute("casPrincipal", principal);
    session.setAttribute("username", request.getRemoteUser());
    session.setAttribute("ur", request.getParameter("locale"));

    Map attributes = principal.getAttributes();

    Iterator attributeNames = attributes.keySet().iterator();
    for (; attributeNames.hasNext();) {
        String attributeName = (String) attributeNames.next();
        Object attributeValue = attributes.get(attributeName);
        session.setAttribute(attributeName, attributeValue);
    }
    String redirectURL = request.getContextPath() + "/webapp.html";
    response.sendRedirect(redirectURL);
%>

<div class="row">
    <div class="col-xs-4"></div>
    <div class="col-xs-8">
        <b><a id="user" href="#" ><fmt:message key="header.welcome" /> <%= session.getAttribute("username")%></a></b>
    </div>

</div>
<div class="row">
    <div class="col-xs-8">
        <a href="https://localhost:8443/admin/PizzaShefu/"><img id="logo" src="<c:url value="/themes/hsenid/images/logo.png"/>" alt="logo"/></a>
    </div>

    <div class="col-xs-4">
        <%--<a id="logout" href="<c:url value="https://localhost:8443/admin/PizzaShefu/logout"/>">Log Out</a>--%>

            <a id="logout" href="#"><fmt:message key="header.logout" /></a>

    </div>


</div>
</body>
</html>